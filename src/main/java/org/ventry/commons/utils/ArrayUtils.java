package org.ventry.commons.utils;

import java.util.Random;

public class ArrayUtils {
	public static int[] concat(int[] base, int... append) {
		if (append == null || append.length == 0)
			return base;

		int[] target = new int[base.length + append.length];
		System.arraycopy(base, 0, target, 0, base.length);
        System.arraycopy(append, 0, target, base.length, append.length);

		return target;
	}
	
	public static int[] randomIntArrays(int size) {
		int[] arrays = new int[size];
		for (int i = 0; i < size; i++)
			arrays[i] = i;
		for (int i = 0; i < size; i++) {
			int key = arrays[i];
			int index = new Random().nextInt(size - i) + i;
			if (i != index) {
				arrays[i] = arrays[index];
				arrays[index] = key;
			}
		}

		return arrays;
	}

	public static void swap(int[] base, int sourceIndex, int targetIndex) {
		int temp = base[sourceIndex];
		base[sourceIndex] = base[targetIndex];
		base[targetIndex] = temp;
	}
}
