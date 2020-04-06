package org.ventry.commons.leetcode.stack;

/**
 * file: org.ventry.commons.leetcode.stack.ValidStackSequences
 * author: ventry
 * create: 2020/4/5 15:04
 * description:
 */
public class ValidStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;

        int poppedCursor = 0;
        for (int i = 0; i < pushed.length; i++) {
            pushed[i - poppedCursor] = pushed[i];

            while (i - poppedCursor > -1
                    && pushed[i - poppedCursor] == popped[poppedCursor]) {
                poppedCursor++;
            }
        }

        return poppedCursor == popped.length;
    }
}
