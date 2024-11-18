package com.company.micha.c_three;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskTwo {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/c_three/input.txt");
    Scanner sc = new Scanner(file);
    var lifeSupportData = new ArrayList<List<Integer>>();

    while (sc.hasNextLine()) {
      String[] numbers = sc.nextLine().split("");
      lifeSupportData.add(
          Arrays.stream(Stream.of(numbers).map(Integer::valueOf).toArray(Integer[]::new)).toList()
      );
    }

    String oxygenAmount = evaluateLifeSupport(new ArrayList<>(lifeSupportData), 1);
    String carbonAmount = evaluateLifeSupport(new ArrayList<>(lifeSupportData), 0);
    System.out.println(Integer.parseInt(String.valueOf(oxygenAmount), 2) * Integer.parseInt(String.valueOf(carbonAmount), 2));
  }

  private static String evaluateLifeSupport(ArrayList<List<Integer>> lifeSupportData, int mode) {
    for (int i = 0; i < 12; i++) {
      if (lifeSupportData.size() > 1) {
        int index = i;
        var cntrOne = BigInteger.ZERO;
        var cntrZero = BigInteger.ZERO;

        for (List<Integer> integers : lifeSupportData) {
          if (integers.get(i) == 1) {
            cntrOne = cntrOne.add(BigInteger.ONE);
          } else {
            cntrZero = cntrZero.add(BigInteger.ONE);
          }
        }

        if (mode == 1) {
          if (cntrOne.compareTo(cntrZero) > 0) {
            lifeSupportData.removeIf(integers -> integers.get(index) == 1);
          } else if (cntrOne.compareTo(cntrZero) < 0) {
            lifeSupportData.removeIf(integers -> integers.get(index) == 0);
          } else {
            lifeSupportData.removeIf(integers -> integers.get(index) == mode);
          }
        } else {
          if (cntrOne.compareTo(cntrZero) > 0) {
            lifeSupportData.removeIf(integers -> integers.get(index) == 0);
          } else if (cntrOne.compareTo(cntrZero) < 0) {
            lifeSupportData.removeIf(integers -> integers.get(index) == 1);
          } else {
            lifeSupportData.removeIf(integers -> integers.get(index) == mode);
          }
        }

      }
    }
    return lifeSupportData.get(0).stream().map(String::valueOf)
        .collect(Collectors.joining(""));
  }

}
