/* package core_search;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class FILOQueue<S, A> extends MyPriorityQueue<S, A> implements Queue<Node<int[][], String>> {
    private final LinkedList<Node<S, A>> stack = new LinkedList<>();  // Use a stack for DFS

    @Override
    public void add(Node<S, A> node) {
        stack.addFirst(node);  // FILO (stack) behavior
    }

    @Override
    public Node<S, A> pop() {
        return stack.pollFirst();  // Remove the last inserted element (LIFO)
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Node<int[][], String>> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Node<int[][], String> stringNode) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Node<int[][], String>> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(Node<int[][], String> stringNode) {
        return false;
    }

    @Override
    public Node<int[][], String> remove() {
        return null;
    }

    @Override
    public Node<int[][], String> poll() {
        return null;
    }

    @Override
    public Node<int[][], String> element() {
        return null;
    }

    @Override
    public Node<int[][], String> peek() {
        return null;
    }
}
*/