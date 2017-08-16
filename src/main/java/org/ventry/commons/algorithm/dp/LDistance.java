package org.ventry.commons.algorithm.dp;

import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.dp.LDistance
 * author: ventry
 * create: 17/8/16 21:53
 * description:
 */

public class LDistance {
    private static float similarity(String str1, String str2) {
        int ld = computeEditDistance(str1, str2);
        return 1 - (float) ld / Math.max(str1.length(), str2.length());
    }

    private static int computeEditDistance(String str1, String str2) {
        int distance[][];    //矩阵
        int lengthOfStr1 = str1.length();
        int lengthOfStr2 = str2.length();
        if (lengthOfStr1 == 0) {
            return lengthOfStr2;
        }
        if (lengthOfStr2 == 0) {
            return lengthOfStr1;
        }
        distance = new int[lengthOfStr1 + 1][lengthOfStr2 + 1];
        for (int i = 0; i <= lengthOfStr1; i++) {    //初始化第一列
            distance[i][0] = i;
        }
        for (int j = 0; j <= lengthOfStr2; j++) {    //初始化第一行
            distance[0][j] = j;
        }

        int temp;//记录相同字符,在某个矩阵位置值的增量,不是0就是1
        for (int i = 1; i <= lengthOfStr1; i++) {//遍历str1
            char ch1 = str1.charAt(i - 1);

            //去匹配str2
            for (int j = 1; j <= lengthOfStr2; j++) {
                char ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //左边+1, 上边+1, 左上角+temp取最小
                distance[i][j] = min(distance[i - 1][j] + 1, distance[i][j - 1] + 1, distance[i - 1][j - 1] + temp);
            }
        }
        return distance[lengthOfStr1][lengthOfStr2];
    }

    private static int min(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    public static void main(String[] args) {
        String x = "abcde";
        String y = "acef";
        Console.writeLine(String.format("distance of %s and %s: %d", x, y, LDistance.computeEditDistance(x, y)));
        Console.writeLine(String.format("similarity of %s and %s: %f", x, y, LDistance.similarity(x, y)));
    }
}
