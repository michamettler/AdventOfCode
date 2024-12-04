package d_day4;

import util.InputReader;
import util.Util;

import java.util.List;

public class Day4 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> in = InputReader.readInput();
        char[][] mat = Util.asCharMatrix(in);
        int[] dx = new int[]{0, 0, 1, 1, 1, -1, -1, -1};
        int[] dy = new int[]{1, -1, 1, -1, 0, -1, 1, 0};
        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (int k = 0; k < 8; k++) {
                    res += checkXMAS(mat, j, i, dx[k], dy[k], "XMAS");
                }
            }
        }
        System.out.println(res);
    }

    public static void part2() {
        List<String> in = InputReader.readInput();
        char[][] mat = Util.asCharMatrix(in);
        int res = 0;
        for (int i = 0; i < mat.length - 2; i++) {
            for (int j = 0; j < mat[i].length - 2; j++) {
                int topLbototmR = checkXMAS(mat, j, i, 1, 1, "MAS")
                        | checkXMAS(mat, j + 2, i+2, -1, -1, "MAS");
                int bottomLtopR = checkXMAS(mat, j, i + 2, 1, -1, "MAS")
                        | checkXMAS(mat, j + 2, i, -1, 1, "MAS");
                res += (topLbototmR + bottomLtopR) >> 1;
            }
        }
        System.out.println(res);
    }

    public static int checkXMAS(char[][] mat, int startX, int startY, int dirX, int dirY, String word) {
        int n = mat.length;
        int m = mat[0].length;
        int l = word.length();
        // check stays within bounds
        int endX = startX + (l - 1) * dirX;
        int endY = startY + (l - 1) * dirY;
        if (endX < 0 || endX >= m || endY < 0 || endY >= n) {
            return 0;
        }
        if (startX < 0 || startX >= m || startY < 0 || startY >= n) {
            // this is only reachable in part 2
            return 0;
        }
        int x, y;
        for (int i = 0; i < l; i++) {
            x = startX + i * dirX;
            y = startY + i * dirY;
            if (mat[y][x] != word.charAt(i)) {
                return 0;
            }
        }
        return 1;
    }
}
