package org.ventry.commons.algorithm.divide;

import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.divide.NumeralConverter
 * author: ventry
 * create: 18/4/19 20:56
 * description: 不识别〇（零），大写数字等
 */

public class NumeralConverter {

    private static final char[] CN_NUMERALS = new char[]{'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    private enum Unit {
        TEN('十', 10), HUNDRED('百', 100), THOUSAND('千', 1000),
        TEN_THOUSAND('万', 10000), HUNDRED_MILLION('亿', 10000 * 10000);

        private char unit;
        private int times;

        Unit(char unit, int times) {
            this.unit = unit;
            this.times = times;
        }
    }

    private static long convert(char[] chars, int startInclude, int endExclude) {
        Unit[] units = Unit.values();
        for (int i = units.length - 1; i > -1; i--) {
            Unit unit = units[i];
            int index = indexOf(chars, unit.unit, startInclude, endExclude);
            if (index > -1) {
                long left = convert(chars, startInclude, index);
                long right = convert(chars, index + 1, endExclude);
                return Math.max(left, 1) * unit.times + right;
            }
        }

        long sum = 0L;
        for (int i = startInclude; i < endExclude; i++) {
            sum = sum * 10 + indexOf(CN_NUMERALS, chars[i], 0, CN_NUMERALS.length);
        }
        return sum;
    }

    private static int indexOf(char[] chars, char tar, int startInclude, int endExclude) {
        final int max = Math.min(chars.length, endExclude);
        if (startInclude < 0) {
            startInclude = 0;
        } else if (startInclude > max) {
            return -1;
        }

        for (int i = startInclude; i < max; i++) {
            if (chars[i] == tar) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] source = new char[][]{"二千零二万七千六百五十四".toCharArray(),
                "二零一八".toCharArray(),
                "二千万二一八".toCharArray(),
                "九".toCharArray(),
                "十一".toCharArray(),
                "二十".toCharArray(),
                "三百".toCharArray(),
                "五千".toCharArray(),
                "六万".toCharArray(),
                "五百三十亿零五万三".toCharArray()
        };
        for (char[] chars : source) {
            Console.writeLine(new String(chars) + " = " + convert(chars, 0, chars.length));
        }
    }
}
