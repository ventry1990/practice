package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        find(root, sum, res, path);
        return res;
    }

    private void find(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<Integer> one = new ArrayList<>(path);
                one.add(sum);
                res.add(one);
            }
        } else {
            sum = sum - root.val;
            path.add(root.val);
            find(root.left, sum, res, path);
            find(root.right, sum, res, path);
            path.remove(path.size() - 1);
        }
    }
}
