package search_problems;

import core_search.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SlidingTilePuzzle implements Problem<int[][], String> {
    private final int[][] initialState;
    private final int[][] goalState;

    public SlidingTilePuzzle(int[][] initialState, int[][] goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    @Override
    public int[][] initialState() {
        return this.initialState;
    }

    @Override
    public int[][] goalState() {
        return this.goalState;
    }

    @Override
    public List<Tuple<int[][], String>> execution(int[][] state) {
        List<Tuple<int[][], String>> successors = new ArrayList<>();
        int size = state.length;
        int blankRow = -1, blankCol = -1;

        // Find the blank tile (0)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (state[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String[] moveNames = { "Up", "Down", "Left", "Right" }; // Action names as Strings

        for (int i = 0; i < directions.length; i++) {
            int newRow = blankRow + directions[i][0];
            int newCol = blankCol + directions[i][1];

            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                int[][] newState = copyState(state);
                newState[blankRow][blankCol] = newState[newRow][newCol];
                newState[newRow][newCol] = 0;

                String action = moveNames[i];
                int cost = 1;
                successors.add(new Tuple<>(newState, action, cost));
            }
        }
        return successors;
    }

    private int[][] copyState(int[][] state) {
        int size = state.length;
        int[][] newState = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(state[i], 0, newState[i], 0, size);
        }
        return newState;
    }

    @Override
    public void printState(int[][] state) {
        for (int[] row : state) {
            for (int tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean equals(int[][] state, int[][] goalState) {
        return Arrays.deepEquals(state, goalState);
    }

    // Heuristic 1: Misplaced tiles
    public int misplacedTiles(int[][] state) {
        int h = 0;
        int n = state.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (state[i][j] != 0 && state[i][j] != goalState[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    // Heuristic 2: Sum of distances
    public int sumOfDistances(int[][] state) {
        int h = 0;
        int n = state.length;
        int goalRow, goalCol, currentRow, currentCol;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = state[i][j];
                if (value != 0) {
                    goalRow = (value - 1) / n;
                    goalCol = (value - 1) % n;
                    currentRow = i;
                    currentCol = j;
                    h += Math.abs(goalRow - currentRow) + Math.abs(goalCol - currentCol);
                }
            }
        }
        return h;
    }

    public int getEstimatedDistance(int[][] state, String heuristicType) {
        if (heuristicType.equals("misplacedTiles")) {
            return misplacedTiles(state);
        } else if (heuristicType.equals("sumOfDistances")) {
            return sumOfDistances(state);
        } else {
            throw new IllegalArgumentException("Unknown heuristic type");
        }
    }
}
