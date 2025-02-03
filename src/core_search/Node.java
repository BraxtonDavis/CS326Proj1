package core_search;

import java.util.Objects;

public class Node<S, A> {
    private final S state;
    private final A action;
    private final int pathCost;
    private final Node<S, A> parent;

    public Node(S state, A action, int pathCost, Node<S, A> parent) {
        this.state = state;
        this.action = action;
        this.pathCost = pathCost;
        this.parent = parent;
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

    public Node<S, A> getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return pathCost == node.pathCost && Objects.equals(state, node.state) && Objects.equals(action, node.action) && Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, action, pathCost, parent);
    }
}
