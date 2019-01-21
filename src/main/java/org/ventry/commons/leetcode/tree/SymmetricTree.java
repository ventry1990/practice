package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.tree.SymmetricTree
 * author: ventry
 * create: 2019/1/21 17:37
 * description:
 */

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null)
            return right == null;

        if (right == null)
            return false;

        return left.val == right.val
                && compare(left.left, right.right)
                && compare(left.right, right.left);
    }
}
