package m_day13;

import util.InputReader;

import java.util.*;

public class Day13 {

    private static class ClawMachine {
        long ax, ay, bx, by, px, py;

        @Override
        public String toString() {
            return "ClawMachine{" +
                    "ax=" + ax +
                    ", ay=" + ay +
                    ", bx=" + bx +
                    ", by=" + by +
                    ", px=" + px +
                    ", py=" + py +
                    '}';
        }
    }

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<ClawMachine> clawMachines = new ArrayList<>();
        List<String> input = InputReader.readInput();
        while (input.size() == 3) {
            ClawMachine cm = new ClawMachine();
            String l1 = input.get(0);
            String l2 = input.get(1);
            String l3 = input.get(2);
            cm.ax = Long.parseLong(l1.split("X+")[1].split(", ")[0]);
            cm.ay = Long.parseLong(l1.split("Y+")[1].trim());
            cm.bx = Long.parseLong(l2.split("X+")[1].split(", ")[0]);
            cm.by = Long.parseLong(l2.split("Y+")[1].trim());
            cm.px = Long.parseLong(l3.split("X=")[1].split(", ")[0]);
            cm.py = Long.parseLong(l3.split("Y=")[1].trim());
            clawMachines.add(cm);

            input = InputReader.readInput();
        }

        int res = 0;
        for (ClawMachine cm : clawMachines) {
            Set<Long> p = findMin(cm);
            if (p.isEmpty()) {
                continue;
            }
            res += Collections.min(p);
        }

        System.out.println(res);
    }

    public static void part2() {
        List<ClawMachine> clawMachines = new ArrayList<>();
        List<String> input = InputReader.readInput();
        while (input.size() == 3) {
            ClawMachine cm = new ClawMachine();
            String l1 = input.get(0);
            String l2 = input.get(1);
            String l3 = input.get(2);
            cm.ax = Long.parseLong(l1.split("X+")[1].split(", ")[0]);
            cm.ay = Long.parseLong(l1.split("Y+")[1].trim());
            cm.bx = Long.parseLong(l2.split("X+")[1].split(", ")[0]);
            cm.by = Long.parseLong(l2.split("Y+")[1].trim());
            cm.px = Long.parseLong(l3.split("X=")[1].split(", ")[0]) + 10000000000000L;
            cm.py = Long.parseLong(l3.split("Y=")[1].trim()) + 10000000000000L;
            clawMachines.add(cm);

            input = InputReader.readInput();
        }
        System.out.println(clawMachines);

        long res = 0;
        for (ClawMachine cm : clawMachines) {
            Set<Long> p = findmin2(cm);
            if (p.isEmpty()) {
                continue;
            }
            res += Collections.min(p);
        }

        System.out.println(res);
    }

    private static Set<Long> findmin2(ClawMachine cm) {
        // solve the equation system (x := button A pushes, y := button B pushes)
        // cm.ax * x + cm.bx * y = cm.px
        // cm.ay * x + cm.by * y = cm.py

        long det = cm.ax * cm.by - cm.ay * cm.bx;

        if (det == 0L) {
            System.out.println("No unique solution (det 0)"); // this should never happen as basis vectors are lin. indep.
            return new HashSet<>();
        }
        // using cramer's rule
        long x = (cm.px * cm.by - cm.py * cm.bx) / det;
        long y = (cm.ax * cm.py - cm.ay * cm.px) / det;

        // verify result
        if (x * cm.ax + y * cm.bx != cm.px || x * cm.ay + y * cm.by != cm.py) {
            // no integer solution
            return new HashSet<>();
        }

        return new HashSet<>(Set.of(3 * x + y));
    }

    private static long gcd(long a, long b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    private static Set<Long> findMin(ClawMachine cm) {
        Set<Long> res = new HashSet<>();
        // Using i times button A
        for (int i = 0; i <= 200; i++) {
            // using j times button B
            for (int j = 0; j <= 200 - i; j++) {
                if (i * cm.ax + j * cm.bx == cm.px && i * cm.ay + j * cm.by == cm.py) {
                    res.add(3L * i + j);
                }
            }
        }
        return res;
    }
}
