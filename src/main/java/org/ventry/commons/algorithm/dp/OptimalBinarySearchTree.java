package org.ventry.commons.algorithm.dp;

import org.apache.commons.lang3.StringUtils;
import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.dp.OptimalBinarySearchTree
 * author: ventry
 * create: 18/6/6 16:52
 * description:
 */

public class OptimalBinarySearchTree {
    private int len;
    private double[] branchProbability;
    private double[] leafProbability;
    private double[][] expectation;
    private double[][] cost;
    private int[][] root;

    private OptimalBinarySearchTree(double[] p, double[] q) {
        len = q.length;
        branchProbability = new double[len];
        System.arraycopy(p, 0, branchProbability, 1, p.length);
        leafProbability = q;
        cost = new double[len + 1][len];
        expectation = new double[len + 1][len];
        root = new int[len][len];

        for (int i = 0; i < len; i++) {
            cost[i + 1][i] = leafProbability[i];
            expectation[i + 1][i] = leafProbability[i];
        }
    }

    private void program() {
        for (int l = 1; l < len; l++) {// l is the sub-length
            for (int start = 1; start < len - l + 1; start++) {
                int end = start + l - 1;
                expectation[start][end] = Double.MAX_VALUE;
                cost[start][end] = cost[start][end - 1] + branchProbability[end] + leafProbability[end];
                for (int k = start; k <= end; k++) {
                    double cur = expectation[start][k - 1] + expectation[k + 1][end] + cost[start][end];
                    if (cur < expectation[start][end]) {
                        expectation[start][end] = cur;
                        root[start][end] = k;
                    }
                }
            }
        }
    }

    private Node build() {
        program();
        return findRoot(1, len - 1);
    }

    private Node findRoot(int start, int end) {
        if (start > end) {
            return null;
        }

        Node node = new Node(root[start][end]);
        if (start == end) {
            return node;
        }

        node.left = findRoot(start, node.index - 1);
        node.right = findRoot(node.index + 1, end);
        return node;
    }

    static class Node {
        private Node left;
        private Node right;
        private int index;

        Node(int idx) {
            index = idx;
        }

        @Override
        public String toString() {
            return print(0);
        }

        private String print(int l) {
            return "{" + newLine(l + 1) +
                    "index=" + index +
                    "," + newLine(l + 1) +
                    "left=" + (left == null ? "#" : left.print(l + 1)) +
                    "," + newLine(l + 1) +
                    "right=" + (right == null ? "#" : right.print(l + 1)) +
                    newLine(l) +
                    "}";
        }

        private String newLine(int t) {
            return "\n" + StringUtils.repeat("\t", t);
        }
    }

    public static void main(String[] args) {
        double[] p = new double[]{0.15, 0.1, 0.05, 0.1, 0.2};
        double[] q = new double[]{0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
        OptimalBinarySearchTree tree = new OptimalBinarySearchTree(p, q);
        tree.program();
        Console.writeLine(tree.build());
    }
}