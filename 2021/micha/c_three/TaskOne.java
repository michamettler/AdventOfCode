package com.company.micha.c_three;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/c_three/input.txt");
    Scanner sc = new Scanner(file);
    var consumptionData = new ArrayList<List<Integer>>();
    StringBuilder gamma = new StringBuilder();
    StringBuilder epsilon = new StringBuilder();

    while (sc.hasNextLine()) {
      String[] numbers = sc.nextLine().split("");
      consumptionData.add(
          Arrays.stream(Stream.of(numbers).map(Integer::valueOf).toArray(Integer[]::new)).toList()
      );
    }

    for (int i = 0; i < 12; i++) {
      var cntrOne = BigInteger.ZERO;
      var cntrZero = BigInteger.ZERO;
      for (List<Integer> integers : consumptionData) {
        if (integers.get(i) == 1) {
          cntrOne = cntrOne.add(BigInteger.ONE);
        } else {
          cntrZero = cntrZero.add(BigInteger.ONE);
        }
      }
      if (cntrOne.compareTo(cntrZero) > 0) {
        gamma.append("1");
        epsilon.append("0");
      } else {
        gamma.append("0");
        epsilon.append("1");
      }
    }

    System.out.println(Integer.parseInt(String.valueOf(gamma), 2) * Integer.parseInt(String.valueOf(epsilon), 2));
  }
}
