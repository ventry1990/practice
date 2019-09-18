package org.ventry.commons.leetcode.search;

/**
 * file: org.ventry.commons.leetcode.search.FindTheDuplicateNumber
 * author: ventry
 * create: 2019-09-18 11:54
 * description:
 */
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            int count = 0;
            for (int num : nums) {
                if (num <= mid)
                    count++;
            }
            if (count <= mid) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    /**
     * http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number
     */
    public int findDuplicate2(int[] nums) {
        // Find the intersection point of the two runners.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}
