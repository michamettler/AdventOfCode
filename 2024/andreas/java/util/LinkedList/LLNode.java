package util.LinkedList;

public class LLNode<T> {

    public LLNode<T> next;
    public LLNode<T> prev;
    public T val;

    public LLNode(T val) {
        this.val = val;
    }
}
