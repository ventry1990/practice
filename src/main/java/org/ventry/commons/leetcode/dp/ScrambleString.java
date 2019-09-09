package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.ScrambleString
 * author: ventry
 * create: 2019-09-03 17:29
 * description:
 */
public class ScrambleString {
    private static final int[] PRIME_NUMS = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
            31, 37, 41, 43, 47, 53, 59, 61, 67,
            71, 73, 79, 83, 89, 97, 101};

    public boolean isScramble(String s1, String s2) {
        int[][] code1 = getStringCode(s1);
        int[][] code2 = getStringCode(s2);
        return isScrambleRegion(s1, code1, 0, s1.length(), s2, code2, 0, s2.length());
    }

    /**
     * Translate each character in a string ('a'-'z') to 26 prime numbers and
     * use product of translated numbers as a code. If two codes are identical,
     * then we know two strings are composed with the same set of characters
     */
    private static int[][] getStringCode(String s) {
        // strCode[i][j] presents the identical code for substring of s from i to j
        int[][] strCode = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j < s.length(); ++j) {
                strCode[i][j] = PRIME_NUMS[s.charAt(j) - 'a'];
                if (j > i)
                    strCode[i][j] *= strCode[i][j - 1];
            }
        }
        return strCode;
    }

    private boolean isScrambleRegion(String s1, int[][] code1, int i1, int j1,
                                     String s2, int[][] code2, int i2, int j2) {
        if (j1 - i1 != j2 - i2) return false;
        // Find if two string are formed with the identical character set.
        if (code1[i1][j1 - 1] != code2[i2][j2 - 1]) return false;
        if (compare(s1, i1, j1, s2, i2, j2)) return true;
        for (int k = 1; k < j1 - i1; ++k)
            if (isScrambleRegion(s1, code1, i1, i1 + k, s2, code2, i2, i2 + k) &&
                    isScrambleRegion(s1, code1, i1 + k, j1, s2, code2, i2 + k, j2)
                    || isScrambleRegion(s1, code1, i1, i1 + k, s2, code2, j2 - k, j2) &&
                    isScrambleRegion(s1, code1, i1 + k, j1, s2, code2, i2, j2 - k))
                return true;
        return false;
    }

    private static boolean compare(String s1, int i1, int j1, String s2, int i2, int j2) {
        if (j1 - i1 != j2 - i2)
            return false;
        for (int i = 0; i < j1 - i1; ++i) {
            if (s1.charAt(i1 + i) != s2.charAt(i2 + i))
                return false;
        }
        return true;
    }

    public boolean _isScramble(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int len = s1.length();
        boolean[][][] dp = new boolean[len + 1][len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int l = 2; l <= len; l++) {
            for (int i = 0; i <= len - l; i++) {
                for (int j = 0; j <= len - l; j++) {
                    for (int c = 1; c < l; c++) {
                        if ((dp[c][i][j] && dp[l - c][i + c][j + c])
                                || (dp[c][i][j + l - c] && dp[l - c][i + c][j]))
                            dp[l][i][j] = true;
                    }
                }
            }
        }
        return dp[len][0][0];
    }
}
