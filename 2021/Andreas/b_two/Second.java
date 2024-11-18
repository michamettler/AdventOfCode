package com.company.andreas.b_two;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Second {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("C:\\Workarea\\diverses\\input.txt"));
        List<Entry> all = new ArrayList<>();
        while(in.hasNext()) {
            Entry e = new Entry();
            e.direction = in.next();
            e.step = in.nextLong();
            all.add(e);
        }
        in.close();

        long forward = 0;
        long depth = 0;
        long aim = 0;
        for (Entry e : all) {
            if (e.direction.equals("forward")) {
                forward += e.step;
                depth += aim * e.step;
            } else if (e.direction.equals("up")) {
                aim -= e.step;
            } else if (e.direction.equals("down")) {
                aim += e.step;
            }
        }
        System.out.println(forward * depth);
    }
}

class Entry {
    public String direction;
    public long step;
}
