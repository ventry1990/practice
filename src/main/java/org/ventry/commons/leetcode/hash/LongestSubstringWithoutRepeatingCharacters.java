package org.ventry.commons.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.hash.LongestSubstringWithoutRepeatingCharacters
 * author: jojo
 * create: 2017/5/11 20:03
 * description:
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;

        int longestSoFar = 0;
        int longestEndingHere = 0;
        List<Character> set = new ArrayList<>();
        for (char c : s.toCharArray()) {
            int index = set.indexOf(c);
            if (index == -1) {
                longestEndingHere++;
                set.add(c);
                continue;
            }

            if (longestSoFar < longestEndingHere) {
                longestSoFar = longestEndingHere;
            }

            set = new ArrayList<>(set.subList(index + 1, set.size()));
            longestEndingHere = set.size() + 1;
            set.add(c);
        }

        return Math.max(longestSoFar, longestEndingHere);
    }

    public int length2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
