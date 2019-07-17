package org.ventry.commons.leetcode.tree;

import org.ventry.commons.leetcode.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * file: org.ventry.commons.leetcode.tree.PopulatingNextRightPointersInEachNode
 * author: ventry
 * create: 2019-07-17 15:24
 * description:
 */
public class PopulatingNextRightPointersInEachNode {

    public Node connect(Node root) {
        if (root == null || root.left == null) return root;

        doConnect(root, null);
        return root;
    }

    private void doConnect(Node left, Node right) {
        if (left == null) return;

        left.next = right;
        doConnect(left.left, left.right);
        if (right != null) {
            doConnect(left.right, right.left);
            doConnect(right.left, right.right);
        }
    }

    public Node connect2(Node root) {
        if (root == null || root.left == null) return root;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node cur = q.poll();
            if (cur == null) break;
            for (int i = 1; i < size; i++) {
                // `cur` is a non-leaf node of the perfect binary tree
                q.offer(cur.left);
                q.offer(cur.right);
                Node next = q.poll();
                cur.next = next;
                cur = next;
            }
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return root;
    }
}
