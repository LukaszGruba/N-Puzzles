package com.lukgru.npuzzles.heuristic;

import com.lukgru.npuzzles.algorithm.PiecePositionFinder;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Position;

/**
 * Created by Lukasz on 02.02.2017.
 */
public class BoardHeuristicEvaluator {

    private PiecePositionFinder positionFinder = new PiecePositionFinder();
    private Heuristic heuristic;
    private Board targetState;

    public BoardHeuristicEvaluator(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public int evaluate(Board board) {
        return board.piecesStream()
                .mapToInt(piece -> {
                    Position p1 = piece.getPosition();
                    Position p2 = positionFinder.getElementPosition(targetState.getBoardArray(), piece.getValue());
                    return heuristic.estimateDistance(p1, p2);
                }).sum();
    }

    public void setTargetState(Board targetState) {
        this.targetState = targetState;
    }
}