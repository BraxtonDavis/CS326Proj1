package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;
import search_problems.PuzzleConfig;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class SlidingTile_UCS extends BaseSearch<int[][], String> {

    public SlidingTile_UCS() {
        super(new SlidingTilePuzzle(PuzzleConfig.INITIAL_STATE, PuzzleConfig.GOAL_STATE),
                new SortedQueue<>(new ComparePathCost())
        );
    }

    @Override
    public void search() {
        Node<int[][], String> initialNode = new Node<>(problem.initialState(), null, 0, null);
        frontier.add(initialNode);

        HashMap<String, Integer> visited = new HashMap<>();
        visited.put(Arrays.deepToString(problem.initialState()), 0);

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
                int newCost = node.getPathCost() + succ.getCost();

                if (!visited.containsKey(stateKey) || newCost < visited.get(stateKey)) {
                    Node<int[][], String> succNode = new Node<>(succState, action, newCost, node);
                    frontier.add(succNode);
                    visited.put(stateKey, newCost);
                }
            }
        }
        printNoSolution();
    }

    private void printSolutionPath(Node<int[][], String> goalNode) {
        List<String> path = new ArrayList<>();
        Node<int[][], String> currentNode = goalNode;

        while (currentNode.getParent() != null) {
            path.add(currentNode.getAction());
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        System.out.println("Solution Path: " + String.join(" -> ", path));
    }

    public static class ComparePathCost implements Comparator<Node<int[][], String>> {
        @Override
        public int compare(Node<int[][], String> o1, Node<int[][], String> o2) {
            return Integer.compare(o1.getPathCost(), o2.getPathCost());
        }
    }

    public static void main(String[] args) {
        SlidingTile_UCS solver = new SlidingTile_UCS();
        solver.search();
    }
}