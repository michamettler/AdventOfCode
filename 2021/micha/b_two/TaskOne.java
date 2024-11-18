package com.company.micha.b_two;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/b_two/input.txt");
    Scanner sc = new Scanner(file);
    var navigations = new HashMap<String, List<BigInteger>>();

    while (sc.hasNextLine()) {
      String[] inputString = sc.nextLine().split(" ");

      if (!navigations.containsKey(inputString[0]))
        navigations.put(inputString[0], new ArrayList<>());

      navigations.get(inputString[0]).add(new BigInteger(inputString[1]));
    }

    Optional<BigInteger> downSum = navigations.get("down").stream().reduce(BigInteger::add);
    Optional<BigInteger> upSum = navigations.get("up").stream().reduce(BigInteger::add);
    Optional<BigInteger> forwardSum = navigations.get("forward").stream().reduce(BigInteger::add);

    if (downSum.isPresent() && upSum.isPresent() && forwardSum.isPresent()) {
      BigInteger depth = downSum.get().subtract(upSum.get());
      BigInteger horizontalPosition = forwardSum.get();
      System.out.println(depth.multiply(horizontalPosition));
    }
  }
}
