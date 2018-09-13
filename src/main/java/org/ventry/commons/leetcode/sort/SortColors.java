package org.ventry.commons.leetcode.sort;

/**
 * file: org.ventry.commons.leetcode.sort.SortColors
 * author: ventry
 * create: 2018/9/13 18:47
 * description:
 */

public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null)
            return;

        order(nums, 0, nums.length - 1);
    }

    private void order(int[] source, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middle = partition(source, startIndex, endIndex);
            order(source, startIndex, middle - 1);
            order(source, middle + 1, endIndex);
        }
    }

    private int partition(int[] source, int startIndex, int endIndex) {
        int j = endIndex;
        for (int i = startIndex + 1; i <= j; ) {
            if (source[i] > source[startIndex]) {
                int temp = source[i];
                source[i] = source[j];
                source[j] = temp;
                j--;
            } else {
                i++;
            }
        }

        int temp = source[startIndex];
        source[startIndex] = source[j];
        source[j] = temp;
        return j;
    }

    public void sortColors2(int[] nums) {
        int i = -1;
        int j = -1;
        int k = -1;
        for (int l = 0; l < nums.length; l++) {
            if (nums[l] == 0) {
                nums[++k] = 2;
                nums[++j] = 1;
                nums[++i] = 0;
            } else if (nums[l] == 1) {
                nums[++k] = 2;
                nums[++j] = 1;
            } else {
                nums[++k] = 2;
            }
        }
    }
}
