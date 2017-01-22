package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;

import java.util.List;

/**
 * Created by Lukasz on 21.01.2017.
 */
public class NPuzzlesSolver {

    private Heuristic heuristic;

    public NPuzzlesSolver(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public List<Move> solve(Board board) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
