package org.ventry.commons.leetcode.divide;

import org.ventry.commons.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.divide.MergeKSortedLists
 * author: jojo
 * create: 2017/7/25 16:22
 * description: 方案2：分治
 */

public class MergeKSortedLists {

    private List<ListNode> heap;

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        heap = new ArrayList<>(lists.length);
        for (ListNode list : lists) {
            insert(list);
        }

        ListNode head = new ListNode(-1);
        ListNode tail = head;
        head.next = tail;
        for (; ; ) {
            ListNode item = first();
            if (item == null)
                break;

            insert(item.next);
            tail.next = new ListNode(item.val);
            tail = tail.next;
        }
        return head.next == head ? null : head.next;
    }

    private void insert(ListNode e) {
        if (e == null)
            return;

        int low = 0;
        int high = heap.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (e.val < heap.get(mid).val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low == heap.size()) {
            heap.add(e);
        } else {
            heap.add(low, e);
        }
    }

    private ListNode first() {
        if (heap.size() < 1)
            return null;

        ListNode e = heap.get(0);
        heap.remove(0);
        return e;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        int len;
        if (lists == null || (len = lists.length) == 0) {
            return null;
        }
        return mergeHelper(0, len - 1, lists);

    }

    private ListNode mergeHelper(int start, int end, ListNode[] lists) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(start, mid, lists);
        ListNode right = mergeHelper(mid + 1, end, lists);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }
}
