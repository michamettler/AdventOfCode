package e_day5;

import util.InputReader;
import util.Util;

import java.util.*;

public class Day5 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> dependencies = InputReader.readInput();
        List<String> updates = InputReader.readInput();

        Map<Integer, List<Integer>> illegal = new HashMap<>();
        for (String s : dependencies) {
            String[] split = s.split("\\|");
            Util.putOrAdd(illegal, Integer.parseInt(split[1]), Integer.parseInt(split[0]));
        }

        long res = 0;
        outer: for (String s : updates) {
            List<Integer> u = Arrays.stream(s.split(",")).map(Integer::parseInt).toList();
            for (int i = 0; i < u.size() - 1; i++) {
                for (int j = i + 1; j < u.size(); j++) {
                    if (illegal.containsKey(u.get(i)) && illegal.get(u.get(i)).contains(u.get(j))) {
                        continue outer;
                    }
                }
            }
            res += u.get(u.size() / 2);
        }

        System.out.println(res);
    }

    public static void part2() {
        List<String> dependencies = InputReader.readInput();
        List<String> updates = InputReader.readInput();

        Map<Integer, List<Integer>> illegal = new HashMap<>();
        for (String s : dependencies) {
            String[] split = s.split("\\|");
            Util.putOrAdd(illegal, Integer.parseInt(split[1]), Integer.parseInt(split[0]));
        }

        long res = 0;
        outer: for (String s : updates) {
            List<Integer> u = Arrays.stream(s.split(",")).map(Integer::parseInt).toList();
            for (int i = 0; i < u.size() - 1; i++) {
                for (int j = i + 1; j < u.size(); j++) {
                    if (illegal.containsKey(u.get(i)) && illegal.get(u.get(i)).contains(u.get(j))) {
                        res += fixedOrderResult(illegal, u);
                        continue outer;
                    }
                }
            }
        }

        System.out.println(res);
    }

    private static long fixedOrderResult(Map<Integer, List<Integer>> illegal, List<Integer> u) {
        List<Integer> order = new ArrayList<>();
        List<Integer> remaining = new ArrayList<>(u);
        Queue<Integer> toCheck = new LinkedList<>(u);
        while (!toCheck.isEmpty()) {
            Integer e = toCheck.poll();
            if (!illegal.containsKey(e)) {
                order.add(e);
                remaining.remove(e);
                continue;
            }
            if (illegal.get(e).stream().anyMatch(remaining::contains)) {
                toCheck.add(e);
                continue;
            }
            order.add(e);
            remaining.remove(e);
        }
        return order.get(order.size() / 2);
    }
}
