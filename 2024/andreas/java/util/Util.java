package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Util {

    public static List<Long> toLongArrayList(String s) {
        return new ArrayList<>(Arrays.stream(s.trim().split(" ")).map(Long::parseLong).toList());
    }

    public static <K, V> void putOrAdd(Map<K, List<V>> map, K k, V v) {
        if (map.containsKey(k)) {
            map.get(k).add(v);
        } else {
            map.put(k, new ArrayList<>(List.of(v)));
        }
    }

    public static <K> void putOrInc(Map<K, BigInteger> map, K k, BigInteger v) {
        if (map.containsKey(k)) {
            map.put(k, map.get(k).add(v));
        } else {
            map.put(k, v);
        }
    }

    public static long[][] asMatrix(List<String> input) {
        long[][] res = new long[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            String[] split = input.get(i).split(" ");
            res[i] = new long[split.length];
            for (int j = 0; j < split.length; j++) {
                res[i][j] = Integer.parseInt(split[j]);
            }
        }
        return res;
    }

    public static int[][] asSingleDigitMatrix(List<String> input) {
        int[][] res = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == '.') {
                    res[i][j] = -1;
                    continue;
                }
                res[i][j] = Integer.parseInt("" + input.get(i).charAt(j));
            }
        }
        return res;
    }

    public static char[][] asCharMatrix(List<String> input) {
        char[][] res = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            res[i] = new char[input.get(i).length()];
            for (int j = 0; j < input.get(i).length(); j++) {
                res[i][j] = input.get(i).charAt(j);
            }
        }
        return res;
    }

    public static long[] copyArrayWithoutIndex(long[] arr, int idx) {
        long[] res = new long[arr.length - 1];
        for (int i = 0; i < res.length; i++) {
            if (i < idx) {
                res[i] = arr[i];
            } else {
                res[i] = arr[i + 1];
            }
        }
        return res;
    }

    public static Integer tryParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static int[] toSingleDigitIntArray(String s) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = (int) (s.charAt(i) - '0');
        }
        return arr;
    }

    public static <T> void padMatrix(T[][] src, T[][] dest, T padVal) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                if (i == 0 || j == 0 || i == dest.length - 1 || j == dest[i].length - 1) {
                    dest[i][j] = padVal;
                } else {
                    dest[i][j] = src[i - 1][j - 1];
                }
            }
        }
    }

    public static <T> void initialize(T[][] mat, T val) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = val;
            }
        }
    }

    public static void printBooleanMap(Boolean[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                Boolean b = mat[i][j];
                if (b) {
                    System.out.print("T");
                } else if (!b) {
                    System.out.print("F");
                } else {
                    System.out.print("N");
                }
            }
            System.out.println();
        }
    }
}
