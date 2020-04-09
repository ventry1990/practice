package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.Node;

/**
 * file: org.ventry.commons.leetcode.tree.ConvertBinarySearchTreeToSortedDoublyLinkedList
 * author: ventry
 * create: 2020/4/9 01:57
 * description:
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    private Node pre = new Node(), head = pre;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head = head.right;
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        pre.right = root;
        root.left = pre;
        // next
        pre = root;
        dfs(root.right);
    }

    public Node _treeToDoublyList(Node root) {
        if (root == null) return root;

        Node[] res = _dfs(root);
        res[0].left = res[1];
        res[1].right = res[0];
        return res[0];
    }

    public Node[] _dfs(Node node) {
        Node[] res = new Node[2];
        if (node == null) return res;


        Node[] prev = _dfs(node.left);
        if (prev[1] != null) {
            node.left = prev[1];
            prev[1].right = node;
            res[0] = prev[0];
        } else {
            res[0] = node;
        }

        Node[] next = _dfs(node.right);
        if (next[0] != null) {
            node.right = next[0];
            next[0].left = node;
            res[1] = next[1];
        } else {
            res[1] = node;
        }

        return res;
    }
}
