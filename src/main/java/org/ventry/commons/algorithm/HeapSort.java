package org.ventry.commons.algorithm;

import org.ventry.commons.utils.ArrayUtils;

public class HeapSort {
	private static void maxHeapify(int[] source, int i, int size) {
		while (i <= size / 2) {
			int left = 2 * (i + 1) - 1;
			int right = 2 * (i + 1);
			int max = i;
			if (left < size && source[left] > source[max])
				max = left;
			if (right < size && source[right] > source[max])
				max = right;

			if (max != i) {
				ArrayUtils.swap(source, i, max);
				i = max;
			} else {
				break;
			}
		}
	}

	/**
	 * 递归的性能和非递归的相近，是否java对递归方法调用有优化？
	 * @param source
	 * @param i
	 */
    private static void maxHeapifyRecursively(int[] source, int i, int size) {
        int left = 2 * (i + 1) - 1;
        int right = 2 * (i + 1);
        int max = i;
        if (left < size && source[left] > source[max])
            max = left;
        if (right < size && source[right] > source[max])
            max = right;

        if (max != i) {
            ArrayUtils.swap(source, i, max);
            maxHeapifyRecursively(source, max, size);
        }
    }

	private static void buildMaxHeap(int[] source) {
		for (int i = source.length / 2; i > -1; i--) {
			maxHeapify(source, i, source.length);
		}
	}

	public static int[] order(int source[]) {
		buildMaxHeap(source);
		int heapSize = source.length;
		for (int i = heapSize - 1; i > 0; i--) {
			ArrayUtils.swap(source, 0, i);
			maxHeapify(source, 0, --heapSize);
		}

		return source;
	}
}
