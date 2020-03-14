package org.ventry.commons.algorithm.tree;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.tree.MaxSegmentTree
 * author: ventry
 * create: 2020/3/14 00:27
 * description:
 */
public class MaxSegmentTree {
    private int[] values;
    private int mid;

    public MaxSegmentTree(int[] source) {
        mid = (source.length & 1) == 1 ? source.length + 1 : source.length;
        values = new int[mid * 2];
        System.arraycopy(source, 0, values, mid, source.length);
        init();
    }

    private void init() {
        for (int i = values.length - 2; i > 0; i -= 2) {
            values[i / 2] = Math.max(values[i + 1], values[i]);
        }
    }

    public void update(int index, int n) {
        int i = mid + index - 1;
        values[i] = n;
        while (i / 2 > 0) {
            if ((i & 1) == 0) {
                values[i / 2] = Math.max(values[i], values[i + 1]);
                i = i / 2;
            } else {
                values[(i - 1) / 2] = Math.max(values[i - 1], values[i]);
                i = (i - 1) / 2;
            }
        }
    }

    public int query(int left, int right) {
        left = mid + left - 1;
        right = mid + right - 1;
        if (left > right) {
            int t = left;
            left = right;
            right = t;
        }

        int max = Integer.MIN_VALUE;
        while (left <= right) {
            if ((left & 1) == 1) {
                max = Math.max(values[left], max);
                left++;
            }
            if ((right & 1) == 0) {
                max = Math.max(values[right], max);
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSegmentTree tree = new MaxSegmentTree(new int[]{1, 2, 3, 4, 5});
        Console.write(12, tree.values);
        tree = new MaxSegmentTree(new int[]{4, 3, 8, 1, 6, 7});
        Console.write(12, tree.values);

        tree.update(1, 10);
        Console.write(12, tree.values);
        System.out.println(tree.query(2, 5));

        tree = new MaxSegmentTree(ArrayUtils.randomIntArrays(10));
        Console.write(20, tree.values);
        System.out.println(tree.query(2, 2));
        System.out.println(tree.query(2, 3));
        System.out.println(tree.query(2, 4));
        System.out.println(tree.query(2, 5));
        System.out.println(tree.query(2, 6));
        System.out.println(tree.query(2, 7));
        System.out.println(tree.query(2, 8));
    }
}
