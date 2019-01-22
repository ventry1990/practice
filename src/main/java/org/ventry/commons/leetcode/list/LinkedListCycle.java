package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.LinkedListCycle
 * author: ventry
 * create: 2019/1/21 18:29
 * description:
 */

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        int slowIndex = 0;
        int fastIndex = 0;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            slowIndex++;
            fastIndex += 2;
            if (slow == fast) {
                int cycleSize = fastIndex - slowIndex;
                ListNode res = head;
                for (int i = 0; i < cycleSize; i++) {
                    res = res.next;
                }
                while (res != head) {
                    res = res.next;
                    head = head.next;
                }
                return res;
            }
        }
        return null;
    }

    /**
     * https://leetcode.com/problems/linked-list-cycle-ii/discuss/44793/O(n)-solution-by-using-two-pointers-without-change-anything
     */
    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
