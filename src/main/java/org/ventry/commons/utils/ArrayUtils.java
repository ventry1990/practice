package org.ventry.commons.utils;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

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

    public static double[] randomDoubleArrays(int size) {
        double[] arrays = new double[size];
        for (int i = 0; i < size; i++)
            arrays[i] = i;

        randomize(arrays);
        return arrays;
    }

    public static double[] randomDoubleArrays(int seed, int size) {
        double[] arrays = new double[seed];
        for (int i = 0; i < seed; i++)
            arrays[i] = i;

        randomize(arrays);
        return Arrays.copyOf(arrays, size);
    }

    private static void randomize(double[] arrays) {
        int size = arrays.length;
        for (int i = 0; i < size; i++) {
            double key = arrays[i];
            int index = new Random().nextInt(size - i) + i;
            if (i != index) {
                arrays[i] = arrays[index];
                arrays[index] = key;
            }
        }
    }

    public static void swap(int[] base, int sourceIndex, int targetIndex) {
        if (sourceIndex != targetIndex) {
            int temp = base[sourceIndex];
            base[sourceIndex] = base[targetIndex];
            base[targetIndex] = temp;
        }
    }

    public static <T> void swap(T[] base, int sourceIndex, int targetIndex) {
        if (sourceIndex != targetIndex) {
            T temp = base[sourceIndex];
            base[sourceIndex] = base[targetIndex];
            base[targetIndex] = temp;
        }
    }

    public static int sum(int[] base) {
        return sum(base, 0, base.length);
    }

    public static int sum(int[] base, int startIndex, int endIndex) {
        assert startIndex >= 0;
        assert startIndex < endIndex;
        assert endIndex <= base.length;

        int sum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            sum += base[i];
        }
        return sum;
    }
}
