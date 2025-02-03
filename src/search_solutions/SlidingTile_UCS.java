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

        while (!frontier.isEmpty()) {
            Node<int[][], String> node = frontier.poll();
            int[][] currentState = node.getState();
            String stateKey = Arrays.deepToString(currentState);

            if (visited.containsKey(stateKey) && node.getPathCost() > visited.get(stateKey)) {
                continue;
            }

            visited.put(stateKey, node.getPathCost());

            if (Arrays.deepEquals(currentState, problem.goalState())) {
                printGoal();
                printSolutionPath(node);
                return;
            }

            List<Tuple<int[][], String>> successors = problem.execution(currentState);
            for (Tuple<int[][], String> succ : successors) {
                int[][] succState = succ.getState();
                String action = succ.getAction();
                String succKey = Arrays.deepToString(succState);
                int newCost = node.getPathCost() + succ.getCost();

                if (!visited.containsKey(succKey) || newCost < visited.get(succKey)) {
                    Node<int[][], String> succNode = new Node<>(succState, action, newCost, node);
                    frontier.add(succNode);
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
