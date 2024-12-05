package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    static Scanner sc = new Scanner(System.in);

    public static List<String> readInput() {
        List<String> result = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                return result;
            }
            result.add(line);
        }
    }

}
