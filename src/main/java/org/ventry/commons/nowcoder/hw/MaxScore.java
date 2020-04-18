package org.ventry.commons.nowcoder.hw;

import org.ventry.commons.algorithm.tree.MaxSegmentTree;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.MaxScore
 * author: ventry
 * create: 2020/3/13 22:21
 * description:
 */
public class MaxScore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] scores = new int[n];
            for (int i = 0; i < n; i++) {
                scores[i] = scanner.nextInt();
            }
            MaxSegmentTree tree = new MaxSegmentTree(scores);
            for (int i = 0; i < m; i++) {
                String cmd = scanner.next();
                if (cmd.equals("Q")) {
                    System.out.println(tree.query(scanner.nextInt(), scanner.nextInt()));
                }
                if (cmd.equals("U")) {
                    tree.update(scanner.nextInt(), scanner.nextInt());
                }
            }
        }
    }
}
