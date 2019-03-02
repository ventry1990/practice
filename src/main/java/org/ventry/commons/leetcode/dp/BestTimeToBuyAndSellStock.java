package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.BestTimeToBuyAndSellStock
 * author: ventry
 * create: 2019/3/2 23:27
 * description:
 */

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int n;
        if (prices == null || (n = prices.length) == 0)
            return 0;

        int lowest = prices[0];
        int beatProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < lowest) {
                lowest = prices[i];
            }

            beatProfit = Math.max(beatProfit, prices[i] - lowest);
        }
        return beatProfit;
    }
}
