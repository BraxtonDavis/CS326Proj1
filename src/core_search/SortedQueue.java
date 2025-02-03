package core_search;

import java.util.*;

public class SortedQueue<S, A> extends MyPriorityQueue<S, A> implements Queue<Node<S, A>> {
    private final PriorityQueue<Node<S, A>> queue;

    public SortedQueue(Comparator<Node<S, A>> c) {
        this.queue = new PriorityQueue<>(c);
    }

    @Override
    public boolean add(Node<S, A> e) {
        return queue.add(e);
    }

    @Override
    public Node<S, A> pop() {
        return queue.poll();
    }

    @Override
    public Node<S, A> poll() {
        return queue.poll();
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
    public Iterator<Node<S, A>> iterator() {
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

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Node<S, A>> c) {
        return queue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean offer(Node<S, A> e) {
        return queue.offer(e);
    }

    @Override
    public Node<S, A> remove() {
        return queue.remove();
    }

    @Override
    public Node<S, A> peek() {
        return queue.peek();
    }

    @Override
    public Node<S, A> element() {
        return queue.element();
    }
}
