package search_solutions;

import core_search.BaseSearch;
import core_search.FILOQueue;
import core_search.Node;
import search_problems.SlidingTilePuzzle;
import core_search.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingTile_DFS extends BaseSearch<int[][], String> {

    public SlidingTile_DFS(int[][] initialState, int[][] goalState) {
        super(new SlidingTilePuzzle(initialState, goalState), new FILOQueue<>());
    }

    @Override
    public void search() {
        Node<int[][], String> startNode = new Node<>(problem.initialState(), null, 0, null, true); // Using DFS constructor
        frontier.add(startNode);

        Set<int[][]> visited = new HashSet<>();

        while (!frontier.isEmpty()) {
            Node<int[][], String> currentNode = frontier.remove();
            printState(currentNode);

            if (problem.equals(currentNode.getState(), problem.goalState())) {
                printGoal();
                return;
            }

            visited.add(currentNode.getState());

            List<Tuple<int[][], String>> successors = problem.execution(currentNode.getState());

            for (Tuple<int[][], String> successor : successors) {
                int[][] successorState = successor.getState();
                if (!visited.contains(successorState)) {
                    Node<int[][], String> successorNode = new Node<>(successorState, successor.getAction(), currentNode.getDepth() + 1, currentNode, true);
                    frontier.add(successorNode);
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
