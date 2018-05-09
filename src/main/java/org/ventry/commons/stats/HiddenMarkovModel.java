package org.ventry.commons.stats;

import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.stats.HiddenMarkovModel
 * author: ventry
 * create: 18/4/26 23:05
 * description:
 */

public class HiddenMarkovModel {
    private int stateCount;
    private int step;
    private int[] observations;
    private double[] startProbability;
    private double[][] transitionProbability;
    private double[][] emissionProbability;

    private double[][] alpha;// α[][](前向概率矩阵：状态i转移1步到j的概率)
    private double[][] beta;// β[][](后向概率矩阵)
    private double[][] gamma;// γ[][](概率矩阵)
    private double[][][] xi;// ξ[][][](在时刻t处于状态i且在时刻t+1处于状态j的概率，本质上就是转移概率)
    private double[][] delta;// δ[][]
    private int[][] psi;// ψ[][]

    private void init() {
        initAlpha();
        initBeta();
        initGamma();
        initXi();
        initDelta();
        initPsi();
    }

    /**
     * 初始化前向概率矩阵。矩阵每一行表示在状态i的前向概率，每一列
     * 表示在时刻t各状态的前向概率
     */
    private void initAlpha() {
        alpha = new double[stateCount][step];
        for (int i = 0; i < stateCount; i++) {
            alpha[i][0] = startProbability[i] * emissionProbability[i][observations[0]];
        }
    }

    /**
     * 初始化后向概率矩阵。矩阵每一行表示在状态i的后向概率，每一列
     * 表示在时刻t各状态的后向概率
     */
    private void initBeta() {
        beta = new double[stateCount][step];
        for (int i = 0; i < stateCount; i++) {
            beta[i][step - 1] = 1;
        }
    }

    /**
     * 初始化概率矩阵。矩阵每一行表示在状态i的概率，每一列
     * 表示在时刻t各状态的概率
     */
    private void initGamma() {
        gamma = new double[stateCount][step];
    }

    /**
     * 初始化转移概率矩阵。矩阵第一维表示起始状态i，第二维表示结束状态j，第三维表示
     * 时刻t
     */
    private void initXi() {
        xi = new double[stateCount][stateCount][step - 1];
    }

    private void initDelta() {
        delta = new double[stateCount][step];
        for (int i = 0; i < stateCount; i++) {
            delta[i][0] = startProbability[i] * emissionProbability[i][observations[0]];
        }
    }

    private void initPsi() {
        psi = new int[stateCount][step];
    }

    private double forward() {
        // 递推计算前向概率矩阵
        for (int t = 1; t < step; t++) {// 时刻
            for (int i = 0; i < stateCount; i++) {// 目标状态
                for (int j = 0; j < stateCount; j++) {// 来源状态
                    alpha[i][t] += alpha[j][t - 1] * transitionProbability[j][i];
                }
                alpha[i][t] = alpha[i][t] * emissionProbability[i][observations[t]];
            }
        }

        // 终止
        double sum = 0D;
        for (double[] a : alpha) {
            sum += a[step - 1];
        }
        return sum;
    }

    private double backward() {
        // 递推计算后向概率矩阵
        for (int t = step - 2; t > -1; t--) {// 时刻
            for (int j = 0; j < stateCount; j++) {// 目标状态
                for (int i = 0; i < stateCount; i++) {// 来源状态
                    beta[i][t] += transitionProbability[i][j]
                            * emissionProbability[j][observations[t + 1]]
                            * beta[j][t + 1];
                }
            }
        }

        // 终止
        double sum = 0D;
        for (int i = 0; i < beta.length; i++) {
            sum += startProbability[i] * emissionProbability[i][observations[0]]
                    * beta[i][0];
        }
        return sum;
    }

    private void calcProbability() {
        for (int t = 0; t < step; t++) {
            double probabilityAtTime = 0D;
            for (int i = 0; i < stateCount; i++) {
                gamma[i][t] = alpha[i][t] * beta[i][t];
                probabilityAtTime += gamma[i][t];
            }
            for (int i = 0; i < stateCount; i++) {
                gamma[i][t] = gamma[i][t] / probabilityAtTime;
            }
        }
    }

    private void calcJointProbability() {
        for (int t = 0; t < step - 1; t++) {
            double sum = 0D;
            for (int i = 0; i < stateCount; i++) {
                for (int j = 0; j < stateCount; j++) {
                    sum += (alpha[i][t]
                            * transitionProbability[i][j]
                            * emissionProbability[j][observations[t + 1]]
                            * beta[j][t + 1]);
                }
            }

            for (int i = 0; i < stateCount; i++) {
                for (int j = 0; j < stateCount; j++) {
                    xi[i][j][t] = (alpha[i][t]
                            * transitionProbability[i][j]
                            * emissionProbability[j][observations[t + 1]]
                            * beta[j][t + 1]) / sum;
                }
            }
        }
    }

