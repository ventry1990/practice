package org.ventry.commons.leetcode.dfs;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.dfs.SameTree
 * author: ventry
 * create: 18/8/16 20:50
 * description:
 */

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == null && q == null;

        return p.getVal() == q.getVal()
                && isSameTree(p.getLeft(), q.getLeft())
                && isSameTree(p.getRight(), q.getRight());
    }
}