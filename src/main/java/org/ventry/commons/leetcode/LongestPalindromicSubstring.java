package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.LongestPalindromicSubstring
 * author: ventry
 * create: 17/5/13 17/5/13
 * description:
 */

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        char[] chars = init(s);
        int center = 0, border = 0;
        int[] p = new int[chars.length];
        for (int i = 1; i < chars.length; i++) {
            p[i] = border > i ? Math.min(p[2 * center - i], border - i) : 1;

            while (i - p[i] > -1 && i + p[i] < chars.length
                    && chars[i + p[i]] == chars[i - p[i]])
                p[i]++;

            if (border < i + p[i]) {
                border = i + p[i];
                center = i;
            }
        }

        int longest = 0;
        for (int i = 0; i < p.length; i++) {
            if (longest < p[i]) {
                longest = Math.max(longest, p[i]);
                center = i;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = center - longest + 1; i < center + longest - 1; i++) {
            if (chars[i] != '#')
                builder.append(chars[i]);
        }
        return builder.toString();
    }

    private char[] init(String s) {
        char[] source = s.toCharArray();
        char[] target = new char[(source.length + 1) * 2];

        target[0] = '#';
        for (int i = 0; i < source.length; i++) {
            target[(i + 1) * 2 - 1] = source[i];
            target[(i + 1) * 2] = '#';
        }

        return target;
    }

    public String find2(String s) {
        if (s == null) {
            return "";
        }
        char[] arr = s.toCharArray();
        int max = 0;
        int maxI = 0;
        int maxJ = 0;

        for (int i = 0; i < arr.length; ) {
            int i1 = getFarestSameElementIndex(arr, i);
            int dist = getDistance(arr, i, i1);
            int index1 = i - dist;
            int index2 = i1 + dist;
            int l = index2 - index1;
            if (l > max) {
                max = l;
                maxI = index1;
                maxJ = index2;
            }
            i = i1 + 1;
        }

        return s.substring(maxI, maxJ + 1);
    }

    private int getDistance(char[] arr, int index1, int index2) {
        int i1 = index1 - 1;
        int i2 = index2 + 1;
        int dist = 0;
        while (i1 >= 0 && i2 < arr.length) {
            if (arr[i1] == arr[i2]) {
                dist++;
            } else {
                break;
            }

            i1--;
            i2++;
        }

        return dist;
    }

    private int getFarestSameElementIndex(char[] arr, int index) {
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] != arr[index]) {
                return i - 1;
            }
        }

        return arr.length - 1;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome("12212331"));
        System.out.println(lps.find2("12212321"));
    }
}
