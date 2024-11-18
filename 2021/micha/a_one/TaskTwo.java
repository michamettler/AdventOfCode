package com.company.micha.a_one;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskTwo {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/a_one/input.txt");
    Scanner sc = new Scanner(file);
    var measurements = new ArrayList<BigDecimal>();
    var resultList = new ArrayList<Boolean>();

    while (sc.hasNextLine())
      measurements.add(new BigDecimal(sc.nextLine()));

    var mappedList = new ArrayList<BigDecimal>();
    for (int i = 0; i < measurements.size() - 2; i++) {
      BigDecimal sum = measurements.get(i).add(measurements.get(i + 1)).add(measurements.get(i + 2));
      mappedList.add(sum);
    }

    // old code
    for (int i = 1; i < mappedList.size(); i++) {
      resultList.add(
          mappedList.get(i).compareTo(mappedList.get(i - 1)) > 0
      );
    }

    System.out.println(resultList.stream().filter(value -> value).toList().size());
  }
}
