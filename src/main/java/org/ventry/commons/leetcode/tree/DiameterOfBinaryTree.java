package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.tree.DiameterOfBinaryTree
 * author: ventry
 * create: 2019-11-22 11:27
 * description:
 */
public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        return calcDiameterAndDepth(root)[0];
    }

    private int[] calcDiameterAndDepth(TreeNode root) {
        if (root == null)
            return new int[3];

        int[] left = calcDiameterAndDepth(root.left);
        int[] right = calcDiameterAndDepth(root.right);

        int maxInLeft = Math.max(left[1], left[2]);
        int maxInRight = Math.max(right[1], right[2]);
        int newDiameter = Math.max(Math.max(left[0], right[0]), maxInLeft + maxInRight);
        return new int[]{newDiameter, maxInLeft + 1, maxInRight + 1};
    }
}
