package org.ventry.commons.leetcode.queue;

/**
 * file: org.ventry.commons.leetcode.queue.RemoveKDigits
 * author: ventry
 * create: 2020/3/20 00:11
 * description:
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        int n;
        if (num == null || (n = num.length()) == 0 || k == 0) return num;

        int tail = 0;
        char[] queue = new char[n];
        queue[tail++] = num.charAt(0);
        int i = 1;
        while (i < n) {
            char c = num.charAt(i);
            while (k > 0 && tail > 0 && queue[tail - 1] > c) {
                tail--;
                k--;
            }
            queue[tail++] = c;
            i++;
        }

        int head = 0;
        while (queue[head] - '0' == 0) {
            head++;
        }

        return tail - head - k < 1 ? "0" : new String(queue, head, tail - head - k);
    }
}
