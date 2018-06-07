package org.ventry.commons.utils;

import java.util.List;

public class Console {
    public static void writeLine(Object... objects) {
        if (objects == null || objects.length == 0) {
            System.out.println();
        } else {
            for (Object object : objects) {
                System.out.println(object);
            }
        }
    }

    public static void write(int num, int... objects) {
        int i = 1;
        for (int object : objects) {
            System.out.print(object + "\t");
            if (i++ % num == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void write(int num, float... objects) {
        int i = 1;
        for (float object : objects) {
            System.out.print(object + "\t");
            if (i++ % num == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static <T> void write(int num, List<T> objects) {
        int i = 1;
        for (T object : objects) {
            System.out.print(object + "\t");
            if (i++ % num == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void write2D(int[][] arrays) {
        for (int[] array : arrays) {
            for (int val : array) {
                System.out.print(String.format("%6d\t", val));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void write2D(double[][] arrays) {
        for (double[] array : arrays) {
            for (double val : array) {
                System.out.print(String.format("%10.4f\t", ((int) (val * 1E5)) / 1E5));
            }
            System.out.println();
        }
        System.out.println();
    }
}