package org.ventry.commons.leetcode.dfs;

import java.util.List;

/**
 * file: org.ventry.commons.leetcode.dfs.ShoppingOffers
 * author: ventry
 * create: 2018/8/23 18:07
 * description:
 */

public class ShoppingOffers {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int cost = 0;
        for (int i = 0; i < needs.size(); i++) {
            cost += price.get(i) * needs.get(i);
        }

        for (List<Integer> offer : special) {
            boolean valid = true;
            int n = offer.size() - 1;
            for (int i = 0; i < n; i++) {
                if (needs.get(i) - offer.get(i) < 0) {
                    valid = false;
                }
                needs.set(i, needs.get(i) - offer.get(i));
            }

            if (valid) {
                cost = Math.min(cost, shoppingOffers(price, special, needs) + offer.get(n));
            }

            for (int i = 0; i < n; i++) {
                needs.set(i, needs.get(i) + offer.get(i));
            }
        }

        return cost;
    }

    public int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int[] n = new int[needs.size()];
        for (int i = 0; i < needs.size(); i++)
            n[i] = needs.get(i);
        return find(price, special, n, 0);
    }

    private int find(List<Integer> price, List<List<Integer>> special, int[] needs, int index) {
        if (index == special.size()) {
            int p = 0;
            for (int i = 0; i < needs.length; i++)
                p += price.get(i) * needs[i];
            return p;
        } else {
            int p1 = find(price, special, needs, index + 1);
            List<Integer> cs = special.get(index);
            if (isEligible(cs, needs)) {
                for (int i = 0; i < needs.length; i++)
                    needs[i] -= cs.get(i);

                int p2 = find(price, special, needs, index) + cs.get(cs.size() - 1);

                for (int i = 0; i < needs.length; i++)
                    needs[i] += cs.get(i);

                return Math.min(p1, p2);
            }
            return p1;
        }
    }

    private boolean isEligible(List<Integer> s, int[] n) {
        for (int i = 0; i < n.length; i++)
            if (n[i] < s.get(i)) return false;
        return true;
    }
}