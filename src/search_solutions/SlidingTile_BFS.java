package search_solutions;

import core_search.BaseSearch;
import core_search.FIFOQueue;
import core_search.Node;
import core_search.Tuple;
import search_problems.SlidingTilePuzzle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class SlidingTile_BFS extends BaseSearch<int[][], String> {

    public SlidingTile_BFS(int[][] initialState, int[][] goalState) {
        super(new SlidingTilePuzzle(initialState, goalState), new FIFOQueue<>());
    }

    @Override
    public void search() {
        Node<int[][], String> initialNode = new Node<>(problem.initialState(), null, 0, null);
        frontier.add(initialNode);

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.deepToString(problem.initialState()));

        while (!frontier.isEmpty()) {
            Node<int[][], String> node = frontier.poll();
            int[][] currentState = node.getState();
            printState(node);

            if (Arrays.deepEquals(currentState, problem.goalState())) {
                printGoal();
                return;
            }

            List<Tuple<int[][], String>> successors = problem.execution(currentState);
            for (Tuple<int[][], String> succ : successors) {
                int[][] succState = succ.getState();
                String action = succ.getAction();
                String stateKey = Arrays.deepToString(succState);

                if (!visited.contains(stateKey)) {
                    Node<int[][], String> succNode = new Node<>(succState, action, node.getPathCost() + succ.getCost(), node);
                    frontier.add(succNode);
                    visited.add(stateKey);

                    printAction(action);
                    printState(succNode);
                }
            }
        }
        printNoSolution();
    }

    public static void main(String[] args) {
        int[][] initialState = {
                {7, 2, 4},
                {5, 0, 6},
                {8, 3, 1}
        };
        int[][] goalState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        SlidingTile_BFS solver = new SlidingTile_BFS(initialState, goalState);
        solver.search();
    }
}
