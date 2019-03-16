package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.InsertionSortList
 * author: ventry
 * create: 2019/3/16 14:01
 * description:
 */

public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = new ListNode(-1);
        p.next = head;
        ListNode cursor = p;
        ListNode end = head;
        head = head.next;
        while (head != null) {
            if (head.val < end.val) {
                while (cursor.next.val <= head.val) cursor = cursor.next;

                end.next = head.next;
                head.next = cursor.next;
                cursor.next = head;
            } else {
                end = head;
            }

            cursor = p;
            head = end.next;
        }
        return p.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode left = insertionSortList2(head);
        ListNode right = insertionSortList2(slow);
        return mergeList(left, right);
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode mergeHead = new ListNode(0);
        ListNode ptr = mergeHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ptr.next = l1;
                l1 = l1.next;
                ptr = ptr.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
                ptr = ptr.next;
            }
        }
        if (l1 != null) {
            ptr.next = l1;
        }
        if (l2 != null) {
            ptr.next = l2;
        }
        return mergeHead.next;
    }
}
