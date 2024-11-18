package com.company.Dominik.one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayOne {

    public static void main(String[] args) {
        List<Integer> all = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dominik Wyss\\IdeaProjects\\AdventOfCode\\src\\com\\company\\one\\numbers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                all.add(Integer.valueOf(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(all.size());

        System.out.println(measureLargerNumber(all, 1));
        System.out.println(measureLargerNumber(all, 3));
    }

    private static int measureLargerNumber(List<Integer> all, int o) {
        int x = 0;
        int number1 = 0;
        int number2 = 0;
        for (int i = 1; i + o - 1 < all.size(); i++) {
            for (int j = 0; j < o; j++) {

                number1 += all.get(i - 1 + j);
                number2 += all.get(i + j);

            }
            if (number1 < number2) {
                x++;
            }
            number1 = 0;
            number2 = 0;
        }
        return x;
    }
}
