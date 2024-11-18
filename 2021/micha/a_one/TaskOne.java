package com.company.micha.a_one;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/a_one/input.txt");
    Scanner sc = new Scanner(file);
    var measurements = new ArrayList<BigDecimal>();
    var resultList = new ArrayList<Boolean>();

    while (sc.hasNextLine())
      measurements.add(new BigDecimal(sc.nextLine()));

    for (int i = 1; i < measurements.size(); i++) {
      resultList.add(
          measurements.get(i).compareTo(measurements.get(i - 1)) > 0
      );
    }

    System.out.println(resultList.stream().filter(value -> value).toList().size());
  }
}
