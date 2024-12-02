package b_day2;

import util.InputReader;
import util.Util;

public class Day2 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        long[][] matrix = Util.asMatrix(InputReader.readInput());
        int res = 0;
        outer: for (long[] line : matrix) {
            int n = line.length;
            boolean allDec = true, allInc = true;
            for (int j = 0; j < n - 1; j++) {
                if (Math.abs(line[j] - line[j + 1]) > 3) {
                    continue outer;
                }
                if (line[j] - line[j + 1] >= 0) {
                    allInc = false;
                }
                if (line[j] - line[j + 1] <= 0) {
                    allDec = false;
                }
            }
            if (allInc || allDec) {
                res++;
            }
        }

        System.out.println(res);

    }

    public static void part2() {
        long[][] matrix = Util.asMatrix(InputReader.readInput());
        int res = 0;
        for (long[] line : matrix) {
            int n = line.length;
            for (int i = 0; i < n; i++) {
                long[] tmp = Util.copyArrayWithoutIndex(line, i);
                if (isMon(tmp, 1) || isMon(tmp, -1)) {
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    // fac = 1 => isDec;; fac = -1 => isInc
    public static boolean isMon(long[] in, long fac) {
        int n = in.length;
        for (int i = 0; i < n - 1; i++) {
            if (fac * (in[i] - in[i + 1]) <= 0 || Math.abs(in[i] - in[i + 1]) > 3) {
                return false;
            }
        }
        return true;
    }
}
