package org.ventry.commons.leetcode.hash;

/**
 * file: org.ventry.commons.leetcode.hash.JewelsAndStones
 * author: ventry
 * create: 2019/3/30 13:58
 * description:
 */

public class JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        boolean[] map = new boolean[128];
        int types = J.length();
        for (int i = 0; i < types; i++) {
            map[J.charAt(i)] = true;
        }

        int res = 0;
        int num = S.length();
        for (int i = 0; i < num; i++) {
            if (map[S.charAt(i)]) res++;
        }
        return res;
    }
}
