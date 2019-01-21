package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * file: org.ventry.commons.leetcode.tree.BinaryTreeLevelOrderTraversal
 * author: ventry
 * create: 2019/1/21 17:48
 * description:
 */

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        walkRecursively(level, res);
        return res;
    }

    private void walkRecursively(List<TreeNode> level, List<List<Integer>> res) {
        if (level.size() == 0) return;
        List<Integer> val = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode treeNode : level) {
            val.add(treeNode.val);

            if (treeNode.left != null)
                nextLevel.add(treeNode.left);

            if (treeNode.right != null)
                nextLevel.add(treeNode.right);
        }
        res.add(val);
        walkRecursively(nextLevel, res);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        fillList(list, root, 0);
        return list;
    }

    private void fillList(List<List<Integer>> list, TreeNode root, int index) {
        if (root == null) return;

        if (list.size() == index)
            list.add(new ArrayList<>());

        list.get(index).add(root.val);

        fillList(list, root.left, index + 1);
        fillList(list, root.right, index + 1);
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
                temp.add(cur.val);
            }
            res.add(temp);
        }
        return res;
    }
}
