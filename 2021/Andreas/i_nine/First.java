package com.company.andreas.i_nine;

import java.io.FileReader;
import java.util.*;

public class First {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> input = new ArrayList<>();
        while (in.hasNext()) {
            input.add(in.next());
        }
        in.close();

        FieldTile[][] matrix = new FieldTile[input.size()][input.get(0).length()];
        PriorityQueue<FieldTile> pq = new PriorityQueue<>(Comparator.comparing(FieldTile::getValue));
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                FieldTile ft = new FieldTile(j, i, Integer.parseInt("" + input.get(i).charAt(j)));
                matrix[i][j] = ft;
                pq.add(ft);
            }
        }

        int[] kx = {-1, 0, 1, 0};
        int[] ky = {0, -1, 0, 1};

        List<Integer> lowestValues = new ArrayList<>();
        while (!pq.isEmpty()) {
            FieldTile ft = pq.poll();
            boolean higher = false;
            for (int i = 0; i < 4; i++) {
                if (ft.x + kx[i] >= 0 && ft.x + kx[i] < matrix[0].length && ft.y + ky[i] >= 0 && ft.y + ky[i] < matrix.length) {
                    if (ft.value >= matrix[ft.y + ky[i]][ft.x + kx[i]].value) {
                        higher = true;
                    }
                }
            }
            if (!higher) {
                lowestValues.add(ft.value);
            }
        }

        System.out.println(lowestValues.stream().map(e -> e + 1).reduce(Integer::sum).orElseThrow());
    }
}

class FieldTile {
    public int x;
    public int y;
    public int value;

    public int getValue() {
        return value;
    }

    public FieldTile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
