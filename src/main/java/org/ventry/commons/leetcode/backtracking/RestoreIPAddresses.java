package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.RestoreIPAddresses
 * author: ventry
 * create: 2018/12/24 20:48
 * description:
 */

public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12)
            return res;

        List<StringBuilder> address = new ArrayList<>();
        backtrack(res, address, s, 0);
        return res;
    }

    private void backtrack(List<String> res, List<StringBuilder> address, String s,
                           int idx) {
        if (address.size() == 4) {
            if (idx == s.length()) {
                StringBuilder addressBuilder = new StringBuilder(address.get(0));
                for (int i = 1; i < address.size(); i++) {
                    addressBuilder.append(".").append(address.get(i));
                }
                res.add(addressBuilder.toString());
            }
            return;
        }

        int len = Math.min(s.length() - idx, 3);
        StringBuilder section = new StringBuilder(3);
        for (int i = 0; i < len; i++) {
            section.append(s.charAt(idx + i));
            if (valid(section, section.length())) {
                address.add(section);
                backtrack(res, address, s, idx + i + 1);
                address.remove(address.size() - 1);
            }
        }
    }

    private boolean valid(CharSequence s, int len) {
        if (len == 0) return false;
        if (len == 1) return true;
        char a = s.charAt(0);
        if (len == 2 && a == 48) return false;
        if (len == 3) {
            char b = s.charAt(1);
            char c = s.charAt(2);
            return !(a == 48 || a > 50 || (a == 50 && b > 53) || (a == 50 && b == 53 && c > 53));
        }
        return len < 4;
    }
}
