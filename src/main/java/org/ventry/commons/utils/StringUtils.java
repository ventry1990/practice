package org.ventry.commons.utils;

/**
 * file: org.ventry.commons.utils.StringUtils
 * author: ventry
 * create: 2019/3/27 11:38
 * description:
 */

public class StringUtils {

    public static boolean contains(String src, String p) {
        int srcLen = src.length();
        int pLen = p.length();
        int matchedLen = 0;
        int[] prefixFunction = computePrefixFunction(p);
        for (int i = 0; i < srcLen; i++) {
            while (matchedLen > 0 && p.charAt(matchedLen) != src.charAt(i))
                matchedLen = prefixFunction[matchedLen - 1];
            if (p.charAt(matchedLen) == src.charAt(i))
                matchedLen++;
            if (matchedLen == pLen)
                return true;
        }
        return false;
    }

    private static int[] computePrefixFunction(String p) {
        int pLen = p.length();
        int maxSuffixLen = 0;
        int[] res = new int[pLen];
        res[0] = 0;
        for (int i = 1; i < pLen; i++) {
            while (maxSuffixLen > 0 && p.charAt(maxSuffixLen) != p.charAt(i))
                maxSuffixLen = res[maxSuffixLen];
            if (p.charAt(maxSuffixLen) == p.charAt(i))
                maxSuffixLen++;
            res[i] = maxSuffixLen;
        }
        return res;
    }
}
