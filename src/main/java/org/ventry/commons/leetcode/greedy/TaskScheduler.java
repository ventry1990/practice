package org.ventry.commons.leetcode.greedy;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.greedy.TaskScheduler
 * author: ventry
 * create: 2019/5/21 18:13
 * description:
 */

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        int[] map = new int[26];
        for (char task : tasks) {
            map[task - 'A']++;
        }

        int mostFrequent = -1, mostFrequentCount = 1;
        for (int i : map) {
            if (i == mostFrequent) {
                mostFrequentCount++;
            } else if (i > mostFrequent) {
                mostFrequent = i;
                mostFrequentCount = 1;
            }
        }
        int chunk = mostFrequent - 1;
        int idle = (n - (mostFrequentCount - 1)) * chunk;
        int taskArranged = mostFrequent * mostFrequentCount;
        int taskLeft = tasks.length - taskArranged;
        return taskArranged + Math.max(idle, taskLeft);
    }

    /**
     * https://leetcode.com/problems/task-scheduler/discuss/104496/concise-java-solution-on-time-o26-space
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char task : tasks) {
            map[task - 'A']++;
        }
        Arrays.sort(map);
        int i = 25;
        while (i > -1 && map[i] == map[25]) i--;
        return Math.max(tasks.length, (map[25] - 1) * (n + 1) + 25 - i);
    }

    private int _leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        int[] map = new int[27];
        for (char task : tasks) {
            map[task - 'A']++;
            map[26]++;
        }
        Arrays.sort(map);
        int end = 25;
        while (map[end] != 0) end--;

        int res = 0, span = n + 1;
        while (map[26] != 0) {
            int cnt = 0;
            for (int i = 0, j = 25; i < span && j > end && map[26] != 0; i++, j--) {
                cnt++;
                map[j]--;
                map[26]--;
            }
            res += map[26] == 0 ? cnt : span;
        }
        return res;
    }
}
