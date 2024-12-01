package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public static List<String> readInput() {
        Scanner sc = new Scanner(System.in);
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
