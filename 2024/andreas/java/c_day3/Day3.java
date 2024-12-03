package c_day3;

import util.InputReader;
import util.Util;

import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        part2();
    }

    private static void part1() {
        List<String> input = InputReader.readInput();
        String s = input.stream().reduce(String::concat).orElseThrow();
        long res = 0L;
        while (s.contains("mul(")) {
            int idx = s.indexOf("mul(");
            s = s.substring(idx + 4);
            String[] split = s.split("\\)");
            if (split.length == 0) {
                continue;
            }
            String[] subsplit = split[0].split(",");
            if (subsplit.length != 2) {
                continue;
            }
            Integer i1 = Util.tryParse(subsplit[0]);
            Integer i2 = Util.tryParse(subsplit[1]);
            if (i1 == null || i2 == null) {
                continue;
            }
            res += (long) i1 * i2;
        }

        System.out.println(res);
    }

    private static void part2() {
        List<String> input = InputReader.readInput();
        String s = input.stream().reduce(String::concat).orElseThrow();
        long res = 0L;
        boolean enabled = true;
        while (s.contains("mul(")) {
            int idx = s.indexOf("mul(");
            if (idx == -1) {
                break;
            }
            int idxDo = s.indexOf("do()");
            if (idxDo > -1 && idxDo < idx) {
                enabled = true;
                s = s.substring(idxDo + 3);
                continue;
            }
            int idxDont = s.indexOf("don't()");
            if (idxDont > -1 && idxDont < idx) {
                enabled = false;
                s = s.substring(idxDont + 6);
                continue;
            }
            s = s.substring(idx + 4);
            String[] split = s.split("\\)");
            if (split.length == 0) {
                continue;
            }
            String[] subsplit = split[0].split(",");
            if (subsplit.length != 2) {
                continue;
            }
            Integer i1 = Util.tryParse(subsplit[0]);
            Integer i2 = Util.tryParse(subsplit[1]);
            if (i1 == null || i2 == null) {
                continue;
            }
            long toAdd = enabled ? (long) i1 * i2 : 0L;
            res += toAdd;
        }

        System.out.println(res);
    }
}
