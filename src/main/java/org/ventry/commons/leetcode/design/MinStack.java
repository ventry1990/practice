package org.ventry.commons.leetcode.design;

/**
 * file: org.ventry.commons.leetcode.design.MinStack
 * author: ventry
 * create: 2018/12/29 19:48
 * description:
 */

public class MinStack {
    private Node head;

    public MinStack() {
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        Node(int x, int min) {
            this(x, min, null);
        }

        Node(int x, int min, Node next) {
            this.val = x;
            this.min = min;
            this.next = next;
        }

    }
}
