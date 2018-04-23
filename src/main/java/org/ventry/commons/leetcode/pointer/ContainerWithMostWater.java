package org.ventry.commons.leetcode.pointer;

/**
 * file: org.ventry.commons.leetcode.pointer.ContainerWithMostWater
 * author: ventry
 * create: 17/11/8 14:04
 * description:
 */

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int maxCapacity = 0;
        int curCapacity;
        int minHeight;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            minHeight = Math.min(height[left], height[right]);
            curCapacity = minHeight * (right - left);
            if (curCapacity > maxCapacity) {
                maxCapacity = curCapacity;
            }

            if (height[left] > height[right]) {
                while (right > left && minHeight >= height[right]) {
                    right--;
                }
            } else {
                while (right > left && minHeight >= height[left]) {
                    left++;
                }
            }
        }

        return maxCapacity;
    }
}
