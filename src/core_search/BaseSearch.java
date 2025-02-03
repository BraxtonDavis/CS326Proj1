package core_search;

import search_problems.Problem;

import java.util.Queue;

public abstract class BaseSearch<S, A> {
    protected final Problem<S, A> problem;
    protected final Queue<Node<S, A>> frontier;

    public BaseSearch(Problem<S, A> problem, Queue<Node<S, A>> frontier) {
        this.problem = problem;
        this.frontier = frontier;
    }

    public abstract void search();


    protected void printGoal() {
        System.out.println("Goal reached!");
    }

    protected void printNoSolution() {
        System.out.println("No solution found");
    }
}
