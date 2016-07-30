package org.ventry.commons.algorithm;

import org.ventry.commons.utils.ArrayUtils;
import org.ventry.commons.utils.Console;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int arraySize = 10000000;
        int[] arrays = ArrayUtils.randomIntArrays(arraySize);
		// Console.write(20, arrays);

        int iterator = 1;
        long start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            // InsertionSort.order(Arrays.copyOf(arrays, arrays.length));
        }
        Console.writeLine("insertion sort cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        // array.size in [500, *), merge-sort faster than insertion-sort
        start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            MergeSort.order(Arrays.copyOf(arrays, arrays.length), 0, arrays.length);
        }
        Console.writeLine("merge sort cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        // array.size in (0, 500) heap-sort's performance approaches to insertion-sort
        // array.size in [500, 10000) heap-sort's performance is far better than two former
        // array.size in [10000, *) heap-sort's performance is worse than merge-sort
        start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            HeapSort.order(Arrays.copyOf(arrays, arrays.length));
        }
        Console.writeLine("heap sort cost:" + (System.nanoTime() - start) / 1000000 + "ms");

        // array.size in [300, *) heap-sort's performance approaches to insertion-sort, worse than heap sort
        start = System.nanoTime();
        for (int i = 0; i < iterator; i++) {
            QuickSort.tailRecursiveOrder(Arrays.copyOf(arrays, arrays.length), 0, arrays.length - 1);
        }
        Console.writeLine("quick sort cost:" + (System.nanoTime() - start) / 1000000 + "ms");
    }
}
