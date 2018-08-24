package org.ventry.commons.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.string.TextJustification
 * author: ventry
 * create: 2018/8/24 13:44
 * description:
 */

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int rest = maxWidth, count = 0, s = 0, e = 0;
        while (s < words.length && e < words.length) {
            if (rest < words[e].length()) {
                result.add(both(words, s, e - 1, maxWidth - count, maxWidth));
                s = e;
                rest = maxWidth;
                count = 0;
            } else {
                count += words[e].length();
                rest -= (words[e].length() + 1);
                e++;
            }
        }

        if (s != words.length) {
            result.add(left(words, s, maxWidth));
        }
        return result;
    }

    private String both(String[] words, int startInclude, int endInclude, int blanks, int maxWidth) {
        char[] chars = new char[maxWidth];
        int cursor = maxWidth - 1;
        for (int i = endInclude; i > startInclude; i--) {
            String word = words[i];
            for (int j = word.length() - 1; j > -1; j--) {
                chars[cursor--] = word.charAt(j);
            }
            for (int j = blanks / (i - startInclude); j > 0; j--) {
                chars[cursor--] = ' ';
            }
            blanks -= blanks / (i - startInclude);
        }

        int i = 0;
        while (i < words[startInclude].length()) {
            chars[i] = words[startInclude].charAt(i);
            i++;
        }
        while (blanks-- > 0) {
            chars[i++] = ' ';
        }
        return new String(chars);
    }

    private String left(String[] words, int startInclude, int maxWidth) {
        char[] chars = new char[maxWidth];
        int cursor = 0;
        for (int i = startInclude; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                chars[cursor++] = words[i].charAt(j);
            }

            if (cursor < maxWidth) {
                chars[cursor++] = ' ';
            }
        }

        for (int i = cursor; i < maxWidth; i++) {
            chars[cursor++] = ' ';
        }
        return new String(chars);
    }
}
