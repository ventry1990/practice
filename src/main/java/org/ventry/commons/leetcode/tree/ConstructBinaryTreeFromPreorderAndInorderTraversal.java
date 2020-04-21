package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.TreeNode;

/**
 * file: org.ventry.commons.leetcode.tree.ConstructBinaryTreeFromPreorderAndInorderTraversal
 * author: ventry
 * create: 2020/4/21 13:31
 * description:
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private int preIndex;
    private int inIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        inIndex = 0;
        return preIn(preorder, inorder, null);
    }

    private TreeNode preIn(int[] preorder, int[] inorder, TreeNode finish) {
        // inorder[inIndex] == finish.val 确定叶子节点
        if (preIndex >= preorder.length || (finish != null && inorder[inIndex] == finish.val)) {
            return null;
        }
        // 前序遍历构建（子）树根结点
        TreeNode root = new TreeNode(preorder[preIndex++]);
        root.left = preIn(preorder, inorder, root);
        // 中序遍历确定左右孩子结点?
        inIndex++;
        root.right = preIn(preorder, inorder, finish);
        return root;
    }

    public TreeNode _buildTree(int[] preorder, int[] inorder) {
        int n;
        if (preorder == null || inorder == null || (n = preorder.length) == 0 || n != inorder.length) return null;
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int m = inStart;
        for (; m <= inEnd; m++) {
            if (inorder[m] == preorder[preStart]) {
                root.left = build(preorder, inorder, preStart + 1, preStart + (m - inStart), inStart, m - 1);
                break;
            }
        }
        root.right = build(preorder, inorder, preStart + (m - inStart) + 1, preEnd, m + 1, inEnd);
        return root;
    }
}
