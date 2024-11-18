package com.company.andreas.j_ten;

import java.io.FileReader;
import java.util.*;

public class First {
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

        Map<Character, Integer> illegalChars = new HashMap<>();
        for (char c : new char[]{')', '}', ']', '>'}) {
            illegalChars.put(c, 0);
        }

        for (String s : input) {
            LinkedList<Character> queue = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (OPENING_CHARS.contains(c)) {
                    queue.add(c);
                } else if (queue.get(queue.size() - 1).equals(MAPPER.get(c))) {
                    queue.pollLast();
                } else {
                    illegalChars.compute(c, (k, v) -> v += 1);
                    break;
                }
            }
        }

        System.out.println((illegalChars.get(')') * 3) +
                (illegalChars.get(']') * 57) +
                (illegalChars.get('}') * 1197) +
                (illegalChars.get('>') * 25137));
    }
}
