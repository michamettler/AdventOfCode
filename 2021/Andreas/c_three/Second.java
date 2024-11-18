package com.company.andreas.c_three;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Second {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> all = new ArrayList<>();
        while(in.hasNext()) {
            all.add(in.next());
        }
        in.close();

        Boolean[][] matrix = new Boolean[all.size()][all.get(0).length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = all.get(i).charAt(j) == '1';
            }
        }

        List<Boolean[]> firstRateMoreCommon = new ArrayList<>(Arrays.asList(matrix));
        List<Boolean[]> firstRateLessCommon = new ArrayList<>(Arrays.asList(matrix));

        String resultLess = "";
        String resultMore = "";
        for (int i = 0; i < matrix[0].length; i++) {
            final int t = i;
            boolean onesOccurMore = firstRateMoreCommon.stream().filter(e -> e[t]).count() * 2 >= firstRateMoreCommon.size();
            boolean zeroesOccurMore = firstRateLessCommon.stream().filter(e -> !e[t]).count() * 2 > firstRateLessCommon.size();
            firstRateMoreCommon = firstRateMoreCommon.stream().filter(e -> e[t] == onesOccurMore).collect(Collectors.toList());
            firstRateLessCommon = firstRateLessCommon.stream().filter(e -> e[t] == zeroesOccurMore).collect(Collectors.toList());
            if (firstRateLessCommon.size() == 1) {
                resultLess = map(firstRateLessCommon.get(0));
                firstRateLessCommon.clear();
            }
            if (firstRateMoreCommon.size() == 1) {
                resultMore = map(firstRateMoreCommon.get(0));
                firstRateMoreCommon.clear();
            }
        }

        System.out.println(binaryToDecimal(resultLess) * binaryToDecimal(resultMore));
    }

    private static String map(Boolean[] booleans) {
        StringBuilder sb = new StringBuilder();
        for (Boolean b : booleans) {
            sb.append(b ? '1' : '0');
        }
        return sb.toString();
    }

    private static int binaryToDecimal(String string) {
        int c = 1;
        int r = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            r += string.charAt(i) == '1' ? c : 0;
            c *= 2;
        }
        return r;
    }
}
