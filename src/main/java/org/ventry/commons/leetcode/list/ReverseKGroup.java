package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.ReverseKGroup
 * author: ventry
 * create: 17/11/9 22:19
 * description:
 */

public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 2)
            return head;

        ListNode res = new ListNode(-1);
        ListNode cur = res;
        res.next = head;
        ListNode one = cur.next;
        ListNode two;
        int i = k - 1;
        while (one.next != null) {
            if (i > 0) {
                two = one.next;
                one.next = two.next;
                two.next = cur.next;
                cur.next = two;
                i--;
            } else {
                cur = one;
                one = cur.next;
                i = k - 1;
            }
        }

        if (i > 0) {
            one = cur.next;
            while (one.next != null) {
                two = one.next;
                one.next = two.next;
                two.next = cur.next;
                cur.next = two;
            }
        }

        return res.next;
    }
}
