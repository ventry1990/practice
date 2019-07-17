package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.ReverseLinkedList
 * author: ventry
 * create: 2019/3/12 18:57
 * description:
 */

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fix = new ListNode(-1);
        fix.next = head;
        while (head.next != null) {
            ListNode cur = head.next;
            head.next = head.next.next;
            cur.next = fix.next;
            fix.next = cur;
        }
        return fix.next;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;

        ListNode fix = new ListNode(-1);
        fix.next = head;
        ListNode start = fix;
        for (int i = 1; head.next != null && i < n; i++) {
            if (i < m) {
                start = start.next;
                head = head.next;
            } else {
                ListNode cur = head.next;
                head.next = head.next.next;
                cur.next = start.next;
                start.next = cur;
            }
        }
        return fix.next;
    }
}
