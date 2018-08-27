package org.ventry.commons.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.math.GrayCode
 * author: ventry
 * create: 2018/8/27 19:27
 * description:
 */

public class GrayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);

        if (n <= 0) {
            return result;
        }

        int l = (int) Math.pow(2, n);
        int[] bits = new int[n];
        for (int i = 1; i < l; i++) {
            for (int j = n - 1; j > -1; j--) {
                int original = bits[j];
                bits[j] = bits[j] ^ 1;
                if ((original & 1) == 0) break;
            }

            int code = bits[0] * (int) Math.pow(2, n - 1);
            for (int j = 1; j < n; j++) {
                code = code + (bits[j - 1] ^ bits[j]) * (int) Math.pow(2, n - j - 1);
            }
            result.add(code);
        }

        return result;
    }

    public List<Integer> grayCode2(int n) {
        if (n == 0) return Collections.singletonList(0);
        if (n == 1) return Arrays.asList(0, 1);
        List<Integer> results = new ArrayList<>();
        results.add(0);
        int totalNum = 1;
        int k = 0;
        while (k < n) {
            totalNum <<= 1;
            k++;
        }
        for (int i = 1; i < totalNum; i++) {
            int ans = i ^ (i >> 1);
            results.add(ans);

        }
        return results;
    }
}
