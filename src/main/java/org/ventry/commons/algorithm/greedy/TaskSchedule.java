package org.ventry.commons.algorithm.greedy;

import org.ventry.commons.utils.Console;

import java.util.*;

/**
 * file: org.ventry.commons.algorithm.greedy.TaskSchedule
 * author: ventry
 * create: 17/8/31 20:19
 * description:
 */

public class TaskSchedule {
    private Set<Task> tasks;// 按惩罚递降排序
    private List<Task> earlyTasks;// 惩罚最大化的提前任务集合
    private List<Task> lateTasks;// 延迟任务集合
    private int[] counts;// 计数器

    private TaskSchedule(int[] d, int[] w) {
        assert d != null;
        assert w != null;
        assert d.length == w.length;

        tasks = new TreeSet<>();
        earlyTasks = new ArrayList<>(d.length);
        lateTasks = new ArrayList<>(d.length);
        int maxDeadline = -1;
        for (int i = 0; i < d.length; i++) {
            tasks.add(new Task("task" + (i + 1), d[i], w[i]));
            maxDeadline = Math.max(maxDeadline, d[i]);
        }
        counts = new int[maxDeadline];
    }

    private void schedule() {
        for (Task task : tasks) {
            counts[task.deadline - 1] += 1;
            if (checkIndependent()) {
                earlyTasks.add(task);
            } else {
                lateTasks.add(task);
                counts[task.deadline - 1] -= 1;
            }
        }

        Collections.sort(earlyTasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.deadline - o2.deadline;
            }
        });
        Console.writeLine("early: " + earlyTasks);
        Console.writeLine("late: " + lateTasks);
    }

    private boolean checkIndependent() {
        int sum = 0;
        for (int i = 0; i < counts.length; i++) {
            sum += counts[i];
            if (sum > i + 1)
                return false;
        }

        return true;
    }

    private static class Task implements Comparable<Task> {
        String name;
        int deadline;
        int weight;

        private Task(String n, int d, int w) {
            this.name = n;
            this.deadline = d;
            this.weight = w;
        }

        @Override
        public int compareTo(Task o) {
            return o.weight - this.weight;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        TaskSchedule taskSchedule = new TaskSchedule(
                new int[]{4, 2, 4, 3, 1, 4, 6},
                new int[]{7, 6, 5, 4, 3, 2, 1}
        );
        taskSchedule.schedule();

        taskSchedule = new TaskSchedule(
                new int[]{4, 2, 4, 3, 1, 4, 6},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        );
        taskSchedule.schedule();

        taskSchedule = new TaskSchedule(
                new int[]{4, 2, 4, 2, 1, 2, 6},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        );
        taskSchedule.schedule();
    }
}
