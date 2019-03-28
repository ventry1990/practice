package org.ventry.commons.leetcode.hash;

/**
 * file: org.ventry.commons.leetcode.hash.ValidAnagram
 * author: ventry
 * create: 2019/3/28 14:18
 * description:
 */

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        int[] map = new int[128];
        for (char temp : s.toCharArray()) map[temp] += 1;
        for (char temp : t.toCharArray()) map[temp] -= 1;
        for (int i : map)
            if (i != 0) return false;
        return true;
    }

    public boolean _isAnagram(String s, String t) {
        int[] sMap = new int[256];
        for (int i = 0; i < s.length(); i++) {
            int idx = (int) s.charAt(i);
            sMap[idx]++;
        }
        int[] tMap = new int[256];
        for (int i = 0; i < t.length(); i++) {
            int idx = (int) t.charAt(i);
            tMap[idx]++;
        }
        for (int i = 0; i < 256; i++) {
            if (sMap[i] != tMap[i]) return false;
        }
        return true;
    }
}
