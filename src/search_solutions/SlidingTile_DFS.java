package search_solutions;

import core_search.BaseSearch;
import core_search.FILOQueue;
import core_search.Node;
import core_search.Tuple;
import search_problems.SlidingTilePuzzle;
import search_problems.PuzzleConfig;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class SlidingTile_DFS extends BaseSearch<int[][], String> {

    public SlidingTile_DFS() {
        super(new SlidingTilePuzzle(PuzzleConfig.INITIAL_STATE, PuzzleConfig.GOAL_STATE), new FILOQueue<>());
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
            Collections.reverse(successors);

            for (Tuple<int[][], String> succ : successors) {
                int[][] succState = succ.getState();
                String action = succ.getAction();
                String stateKey = Arrays.deepToString(succState);

                if (!visited.contains(stateKey)) {
                    Node<int[][], String> succNode = new Node<>(succState, action, node.getDepth() + 1, node);
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

    public static void main(String[] args) {
        SlidingTile_DFS solver = new SlidingTile_DFS();
        solver.search();
    }
}
