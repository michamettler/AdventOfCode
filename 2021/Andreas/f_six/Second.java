package com.company.andreas.f_six;


import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        HashMap<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(i, 0L);
        }
        String[] inputSplitted = in.next().split(",");
        for (String s : inputSplitted) {
            int i = Integer.parseInt(s);
            map.compute(i, (k, v) -> v += 1L);
        }
        in.close();

        for (int i = 0; i < 256; i++) {
            long amount0Beginning = map.get(0);
            for (int j = 0; j < 8; j++) {
                map.put(j, map.get(j + 1));
            }
            map.compute(6, (k, v) -> v += amount0Beginning);
            map.put(8, amount0Beginning);
        }

        long total = 0;
        for (int i = 0; i < 9; i++) {
            total += map.get(i);
        }
        System.out.println(total);
    }

}
