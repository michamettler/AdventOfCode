package com.company.andreas.e_five;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\input.txt"));
        List<Line2> lines = new ArrayList<>();
        while (in.hasNext()) {
            Line2 line = new Line2();
            String[] start = in.next().split(",");
            in.next();
            String[] end = in.next().split(",");
            line.startX = Integer.parseInt(start[0]);
            line.startY = Integer.parseInt(start[1]);
            line.endX = Integer.parseInt(end[0]);
            line.endY = Integer.parseInt(end[1]);
            lines.add(line);
        }
        in.close();

        int fieldSize = lines.stream()
                .map(e -> Math.max(Math.max(e.startX, e.startY), Math.max(e.endX, e.endY)))
                .reduce(Math::max)
                .orElseThrow();
        int[][] field = new int[fieldSize + 1][fieldSize + 1];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 0;
            }
        }

        for (Line2 l : lines) {
            if (l.startX == l.endX || l.startY == l.endY) {
                if (l.startX > l.endX || l.startY > l.endY) {
                    int tempX = l.startX;
                    l.startX = l.endX;
                    l.endX = tempX;

                    int tempY = l.startY;
                    l.startY = l.endY;
                    l.endY = tempY;
                }
                for (int i = l.startY; i <= l.endY; i++) {
                    for (int j = l.startX; j <= l.endX; j++) {
                        field[i][j]++;
                    }
                }
            } else {
                LinkedList<Integer> xValues = Arrays.stream(IntStream.range(Math.min(l.startX, l.endX), Math.max(l.startX + 1, l.endX + 1))
                                .map(i -> l.startX <= l.endX ? i : l.endX - i + l.startX)
                                .toArray())
                                .boxed()
                                .collect(Collectors.toCollection(LinkedList::new));
                LinkedList<Integer> yValues = Arrays.stream(IntStream.range(Math.min(l.startY, l.endY), Math.max(l.startY + 1, l.endY + 1))
                                .map(i -> l.startY <= l.endY ? i : l.endY - i + l.startY)
                                .toArray())
                                .boxed()
                                .collect(Collectors.toCollection(LinkedList::new));
                while (!yValues.isEmpty() && !xValues.isEmpty()) {
                    field[yValues.poll()][xValues.poll()]++;
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
    }
}

class Line2 {
    public int startX;
    public int startY;
    public int endX;
    public int endY;
}
