package com.company.andreas.h_eight;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class First {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> numbers = new ArrayList<>();
        while (in.hasNext()) {
            String line = in.nextLine();
            line = line.split("\\|")[1];
            numbers.addAll(Arrays.asList(line.split(" ")));
        }
        in.close();

        List<Integer> validLengths = List.of(2, 3, 4, 7);
        int ans = 0;
        for (String s : numbers) {
            if (validLengths.contains(s.length())) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
