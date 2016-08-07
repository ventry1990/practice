package org.ventry.commons.algorithm;

/**
 * Created by ventry on 16/8/8.
 */
public class OrderStatistics {
    public static int select(int[] source, int startIndex, int endIndex, int seq) {
        //Assert.isTrue(source.length > 0 && args valid)
        int i, j;
        for (i = startIndex, j = endIndex; i < j; ) {
            int middle = QuickSort.partition(source, i, j);
            if (middle == seq) {
                return source[middle];
            } else if (middle > seq) {
                j = middle - 1;
            } else {
                i = middle + 1;
            }
        }

        return source[i];
    }
}
