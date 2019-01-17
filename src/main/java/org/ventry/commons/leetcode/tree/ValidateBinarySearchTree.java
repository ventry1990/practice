package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.tree.ValidateBinarySearchTree
 * author: ventry
 * create: 2019/1/17 11:15
 * description:
 */

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        walkRecursively(root, res);
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i - 1) > res.get(i))
                return false;
        }
        return true;
    }

    private void walkRecursively(TreeNode root, List<Integer> res) {
        if (root == null) return;
        walkRecursively(root.left, res);
        res.add(root.val);
        walkRecursively(root.right, res);
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long min, long max) {
        if (node.val <= min || node.val >= max) {
            return false;
        }
        if (node.left != null) {
            if (!dfs(node.left, min, node.val)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!dfs(node.right, node.val, max)) {
                return false;
            }
        }
        return true;
    }
}
