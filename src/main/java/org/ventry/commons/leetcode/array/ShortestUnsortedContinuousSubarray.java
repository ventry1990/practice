package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.ShortestUnsortedContinuousSubarray
 * author: ventry
 * create: 2020/10/18 23:17
 * description:
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;
        int[] stack = new int[len];
        int top = -1;
        stack[++top] = nums[0];
        int left = len;
        int right = 0;
        for (int i = 1; i < len; i++) {
            if (stack[top] <= nums[i]) {
                stack[++top] = nums[i];
            } else {
                int j = top;
                right = ++top;
                while (j > -1 && stack[j] > nums[i]) {
                    stack[j + 1] = stack[j];
                    j--;
                }
                stack[j + 1] = nums[i];
                left = Math.min(j + 1, left);
            }
        }
        return right > left ? right - left + 1 : 0;
    }

    public int findUnsortedSubarray2(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        boolean hasUnSort = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                hasUnSort = true;
                // 继续往后找最小的，再根据最小的值应该在的位置取开始
                int min = nums[i];
                while (i < nums.length) {
                    min = Math.min(min, nums[i]);
                    i++;
                }
                while (nums[begin] <= min) {
                    begin++;
                }
                break;
            }
        }
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] < nums[i - 1]) {
                hasUnSort = true;
                // 继续往前找最大的，再根据最大的值应该在的位置取结束
                int max = nums[i];
                while (i >= 0) {
                    max = Math.max(max, nums[i]);
                    i--;
                }
                while (nums[end] >= max) {
                    end--;
                }
                break;
            }
        }
        return hasUnSort ? end - begin + 1 : 0;
    }
}
