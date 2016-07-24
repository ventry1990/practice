package org.ventry.commons.algorithm;

public class SelectionSort {
	public static int[] order(int[] source) {
		for (int i = 0; i < source.length; i++) {
			int index = i;
			for (int j = i + 1; j < source.length; j++) {
				if (source[index] > source[j]) {
					index = j;
				}
			}

			if (index != i) {
				int key = source[i];
				source[i] = source[index];
				source[index] = key;
			}
		}
		return source;
	}
}
