package g_day7;

import util.InputReader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day7 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> input = InputReader.readInput();

        long res = 0L;
        for (String l : input) {
            String[] split1 = l.split(":");
            long target = Long.parseLong(split1[0]);
            Queue<Long> vals = new LinkedList<>(Arrays.stream(split1[1].trim().split(" ")).map(Long::parseLong).toList());
            if (!vals.isEmpty() && isPossible(target, vals.poll(), vals)) {
                res += target;
            }
        }

        System.out.println(res);
    }

    public static boolean isPossible(long target, long current, Queue<Long> remaining) {
        if (remaining.isEmpty()) {
            return target == current;
        }
        long p = remaining.poll();
        return isPossible(target, current + p, new LinkedList<>(remaining))
                || isPossible(target, current * p, new LinkedList<>(remaining));
    }

    public static void part2() {
        List<String> input = InputReader.readInput();

        long res = 0L;
        for (String l : input) {
            String[] split1 = l.split(":");
            long target = Long.parseLong(split1[0]);
            Queue<Long> vals = new LinkedList<>(Arrays.stream(split1[1].trim().split(" ")).map(Long::parseLong).toList());
            if (!vals.isEmpty() && isPossible2(target, vals.poll(), vals)) {
                res += target;
            }
        }

        System.out.println(res);
    }

    public static boolean isPossible2(long target, long current, Queue<Long> remaining) {
        if (remaining.isEmpty()) {
            return target == current;
        }
        long p = remaining.poll();
        return isPossible2(target, current + p, new LinkedList<>(remaining))
                || isPossible2(target, current * p, new LinkedList<>(remaining))
                || isPossible2(target, Long.parseLong(("" + current) + p), new LinkedList<>(remaining));
    }
}
