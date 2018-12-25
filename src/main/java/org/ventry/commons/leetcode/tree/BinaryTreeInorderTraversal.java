package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.tree.BinaryTreeInorderTraversal
 * author: ventry
 * create: 2018/12/25 11:33
 * description:
 */

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        walkRecursively(root, res);
        return res;
    }

    private void walkRecursively(TreeNode root, List<Integer> res) {
        if (root == null) return;
        walkRecursively(root.getLeft(), res);
        res.add(root.getVal());
        walkRecursively(root.getRight(), res);
    }
}
