package h_day8;

import util.InputReader;
import util.Util;

import java.util.*;

public class Day8 {

    private static class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> input = InputReader.readInput();
        char[][] mat = Util.asCharMatrix(input);
        int n = mat.length;
        int m = mat[0].length;

        Set<Character> chars = new HashSet<>();
        for (char[] a : mat) {
            for (char b : a) {
                chars.add(b);
            }
        }
        chars.remove('.');

        char[][] acc = new char[n][m];
        for (char[] c : acc) {
            Arrays.fill(c, '.');
        }

        for (char c : chars) {
            List<Coordinate> coords = findCoords(mat, c);
            for (int i = 0; i < coords.size() - 1; i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    Coordinate c1 = coords.get(i);
                    Coordinate c2 = coords.get(j);
                    int diffX = c1.x - c2.x;
                    int diffY = c1.y - c2.y;
                    try {
                        acc[c1.y + diffY][c1.x + diffX] = '#';
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore
                    }
                    try {
                        acc[c2.y - diffY][c2.x - diffX] = '#';
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore
                    }
                }
            }
        }

        int res = 0;
        for (char[] p : acc) {
            for (char q : p) {
                res += q == '#' ? 1 : 0;
            }
        }

        System.out.println(res);
    }

    public static void part2() {
        List<String> input = InputReader.readInput();
        char[][] mat = Util.asCharMatrix(input);
        int n = mat.length;
        int m = mat[0].length;

        Set<Character> chars = new HashSet<>();
        for (char[] a : mat) {
            for (char b : a) {
                chars.add(b);
            }
        }
        chars.remove('.');

        char[][] acc = new char[n][m];
        for (char[] c : acc) {
            Arrays.fill(c, '.');
        }

        for (char c : chars) {
            List<Coordinate> coords = findCoords(mat, c);
            for (int i = 0; i < coords.size() - 1; i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    Coordinate c1 = coords.get(i);
                    Coordinate c2 = coords.get(j);
                    int diffX = c1.x - c2.x;
                    int diffY = c1.y - c2.y;
                    try {
                        for (int k = 0; ; k++) {
                            acc[c1.y + k * diffY][c1.x + k * diffX] = '#';
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore
                    }
                    try {
                        for (int k = 0; ; k++) {
                            acc[c2.y - k * diffY][c2.x - k * diffX] = '#';
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // ignore
                    }
                }
            }
        }

        int res = 0;
        for (char[] p : acc) {
            for (char q : p) {
                res += q == '#' ? 1 : 0;
            }
        }

        System.out.println(res);
    }

    private static List<Coordinate> findCoords(char[][] mat, char c) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == c) {
                    coordinates.add(new Coordinate(j, i));
                }
            }
        }
        return coordinates;
    }
}
