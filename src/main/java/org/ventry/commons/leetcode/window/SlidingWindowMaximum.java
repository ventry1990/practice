package org.ventry.commons.leetcode.window;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * file: org.ventry.commons.leetcode.window.SlidingWindowMaximum
 * author: ventry
 * create: 2020/4/23 14:13
 * description:
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0)
            return new int[0];

        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            // amortized analysis: each index only offer/poll once
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }

    /**
     * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetcode-3
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i]; // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j]; // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

    public int[] _maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        int[] res = new int[n - k + 1];
        res[0] = heap.peek();
        for (int i = k, j = 1; i < n; i++, j++) {
            heap.remove(nums[i - k]);
            heap.offer(nums[i]);
            res[j] = heap.peek();
        }
        return res;
    }
}
