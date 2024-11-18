package com.company.micha.f_six;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/f_six/input.txt");
    Scanner sc = new Scanner(file);

    var fishList = new ArrayList<Integer>();
    while (sc.hasNextLine()) {
      String[] initialFishData = sc.nextLine().split(",");
      Arrays.asList(initialFishData).forEach(value -> fishList.add(Integer.parseInt(value)));
    }

    for (int i = 0; i < 80; i++) {
      for (int j = 0; j < fishList.size(); j++) {
        if (fishList.get(j) != 0) {
          fishList.set(j, fishList.get(j) - 1);
        } else {
          fishList.set(j, 6);
          fishList.add(9);
        }
      }
    }
    System.out.println(fishList.size());
  }

}
