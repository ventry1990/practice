package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.ReorderList
 * author: ventry
 * create: 2019-07-17 13:29
 * description:
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        prev.next = null;

        slow = reverse(slow);
        ListNode cur = head;
        prev = null;
        while (cur != null && slow != null) {
            ListNode node = slow;
            slow = slow.next;
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
            prev = node;
        }
        if (slow != null)
            prev.next = slow;
    }

    private ListNode reverse(ListNode head) {
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
}
