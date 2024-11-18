package com.company.Dominik.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DayTwo2 {

    public static void main(String[] args) {

        int horizontal = 0;
        int aim = 0;
        int depth = 0;

        try {
            Scanner input = new Scanner(new File("C:\\Users\\Dominik Wyss\\IdeaProjects\\AdventOfCode\\src\\com\\company\\two\\numbers.txt"));

            while (input.hasNextLine()) {
                String word = input.next();
                int value = Integer.parseInt(input.next());
                switch (word) {
                    case "forward":
                        horizontal += value;
                        depth += aim * value;
                        break;
                    case "up":
                        aim -= value;
                        break;
                    case "down":
                        aim += value;
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(horizontal * depth);
    }
}
