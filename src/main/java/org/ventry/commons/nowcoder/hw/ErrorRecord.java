package org.ventry.commons.nowcoder.hw;

import java.util.*;

/**
 * file: org.ventry.commons.nowcoder.hw.ErrorRecord
 * author: ventry
 * create: 2020/3/13 23:23
 * description:
 */
public class ErrorRecord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Error> records = new ArrayList<>();
        while (scanner.hasNext()) {
            records.add(new Error(scanner.next(), scanner.nextInt()));
        }
        scanner.close();

        Map<Error, Integer> map = new HashMap<>();
        List<Error> errors = new ArrayList<>();
        for (Error error : records) {
            if (map.containsKey(error)) {
                errors.get(map.get(error)).count++;
            } else {
                errors.add(error);
                map.put(error, errors.size() - 1);
            }
        }

        errors.sort((a, b) -> b.count - a.count);

        int end = Math.min(8, errors.size());
        for (int i = 0; i < end; i++) {
            Error error = errors.get(i);
            String name = error.file.length() > 16 ? error.file.substring(error.file.length() - 16)
                    : error.file;
            System.out.println(name + " " + error.line + " " + error.count);
        }
    }

    static class Error {
        private String file;
        private int line;
        private int count;

        private Error(String f, int l) {
            file = f.substring(f.lastIndexOf("\\") + 1);
            line = l;
            count = 1;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Error error = (Error) o;
            return Objects.equals(file, error.file) &&
                    Objects.equals(line, error.line);
        }

        public int hashCode() {
            return Objects.hash(file, line);
        }
    }
}
