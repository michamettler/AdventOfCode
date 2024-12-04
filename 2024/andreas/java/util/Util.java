package util;

import java.util.List;

public class Util {

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
}
