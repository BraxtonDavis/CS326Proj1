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

public class SlidingTile_AStar extends BaseSearch<int[][], String> {

    public SlidingTile_AStar(int[][] initialState, int[][] goalState) {
        super(new SlidingTilePuzzle(initialState, goalState),
                new SortedQueue<>(new CompareDistances(new SlidingTilePuzzle(initialState, goalState)))
        );
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

    public static class CompareDistances implements Comparator<Node<int[][], String>> {
        private final SlidingTilePuzzle problem;

        public CompareDistances(SlidingTilePuzzle problem) {
            this.problem = problem;
        }

        @Override
        public int compare(Node<int[][], String> o1, Node<int[][], String> o2) {
            int f1 = o1.getPathCost() + problem.heuristic(o1.getState());
            int f2 = o2.getPathCost() + problem.heuristic(o2.getState());
            return Integer.compare(f1, f2);
        }
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

        SlidingTile_AStar solver = new SlidingTile_AStar(initialState, goalState);
        solver.search();
    }
}
