package org.ventry.commons.nowcoder.hw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.Poker
 * author: ventry
 * create: 2020/3/13 22:36
 * description:
 */
public class Poker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pair = line.split("-");
            Hand left = Hand.create(pair[0]);
            Hand right = Hand.create(pair[1]);
            Hand winner = compare(left, right);
            System.out.println(winner == null ? "ERROR" : winner.value);
        }
    }

    private static Hand compare(Hand left, Hand right) {
        if (left.type == 4) {
            if (right.type == 4) {
                return left.first < right.first ? right : left;
            } else {
                return left;
            }
        }
        if (right.type == 4) {
            return right;
        }
        return left.type != right.type ? null : (left.first < right.first ? right : left);
    }

    private static final Map<String, Integer> MAP = new HashMap<>(16);

    static {
        for (int i = 3; i <= 10; i++) {
            MAP.put("" + i, i);
        }
        MAP.put("J", 11);
        MAP.put("Q", 12);
        MAP.put("K", 13);
        MAP.put("A", 14);
        MAP.put("2", 15);
        MAP.put("joker", 16);
        MAP.put("JOKER", 17);
    }

    private static class Hand {
        private int type;
        private Integer first;
        private String value;

        private static Hand create(String input) {
            String[] values = input.split(" ");

            Hand hand = new Hand();
            hand.type = values.length;
            hand.first = MAP.get(values[0]);
            hand.value = input;
            if (values[0].equals("joker") && values.length == 2) {
                hand.type = 4;
            }
            return hand;
        }
    }
}
