package i_day9;

import util.InputReader;
import util.Util;

import java.util.Arrays;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        part1();
    }

    public static void part1() {
        List<String> input = InputReader.readInput();
        if (input.size() != 1) {
            throw new RuntimeException("wrong input format");
        }

        int[] in = Util.toSingleDigitIntArray(input.get(0));
        int t = Arrays.stream(in).reduce(Integer::sum).orElseThrow();
        int[] map = new int[t];

        int c = 0;
        int idC = 0;
        for (int i = 0; i < in.length; i++) {
            if (i % 2 == 0) {
                // current is block
                for (int k = c; k < c + in[i]; k++) {
                    map[k] = idC;
                }
                idC++;
            } else {
                // current is free
                for (int k = c; k < c + in[i]; k++) {
                    map[k] = -1;
                }
            }
            c += in[i];
        }

        // compress
        int lp = 0;
        int rp = map.length - 1;
        while (lp < rp) {
            while (map[lp] != -1 && lp < rp) {
                // search for first free spot
                lp++;
            }
            while (map[rp] == -1 && lp < rp) {
                // search for first element from right
                rp--;
            }

            if (lp >= rp) {
                break;
            }
            // swap
            int z = map[rp];
            map[rp] = map[lp];
            map[lp] = z;
        }

        long res = 0L;
        for (int i = 0; i < map.length && map[i] != -1; i++) {
            res += (long)map[i] * i;
        }

        System.out.println(res);
    }
}
