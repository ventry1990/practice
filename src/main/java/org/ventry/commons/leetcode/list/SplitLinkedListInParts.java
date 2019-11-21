package org.ventry.commons.leetcode.list;

import org.ventry.commons.leetcode.ListNode;

/**
 * file: org.ventry.commons.leetcode.list.SplitLinkedListInParts
 * author: ventry
 * create: 2019-11-21 17:44
 * description:
 */
public class SplitLinkedListInParts {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null)
            return res;

        int size = sizeOf(root);
        int base = size / k;
        int additional = size % k;
        ListNode head = root;
        for (int i = 0; i < k; i++) {
            if (head == null) break;

            res[i] = head;
            ListNode tail = head;
            for (int j = 1; j < base; j++) {
                tail = tail.next;
            }
            if (i < additional && base > 0) {
                tail = tail.next;
            }
            head = tail.next;
            tail.next = null;
        }
        return res;
    }

    private int sizeOf(ListNode root) {
        int size = 0;
        ListNode cur = root;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }
}
