package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.Move;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.io.SolutionPrinter;
import com.lukgru.npuzzles.model.Board;

import java.util.List;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        Board board = getBoard();
        Board targetBoard = getTarget();
        List<Move> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board, targetBoard);
        new SolutionPrinter(System.out).print(solution);
    }

    private static Board getBoard() {
        System.out.println("Provide initial state of NxN matrix:");
        String[] boardLines = null; //TODO: read from input
        return new InputParser().parse(boardLines);
    }

    private static Board getTarget() {
        System.out.println("\nProvide target state of NxN matrix:");
        String[] targetLines = null; //TODO: read from input
        return new InputParser().parse(targetLines);
    }

}
