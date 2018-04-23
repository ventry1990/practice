package org.ventry.commons.leetcode.pointer;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.pointer.RemoveNthFromTheEndOfList
 * author: ventry
 * create: 17/9/26 22:44
 * description:
 */

public class RemoveNthFromTheEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n < 1)
            return head;

        ListNode cur = head;
        ListNode mark = head;
        int i = 0;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
            if (i == n + 1)
                mark = mark.next;
            else
                i++;
        }

        if (size <= n)
            return head.next;

        if (mark.next != null)
            mark.next = mark.next.next;
        return head;
    }
}
