package com.company.andreas.e_five;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class First {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<Line> lines = new ArrayList<>();
        while(in.hasNext()) {
            Line line = new Line();
            String[] start = in.next().split(",");
            in.next();
            String[] end = in.next().split(",");
            line.startX = Integer.parseInt(start[0]);
            line.startY = Integer.parseInt(start[1]);
            line.endX = Integer.parseInt(end[0]);
            line.endY = Integer.parseInt(end[1]);
            if (line.startX == line.endX || line.startY == line.endY) {
                if (line.startX > line.endX || line.startY > line.endY) {
                    int tempX = line.startX;
                    line.startX = line.endX;
                    line.endX = tempX;

                    int tempY = line.startY;
                    line.startY = line.endY;
                    line.endY = tempY;
                }
                lines.add(line);
            }
        }

        int fieldSize = lines.stream()
                .map(e -> Math.max(Math.max(e.startX, e.startY), Math.max(e.endX, e.endY)))
                .reduce(Math::max).orElseThrow();
        int[][] field = new int[fieldSize + 1][fieldSize + 1];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 0;
            }
        }

        for (Line l : lines) {
            for (int i = l.startY; i <= l.endY; i++) {
                for (int j = l.startX; j <= l.endX; j++) {
                    field[i][j]++;
                }
            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }


        long amountOver1 = Arrays.stream(field).flatMapToInt(IntStream::of).filter(e -> e >= 2).count();
        System.out.println(amountOver1);
        in.close();
    }
}

class Line {
    public int startX;
    public int startY;
    public int endX;
    public int endY;
}
