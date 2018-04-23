package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.MergeTwoSortedLists
 * author: ventry
 * create: 17/11/9 16:38
 * description:
 */

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode res = new ListNode(-1);
        ListNode pointer = res;
        ListNode node;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                node = new ListNode(l1.val);
                l1 = l1.next;
            }
            pointer.next = node;
            pointer = pointer.next;
        }

        if (l1 != null)
            pointer.next = l1;
        if (l2 != null)
            pointer.next = l2;

        return res.next;
    }
}
