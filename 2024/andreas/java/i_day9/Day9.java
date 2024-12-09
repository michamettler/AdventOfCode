package i_day9;

import util.InputReader;
import util.LinkedList.LL;
import util.LinkedList.LLNode;
import util.Util;

import java.util.Arrays;
import java.util.List;

public class Day9 {

    private static class Block {
        public boolean isFree;
        public int amount;
        public int id;

        public Block(boolean isFree, int amount, int id) {
            this.isFree = isFree;
            this.amount = amount;
            this.id = id;
        }

        @Override
        public String toString() {
            if (isFree) {
                return ".".repeat(amount);
            }
            return ("" + id).repeat(amount);
        }
    }

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        List<String> input = InputReader.readInput();
        if (input.size() != 1) {
            throw new RuntimeException("wrong input format");
        }

        int[] in = Util.toSingleDigitIntArray(input.get(0));
        int t = Arrays.stream(in).reduce(Integer::sum).orElseThrow();
        int[] map = new int[t];

        int c = 0;
        int idC = 0;
        for (int i = 0; i < in.length; i++) {
            if (i % 2 == 0) {
                // current is block
                for (int k = c; k < c + in[i]; k++) {
                    map[k] = idC;
                }
                idC++;
            } else {
                // current is free
                for (int k = c; k < c + in[i]; k++) {
                    map[k] = -1;
                }
            }
            c += in[i];
        }

        // compress
        int lp = 0;
        int rp = map.length - 1;
        while (lp < rp) {
            while (map[lp] != -1 && lp < rp) {
                // search for first free spot
                lp++;
            }
            while (map[rp] == -1 && lp < rp) {
                // search for first element from right
                rp--;
            }

            if (lp >= rp) {
                break;
            }
            // swap
            int z = map[rp];
            map[rp] = map[lp];
            map[lp] = z;
        }

        long res = 0L;
        for (int i = 0; i < map.length && map[i] != -1; i++) {
            res += (long)map[i] * i;
        }

        System.out.println(res);
    }

    public static void part2() {
        List<String> input = InputReader.readInput();
        if (input.size() != 1) {
            throw new RuntimeException("wrong input format");
        }

        int[] in = Util.toSingleDigitIntArray(input.get(0));
        LL<Block> ll = new LL<>();
        for (int i = 0; i < in.length; i++) {
            ll.append(new Block(i % 2 == 1, in[i], i / 2));
        }

        LLNode<Block> backPointer = ll.tail;
        while (!backPointer.equals(ll.head)) {
            if (backPointer.val.isFree) {
                backPointer = backPointer.prev;
                continue;
            }
            // for each element from the back try to insert it somewhere before (try to insert it in some free part)
            LLNode<Block> headPointer = ll.head;
            while (!headPointer.equals(backPointer)) {
                if (headPointer.val.isFree && headPointer.val.amount >= backPointer.val.amount) {
                    // Has enough space
                    LLNode<Block> prev = headPointer.prev;
                    LLNode<Block> next = headPointer.next;
                    ll.remove(headPointer);
                    ll.insert(prev, next, new Block(false, backPointer.val.amount, backPointer.val.id));
                    ll.insert(prev.next, next, new Block(true, headPointer.val.amount - backPointer.val.amount, -1));
                    backPointer.val.isFree = true;
                    if (backPointer.next != null && backPointer.next.val.isFree) {
                        // do coalescing
                        backPointer.val.amount += backPointer.next.val.amount;
                        ll.remove(backPointer.next);
                    }
                    if (backPointer.prev != null && backPointer.prev.val.isFree) {
                        // do coalescing
                        backPointer.val.amount += backPointer.prev.val.amount;
                        ll.remove(backPointer.prev);
                    }
                    break;
                }
                headPointer = headPointer.next;
            }
            backPointer = backPointer.prev;
        }

        long res = 0;
        long c = 0;
        LLNode<Block> iter = ll.head;
        while (iter != null) {
            for (int i = 0; i < iter.val.amount; i++) {
                if (!iter.val.isFree) {
                    res += c * iter.val.id;
                }
                c++;
            }
            iter = iter.next;
        }

        Block[] ref = new Block[ll.size];
        LL.asArray(ll, ref);
        System.out.println(res);
    }
}
