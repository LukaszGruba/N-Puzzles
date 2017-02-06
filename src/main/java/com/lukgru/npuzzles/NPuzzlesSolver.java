package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.algorithm.SolvabilityVerification;
import com.lukgru.npuzzles.heuristic.BoardHeuristicEvaluator;
import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;

import java.util.*;

/**
 * Created by Lukasz on 21.01.2017.
 */
//TODO: allow only square boards!!!
//TODO: handle case when state is present in open/closed but with shorter path
public class NPuzzlesSolver {

    private BoardHeuristicEvaluator heuristicEvaluator;
    private BoardPieceMover mover = new BoardPieceMover();
    private SolvabilityVerification solvabilityVerification = new SolvabilityVerification();
    private Map<Board, Integer> costMap = new HashMap<>();
    private Map<Board, Board> previousStates = new HashMap<>();

    public NPuzzlesSolver(Heuristic heuristic) {
        this.heuristicEvaluator = new BoardHeuristicEvaluator(heuristic);
    }

    public List<Board> solve(Board board, Board target) {
        heuristicEvaluator.setTargetState(target);
        if (!solvabilityVerification.isSolvable(board, target)) {
            throw new RuntimeException("Puzzle is not solvable.");
        }

        Queue<Board> open = new PriorityQueue<>(this::compareBoards);
        HashSet<Board> openHashSet = new HashSet<>();
        HashSet<Board> closed = new HashSet<>();
        open.add(board);
        openHashSet.add(board);
        Board currentStep = open.poll();
        while (!currentStep.equals(target)) {
            closed.add(currentStep);
            addToOpenIfPossible(mover.fillGapByPieceFromUp(currentStep), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromDown(currentStep), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromLeft(currentStep), currentStep, open, openHashSet, closed);
            addToOpenIfPossible(mover.fillGapByPieceFromRight(currentStep), currentStep, open, openHashSet, closed);
            currentStep = open.poll();
        }

        return reconstuctSteps(currentStep);
    }

    private void addToOpenIfPossible(Board currentState, Board previousState, Queue<Board> open, HashSet<Board> openHashSet, HashSet<Board> closed) {
        boolean isInOpen = openHashSet.contains(currentState);
        boolean isInClosed = closed.contains(currentState);
        if (!isInClosed) {
            if (!isInOpen || hasLowerCost(currentState, previousState)) {
                open.add(currentState);
                previousStates.put(currentState, previousState);
                openHashSet.add(currentState);
            }
        }
    }

    private boolean hasLowerCost(Board currentState, int currentCost) {
        return costMap.get(currentState) < currentCost;
    }

    private int compareBoards(Board b1, Board b2) {
        int b1Heuristic = heuristicEvaluator.evaluate(b1);
        int b2Heuristic = heuristicEvaluator.evaluate(b2);
        return b1Heuristic - b2Heuristic;
    }
}
