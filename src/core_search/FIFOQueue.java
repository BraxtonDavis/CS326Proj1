package core_search;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOQueue<S, A> implements Queue<Node<S, A>> {
    private final LinkedList<Node<S, A>> queue = new LinkedList<>();

    @Override
    public boolean offer(Node<S, A> node) {
        queue.addLast(node);
        return true;
    }

    @Override
    public Node<S, A> remove() {
        return null;
    }

    @Override
    public Node<S, A> poll() {
        return queue.pollFirst();
    }

    @Override
    public Node<S, A> element() {
        return null;
    }

    @Override
    public Node<S, A> peek() {
        return queue.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean add(Node<S, A> node) {
        return queue.add(node);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Node<S, A>> c) {
        return queue.addAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public java.util.Iterator<Node<S, A>> iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }
}
