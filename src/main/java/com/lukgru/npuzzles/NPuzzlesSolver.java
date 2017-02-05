package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.algorithm.SolvabilityVerification;
import com.lukgru.npuzzles.heuristic.BoardHeuristicEvaluator;
import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Lukasz on 21.01.2017.
 */
//TODO: allow only square boards!!!
//TODO: handle case when state is present in open/closed but with shorter path
public class NPuzzlesSolver {

    private BoardHeuristicEvaluator heuristicEvaluator;
    private BoardPieceMover mover = new BoardPieceMover();
    private SolvabilityVerification solvabilityVerification = new SolvabilityVerification();

    public NPuzzlesSolver(Heuristic heuristic) {
        this.heuristicEvaluator = new BoardHeuristicEvaluator(heuristic);
    }

    public List<Step> solve(Board board, Board target) {
        heuristicEvaluator.setTargetState(target);
        if (!solvabilityVerification.isSolvable(board, target)) {
            throw new RuntimeException("Puzzle is not solvable.");
        }

        Queue<Step> open = new PriorityQueue<>((s1, s2) -> compareBoards(s1.getState(), s2.getState()));
        HashSet<Board> closed = new HashSet<>();
        HashSet<Board> openHashSet = new HashSet<>();
        open.add(new Step(board, null));
        openHashSet.add(board);
        Step currentStep = open.poll();
        while (currentStep != null && !currentStep.getState().equals(target)) {
            closed.add(currentStep.getState());
            addToOpenIfPossible(mover.fillGapByPieceFromUp(currentStep.getState()), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromDown(currentStep.getState()), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromLeft(currentStep.getState()), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromRight(currentStep.getState()), currentStep, open, openHashSet, closed);
            currentStep = open.poll();
        }

        return currentStep.getAllSteps();
    }

    private void addToOpenIfPossible(Board state, Step previousStep, Queue<Step> open, HashSet<Board> openHashSet, HashSet<Board> closed) {
        boolean isInOpen = openHashSet.contains(state);
        boolean isInClosed = closed.contains(state);
        if (!isInOpen && !isInClosed) {
            open.add(new Step(state, previousStep));
            openHashSet.add(state);
        }
    }

    private int compareBoards(Board b1, Board b2) {
        int b1Heuristic = heuristicEvaluator.evaluate(b1);
        int b2Heuristic = heuristicEvaluator.evaluate(b2);
        return b1Heuristic - b2Heuristic;
    }
}
