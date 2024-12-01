package a_day1;

import util.InputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    private static class Pair {
        public List<Integer> left = new ArrayList<>();
        public List<Integer> right = new ArrayList<>();
    }

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> input = InputReader.readInput();

        Pair p = input.stream().map(e -> e.split("   "))
                .reduce(new Pair(), (acc, e) -> {
                    acc.left.add(Integer.parseInt(e[0]));
                    acc.right.add(Integer.parseInt(e[1]));
                    return acc;
                }, (p1, p2) -> {
                    p1.left.addAll(p2.left);
                    p1.right.addAll(p2.right);
                    return p1;
                });

        Collections.sort(p.left);
        Collections.sort(p.right);

        int sum = 0;
        for (int i = 0; i < p.left.size(); i++) {
            sum += Math.abs(p.left.get(i) - p.right.get(i));
        }

        System.out.println(sum);
    }

    public static void part2() {
        List<String> input = InputReader.readInput();

        Pair p = input.stream().map(e -> e.split("   "))
                .reduce(new Pair(), (acc, e) -> {
                    acc.left.add(Integer.parseInt(e[0]));
                    acc.right.add(Integer.parseInt(e[1]));
                    return acc;
                }, (p1, p2) -> {
                    p1.left.addAll(p2.left);
                    p1.right.addAll(p2.right);
                    return p1;
                });

        long sum = 0;
        for (int i = 0; i < p.left.size(); i++) {
            final int v = p.left.get(i);
            sum += p.left.get(i) * p.right.stream().filter(e -> e == v).count();
        }

        System.out.println(sum);
    }
}