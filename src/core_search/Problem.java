package core_search;

import java.util.List;

public interface Problem<S, A> {
    S initialState();
    S goalState();
    List<Tuple<S, A>> execution(S state);
    void printState(S state);

    boolean equals(S state, S s);
}