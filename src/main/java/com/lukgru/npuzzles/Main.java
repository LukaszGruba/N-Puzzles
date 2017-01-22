package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.Move;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.io.SolutionPrinter;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.BoardBuilder;

import java.util.List;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        new InputParser(System.in).parse();
        String[] boardString = null; //TODO: extract from input
        String[] targetString = null;
        Board board = new BoardBuilder(boardString).build();
        Board targetBoard = new BoardBuilder(targetString).build();
        List<Move> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board, targetBoard);
        new SolutionPrinter(System.out).print(solution);
    }

}
