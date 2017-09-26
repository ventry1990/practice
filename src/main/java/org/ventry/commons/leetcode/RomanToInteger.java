package org.ventry.commons.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.RomanToInteger
 * author: ventry
 * create: 17/9/26 22:08
 * description:
 */

public class RomanToInteger {

    private static final Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    public int romanToInt(String s) {
        int result = 0;
        if (s == null || s.length() == 0)
            return result;

        // assert map.containsKey(s.charAt([0, s.length())));
        int sLength = s.length();
        int pre = map.get(s.charAt(0));
        result = pre;
        for (int i = 1; i < sLength; i++) {
            int cur = map.get(s.charAt(i));
            if (pre >= cur) {
                result += cur;
            } else {
                result += cur - 2 * pre;
            }
            pre = cur;
        }
        return result;
    }

    public int romanToInt2(String s) {
        int[] num = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'I') num[i] = 1;
            if(s.charAt(i) == 'V') num[i] = 5;
            if(s.charAt(i) == 'X') num[i] = 10;
            if(s.charAt(i) == 'L') num[i] = 50;
            if(s.charAt(i) == 'C') num[i] = 100;
            if(s.charAt(i) == 'D') num[i] = 500;
            if(s.charAt(i) == 'M') num[i] = 1000;
        }
        int sum = 0;
        for(int i = 0; i < s.length() - 1; i++){
            if(num[i] < num[i + 1]){
                sum -= num[i];
            }
            else{
                sum += num[i];
            }
        }
        return sum + num[num.length - 1];
    }
}
