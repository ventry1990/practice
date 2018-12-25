package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.PartitionList
 * author: ventry
 * create: 2018/12/25 15:10
 * description:
 */

public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode lt = new ListNode(-1);
        ListNode ltHead = lt;
        ListNode ge = new ListNode(-1);
        ListNode geHead = ge;
        while (head != null) {
            if (head.val < x) {
                lt.next = head;
                lt = lt.next;
            } else {
                ge.next = head;
                ge = ge.next;
            }
            head = head.next;
        }
        ge.next = null;
        lt.next = geHead.next;
        return ltHead.next;
    }
}
