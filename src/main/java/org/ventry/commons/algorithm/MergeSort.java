package org.ventry.commons.algorithm;

public class MergeSort {

	public static int[] order(int[] source, int start, int end) {
		if (end - start == 1)
			return source;

		int middle = (end + start) / 2;
		order(source, start, middle);
		order(source, middle, end);
		merge(source, start, middle, end-1);
		return source;
	}

	private static void merge(int[] source, int start, int middle, int end) {
		int[] left = new int[middle - start + 1];
		System.arraycopy(source, start, left, 0, middle - start);
		left[left.length - 1] = Integer.MAX_VALUE;

		int[] right = new int[end - middle + 1];
		System.arraycopy(source, middle, right, 0, end - middle);
		right[right.length - 1] = Integer.MAX_VALUE;

		int i = 0, j = 0;
		for (int k = start; k < end; k++) {
			if (left[i] <= right[j]) {
				source[k] = left[i++];
			} else {
				source[k] = right[j++];
			}
		}
	}
}
