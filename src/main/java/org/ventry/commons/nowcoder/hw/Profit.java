package org.ventry.commons.nowcoder.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.Profit
 * author: ventry
 * create: 2020/3/18 23:08
 * description:
 */
public class Profit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String[] cost = scanner.nextLine().split(",");
            String[] profit = scanner.nextLine().split(",");
            int principal = Integer.parseInt(scanner.nextLine());
            int size = cost.length;//assert cost.length == profit.length
            List<Pair> pairs = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                pairs.add(new Pair(Integer.parseInt(cost[i]), Integer.parseInt(profit[i])));
            }
            pairs.sort((a, b) -> {
                if (a.c == b.c) {
                    return b.p - a.p;
                } else return a.c - b.c;
            });
            for (Pair pair : pairs) {
                if (pair.p < pair.c) continue;
                if (pair.c > principal) break;
                principal += pair.p - pair.c;
            }
            System.out.println(principal);
        }
    }

    static class Pair {
        private int c;
        private int p;

        public Pair(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
