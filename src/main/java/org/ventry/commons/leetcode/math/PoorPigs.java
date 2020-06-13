package org.ventry.commons.leetcode.math;

/**
 * file: org.ventry.commons.leetcode.math.PoorPigs
 * author: ventry
 * create: 2020/6/14 01:17
 * description:
 */
public class PoorPigs {

    /**
     * https://www.zhihu.com/question/60227816/answer/1274071217
     * & https://www.zhihu.com/question/60227816/answer/1274968885
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = (minutesToDie != 0 ? minutesToTest / minutesToDie : 1) + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }
}
