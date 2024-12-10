package j_day10;

import util.InputReader;
import util.Util;

import java.util.Arrays;

public class Day10 {

    private static final int[] dx = new int[]{0, 1, 0, -1};
    private static final int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        int[][] mat = Util.asSingleDigitMatrix(InputReader.readInput());
        int t = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    boolean[][] res = new boolean[mat.length][mat[0].length];
                    dfs(mat, res, i, j, 0);
                    for (boolean[] b : res) {
                        for (boolean c : b) {
                            if (c) {
                                t++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(t);
    }

    public static void part2() {
        int[][] mat = Util.asSingleDigitMatrix(InputReader.readInput());
        int t = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    t += dfs2(mat, i, j, 0);
                }
            }
        }

        System.out.println(t);
    }

    public static void dfs(int[][] mat, boolean[][] res, int y, int x, int exp) {
        if (y < 0 || y >= mat.length || x < 0 || x >= mat[0].length) {
            return;
        }
        if (mat[y][x] != exp) {
            return;
        }
        if (mat[y][x] == 9) {
            res[y][x] = true;
            return;
        }
        for (int i = 0; i < 4; i++) {
            dfs(mat, res, y + dy[i], x + dx[i], exp + 1);
        }
    }

    public static int dfs2(int[][] mat, int y, int x, int exp) {
        if (y < 0 || y >= mat.length || x < 0 || x >= mat[0].length) {
            return 0;
        }
        if (mat[y][x] != exp) {
            return 0;
        }
        if (mat[y][x] == 9) {
            return 1;
        }
        int s = 0;
        for (int i = 0; i < 4; i++) {
            s += dfs2(mat, y + dy[i], x + dx[i], exp + 1);
        }
        return s;
    }
}
