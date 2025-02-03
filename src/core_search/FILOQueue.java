package core_search;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class FILOQueue<S, A> implements Queue<Node<S, A>> {
    private final Stack<Node<S, A>> myQueue = new Stack<>();

    public Node<S, A> pop() {
        if (myQueue.isEmpty()) {
            return null;
        }
        return myQueue.pop();
    }

    @Override
    public boolean add(Node<S, A> e) {
        myQueue.push(e);
        return true;
    }

    @Override
    public int size() {
        return myQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return myQueue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return myQueue.contains(o);
    }

    @Override
    public Iterator<Node<S, A>> iterator() {
        return myQueue.iterator();
    }

    @Override
    public Object[] toArray() {
        return myQueue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return myQueue.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return myQueue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return myQueue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Node<S, A>> c) {
        return myQueue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return myQueue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return myQueue.retainAll(c);
    }

    @Override
    public void clear() {
        myQueue.clear();
    }

    @Override
    public boolean offer(Node<S, A> e) {
        return myQueue.push(e) != null;
    }

    @Override
    public Node<S, A> remove() {
        return myQueue.pop();
    }

    @Override
    public Node<S, A> poll() {
        return myQueue.isEmpty() ? null : myQueue.pop();
    }

    @Override
    public Node<S, A> element() {
        return myQueue.peek();
    }

    @Override
    public Node<S, A> peek() {
        return myQueue.peek();
    }
}
