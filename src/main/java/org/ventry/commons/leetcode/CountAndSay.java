package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.CountAndSay
 * author: ventry
 * create: 17/11/17 17:52
 * description:
 */

public class CountAndSay {

    public String countAndSay(int n) {
        if (n < 2)
            return "1";

        String str = countAndSay(n - 1);
        char rem = str.charAt(0);
        int count = 1;
        StringBuilder builder = new StringBuilder(n * 2);
        for (int i = 1; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (cur == rem) {
                count++;
            } else {
                builder.append(count).append(rem);
                rem = cur;
                count = 1;
            }
        }

        builder.append(count).append(rem);

        return builder.toString();
    }
}
