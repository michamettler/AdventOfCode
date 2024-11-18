package com.company.andreas.a_one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class First {
    public static void main(String[] args) throws Exception {
        List<Integer> all = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\workarea\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                all.add(Integer.valueOf(line));
            }
        }
        System.out.println(all.size());
        int counter = 0;
        Integer previous = null;
        for (int integer : all) {
            if (previous == null) {
                previous = integer;
                continue;
            }
            if (previous < integer) {
                counter++;
            }
            previous = integer;
        }
        System.out.println(counter);
    }
}
