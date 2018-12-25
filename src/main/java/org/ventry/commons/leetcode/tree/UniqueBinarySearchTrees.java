package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.tree.UniqueBinarySearchTrees
 * author: ventry
 * create: 2018/12/25 13:04
 * description:
 */

public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;

        // 卡兰塔数，递归公式：C_0 = 1 and \sum_{i=0}^{n} {{C_i}{C_{n-i}}}
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int x = i / 2;
            for (int j = 0; j < x; j++) {
                dp[i] += (2 * dp[j] * dp[i - j - 1]);
            }
            if (i % 2 == 1) {
                dp[i] += (dp[x] * dp[x]);
            }
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }

    private List<TreeNode> generate(int s, int e) {
        List<TreeNode> res = new ArrayList<>();
        if (s > e) {
            res.add(null);
        } else {
            for (int i = s; i <= e; i++) {
                List<TreeNode> leftTrees = generate(s, i - 1);
                List<TreeNode> rightTrees = generate(i + 1, e);
                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }

    public List<TreeNode> generateTrees2(int n) {
        @SuppressWarnings("unchecked") List<TreeNode>[] ret = new List[n + 1];
        ret[0] = new ArrayList<>();
        if (n == 0) return ret[0];

        ret[0].add(null);
        for (int i = 1; i <= n; ++i) {
            ret[i] = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                List<TreeNode> leftTrees = ret[j];
                List<TreeNode> rightTrees = ret[i - j - 1];
                for (TreeNode leftSubTree : leftTrees) {
                    for (TreeNode rightSubTree : rightTrees) {
                        TreeNode root = new TreeNode(j + 1);
                        root.left = leftSubTree;
                        root.right = clone(rightSubTree, j + 1);
                        ret[i].add(root);
                    }
                }
            }
        }
        return ret[n];
    }

    private TreeNode clone(TreeNode root, int offset) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val + offset);
        node.left = clone(root.left, offset);
        node.right = clone(root.right, offset);
        return node;
    }
}
