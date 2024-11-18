package com.company.andreas.d_four;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class First {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<Integer> drawnNumbers = Stream.of(in.next().split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        List<FieldTile[][]> fields = new ArrayList<>();
        while(in.hasNext()) {
            FieldTile[][] field = new FieldTile[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    field[i][j] = new FieldTile(in.nextInt());
                }
            }
            fields.add(field);
        }
        in.close();

        boolean exit = false;
        for (int drawnNumber : drawnNumbers) {
            if (exit) {
                break;
            }
            for (FieldTile[][] field : fields) {
                tick(field, drawnNumber);
                if (checkBingo(field)) {
                    int totalUnticked = Arrays.stream(field)
                            .flatMap(Arrays::stream)
                            .filter(e -> !e.isTicked)
                            .map(e -> e.number)
                            .reduce(Integer::sum)
                            .orElseThrow();
                    System.out.println(drawnNumber * totalUnticked);
                    exit = true;
                }
            }
        }
    }

    private static boolean checkBingo(FieldTile[][] field) {
        boolean isBingo = false;
        for (int i = 0; i < field.length; i++) {
            boolean isRowBingo = true;
            boolean isColumnBingo = true;
            for (int j = 0; j < field[i].length; j++) {
                if (!field[i][j].isTicked) {
                    isColumnBingo = false;
                }
                if (!field[j][i].isTicked) {
                    isRowBingo = false;
                }
            }
            if (isRowBingo || isColumnBingo) {
                isBingo = true;
                break;
            }
        }
        return isBingo;
    }

    private static void tick(FieldTile[][] field, int drawnNumber) {
        for (FieldTile[] fieldTiles : field) {
            for (FieldTile fieldTile : fieldTiles) {
                if (fieldTile.number == drawnNumber) {
                    fieldTile.isTicked = true;
                }
            }
        }
    }
}

class FieldTile {
    public int number;
    public boolean isTicked = false;

    public FieldTile(int number) {
        this.number = number;
    }
}
