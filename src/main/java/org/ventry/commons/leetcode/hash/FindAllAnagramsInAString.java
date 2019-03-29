package org.ventry.commons.leetcode.hash;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.hash.FindAllAnagramsInAString
 * author: ventry
 * create: 2019/3/29 21:45
 * description:
 */

public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];
        char[] sc = s.toCharArray();
        int n = sc.length;
        int len = p.length();
        int numDiffChar = 0;

        for (char c : p.toCharArray()) {
            if (map[c - 'a']++ == 0) {
                numDiffChar++;
            }
        }
        int start = 0, end = 0;
        int count = 0;
        while (end < n) {
            if (--map[sc[end] - 'a'] == 0) {
                count++;
            }
            end++;

            // if end > p.length(), every time we add a character, we also need
            // to delete a character from the left of substring to make the length
            // of substring equal to p.length(), then we modify the counter if necessary
            if ((end - start) > len && map[sc[start++] - 'a']++ == 0) {
                count--;
            }

            if (count == numDiffChar) {
                list.add(start);
            }
        }
        return list;
    }

    public List<Integer> _findAnagrams(String s, String p) {
        if (s == null || s.length() == 0)
            return Collections.emptyList();

        int len = p.length();
        int[] map = new int[128];
        boolean[] marked = new boolean[128];
        map[0] = len;
        for (int i = 0; i < len; i++) {
            char c = p.charAt(i);
            map[c]++;
            marked[c] = true;
        }

        int n = s.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0, start = 0; i < n; i++) {
            char c = s.charAt(i);
            if (marked[c]) {
                while (start < i && map[c] == 0) {
                    map[s.charAt(start)]++;
                    map[0]++;
                    start++;
                }
                map[c]--;
                map[0]--;
                if (map[0] == 0) {
                    res.add(start);
                    map[s.charAt(start)]++;
                    map[0]++;
                    start++;
                }
            } else {
                for (int j = start; j < i; j++) {
                    map[s.charAt(j)]++;
                }
                map[0] = len;
                start = i + 1;
            }
        }

        return res;
    }
}
