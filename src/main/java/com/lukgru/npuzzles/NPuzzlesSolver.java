package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Lukasz on 21.01.2017.
 */
public class NPuzzlesSolver {

    private BoardHeuristicEvaluator heuristicEvaluator;
    private BoardPieceMover mover = new BoardPieceMover();

    public NPuzzlesSolver(Heuristic heuristic) {
        this.heuristicEvaluator = new BoardHeuristicEvaluator(heuristic);
    }

    public List<Board> solve(Board board, Board target) {
        heuristicEvaluator.setTargetState(target);

        List<Board> moves = new ArrayList<>();
        moves.add(board);

        Queue<Board> open = new PriorityQueue<>(this::compareBoards);
        List<Board> closed = new ArrayList<>();
        open.add(board);
        Board currentState = open.poll();

        while (currentState != null && !currentState.equals(target)) {
            closed.add(currentState);
            addToOpenIfPossible(mover.fillGapByPieceFromUp(currentState), open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromDown(currentState), open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromLeft(currentState), open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromRight(currentState), open, closed);
            currentState = open.poll();
        }
        moves.add(target);
        return moves;
    }

    private void addToOpenIfPossible(Board state, Queue<Board> open, List<Board> closed) {
        if (!open.contains(state) && !closed.contains(state)) {
            open.add(state);
        }
    }

    private int compareBoards(Board b1, Board b2) {
        int b1Heuristic = heuristicEvaluator.evaluate(b1);
        int b2Heuristic = heuristicEvaluator.evaluate(b2);
        return b1Heuristic - b2Heuristic;
    }
}