    private int[] viterbi() {
        for (int t = 1; t < step; t++) {
            for (int i = 0; i < stateCount; i++) {
                double curMax = 0D;
                for (int j = 0; j < stateCount; j++) {
                    double curDelta = delta[j][t - 1] * transitionProbability[j][i];
                    if (curMax < curDelta) {
                        curMax = curDelta;
                        psi[i][t] = j;
                    }
                }
                delta[i][t] = curMax * emissionProbability[i][observations[t]];
            }
        }

        return backtracking();
    }

    private int[] backtracking() {
        int[] bestPath = new int[step];

        bestPath[step - 1] = 0;
        for (int i = 0; i < stateCount; i++) {
            if (delta[bestPath[step - 1]][step - 1] < delta[i][step - 1]) {
                bestPath[step - 1] = i;
            }
        }

        for (int t = step - 2; t > -1; t--) {
            bestPath[t] = psi[bestPath[t + 1]][t + 1];
        }

        return bestPath;
    }

    public static void main(String[] args) {
        // region Example 10.2
        HiddenMarkovModel.Builder builder = new HiddenMarkovModel.Builder()
                .setObservations(new int[]{0, 1, 0})// 观察序列：红，白，红
                .setStartProbability(new double[]{0.2, 0.4, 0.4})
                .setTransitionProbability(new double[][]{
                        {0.5, 0.2, 0.3},
                        {0.3, 0.5, 0.2},
                        {0.2, 0.3, 0.5}
                })
                .setEmissionProbability(new double[][]{
                        {0.5, 0.5},
                        {0.4, 0.6},
                        {0.7, 0.3}
                });
        HiddenMarkovModel hmm = builder.build();
        Console.writeLine("forward: " + hmm.forward());
        // endregion
        // region Exercise 10.3
        Console.write(hmm.step, hmm.viterbi());
        // endregion
        // region Exercise 10.1
        builder.setObservations(new int[]{0, 1, 0, 1});// 观察序列：红，白，红，白
        hmm = builder.build();
        Console.writeLine("backward: " + hmm.backward());
        Console.writeLine("");
        // endregion
        // region Exercise 10.2
        builder = new HiddenMarkovModel.Builder()
                .setObservations(new int[]{0, 1, 0, 0, 1, 0, 1, 1})// 观察序列：红，白，红，红，白，红，白，白
                .setStartProbability(new double[]{0.2, 0.3, 0.5})
                .setTransitionProbability(new double[][]{
                        {0.5, 0.1, 0.4},
                        {0.3, 0.5, 0.2},
                        {0.2, 0.2, 0.6}
                })
                .setEmissionProbability(new double[][]{
                        {0.5, 0.5},
                        {0.4, 0.6},
                        {0.7, 0.3}
                });
        hmm = builder.build();
        Console.writeLine("forward: " + hmm.forward());
        Console.writeLine("backward: " + hmm.backward());
        hmm.calcProbability();
        hmm.calcJointProbability();
        Console.writeLine("P{t_4=i_3|O,λ}: " + hmm.gamma[2][3]);
        Console.writeLine("P{t_4=i_2,t_5=i_3|O,λ}: " + hmm.xi[1][2][3]);
        Console.writeLine("");
        // endregion
    }

    static class Builder {
        private int[] observations;
        private double[] startProbability;
        private double[][] transitionProbability;
        private double[][] emissionProbability;

        Builder setObservations(int[] observations) {
            this.observations = observations;
            return this;
        }

        Builder setStartProbability(double[] startProbability) {
            this.startProbability = startProbability;
            return this;
        }

        Builder setTransitionProbability(double[][] transitionProbability) {
            this.transitionProbability = transitionProbability;
            return this;
        }

        Builder setEmissionProbability(double[][] emissionProbability) {
            this.emissionProbability = emissionProbability;
            return this;
        }

        HiddenMarkovModel build() {
            HiddenMarkovModel hmm = new HiddenMarkovModel();
            hmm.stateCount = startProbability.length;
            hmm.step = observations.length;
            hmm.observations = observations;
            hmm.startProbability = startProbability;
            hmm.transitionProbability = transitionProbability;
            hmm.emissionProbability = emissionProbability;
            hmm.init();
            return hmm;
        }
    }
}
