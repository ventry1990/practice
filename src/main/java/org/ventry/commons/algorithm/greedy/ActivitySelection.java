package org.ventry.commons.algorithm.greedy;

import org.ventry.commons.utils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * file: org.ventry.commons.algorithm.greedy.ActivitySelection
 * author: ventry
 * create: 17/8/18 16:32
 * description:
 */

public class ActivitySelection {
    private Activity[] activities;

    private ActivitySelection(int[] s, int[] e) {
        assert s != null && e != null && s.length == e.length;

        this.activities = new Activity[e.length];
        for (int i = 0; i < e.length; i++) {
            activities[i] = new Activity(s[i], e[i]);
        }
        Arrays.sort(activities);
    }

    private List<Activity> select() {
        List<Activity> selected = new ArrayList<>();
        if (activities.length == 0)
            return selected;

        selected.add(activities[0]);
        for (int i = 1; i < activities.length; i++) {
            if (selected.get(selected.size() - 1).end <= activities[i].start) {
                selected.add(activities[i]);
            }
        }
        return selected;
    }

    static class Activity implements Comparable<Activity> {
        private int start;
        private int end;

        Activity(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Activity o) {
            return end - o.end;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }

    public static void main(String[] args) {
        ActivitySelection selection = new ActivitySelection(
                new int[]{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12},
                new int[]{4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16});
        Console.writeLine(selection.select());
    }
}
