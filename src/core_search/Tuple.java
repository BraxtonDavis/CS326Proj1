package core_search;

import java.util.Objects;

public class Tuple<S, A> {
    private final S state;
    private final A action;
    private final int cost;

    public Tuple(S state, A action, int cost) {
        this.state = state;
        this.action = action;
        this.cost = cost;
    }

    public S getState() {
        return state;
    }

    public A getAction() {
        return action;
    }

    public int getCost() {
        return cost;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return cost == tuple.cost && Objects.equals(state, tuple.state) && Objects.equals(action, tuple.action);
    }

    public int hashCode() {
        return Objects.hash(state, action, cost);
    }
}