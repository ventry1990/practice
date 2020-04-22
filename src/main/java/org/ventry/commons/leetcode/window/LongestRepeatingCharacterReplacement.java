package org.ventry.commons.leetcode.window;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.window.LongestRepeatingCharacterReplacement
 * author: ventry
 * create: 2020/4/22 14:01
 * description:
 */
public class LongestRepeatingCharacterReplacement {

    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
     */
    public int characterReplacement(String s, int k) {
        if (s == null) return 0;

        int[] count = new int[26];
        char[] str = s.toCharArray();
        int maxCount = 0, maxLen = 0;
        for (int left = 0, right = 0; right < str.length; right++) {
            // k is fixed, find char that repeats the most
            maxCount = Math.max(maxCount, ++count[str[right] - 'A']);
            while (right - left + 1 - maxCount > k) {
                count[str[left] - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation/95833
     */
    public int characterReplacement2(String s, int k) {
        int[] count = new int[128];
        int max = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            max = Math.max(max, ++count[s.charAt(end)]);
            if (max + k <= end - start)
                count[s.charAt(start++)]--;
        }
        return s.length() - start;
    }

    public int _characterReplacement(String s, int k) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        if (k > len) return len;

        int max = 0;
        int[] rightBound = new int[26];
        int[] remains = new int[26];
        Arrays.fill(remains, k);
        for (int i = 0; i < len; ) {
            char c = s.charAt(i);
            int hash = c - 'A';
            for (int j = Math.max(i, rightBound[hash]); j < len; rightBound[hash] = ++j) {
                if (c == s.charAt(j)) continue;
                if (remains[hash] > 0) {
                    remains[hash]--;
                } else {
                    break;
                }
            }
            // remains[hash] > 0 means the char before position i can be replaced
            max = Math.max(max, rightBound[hash] - i + remains[hash]);
            // skip the same char
            while (i < len && s.charAt(i) == c) i++;
            // return replacement permits
            for (int j = i; j < rightBound[hash] && c != s.charAt(j); j++) {
                remains[hash]++;
            }
        }
        return Math.min(max, len);
    }
}
