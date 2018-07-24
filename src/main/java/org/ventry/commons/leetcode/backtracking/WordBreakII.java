package org.ventry.commons.leetcode.backtracking;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.backtracking.WordBreakII
 * author: ventry
 * create: 18/7/23 15:05
 * description:
 */

public class WordBreakII {
    private List<String> dict;
    private List<String> sentences;
    private List<String> interim;

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return Collections.emptyList();
        }

        dict = wordDict;
        if (!canBreak(s)) {
            return Collections.emptyList();
        }

        sentences = new ArrayList<>();
        interim = new ArrayList<>();
        resolve(s, 0);
        return sentences;
    }

    private boolean canBreak(String s) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = i; dp[i] && j < n; j++) {
                String word = s.substring(i, j + 1);
                if (dict.contains(word)) {
                    dp[j + 1] = true;
                }
            }
        }
        return dp[n];
    }

    private void resolve(String s, int offset) {
        if (s.length() == offset) {
            if (interim.size() > 0) {
                StringBuilder resultBuilder = new StringBuilder(s.length() + interim.size());
                resultBuilder.append(interim.get(0));
                for (int i = 1; i < interim.size(); i++) {
                    resultBuilder.append(" ").append(interim.get(i));
                }
                sentences.add(resultBuilder.toString());
            }
            return;
        }

        for (int i = offset; i < s.length(); i++) {
            String word = s.substring(offset, i + 1);
            if (dict.contains(word)) {
                interim.add(word);
                resolve(s, i + 1);
                interim.remove(interim.size() - 1);
            }
        }
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int max = 0;
        for (String w : wordDict) max = Math.max(max, w.length());
        boolean[] canBreak = new boolean[s.length() + 1];
        Arrays.fill(canBreak, true);
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), canBreak, set, s, max, 0);
        return res;
    }

    private boolean dfs(List<String> res, StringBuilder sb, boolean[] canBreak,
                        Set<String> set, String s, int max, int lo) {
        if (lo == s.length()) {
            res.add(sb.toString().trim());
            return true;
        }
        boolean breakable = false;
        int rightBound = Math.min(s.length(), lo + max);
        int len = sb.length();
        for (int hi = lo + 1; hi <= rightBound; hi++) {
            if (!canBreak[hi]) continue;
            String temp = s.substring(lo, hi);
            if (set.contains(temp)) {
                sb.append(temp).append(" ");
                breakable |= dfs(res, sb, canBreak, set, s, max, hi);
                sb.setLength(len);
            }
        }
        canBreak[lo] = breakable;
        return breakable;
    }

    public List<String> wordBreak3(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> wordsStartingAtIndex = new HashMap<>();
        return dfs(0, s, wordsStartingAtIndex, dict);
    }

    private List<String> dfs(int startIndex, String s, Map<Integer, List<String>> wordsStartingAtIndex, Set<String> dict) {
        if (startIndex >= s.length()) {
            return new ArrayList<>();
        }
        if (wordsStartingAtIndex.containsKey(startIndex)) {
            return wordsStartingAtIndex.get(startIndex);
        }
        List<String> current = new ArrayList<>();
        for (int i = startIndex; i < s.length(); ++i) {
            String prefix = s.substring(startIndex, i + 1);
            if (dict.contains(prefix)) {
                if (i + 1 >= s.length()) {
                    current.add(prefix);
                } else {
                    List<String> suffix = dfs(i + 1, s, wordsStartingAtIndex, dict);
                    for (String su : suffix) {
                        current.add(prefix + " " + su);
                    }
                }
            }
        }
        wordsStartingAtIndex.put(startIndex, current);
        return current;
    }
}