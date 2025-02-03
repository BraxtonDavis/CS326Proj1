package search_solutions;

import core_search.BaseSearch;
import core_search.Node;

import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;

import java.util.Comparator;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class SlidingTile_UCS extends BaseSearch<int[][], String> {

    public SlidingTile_UCS(int[][] initialState, int[][] goalState) {
        super(new SlidingTilePuzzle(initialState, goalState), new SortedQueue<>(new ComparePathCost()));
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

        SlidingTile_UCS solver = new SlidingTile_UCS(initialState, goalState);
        solver.search();
    }

    public static class ComparePathCost implements Comparator<Node<int[][], String>> {
        @Override
        public int compare(Node<int[][], String> o1, Node<int[][], String> o2) {
            return Integer.compare(o1.getPathCost(), o2.getPathCost());
        }
    }
}
