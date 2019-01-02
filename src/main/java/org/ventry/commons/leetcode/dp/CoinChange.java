package org.ventry.commons.leetcode.dp;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.dp.CoinChange
 * author: ventry
 * create: 2019/1/2 15:59
 * description:
 */

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                int idx = i - coin;
                if (idx > -1 && dp[idx] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[idx] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    private int minCount = Integer.MAX_VALUE;

    public int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        count(amount, coins.length - 1, coins, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void count(int amount, int index, int[] coins, int count) {
        if (index < 0 || count + 2 > minCount) return;
        for (int i = amount / coins[index]; i >= 0; i--) {
            int newAmount = amount - i * coins[index];
            int newCount = count + i;
            if (newAmount > 0 && newCount + 1 < minCount)
                count(newAmount, index - 1, coins, newCount);
            else {
                if (newAmount == 0 && newCount < minCount)
                    minCount = newCount;
                break;
            }
        }
    }
}
