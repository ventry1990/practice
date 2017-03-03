package org.ventry.commons.stats;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

import java.util.Arrays;
import java.util.Random;

/**
 * file: org.ventry.commons.stats.Perceptron
 * author: jojo
 * create: 2017/3/3 20:24
 * description:
 */

public class Perceptron {
    private static final Random RANDOM = new Random();

    /**
     * 随机梯度下降算法原始形式
     *
     * @param samples (x_i, y_i) <- (x^1_i, x^2_i, ... x^k_i, y_i)：
     *                x_i为k维向量，x^k_i表示向量x_i在第k个特征处的观测值；
     *                y_i∈{+1，-1}，+1表示正分类，-1表示负分类
     * @param eta     学习能力，η∈[0, 1]
     * @return (w, b)
     */
    static float[] sgd(int[][] samples, float eta) {
        assert samples != null;

        float[] w = new float[samples[0].length - 1];
        float b = 0F;

        int y_index = w.length;
        for (int k = 0; k < samples.length; ) {
            int[] sample = samples[k];

            float product = 0F;
            for (int i = 0; i < y_index; i++) {
                product += (w[i] * sample[i]);
            }
            float distance = sample[y_index] * (product + b);
            if (distance > 0) {
                k++;
                continue;
            }

            for (int i = 0; i < y_index; i++) {
                w[i] += eta * sample[y_index] * sample[i];
            }
            b += eta * sample[y_index];
            k = k == 0 ? 0 : RANDOM.nextInt(k);
        }

        float[] result = Arrays.copyOf(w, y_index + 1);
        result[y_index] = b;
        return result;
    }

    public static void main(String[] args) {
        final float eta = 1F;

        Console.writeLine("set1:");
        int[][] set1 = {{3, 3, 1}, {4, 3, 1}, {1, 1, -1}};
        for (int[] ints : set1) {
            Console.write(ints.length, ints);
        }
        Console.writeLine("-------------------------------");
        Console.write(set1[0].length, sgd(set1, eta));

        Console.writeLine("set2:");
        int[][] set2 = new int[100][3];
        for (int[] ints : set2) {
            for (int i = 0; i < ints.length - 1; i++)
                ints[i] = RANDOM.nextInt(50);
            ints[ints.length - 1] = ArrayUtils.sum(ints) >= 50 * (ints.length - 2) ? 1 : -1;
            Console.write(ints.length, ints);
        }
        Console.writeLine("-------------------------------");
        Console.write(set2[0].length, sgd(set2, eta));

        Console.writeLine("exit...");
    }
}
