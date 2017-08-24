package org.ventry.commons.algorithm.greedy;

import org.apache.commons.lang3.StringUtils;
import org.ventry.commons.utils.Console;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * file: org.ventry.commons.algorithm.greedy.Haffman
 * author: ventry
 * create: 17/8/24 15:28
 * description:
 */

public class Haffman {
    private Queue<Data> dataQueue;

    private Haffman(char[] chars, int[] frequencies) {
        assert chars != null && frequencies != null && chars.length == frequencies.length;

        int size = chars.length;
        dataQueue = new PriorityQueue<>(size);
        for (int i = 0; i < size; i++) {
            dataQueue.add(new Data(chars[i], frequencies[i]));
        }
    }

    private void build() {
        while (dataQueue.size() > 1) {
            Data one = new Data();
            one.left = dataQueue.poll();
            one.right = dataQueue.poll();
            one.freq = one.left.freq + one.right.freq;
            dataQueue.add(one);
        }
    }

    private void show() {
        Data data = dataQueue.peek();
        Console.writeLine(data);
    }

    private static class Data implements Comparable<Data> {
        private char value;
        private int freq;
        private Data left;
        private Data right;

        private Data() {
            this(' ', -1);
        }

        private Data(char value, int freq) {
            this.value = value;
            this.freq = freq;
        }

        public int compareTo(Data o) {
            return this.freq - o.freq;
        }

        @Override
        public String toString() {
            return print(0);
        }

        private String print(int l) {
            return "\n" +
                    StringUtils.repeat("\t", l) +
                    "Data@{value=" + value +
                    ",freq=" + freq +
                    ",left=" + (left == null ? "#" : left.print(l + 1)) +
                    ",right=" + (right == null ? "#" : right.print(l + 1)) +
                    "}\n" +
                    StringUtils.repeat("\b", l) +
                    StringUtils.repeat("\t", l - 1);
        }
    }

    public static void main(String[] args) {
        Haffman haffman = new Haffman(
                new char[]{'a', 'b', 'c', 'd', 'e', 'f'},
                new int[]{45, 13, 12, 16, 9, 5}
        );
        haffman.build();
        haffman.show();
    }
}
