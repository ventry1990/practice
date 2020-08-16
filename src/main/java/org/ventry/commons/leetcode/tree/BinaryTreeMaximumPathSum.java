package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.tree.BinaryTreeMaximumPathSum
 * author: ventry
 * create: 2020/6/21 18:56
 * description:
 */
public class BinaryTreeMaximumPathSum {
    private int sumMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        path(root);
        return sumMax;
    }

    public int path(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(path(root.left), 0);
        int right = Math.max(path(root.right), 0);
        sumMax = Math.max(sumMax, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public int _maxPathSum(TreeNode root) {
        return dfs(root)[0];
    }

    private int[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new int[]{root.val, root.val, root.val};
        } else if (root.left != null && root.right == null) {
            int[] leftSumOfPath = dfs(root.left);
            int maxLeft = Math.max(leftSumOfPath[1], leftSumOfPath[2]);
            maxLeft = Math.max(root.val, root.val + maxLeft);
            return new int[]{Math.max(leftSumOfPath[0], maxLeft),
                    maxLeft, root.val};
        } else if (root.left == null) {
            int[] rightSumOfPath = dfs(root.right);
            int maxRight = Math.max(rightSumOfPath[1], rightSumOfPath[2]);
            maxRight = Math.max(root.val, root.val + maxRight);
            return new int[]{Math.max(rightSumOfPath[0], maxRight),
                    root.val, maxRight};
        } else {
            int[] leftSumOfPath = dfs(root.left);
            int[] rightSumOfPath = dfs(root.right);
            int maxLeft = Math.max(leftSumOfPath[1], leftSumOfPath[2]);
            int newMaxLeft = Math.max(root.val, root.val + maxLeft);
            int maxRight = Math.max(rightSumOfPath[1], rightSumOfPath[2]);
            int newMaxRight = Math.max(root.val, root.val + maxRight);
            int max = Math.max(leftSumOfPath[0], rightSumOfPath[0]);
            max = Math.max(max, root.val);
            max = Math.max(max, Math.max(newMaxLeft, newMaxRight));
            max = Math.max(max, root.val + maxLeft + maxRight);
            return new int[]{max, newMaxLeft, newMaxRight};
        }
    }
}
