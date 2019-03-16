package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.tree.PathSum
 * author: ventry
 * create: 2019/3/16 14:20
 * description:
 */

public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            return root.val == sum;
        } else {
            sum = sum - root.val;
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }
    }
}
