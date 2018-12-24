package org.ventry.commons.algorithm.tree;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

import java.util.LinkedList;

/**
 * Created by ventry on 16/9/9.
 */
public class BinarySearchTree {
    TreeNode root;
    int size;

    class TreeNode {
        int key;
        TreeNode parent;
        TreeNode left;
        TreeNode right;

        TreeNode(int key) {
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode build(int[] source) {
        for (int i : source) {
            TreeNode node = new TreeNode(i);
            insert(node);
            size++;
        }

        return root;
    }

    private void insert(TreeNode node) {
        TreeNode x = root;
        while (x != null) {
            node.parent = x;

            if (node.key > x.key)
                x = x.right;
            else
                x = x.left;

        }

        if (node.parent == null)
            root = node;
        else if (node.parent.key < node.key)
            node.parent.right = node;
        else
            node.parent.left = node;
    }

    private void delete(int key) {
        TreeNode p = root;
        while (p != null) {
            if (p.key < key) {
                p = p.right;
            } else if (p.key > key) {
                p = p.left;
            } else {
                if (p.left == null) {
                    transplant(p, p.right);
                } else if (p.right == null) {
                    transplant(p, p.left);
                } else {
                    TreeNode sub = minimum(p.right);
                    if (sub != p.right) {
                        transplant(sub, sub.right);
                        sub.right = p.right;
                        sub.right.parent = sub;
                    }
                    sub.left = p.left;
                    p.left.parent = sub;
                    transplant(p, sub);
                }
                return;
            }
        }
    }

    private void transplant(TreeNode origin, TreeNode substitute) {
        if (origin.parent == null)
            root = substitute;
        else if (origin.parent.left == origin)
            origin.parent.left = substitute;
        else
            origin.parent.right = substitute;

        if (substitute != null)
            substitute.parent = origin.parent;
    }

    private int[] inorderTreeWalk() {
        int[] result = new int[size];
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode p = root;
        int i = 0;
        // 若其左孩子不为空，则将P入栈并将P的左孩子置为当前的P，然后对当前结点P再进行相同的处理；
        // 若其左孩子为空，则取栈顶元素并进行出栈操作，访问该栈顶结点，然后将当前的P置为栈顶结点的右孩子；
        // 直到P为NULL并且栈为空则遍历结束。
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.addLast(p);
                p = p.left;
            }
            p = stack.removeLast();
            result[i++] = p.key;
            p = p.right;
        }

        return result;
    }

    private TreeNode minimum(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public static void main(String[] args) {
        int arraySize = 100;
        int[] arrays = ArrayUtils.randomIntArrays(arraySize);
        BinarySearchTree tree = new BinarySearchTree();
        tree.build(arrays);
        // Console.write(20, tree.inorderTreeWalk());
        for (int i = 17; i < arraySize; i += 17) {
            tree.delete(i);
            Console.write(20, tree.inorderTreeWalk());
            Console.writeLine("--------------------------------");
        }
    }
}
