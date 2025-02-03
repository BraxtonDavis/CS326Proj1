package search_solutions;

import core_search.BaseSearch;
import core_search.FILOQueue;
import core_search.Node;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class SlidingTile_DFS extends BaseSearch<int[][], String> {

    public SlidingTile_DFS(int[][] initialState, int[][] goalState) {
        super(new SlidingTilePuzzle(initialState, goalState), new FILOQueue<>());
    }

    @Override
    public void search() {
        Node<int[][], String> startNode = new Node<>(problem.initialState(), null, 0, null, true); // Using DFS constructor
        frontier.add(startNode);

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.deepToString(problem.initialState()));

        while (!frontier.isEmpty()) {
            Node<int[][], String> currentNode = frontier.remove();
            int[][] currentState = currentNode.getState();
            printState(currentNode);

            if (Arrays.deepEquals(currentState, problem.goalState())) {
                printGoal();
                return;
            }

            List<Tuple<int[][], String>> successors = problem.execution(currentState);

            for (Tuple<int[][], String> successor : successors) {
                int[][] successorState = successor.getState();
                String stateKey = Arrays.deepToString(successorState);

                if (!visited.contains(stateKey)) {
                    visited.add(stateKey);

                    Node<int[][], String> successorNode = new Node<>(successorState, successor.getAction(), currentNode.getDepth() + 1, currentNode, true);
                    frontier.add(successorNode);

                    printAction(successor.getAction());
                    printState(successorNode);
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

        SlidingTile_DFS solver = new SlidingTile_DFS(initialState, goalState);
        solver.search();
    }
}
