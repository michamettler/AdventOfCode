package com.company.andreas.k_eleven;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class First {

    private static final int[] kx = {-1, 0, 1, 0, 1, -1, 1, -1};
    private static final int[] ky = {0, -1, 0, 1, 1, 1, -1, -1};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<String> input = new ArrayList<>();
        while (in.hasNext()) {
            input.add(in.next());
        }
        in.close();

        FieldTile[][] matrix = new FieldTile[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                matrix[i][j] = new FieldTile(j, i, Integer.parseInt("" + input.get(i).charAt(j)));
            }
        }

        long ans = 0;
        for (int i = 0; i < 100; i++) {
            LinkedList<FieldTile> flashingFieldTiles = new LinkedList<>();

            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    matrix[j][k].value++;
                    if (matrix[j][k].value == 10) {
                        ans++;
                        flashingFieldTiles.add(matrix[j][k]);
                    }
                }
            }

            while (!flashingFieldTiles.isEmpty()) {
                FieldTile ft = flashingFieldTiles.poll();
                for (int j = 0; j < 8; j++) {
                    if (ft.x + kx[j] >= 0 && ft.x + kx[j] < matrix[0].length && ft.y + ky[j] >= 0 && ft.y + ky[j] < matrix.length) {
                        FieldTile other = matrix[ft.y + ky[j]][ft.x + kx[j]];
                        other.value++;
                        if (other.value == 10) {
                            ans++;
                            flashingFieldTiles.add(other);
                        }
                    }
                }
            }

            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix[1].length; k++) {
                    if (matrix[j][k].value >= 10) {
                        matrix[j][k].value = 0;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

class FieldTile {
    public int x;
    public int y;
    public int value;

    public FieldTile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
