package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;
import search_problems.PuzzleConfig;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class SlidingTile_AStar extends BaseSearch<int[][], String> {

    private final String heuristicType;

    // Constructor with heuristic type
    public SlidingTile_AStar(String heuristicType) {
        // Initialize SlidingTilePuzzle with PuzzleConfig states
        super(new SlidingTilePuzzle(PuzzleConfig.INITIAL_STATE, PuzzleConfig.GOAL_STATE),
                new SortedQueue<>(new CompareDistances(new SlidingTilePuzzle(PuzzleConfig.INITIAL_STATE, PuzzleConfig.GOAL_STATE), heuristicType)));
        this.heuristicType = heuristicType;
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

    // Comparator for A* to consider both the path cost and heuristic estimate
    public static class CompareDistances implements Comparator<Node<int[][], String>> {
        private final SlidingTilePuzzle problem;
        private final String heuristicType;

        public CompareDistances(SlidingTilePuzzle problem, String heuristicType) {
            this.problem = problem;
            this.heuristicType = heuristicType;
        }

        @Override
        public int compare(Node<int[][], String> o1, Node<int[][], String> o2) {
            // Get heuristic distance for both nodes
            int h1 = problem.getEstimatedDistance(o1.getState(), heuristicType);
            int h2 = problem.getEstimatedDistance(o2.getState(), heuristicType);
            // Get the path cost for both nodes
            int cost1 = o1.getPathCost();
            int cost2 = o2.getPathCost();
            // Compare the sum of path cost and heuristic distance (A* formula)
            return Integer.compare(h1 + cost1, h2 + cost2);
        }
    }

    public static void main(String[] args) {
        SlidingTile_AStar solver = new SlidingTile_AStar("misplacedTiles"); // Or "sumOfDistances"
        solver.search();
    }
}
