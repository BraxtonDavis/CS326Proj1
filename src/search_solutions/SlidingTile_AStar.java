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
import java.util.ArrayList;
import java.util.Collections;

public class SlidingTile_AStar extends BaseSearch<int[][], String> {

    private final String heuristicType;

    public SlidingTile_AStar(String heuristicType) {
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

            if (Arrays.deepEquals(currentState, problem.goalState())) {
                printGoal();
                printSolutionPath(node);
                return;
            }

            List<Tuple<int[][], String>> successors = problem.execution(currentState);
            for (Tuple<int[][], String> succ : successors) {
                int[][] succState = succ.getState();
                String action = succ.getAction();
                String stateKey = Arrays.deepToString(succState);

                if (!visited.contains(stateKey)) {
                    int pathCost = node.getPathCost() + succ.getCost();
                    Node<int[][], String> succNode = new Node<>(succState, action, pathCost, node);
                    frontier.add(succNode);
                    visited.add(stateKey);
                }
            }
        }
        printNoSolution();
    }

    private void printSolutionPath(Node<int[][], String> goalNode) {
        List<Node<int[][], String>> path = new ArrayList<>();
        Node<int[][], String> currentNode = goalNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);

        System.out.println("Solution Path:");
        for (Node<int[][], String> node : path) {
            if (node.getAction() != null) {
                System.out.println("Move: " + node.getAction());
            }
            problem.printState(node.getState());
        }
    }

    public static class CompareDistances implements Comparator<Node<int[][], String>> {
        private final SlidingTilePuzzle problem;
        private final String heuristicType;

        public CompareDistances(SlidingTilePuzzle problem, String heuristicType) {
            this.problem = problem;
            this.heuristicType = heuristicType;
        }

        @Override
        public int compare(Node<int[][], String> o1, Node<int[][], String> o2) {
            int h1 = problem.getEstimatedDistance(o1.getState(), heuristicType);
            int h2 = problem.getEstimatedDistance(o2.getState(), heuristicType);
            int cost1 = o1.getPathCost();
            int cost2 = o2.getPathCost();
            return Integer.compare((cost1 + h1), (cost2 + h2));
        }
    }

    public static void main(String[] args) {
        SlidingTile_AStar solver = new SlidingTile_AStar("misplacedTiles");
        solver.search();
    }
}
