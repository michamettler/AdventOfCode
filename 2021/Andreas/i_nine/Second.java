package com.company.andreas.i_nine;

import java.io.FileReader;
import java.util.*;

public class Second {

    static int[] kx = {-1, 0, 1, 0};
    static int[] ky = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> input = new ArrayList<>();
        while (in.hasNext()) {
            input.add(in.next());
        }
        in.close();

        FieldTile2[][] matrix = new FieldTile2[input.size()][input.get(0).length()];
        PriorityQueue<FieldTile2> pq = new PriorityQueue<>(Comparator.comparing(FieldTile2::getValue));
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                FieldTile2 ft = new FieldTile2(j, i, Integer.parseInt("" + input.get(i).charAt(j)));
                matrix[i][j] = ft;
                pq.add(ft);
            }
        }

        List<FieldTile2> lowestTiles = new ArrayList<>();
        while (!pq.isEmpty()) {
            FieldTile2 ft = pq.poll();
            boolean higher = false;
            for (int i = 0; i < 4; i++) {
                if (ft.x + kx[i] >= 0 && ft.x + kx[i] < matrix[0].length && ft.y + ky[i] >= 0 && ft.y + ky[i] < matrix.length) {
                    if (ft.value >= matrix[ft.y + ky[i]][ft.x + kx[i]].value) {
                        higher = true;
                    }
                }
            }
            if (!higher) {
                lowestTiles.add(ft);
            }
        }

        List<Long> bassins = new ArrayList<>();
        for (FieldTile2 ft : lowestTiles) {
            bassins.add(calculateHigherNeighbors(matrix, ft));
        }

        bassins.sort(Long::compare);
        Collections.reverse(bassins);
        System.out.println(bassins.get(0) * bassins.get(1) * bassins.get(2));
    }

    private static long calculateHigherNeighbors(FieldTile2[][] matrix, FieldTile2 ft) {
        if (ft.value == 9) {
            return 0;
        }
        long value = ft.checked ? 0 : 1;
        ft.checked = true;
        for (int i = 0; i < 4; i++) {
            if (ft.x + kx[i] >= 0 && ft.x + kx[i] < matrix[0].length && ft.y + ky[i] >= 0 && ft.y + ky[i] < matrix.length) {
                FieldTile2 other = matrix[ft.y + ky[i]][ft.x + kx[i]];
                if (ft.value < other.value) {
                    value += calculateHigherNeighbors(matrix, other);
                }
            }
        }
        return value;
    }
}

class FieldTile2 {
    public int x;
    public int y;
    public int value;
    public boolean checked = false;

    public int getValue() {
        return value;
    }

    public FieldTile2(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
