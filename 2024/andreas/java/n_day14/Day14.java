package n_day14;

import util.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 {

    private static class Robot {
        public int px, py, vx, vy;

        public Robot() {
        }

        public Robot(int px, int py, int vx, int vy) {
            this.px = px;
            this.py = py;
            this.vx = vx;
            this.vy = vy;
        }
    }

//    private static final int WIDTH = 11;
    private static final int WIDTH = 101;
//    private static final int HEIGHT = 7;
    private static final int HEIGHT = 103;

    public static void main(String[] args) throws InterruptedException {
        part2();
    }

    public static void part2() throws InterruptedException {
        List<String> input = InputReader.readInput();
        List<Robot> robots = new ArrayList<>();
        for (String s : input) {
            Robot r = new Robot();
            r.px = Integer.parseInt(s.split("=")[1].split(",")[0]);
            r.py = Integer.parseInt(s.split(",")[1].split(" ")[0]);
            r.vx = Integer.parseInt(s.split("=")[2].split(",")[0]);
            r.vy = Integer.parseInt(s.split(",")[2].trim());
            robots.add(r);
        }

        int c = 0;
        while (true) {
            robots = robots.stream().map(Day14::step).toList();
            c++;
            if (hasFilled5by5(robots)) {
                System.out.println((c) + ":" + "-".repeat(WIDTH + 20));
                printMap(robots);
                System.out.println("-".repeat(WIDTH + 20));
                break;
            }
        }
    }

    private static boolean hasFilled5by5(List<Robot> robots) {
        int[][] map = new int[HEIGHT][WIDTH];
        for (Robot r : robots) {
            map[r.py][r.px]++;
        }
        for (int i = 0; i < HEIGHT - 5; i++) {
            outer: for (int j = 0; j < WIDTH - 5; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (map[i + k][j + l] == 0) {
                            continue outer;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void part1() {
        List<String> input = InputReader.readInput();
        List<Robot> robots = new ArrayList<>();
        for (String s : input) {
            Robot r = new Robot();
            r.px = Integer.parseInt(s.split("=")[1].split(",")[0]);
            r.py = Integer.parseInt(s.split(",")[1].split(" ")[0]);
            r.vx = Integer.parseInt(s.split("=")[2].split(",")[0]);
            r.vy = Integer.parseInt(s.split(",")[2].trim());
            robots.add(r);
        }

        for (int i = 0; i < 100; i++) {
            robots = robots.stream().map(Day14::step).toList();
        }

        int[] q = new int[4];
        for (Robot r : robots) {
            if (quadrant(r) >= 0) {
                q[quadrant(r)]++;
            }
        }

        System.out.println(Arrays.stream(q).reduce((a,b) -> a * b).orElseThrow());

    }

    private static void printMap(List<Robot> robots) {
        int[][] map = new int[HEIGHT][WIDTH];
        for (Robot r : robots) {
            map[r.py][r.px]++;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static int quadrant(Robot r) {
        if (r.py < HEIGHT / 2) {
            if (r.px < WIDTH / 2) {
                return 0;
            }
            if (r.px > WIDTH / 2) {
                return 1;
            }
        }
        if (r.py > HEIGHT / 2) {
            if (r.px < WIDTH / 2) {
                return 2;
            }
            if (r.px > WIDTH / 2) {
                return 3;
            }
        }
        return -1;
    }

    private static Robot step(Robot r) {
        return new Robot(mod(r.px + r.vx, WIDTH), mod(r.py + r.vy, HEIGHT), r.vx, r.vy);
    }

    private static int mod(int v, int m) {
        if (v < 0) {
            return mod(v + m, m);
        }
        return v % m;
    }
}
