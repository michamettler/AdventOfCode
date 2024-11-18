package com.company.micha.f_six;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskTwo {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/f_six/input.txt");
    Scanner sc = new Scanner(file);

    var fishList = new ArrayList<Integer>();
    while (sc.hasNextLine()) {
      String[] initialFishData = sc.nextLine().split(",");
      Arrays.asList(initialFishData).forEach(value -> fishList.add(Integer.parseInt(value)));
    }

    int days = 256;
    long totalFishCntr = 0;

    for (Integer fish : fishList) {
      var daysUntilReproduction = fish + 1;
      int reproductionDays = (days - daysUntilReproduction) / 7;

      long fishCntr = 2;

      for (int i = 0; i < reproductionDays; i++) {
        fishCntr = fishCntr * 2;
      }

      totalFishCntr += fishCntr;
    }

    System.out.println(totalFishCntr);
  }

}
