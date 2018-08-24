package org.ventry.commons.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.hash.GroupAnagrams
 * author: ventry
 * create: 2018/8/24 12:27
 * description:
 */

public class GroupAnagrams {
    private static int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
            79, 83, 89, 97, 101};

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Long key = keyOf(str);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    private Long keyOf(String str) {
        Long key = 1L;
        for (int i = 0; i < str.length(); i++) {
            key *= prime[str.charAt(i) - 'a'];
        }
        return key;
    }
}