package org.ventry.commons.algorithm;

public class InsertionSort {

	public static int[] order(int[] source) {
		for (int i = 1; i < source.length; i++) {
			int key = source[i];

			int j = i - 1;
			while (j > -1 && source[j] > key) {
				source[j + 1] = source[j];
				j--;
			}
			source[j + 1] = key;
		}
		return source;
	}
	
	public static int[] orderDescending(int[] source) {
		for (int i = 1; i < source.length; i++) {
			int key = source[i];

			int j = i - 1;
			while (j > -1 && source[j] < key) {
				source[j + 1] = source[j];
				j--;
			}
			source[j + 1] = key;
		}
		return source;
	}

	/**
	 * array.size位于[10000, *)区间内，二分查找插入排序优于原始插入排序
	 * @param source
	 * @return
	 */
	public static int[] orderBybinarySearch(int[] source) {
		for (int i = 1; i < source.length; i++) {
			int key = source[i];

			int index = binarySearchIndex(source, i, key);
			if(i != index) {
				int j = i - 1;
				while (j >= index) {
					source[j + 1] = source[j];
					j--;
				}
				source[j + 1] = key;
			}
		}
		return source;
	}
	
	private static int binarySearchIndex(int[] array, int end, int key) {
		int low = 0;
		int high = end;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (key < array[mid]) {
				high = mid - 1;
			} else if (key > array[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}

		return low;
	}
}
