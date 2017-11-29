package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.TrappingRainWater
 * author: ventry
 * create: 17/11/27 10:40
 * description:
 */

public class TrappingRainWater {

    public int trap(int[] height) {
        int size = 0;
        int[] desc = new int[height.length];
        int[] areas = new int[height.length];

        int left = 0;
        while (left < height.length - 1 && height[left] <= height[left + 1]) left++;

        int right = height.length - 1;
        while (right > 0 && height[right - 1] >= height[right]) right--;

        while (left < right) {
            desc[size++] = left;
            while (left < right && height[left] >= height[left + 1])
                left++;

            while (left < right && height[left] <= height[left + 1])
                left++;

            while (size > 0 && height[desc[--size]] < height[left]) ;

            int horizon = Math.min(height[desc[size]], height[left]);
            for (int l = desc[size] + 1; l < left; l++) {
                areas[l] = horizon - height[l];
            }

            if (height[desc[size]] > height[left])
                size++;
        }

        int res = 0;
        for (int area : areas) {
            if (area > 0)
                res += area;
        }
        return res;
    }

    public int trap2(int[] height) {
        int l = 0, r = height.length - 1, sum = 0;

        while (l < r && height[l] < height[l + 1]) l++;
        while (l < r && height[r] < height[r - 1]) r--;
        while (l < r) {
            int right = height[r];
            int left = height[l];
            if (left <= right)
                while (l < r && left > height[++l]) sum += left - height[l];
            else
                while (l < r && right > height[--r]) sum += right - height[r];
        }
        return sum;
    }
}
