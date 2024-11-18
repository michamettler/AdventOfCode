package com.company.micha.d_four;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class TaskOne {

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("AdventOfCode/src/com/company/micha/d_four/input.txt");
    Scanner sc = new Scanner(file);
    var boardList = new ArrayList<Integer[][]>();

    List<Integer> inputNumbers = Arrays.stream(Stream.of(sc.nextLine().split(","))
        .map(Integer::valueOf).toArray(Integer[]::new)).toList();

    while (sc.hasNextLine()) {
      var board = new Integer[5][5];
      sc.nextLine();
      for (int i = 0; i < 5; i++) {
        String value = sc.nextLine();
        var numbers = Arrays.stream(value.split(" +")).filter(e -> !e.equals("")).toArray(String[]::new);
        Integer[] mapped = Stream.of(numbers).map(Integer::valueOf).toArray(Integer[]::new);
        System.arraycopy(mapped, 0, board[i], 0, mapped.length);
      }
      boardList.add(board);
    }

    System.out.println(evaluateWinner(inputNumbers, boardList));

  }

  private static Integer evaluateWinner(List<Integer> inputNumbers, ArrayList<Integer[][]> boardList) {
    var numbersInGame = new ArrayList<Integer>();
    for (var inputNumber : inputNumbers) {
      numbersInGame.add(inputNumber);
      for (var board : boardList) {
        for (int i = 0; i < 5; i++) {
          var rowNumbers = new ArrayList<Integer>();
          var columnNumbers = new ArrayList<Integer>();
          for (int j = 0; j < 5; j++) {
            rowNumbers.add(board[i][j]);
            columnNumbers.add(board[j][i]);
          }
          if (numbersInGame.containsAll(columnNumbers) || numbersInGame.containsAll(rowNumbers))
            return evaluateScore(board, numbersInGame, inputNumber);
        }
      }
    }
    return null;
  }

  private static Integer evaluateScore(Integer[][] board, ArrayList<Integer> numbersInGame, Integer inputNumber) {
    var uncheckedNumbers = new ArrayList<Integer>();
    for (int i = 0; i < 5; i++) {
      var rowNumbers = new ArrayList<>(Arrays.asList(board[i]).subList(0, 5));
      for (var rowNumber : rowNumbers) {
        if (!numbersInGame.contains(rowNumber)) {
          uncheckedNumbers.add(rowNumber);
        }
      }
    }
    int sum = 0;
    for (Integer number : uncheckedNumbers)
      sum += number;
    return sum * inputNumber;
  }
}
