package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.ListNode
 * author: jojo
 * create: 2017/7/25 17:21
 * description:
 */

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + (next != null ? "->" + next.toString() : "");
    }
}
