package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.TreeNode
 * author: ventry
 * create: 18/8/16 20:51
 * description:
 */

public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
