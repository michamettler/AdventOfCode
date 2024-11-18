package com.company.andreas.b_two;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class First {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\diverses\\input.txt"));
        List<Entry2> all = new ArrayList<>();
        while(in.hasNext()) {
            Entry2 e = new Entry2();
            e.direction = in.next();
            e.step = in.nextLong();
            all.add(e);
        }
        in.close();

        long forward = 0;
        long depth = 0;
        for (Entry2 e : all) {
            if (e.direction.equals("forward")) {
                forward += e.step;
            } else if (e.direction.equals("up")) {
                depth -= e.step;
            } else if (e.direction.equals("down")) {
                depth += e.step;
            }
        }
        System.out.println(forward * depth);
    }
}

class Entry2 {
    public String direction;
    public long step;
}
