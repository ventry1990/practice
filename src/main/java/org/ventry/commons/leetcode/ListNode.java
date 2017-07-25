package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.ListNode
 * author: jojo
 * create: 2017/7/25 17:21
 * description:
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + "->";
    }
}
