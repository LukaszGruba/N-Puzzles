package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.model.Board;

import java.util.List;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        Board board = new BoardBuilder(args).build();
        List<Move> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board);
        new SolutionPrinter(System.out).print(solution);
    }

}
