package com.company.micha.e_five;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/e_five/input.txt");
    Scanner sc = new Scanner(file);

    var diagram = new BigInteger[1000][1000];

    while (sc.hasNextLine()) {
      String value = sc.nextLine();
      var range = value.split(" -> ");
      Integer[] firstCoordinates = Stream.of(range[0].split(",")).map(Integer::valueOf).toArray(Integer[]::new);
      Integer[] secondCoordinates = Stream.of(range[1].split(",")).map(Integer::valueOf).toArray(Integer[]::new);

      int differenceX = Math.abs(firstCoordinates[0] - secondCoordinates[0]);
      int differenceY = Math.abs(secondCoordinates[1] - firstCoordinates[1]);

      if (differenceX == 0) {
        int x = firstCoordinates[0];
        List<Integer> yRange = firstCoordinates[1] < secondCoordinates[1] ?
            IntStream.rangeClosed(firstCoordinates[1], secondCoordinates[1]).boxed().toList() :
            IntStream.rangeClosed(secondCoordinates[1], firstCoordinates[1]).boxed().toList();

        for (var y : yRange) {
          diagram[y][x] = diagram[y][x] != null ? diagram[y][x].add(BigInteger.ONE) : BigInteger.ONE;
        }
      } else if (differenceY == 0) {
        int y = firstCoordinates[1];
        List<Integer> xRange = firstCoordinates[0] < secondCoordinates[0] ?
            IntStream.rangeClosed(firstCoordinates[0], secondCoordinates[0]).boxed().toList() :
            IntStream.rangeClosed(secondCoordinates[0], firstCoordinates[0]).boxed().toList();

        for (var x : xRange) {
          diagram[y][x] = diagram[y][x] != null ? diagram[y][x].add(BigInteger.ONE) : BigInteger.ONE;
        }
      }
    }

    int dangerCount = 0;
    for (BigInteger[] integers : diagram) {
      for (int j = 0; j < diagram.length; j++) {
        if (integers[j] != null && integers[j].compareTo(new BigInteger("2")) >= 0)
          dangerCount++;
      }
    }
    System.out.println(dangerCount);
  }
}