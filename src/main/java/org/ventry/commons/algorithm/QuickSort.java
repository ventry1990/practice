package org.ventry.commons.algorithm;

import org.ventry.commons.utils.ArrayUtils;

import java.util.Random;

/**
 * Created by ventry on 16/7/29.
 */
public class QuickSort {
    private static final Random random = new Random();

    public static int[] order(int[] source, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middle = partition(source, startIndex, endIndex);
            order(source, startIndex, middle - 1);
            order(source, middle + 1, endIndex);
        }

        return source;
    }

    public static int[] tailRecursiveOrder(int[] source, int startIndex, int endIndex) {
        while(startIndex < endIndex) {
            int middle = partition(source, startIndex, endIndex);
            tailRecursiveOrder(source, startIndex, middle - 1);
            startIndex = middle + 1;
        }

        return source;
    }

    public static int partition(int[] source, int startIndex, int endIndex) {
        int randomIndex = random.nextInt(endIndex - startIndex) + startIndex;
        ArrayUtils.swap(source, startIndex, randomIndex);

        int j = endIndex;
        for (int i = startIndex + 1; i <= j; ) {
            if (source[i] > source[startIndex]) {
                ArrayUtils.swap(source, i, j);
                j--;
            } else {
                i++;
            }
        }

        ArrayUtils.swap(source, startIndex, j);
        return j;
    }
}
