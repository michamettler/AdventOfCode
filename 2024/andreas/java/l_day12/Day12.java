package l_day12;

import util.InputReader;
import util.Util;

public class Day12 {

    private static final int[] dx = new int[]{0, 1, 0, -1};
    private static final int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        char[][] mat = Util.asCharMatrix(InputReader.readInput());
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] processed = new boolean[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!processed[i][j]) {
                    Boolean[][] visited = new Boolean[n][m];
                    Util.initialize(visited, false);
                    int perimeter = dfs(mat, visited, mat[i][j], i, j);
                    int area = 0;
                    for (int ii = 0; ii < n; ii++) {
                        for (int jj = 0; jj < m; jj++) {
                            area += visited[ii][jj] ? 1 : 0;
                            processed[ii][jj] |= visited[ii][jj];
                        }
                    }
                    res += area * perimeter;
                }
            }
        }

        System.out.println(res);
    }

    public static void part2() {
        char[][] mat = Util.asCharMatrix(InputReader.readInput());
        int n = mat.length;
        int m = mat[0].length;

        boolean[][] processed = new boolean[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!processed[i][j]) {
                    Boolean[][] visited = new Boolean[n][m];
                    Util.initialize(visited, false);
                    dfs(mat, visited, mat[i][j], i, j);
                    int area = 0;
                    for (int ii = 0; ii < n; ii++) {
                        for (int jj = 0; jj < m; jj++) {
                            area += visited[ii][jj] ? 1 : 0;
                            processed[ii][jj] |= visited[ii][jj];
                        }
                    }
                    Boolean[][] padded = new Boolean[n + 2][m + 2];
                    Util.padMatrix(visited, padded, false);
                    int perimeter = 0;
                    for (int ii = 0; ii < n + 1; ii++) {
                        boolean isTF = false, isFT = false;
                        for (int jj = 0; jj < m + 2; jj++) {
                            int p = 0;
                            if (!isTF) {
                                if (padded[ii][jj] && !padded[ii + 1][jj]) {
                                    isTF = true;
                                    p |= 1;
                                }
                            }
                            if (!isFT) {
                                if (!padded[ii][jj] && padded[ii + 1][jj]) {
                                    isFT = true;
                                    p |= 1;
                                }
                            }
                            perimeter += p;
                            if (isTF && (!padded[ii][jj] || padded[ii + 1][jj])) {
                                isTF = false;
                            }
                            if (isFT && (padded[ii][jj] || !padded[ii + 1][jj])) {
                                isFT = false;
                            }
                        }
                    }
                    for (int jj = 0; jj < m + 1; jj++) {
                        boolean isTF = false, isFT = false;
                        for (int ii = 0; ii < n + 2; ii++) {
                            int p = 0;
                            if (!isTF) {
                                if (padded[ii][jj] && !padded[ii][jj + 1]) {
                                    isTF = true;
                                    p |= 1;
                                }
                            }
                            if (!isFT) {
                                if (!padded[ii][jj] && padded[ii][jj + 1]) {
                                    isFT = true;
                                    p |= 1;
                                }
                            }
                            perimeter += p;
                            if (isTF && (!padded[ii][jj] || padded[ii][jj + 1])) {
                                isTF = false;
                            }
                            if (isFT && (padded[ii][jj] || !padded[ii][jj + 1])) {
                                isFT = false;
                            }
                        }
                    }
                    res += area * perimeter;
                }
            }
        }

        System.out.println(res);
    }

    public static int dfs(char[][] mat, Boolean[][] visited, char c, int y, int x) {
        int n = mat.length;
        int m = mat[0].length;
        if (y < 0 || x < 0 || y >= n || x >= m) {
            return 0;
        }
        if (visited[y][x] || mat[y][x] != c) {
            return 0;
        }
        visited[y][x] = true;
        int t = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || ny >= n || nx >= m) {
                t++;
                continue;
            }
            if (mat[y][x] != mat[ny][nx]) {
                t++;
            }
            t += dfs(mat, visited, c, ny, nx);
        }
        return t;
    }
}
