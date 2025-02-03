package core_search;

public abstract class MyPriorityQueue<S, A> {
    public abstract boolean add(Node<S, A> node);
    public abstract Node<S, A> pop();
    public abstract boolean isEmpty();
}
