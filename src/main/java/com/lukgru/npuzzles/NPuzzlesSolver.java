package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;

import java.util.ArrayList;
import java.util.Collection;
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

    public List<Step> solve(Board board, Board target) {
        heuristicEvaluator.setTargetState(target);

        Queue<Step> open = new PriorityQueue<>((s1, s2) -> compareBoards(s1.getState(), s2.getState()));
        List<Step> closed = new ArrayList<>();
        open.add(new Step(board, null));
        Step currentStep = open.poll();

        while (currentStep != null && !currentStep.getState().equals(target)) {
            closed.add(currentStep);
            addToOpenIfPossible(mover.fillGapByPieceFromUp(currentStep.getState()), currentStep, open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromDown(currentStep.getState()), currentStep, open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromLeft(currentStep.getState()), currentStep, open, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromRight(currentStep.getState()), currentStep, open, closed);
            currentStep = open.poll();
        }

        return currentStep.getAllSteps();
    }

    private void addToOpenIfPossible(Board state, Step previousStep, Queue<Step> open, List<Step> closed) {
        boolean isInOpen = contains(state, open);
        boolean isInClosed = contains(state, closed);
        if (!isInOpen && !isInClosed) {
            open.add(new Step(state, previousStep));
        }
    }

    private boolean contains(Board state, Collection<Step> open) {
        return open.stream().map(Step::getState).anyMatch(state::equals);
    }

    private int compareBoards(Board b1, Board b2) {
        int b1Heuristic = heuristicEvaluator.evaluate(b1);
        int b2Heuristic = heuristicEvaluator.evaluate(b2);
        return b1Heuristic - b2Heuristic;
    }
}
