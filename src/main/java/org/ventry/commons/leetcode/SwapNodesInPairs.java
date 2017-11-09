package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.SwapNodesInPairs
 * author: ventry
 * create: 17/11/9 20:09
 * description:
 */

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;

        ListNode res = new ListNode(-1);
        ListNode cur = res;
        res.next = head;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            cur.next = second;
            first.next = second.next;
            second.next = first;
            cur = first;
        }

        return res.next;
    }
}
