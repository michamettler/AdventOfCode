package k_day11;

import util.InputReader;
import util.Util;

import java.math.BigInteger;
import java.util.*;

public class Day11 {

    public static void main(String[] args) {
        part2();
    }

    public static void part2() {
        List<String> input = InputReader.readInput();
        if (input.size() != 1) {
            throw new RuntimeException();
        }
        List<Long> l = Util.toLongArrayList(input.get(0));
        Map<Long, BigInteger> prev, curr = new HashMap<>();
        for (long e : l) {
            Util.putOrInc(curr, e, BigInteger.ONE);
        }
        for (int i = 0; i < 75; i++) {
            prev = curr;
            curr = new HashMap<>();
            for (Map.Entry<Long, BigInteger> e : prev.entrySet()) {
                int z = (e.getKey() + "").length();
                if (e.getKey() == 0L) {
                    Util.putOrInc(curr, 1L, e.getValue());
                } else if (z % 2 == 0) {
                    Util.putOrInc(curr, Long.parseLong(("" + e.getKey()).substring(0, z / 2)), e.getValue());
                    Util.putOrInc(curr, Long.parseLong(("" + e.getKey()).substring(z / 2)), e.getValue());
                } else {
                    Util.putOrInc(curr, e.getKey() * 2024, e.getValue());
                }
            }
        }
        BigInteger res = curr.values().stream().reduce(BigInteger::add).orElseThrow();
        System.out.println(res);
    }

    public static void part1() {
        List<String> input = InputReader.readInput();
        if (input.size() != 1) {
            throw new RuntimeException();
        }
        List<Long> l = Util.toLongArrayList(input.get(0));
        Queue<Long> prev = null, curr = new LinkedList<>(l);
        for (int i = 0; i < 25; i++) {
            prev = curr;
            curr = new LinkedList<>();
            while (!prev.isEmpty()) {
                long e = prev.poll();
                int z = (e + "").length();
                if (e == 0L) {
                    curr.add(1L);
                } else if (z % 2 == 0) {
                    curr.add(Long.parseLong(("" + e).substring(0, z / 2)));
                    curr.add(Long.parseLong(("" + e).substring(z/2)));
                } else {
                    curr.add(e * 2024);
                }
            }
        }

        System.out.println(curr.size());
    }
}
