package org.ventry.commons.leetcode.math;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.math.AddTwoNumbers
 * author: jojo
 * create: 2016/8/22 19:11
 * description:
 */

public class AddTwoNumbers {
    class LinkedList {
        ListNode head;
        ListNode tail;

        LinkedList() {
            head = new ListNode(-1);
            tail = new ListNode(-1);
        }

        void add(ListNode node) {
            tail.next = node;
            tail = node;

            if (head.val == -1) {
                head.next = tail.next;
                head = tail;
            }
        }

        ListNode getHead() {
            return head;
        }
    }

    public ListNode plus(ListNode l1, ListNode l2) {
        LinkedList list = new LinkedList();
        ListNode carry = new ListNode(0);
        ListNode cur;
        int val;
        while (l1 != null || l2 != null) {
            val = carry.val;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }

            carry = new ListNode(val / 10);
            cur = new ListNode(val % 10);
            list.add(cur);
        }

        if (carry.val > 0) {
            list.add(carry);
        }

        return list.getHead();
    }
}
