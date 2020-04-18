package org.ventry.commons.nowcoder.hw;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.CharSet
 * author: ventry
 * create: 2020/3/16 20:12
 * description:
 */
public class CharSet {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            int[] lower = new int[26];
            int[] upper = new int[26];
            StringBuilder result = new StringBuilder(52);
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    lower[c - 'a']++;
                    if (lower[c - 'a'] == 1) {
                        result.append(c);
                    }
                } else {
                    upper[c - 'A']++;
                    if (upper[c - 'A'] == 1) {
                        result.append(c);
                    }
                }
            }


            System.out.println(result.toString());
        }
    }
}
