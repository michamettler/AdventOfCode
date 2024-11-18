package com.company.micha.b_two;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class TaskTwo {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/b_two/input.txt");
    Scanner sc = new Scanner(file);
    var navigations = new ArrayList<Navigator>();

    while (sc.hasNextLine()) {
      String[] inputString = sc.nextLine().split(" ");
      Navigator nav = new Navigator(inputString[0], new BigInteger(inputString[1]));
      navigations.add(nav);
    }

    BigInteger depth = BigInteger.ZERO;
    BigInteger horizontalValue = BigInteger.ZERO;
    BigInteger aim = BigInteger.ZERO;
    for (var nav : navigations) {
      if (nav.direction.equals("up")) {
        aim = aim.subtract(nav.unit);
      } else if (nav.direction.equals("down")) {
        aim = aim.add(nav.unit);
      } else {
        horizontalValue = horizontalValue.add(nav.unit);
        depth = depth.add(aim.multiply(nav.unit));
      }
    }

    System.out.println(depth.multiply(horizontalValue));

  }
}

