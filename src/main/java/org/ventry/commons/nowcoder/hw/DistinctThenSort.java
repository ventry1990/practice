package org.ventry.commons.nowcoder.hw;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.DistinctThenSort
 * author: ventry
 * create: 2020/3/13 18:48
 * description:
 */
public class DistinctThenSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = null;
        int cursor = 0;
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (input == null) {
                input = new int[value];
                cursor = 0;
            } else {
                input[cursor++] = value;
                if (cursor == input.length) {
                    int[] res = hash(input);
                    for (int i = 0; i < res.length; i++) {
                        if (res[i] != 0) {
                            System.out.println(i);
                        }
                    }
                    input = null;
                }
            }
        }
    }

    private static int[] hash(int[] input) {
        int[] map = new int[1000];
        for (int i : input) {
            map[i]++;
        }
        return map;
    }
}
