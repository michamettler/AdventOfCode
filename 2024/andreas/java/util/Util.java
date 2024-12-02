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
}
