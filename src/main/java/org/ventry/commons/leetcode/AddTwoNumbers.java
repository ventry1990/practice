package org.ventry.commons.leetcode;

import java.util.Random;

/**
 * file: org.ventry.commons.leetcode.AddTwoNumbers
 * author: jojo
 * create: 2016/8/22 19:11
 * description:
 */

public class AddTwoNumbers {
    class LinkedList {
        ListNode head;
        ListNode tail;

        public LinkedList() {
            head = new ListNode(-1);
            tail = new ListNode(-1);
        }

        public void add(ListNode node) {
            tail.next = node;
            tail = node;

            if (head.val == -1) {
                head.next = tail.next;
                head = tail;
            }
        }

        public ListNode getHead() {
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

    public static void main(String[] args) {
        LinkedList l1 = new AddTwoNumbers().new LinkedList();
        l1.add(new ListNode(2));
        l1.add(new ListNode(4));
        l1.add(new ListNode(3));
        LinkedList l2 = new AddTwoNumbers().new LinkedList();
        l2.add(new ListNode(5));
        l2.add(new ListNode(6));
        l2.add(new ListNode(4));
        print(l1.getHead());
        print(l2.getHead());
        System.out.println();

        print(new AddTwoNumbers().plus(l1.getHead(), l2.getHead()));
        print(new AddTwoNumbers().plus(l1.getHead(), null));

        l1 = new AddTwoNumbers().new LinkedList();
        l1.add(new ListNode(2));
        l1.add(new ListNode(4));
        l1.add(new ListNode(9));
        l2 = new AddTwoNumbers().new LinkedList();
        l2.add(new ListNode(5));
        l2.add(new ListNode(6));
        l2.add(new ListNode(7));
        l2.add(new ListNode(9));
        l2.add(new ListNode(9));
        print(new AddTwoNumbers().plus(l1.getHead(), l2.getHead()));

        l1 = new AddTwoNumbers().new LinkedList();
        l2 = new AddTwoNumbers().new LinkedList();
        for(int i = 0; i < 100; i++) {
            l1.add(new ListNode(new Random().nextInt(9)));
            l2.add(new ListNode(new Random().nextInt(9)));
        }
        print(new AddTwoNumbers().plus(l1.getHead(), l2.getHead()));
    }

    static void print(ListNode head) {
        while (head != null) {
            System.out.print(head);
            head = head.next;
        }
        System.out.println();
    }

}
