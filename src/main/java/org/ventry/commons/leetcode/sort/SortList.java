package org.ventry.commons.leetcode.sort;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.sort.SortList
 * author: ventry
 * create: 2018/12/29 13:33
 * description:
 */

public class SortList {

    public ListNode sortList(ListNode head) {
        return sort(head, null);
    }

    private ListNode sort(ListNode head, ListNode tail) {
        if (head != tail) {
            ListNode mid = partition(head, tail);
            sort(head, mid);
            sort(mid.next, tail);
        }
        return head;
    }

    private ListNode partition(ListNode head, ListNode tail) {
        int x = head.val;
        ListNode cur = head.next;
        ListNode prev = cur;
        ListNode mid = null;
        while (cur != tail) {
            if (cur.val < x && mid == null) {
                mid = cur;
            }
            if (cur.val < x && cur != head.next) {
                prev.next = cur.next;
                cur.next = head.next;
                head.next = cur;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        if (mid != null) {
            int temp = mid.val;
            mid.val = head.val;
            head.val = temp;
            return mid;
        } else {
            return head;
        }
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode l1 = sortList2(head);
        ListNode l2 = sortList2(slow);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode root = null;
        ListNode cur = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (root == null)
                    root = l1;
                if (cur != null) {
                    cur.next = l1;
                }
                cur = l1;
                l1 = l1.next;
            } else {
                if (root == null)
                    root = l2;
                if (cur != null)
                    cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }

        if (l1 != null)
            cur.next = l1;
        if (l2 != null)
            cur.next = l2;

        return root;
    }
}
