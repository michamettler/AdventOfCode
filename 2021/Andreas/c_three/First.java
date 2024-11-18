package com.company.andreas.c_three;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class First {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> all = new ArrayList<>();
        while(in.hasNext()) {
            all.add(in.next());
        }
        in.close();

        boolean[][] matrix = new boolean[all.size()][all.get(0).length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = all.get(i).charAt(j) == '1';
            }
        }

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < matrix[0].length; i++) {
            int onesCounter = 0;
            for (boolean[] booleans : matrix) {
                onesCounter += booleans[i] ? 1 : 0;
            }
            gammaRate.append(onesCounter * 2 > matrix.length ? 1 : 0);
            epsilonRate.append(gammaRate.charAt(gammaRate.length() - 1) == '0' ? 1 : 0);
        }

        System.out.println(binaryToDecimal(gammaRate.toString()) * binaryToDecimal(epsilonRate.toString()));
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
