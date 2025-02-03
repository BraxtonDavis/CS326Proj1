package core_search;

import java.util.Objects;

public class Node<S, A> {
    private final S state;
    private final A action;
    private final int pathCost; // Used for BFS
    private final int depth;    // Used for DFS
    private final Node<S, A> parent;

    // Constructor for BFS (using pathCost)
    public Node(S state, A action, int pathCost, Node<S, A> parent) {
        this.state = state;
        this.action = action;
        this.pathCost = pathCost;
        this.parent = parent;
        this.depth = -1; // No depth in BFS
    }

    // Constructor for DFS (using depth)
    public Node(S state, A action, int depth, Node<S, A> parent, boolean isDFS) {
        this.state = state;
        this.action = action;
        this.depth = depth;
        this.parent = parent;
        this.pathCost = -1; // No pathCost in DFS
    }

    public S getState() {
        return state;
    }

    public A getAction() {
        return action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }

    public Node<S, A> getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return pathCost == node.pathCost && depth == node.depth && Objects.equals(state, node.state) && Objects.equals(action, node.action) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, action, pathCost, depth, parent);
    }
}
