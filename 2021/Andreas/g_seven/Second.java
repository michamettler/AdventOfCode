package com.company.andreas.g_seven;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<Integer> ships = Arrays.stream(in.next().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        in.close();

        int t = 0;
        for (int i : ships) {
            t += i;
        }

        int average = t / ships.size();
        int currentT = c(ships, average);
        boolean exit = false;
        while (!exit) {
            if (c(ships, average + 1) < currentT) {
                average++;
                currentT = c(ships, average);
            } else if (c(ships, average - 1) < currentT) {
                average--;
                currentT = c(ships, average);
            } else {
                exit = true;
            }
        }
        System.out.println(currentT);
    }

    private static int c(List<Integer> ships, int num) {
        int t = 0;
        for (int i : ships) {
            for (int j = 0; j <= Math.abs(i - num); j++) {
                t += j;
            }
        }
        return t;
    }
}
