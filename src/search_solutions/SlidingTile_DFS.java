package search_solutions;

import core_search.BaseSearch;
import core_search.FILOQueue;
import core_search.Node;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;
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
        Node<int[][], String> startNode = new Node<>(problem.initialState(), null, 0, null, true);
        frontier.add(startNode);

        Set<String> visited = new HashSet<>();
        visited.add(Arrays.deepToString(problem.initialState()));

        while (!frontier.isEmpty()) {
            Node<int[][], String> currentNode = frontier.remove();
            int[][] currentState = currentNode.getState();
            printState(currentNode);

            if (Arrays.deepEquals(currentState, problem.goalState())) {
                printGoal();
                printSolutionPath(currentNode);
                return;
            }

            List<Tuple<int[][], String>> successors = problem.execution(currentState);

            for (Tuple<int[][], String> successor : successors) {
                int[][] successorState = successor.getState();
                String stateKey = Arrays.deepToString(successorState);

                if (!visited.contains(stateKey)) {
                    visited.add(stateKey);

                    Node<int[][], String> successorNode = new Node<>(successorState, successor.getAction(),
                            currentNode.getDepth() + 1, currentNode, true);
                    frontier.add(successorNode);

                    printAction(successor.getAction());
                    printState(successorNode);
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

    public static void main(String[] args) {
        SlidingTile_DFS solver = new SlidingTile_DFS();
        solver.search();
    }
}
