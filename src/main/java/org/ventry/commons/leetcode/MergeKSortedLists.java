package org.ventry.commons.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * file: org.ventry.commons.leetcode.MergeKSortedLists
 * author: jojo
 * create: 2017/7/25 16:22
 * description:
 */

public class MergeKSortedLists {

    // 方案2：分治
    class Solution {
        List<ListNode> heap;

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
    }

    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
        Random rnd = new Random();
        int size = rnd.nextInt(100);
        ListNode[] listNodes = new ListNode[size];
        for (int i = 0; i < size; i++) {
            AddTwoNumbers.LinkedList l1 = new AddTwoNumbers().new LinkedList();
            int subSize = rnd.nextInt(1000);
            int eta = rnd.nextInt(size) + 1;
            for (int i1 = 0; i1 < subSize; i1++) {
                if (i1 % eta == 0)
                    l1.add(new ListNode(i1));
            }
            listNodes[i] = l1.head;
        }

        ListNode out = solution.mergeKLists(listNodes);
        AddTwoNumbers.print(out);
    }
}
