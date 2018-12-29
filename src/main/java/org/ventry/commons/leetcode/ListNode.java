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

    public static ListNode build(int[] src) {
        if (src == null || src.length == 0)
            return null;
        ListNode head = new ListNode(src[0]);
        ListNode cur = head;
        for (int i = 1; i < src.length; i++) {
            cur.next = new ListNode(src[i]);
            cur = cur.next;
        }
        return head;
    }

    @Override
    public String toString() {
        return val + (next != null ? "->" + next.toString() : "");
    }
}
