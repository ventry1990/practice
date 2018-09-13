package org.ventry.commons.algorithm;

/**
 * Created by ventry on 16/7/30.
 */
public class CountingSort {

    public static int[] order(int[] source, int bound) {
        int[] countingPool = new int[bound];
        for (int i = 0; i < source.length; i++) {
            countingPool[source[i]]++;
        }
        for (int i = 1; i < bound; i++) {
            countingPool[i] += countingPool[i - 1];
        }

        int[] target = new int[source.length];
        for (int i = source.length - 1; i > -1; i--) {
            target[countingPool[source[i]] - 1] = source[i];
            countingPool[source[i]]--;
        }
        return target;
    }

    public static int statsNumberInRange(int[] source, int bound, int lowerBound, int upperBound) {
        // Assert.that(lowerBound > -1, "lower bound is overflow");
        // Assert.that(lowerBound <= upperBound, "lower bound should smaller than upper bound");
        // Assert.that(upperBound < bound, "upper bound is overflow");

        int[] countingPool = new int[bound];

        for (int i = 0; i < source.length; i++) {
            countingPool[source[i]]++;
        }

        for (int i = 1; i < bound; i++) {
            countingPool[i] += countingPool[i - 1];
        }

        return lowerBound == 0
                ? countingPool[upperBound]
                : countingPool[upperBound] - countingPool[lowerBound - 1];
    }
}
