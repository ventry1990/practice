package org.ventry.commons.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.string.MinimumWindowSubstring
 * author: ventry
 * create: 2018/11/2 18:59
 * description:
 */

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int len;
        if (t.length() == 0 || (len = s.length()) == 0 || t.length() > len)
            return "";

        Map<Character, Integer> dict = init(t);
        Map<Character, Integer> cached = new HashMap<>();
        boolean expand = true;
        int[] currentWindow = new int[]{0, Integer.MAX_VALUE};
        int[] bestWindow = new int[]{0, Integer.MAX_VALUE};
        for (int i = 0, j = 0; (!expand && i < len) || (expand && j < len); ) {
            if (expand) {
                increase(cached, s.charAt(j));
                j++;
                if (containsAll(cached, dict)) {
                    currentWindow[0] = i;
                    currentWindow[1] = j;
                    compareThenReplace(currentWindow, bestWindow);
                    expand = false;
                }
            } else {
                decrease(cached, s.charAt(i));
                i++;
                if (containsAll(cached, dict)) {
                    currentWindow[0] = i;
                    compareThenReplace(currentWindow, bestWindow);
                } else {
                    expand = true;
                }
            }
        }

        return bestWindow[1] == Integer.MAX_VALUE ? "" : s.substring(bestWindow[0], bestWindow[1]);
    }

    private Map<Character, Integer> init(String t) {
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            increase(dict, t.charAt(i));
        }
        return dict;
    }

    private void increase(Map<Character, Integer> map, Character key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    private void decrease(Map<Character, Integer> map, Character key) {
        map.put(key, map.get(key) - 1);
    }

    private boolean containsAll(Map<Character, Integer> s, Map<Character, Integer> t) {
        for (Map.Entry<Character, Integer> entry : t.entrySet()) {
            Character key = entry.getKey();
            if (!s.containsKey(key) || s.get(key) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    private void compareThenReplace(int[] cur, int[] best) {
        if (cur[1] - cur[0] < best[1] - best[0]) {
            best[0] = cur[0];
            best[1] = cur[1];
        }
    }

    public String minWindow2(String s, String t) {
        if (s == null || t == null)
            return "";

        char[] cs = s.toCharArray();
        int sLen = cs.length;
        char[] ts = t.toCharArray();
        int tLen = ts.length;
        if (sLen == 0 || tLen == 0)
            return "";

        int[] map = new int[256];
        for (int i = 0; i < tLen; i++) {
            map[ts[i]]++;
        }
        boolean[] set = new boolean[256];
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                set[i] = true;
            }
        }

        int left = 0, right = 0, need = tLen;
        int min = Integer.MAX_VALUE;
        String ans = null;
        while (right < sLen) {
            char cur = cs[right];
            if (set[cur]) {
                map[cur]--;
                if (map[cur] >= 0)
                    need--;
                if (need == 0) {
                    while (need == 0) {
                        char cur2 = cs[left];
                        if (set[cur2])
                            map[cur2]++;
                        if (map[cur2] > 0)
                            need++;
                        left++;
                    }
                    if (right - left + 2 < min) {
                        min = right - left + 2;
                        ans = String.valueOf(cs, left - 1, min);
                    }
                }
            }
            right++;
        }
        if (min == Integer.MAX_VALUE)
            return "";
        return ans;
    }
}
