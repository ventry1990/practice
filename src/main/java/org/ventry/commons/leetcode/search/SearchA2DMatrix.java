package org.ventry.commons.leetcode.search;

/**
 * file: org.ventry.commons.leetcode.search.SearchA2DMatrix
 * author: ventry
 * create: 2018/9/13 19:10
 * description:
 */

public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int row = matrix.length, col = matrix[0].length;
        int low = 0, high = row * col;
        while (low < high) {
            int mid = (low + high) / 2;
            if (matrix[mid / col][mid % col] == target)
                return true;
            else if (matrix[mid / col][mid % col] < target)
                low = mid + 1;
            else
                high = mid;
        }

        return false;
    }

    public boolean _searchMatrix(int[][] matrix, int target) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0
                || matrix[0] == null || (n = matrix[0].length - 1) == -1)
            return false;

        int row = -1;
        for (int i = 0; i < m; i++) {
            if (matrix[i][n] == target)
                return true;

            if (matrix[i][n] > target) {
                row = i;
                break;
            }
        }

        if (row == -1)
            return false;

        return search(matrix[row], target, n);
    }

    private boolean search(int[] nums, int target, int high) {
        int low = 0;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0
                || (n = matrix[0].length) == 0)
            return false;

        m = m - 1;
        int i = 0;
        while (i < n && m > -1) {
            if (matrix[m][i] == target) return true;
            else if (matrix[m][i] > target) m--;
            else i++;
        }
        return false;
    }
}
