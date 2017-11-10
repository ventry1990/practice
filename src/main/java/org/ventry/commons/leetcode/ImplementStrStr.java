package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.ImplementStrStr
 * author: ventry
 * create: 17/11/10 20:12
 * description:
 */

public class ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;

        int haystackCount = haystack.length();
        int needleCount = needle.length();

        if (needleCount > haystackCount)
            return -1;

        if (needleCount == 0)
            return 0;

        for (int i = 0; i < haystackCount; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) {
                while (++i < haystackCount && haystack.charAt(i) != needle.charAt(0)) ;
            }

            int end = i + needleCount;
            if (end <= haystackCount) {
                int k = 1;
                for (int j = i + 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++) ;

                if (k == needleCount)
                    return i;
            }
        }

        return -1;
    }
}
