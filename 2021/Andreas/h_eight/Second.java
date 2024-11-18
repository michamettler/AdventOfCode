package com.company.andreas.h_eight;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<Line> allLines = new ArrayList<>();
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] lineSplit = line.split("\\|");
            Line l = new Line();
            l.evaulationNumbers.addAll(Arrays.asList(lineSplit[0].split(" ")));
            l.solutionNumbers.addAll(Arrays.asList(lineSplit[1].split(" ")));
            allLines.add(l);
        }
        in.close();

        final Map<Integer, Integer> UNIQUE_LENGTHS = Map.ofEntries(
                Map.entry(2, 1),
                Map.entry(3, 7),
                Map.entry(4, 4),
                Map.entry(7, 8)
        );

        for (Line l : allLines) {
            // Number 1, 4, 7, 8
            List<String> remove = new ArrayList<>();
            for (Integer i : UNIQUE_LENGTHS.keySet()) {
                for (String s : l.evaulationNumbers) {
                    if (s.length() == i) {
                        l.solutionMapping.put(UNIQUE_LENGTHS.get(i), s);
                        remove.add(s);
                        break;
                    }
                }
            }

            l.evaulationNumbers.removeAll(remove);
            remove.clear();

            // 3 must contain all lines of 1 && length 5
            // 9 must contain all lines of 4 && length 6
            for (String s : l.evaulationNumbers) {
                if (s.length() == 5 && containsAllChars(s, l.solutionMapping.get(1))) {
                    l.solutionMapping.put(3, s);
                    remove.add(s);
                }
                if (s.length() == 6 && containsAllChars(s, l.solutionMapping.get(4))) {
                    l.solutionMapping.put(9, s);
                    remove.add(s);
                }
            }

            l.evaulationNumbers.removeAll(remove);
            remove.clear();

            // At this point only 0, 2, 5, 6 are not evaluated
            // 5 must fit into 9 && length 5
            // 0 must contain all lines of 1 && length 6
            for (String s : l.evaulationNumbers) {
                if (s.length() == 5 && containsAllChars(l.solutionMapping.get(9), s)) {
                    l.solutionMapping.put(5, s);
                    remove.add(s);
                }
                if (s.length() == 6 && containsAllChars(s, l.solutionMapping.get(1))) {
                    l.solutionMapping.put(0, s);
                    remove.add(s);
                }
            }

            l.evaulationNumbers.removeAll(remove);

            // only 2 and 6 left, both have different lengths (5 & 6)
            for (String s : l.evaulationNumbers) {
                if (s.length() == 5) {
                    l.solutionMapping.put(2, s);
                } else {
                    l.solutionMapping.put(6, s);
                }
            }
        }

        List<Long> allValues = new ArrayList<>();
        for (Line l : allLines) {
            StringBuilder temp = new StringBuilder();
            for (String sNum : l.solutionNumbers) {
                for (String mapping : l.swappedSolution().keySet()) {
                    if (isAnagram(sNum, mapping)) {
                        temp.append(l.swappedSolution().get(mapping));
                        break;
                    }
                }
            }
            allValues.add(Long.parseLong(temp.toString()));
        }

        System.out.println(allValues.stream().reduce(Long::sum).orElseThrow());
    }

    private static boolean containsAllChars(String outer, String inner) {
        for (char i : inner.toCharArray()) {
            if (!outer.contains("" + i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAnagram(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        return containsAllChars(first, second);
    }
}

class Line {
    public List<String> evaulationNumbers = new ArrayList<>();
    public List<String> solutionNumbers = new ArrayList<>();
    public Map<Integer, String> solutionMapping = new HashMap<>();
    public Map<String, Integer> swappedSolution() {
        return solutionMapping.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
