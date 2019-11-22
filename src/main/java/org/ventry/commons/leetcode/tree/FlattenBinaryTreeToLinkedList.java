package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.LinkedList;

/**
 * file: org.ventry.commons.leetcode.tree.FlattenBinaryTreeToLinkedList
 * author: ventry
 * create: 2019-11-22 14:24
 * description:
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        walkRecursively(root, nodes);
        TreeNode prev = nodes.poll();
        TreeNode cur;
        while (prev != null && (cur = nodes.poll()) != null) {
            prev.left = null;
            prev.right = cur;
            prev = cur;
        }
    }

    private void walkRecursively(TreeNode root, LinkedList<TreeNode> res) {
        if (root == null) return;
        res.add(root);
        walkRecursively(root.left, res);
        walkRecursively(root.right, res);
    }

    private TreeNode pre = null;

    public void flatten2(TreeNode root) {
        if (root == null) return;

        flatten2(root.right);
        flatten2(root.left);

        root.right = pre;
        root.left = null;

        pre = root;
    }
}
