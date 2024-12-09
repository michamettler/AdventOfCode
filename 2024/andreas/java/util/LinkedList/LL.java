package util.LinkedList;

public class LL<T> {

    public int size = 0;
    public LLNode<T> head;
    public LLNode<T> tail;

    public void append(T v) {
        LLNode<T> n = new LLNode<>(v);
        if (size == 0) {
            head = n;
            tail = n;
            size = 1;
            return;
        }
        n.prev = tail;
        tail.next = n;
        tail = n;
        size++;
    }

    public void insert(LLNode<T> prev, LLNode<T> next, T val) {
        LLNode<T> n = new LLNode<>(val);
        if (prev == null && !next.equals(head)) {
            throw new RuntimeException();
        }
        if (next == null && !prev.equals(tail)) {
            throw new RuntimeException();
        }
        n.prev = prev;
        n.next = next;
        if (prev == null) {
            head = n;
        }
        if (next == null) {
            tail = n;
        }
        size++;
    }

    public void remove(LLNode<T> n) {
        if (n.equals(head)) {
            head.next.prev = null;
            head = head.next;
        } else if (n.equals(tail)) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            LLNode<T> prev = n.prev;
            LLNode<T> next = n.next;
            prev.next = next;
            next.prev = prev;
        }
        size--;
    }

    public static <T> LL<T> fromArray(T[] arr) {
        LL<T> ll = new LL<>();
        for (T v : arr) {
            ll.append(v);
        }
        return ll;
    }

    public static <T> void asArray(LL<T> ll, T[] ref) {
        if (ll.size != ref.length) {
            throw new RuntimeException();
        }
        int c = 0;
        LLNode<T> iter = ll.head;
        while (iter != null) {
            ref[c++] = iter.val;
            iter = iter.next;
        }
    }
}
