package f_day6;

import util.InputReader;
import util.Util;

public class Day6 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        char[][] map = Util.asCharMatrix(InputReader.readInput());

        int n = map.length;
        int m = map[0].length;
        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '^') {
                    x = j;
                    y = i;
                    map[i][j] = 'X';
                }
            }
        }

        int dx = 0;
        int dy = -1;

        try {
            while (true) {
                if (map[y + dy][x + dx] == '#') {
                    // turn velocity right
                    int t = dx;
                    dx = -dy;
                    dy = t;
                }
                x += dx;
                y += dy;
                map[y][x] = 'X';
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'X') {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    public static void part2() {
        char[][] map = Util.asCharMatrix(InputReader.readInput());

        int n = map.length;
        int m = map[0].length;
        int initX = -1, initY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '^') {
                    initX = j;
                    initY = i;
                }
            }
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '^' && map[i][j] != '#') {
                    map[i][j] = '#';
                    map[initY][initX] = '.';
                    if (checkLoopRec(map, initX, initY, 0, -1)) {
                        res++;
                    }
                    map[initY][initX] = '^';
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(res);
    }

    public static boolean checkLoopRec(char[][] map, int x, int y, int dx, int dy) {
        char z = '-';
        try {
            if (map[y][x] == 'C') {
                return true;
            }
            z = map[y][x];
            map[y][x] = z == '.' ? 'A' : (char)((int)map[y][x] + 1);
            while (map[y + dy][x + dx] == '#') {
                // turn velocity right
                int t = dx;
                dx = -dy;
                dy = t;
            }
            boolean b = checkLoopRec(map, x + dx, y + dy, dx, dy);
            map[y][x] = z;
            return b;
        } catch (ArrayIndexOutOfBoundsException e) {
            map[y][x] = z;
            return false;
        }
    }
}