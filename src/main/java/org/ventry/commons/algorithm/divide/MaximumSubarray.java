package org.ventry.commons.algorithm.divide;

import org.ventry.commons.utils.Console;

public class MaximumSubarray {
    private static int findMaximum(int[] source, int low, int high) {
        if (low == high - 1) {
            return source[low];
        } else {
            int middle = (low + high) / 2;
            int maxInLeft = findMaximum(source, low, middle);
            int maxInRight = findMaximum(source, middle, high);
            int maxCrossMiddle = findCrossMiddle(source, low, middle, high);

            return Math.max(Math.max(maxInLeft, maxInRight), maxCrossMiddle);
        }
    }

    private static int findCrossMiddle(int[] source, int low, int middle, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = middle - 1; i >= low; i--) {
            sum += source[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = middle; i < high; i++) {
            sum += source[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        return leftSum + rightSum;
    }

    public static int findMaximumLinear(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return Math.max(maxSoFar, maxEndingHere);
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, -7};
        // int[] arrays = new int[]{-1, -2, -3, -4};
        // int[] arrays = new int[]{-1, -2, 4, 5, -6, 7, -4, 8, -3, -4, -5, -6};
        Console.writeLine(findMaximum(arrays, 0, arrays.length));
        Console.writeLine(findMaximumLinear(arrays));
        int iterator = 1000000;
        long start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            findMaximum(arrays, 0, arrays.length);
        }
        Console.writeLine("divide conquer maximum subarray cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            findMaximumLinear(arrays);
        }
        Console.writeLine("dynamic programming maximum subarray cost:" + (System.nanoTime() - start) / 1000000 + "ms");
    }
}
