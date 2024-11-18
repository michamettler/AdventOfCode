package com.company.andreas.j_ten;

import java.io.FileReader;
import java.util.*;

public class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> input = new ArrayList<>();
        while (in.hasNext()) {
            input.add(in.next());
        }
        in.close();

        final Map<Character, Character> MAPPER = Map.ofEntries(
                Map.entry('>', '<'),
                Map.entry(']', '['),
                Map.entry('}', '{'),
                Map.entry(')', '(')
        );
        final List<Character> OPENING_CHARS = List.of('<', '[', '{', '(');

        Map<Character, Integer> POINTS_MAP = new HashMap<>();
        char[] cArr = new char[]{'(', '[', '{', '<'};
        for (int i = 0; i < 4; i++) {
            POINTS_MAP.put(cArr[i], i + 1);
        }

        List<Long> results = new ArrayList<>();

        outer: for (String s : input) {
            LinkedList<Character> queue = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (OPENING_CHARS.contains(c)) {
                    queue.add(c);
                } else if (queue.get(queue.size() - 1).equals(MAPPER.get(c))) {
                    queue.pollLast();
                } else {
                    continue outer;
                }
            }
            long t = 0;
            while (!queue.isEmpty()) {
                t *= 5;
                t += POINTS_MAP.get(queue.pollLast());
            }
            results.add(t);
        }

        results.sort(Long::compareTo);
        System.out.println(results.get(results.size() / 2));
    }
}
