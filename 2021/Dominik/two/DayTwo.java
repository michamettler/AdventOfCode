package com.company.Dominik.two;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DayTwo {

    public static void main(String[] args) {

        HashMap<String, Integer> numbers = new HashMap<>();
        numbers.put("forward", 0);
        numbers.put("up", 0);
        numbers.put("down", 0);

        try {
            Scanner input = new Scanner(new File("C:\\Users\\Dominik Wyss\\IdeaProjects\\AdventOfCode\\src\\com\\company\\two\\numbers.txt"));

            while (input.hasNextLine()) {
                String word = input.next();
                String value = input.next();
                numbers.put(word, numbers.get(word) + Integer.parseInt(value));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println((numbers.get("down") - numbers.get("up")) * numbers.get("forward"));


//        System.out.println(measureLargerNumber(all, 1));
//        System.out.println(measureLargerNumber(all, 3));
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
