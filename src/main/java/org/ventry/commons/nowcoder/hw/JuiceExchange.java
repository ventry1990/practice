package org.ventry.commons.nowcoder.hw;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.JuiceExchange
 * author: ventry
 * create: 2020/3/13 18:28
 * description:
 */
public class JuiceExchange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (value == 0)
                break;

            System.out.println(calc(value));
        }
    }

    private static int calc(int init) {
        int result = 0;

        while (init > 2) {
            result += init / 3;
            init = init % 3 + init / 3;
        }

        return init == 2 ? result + 1 : result;
    }
}
