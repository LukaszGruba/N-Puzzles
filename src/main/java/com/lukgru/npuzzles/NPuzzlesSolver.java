package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.algorithm.SolvabilityVerification;
import com.lukgru.npuzzles.heuristic.BoardHeuristicEvaluator;
import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.model.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

        Queue<Board> open = new PriorityQueue<>((a, b) -> compareBoards(a, costMap.get(a), b, costMap.get(b)));
        HashSet<Board> openHashSet = new HashSet<>();
        HashSet<Board> closed = new HashSet<>();
        open.add(board);
        openHashSet.add(board);
        Board currentStep = open.poll();
        int currentCost = 0;
        while (!currentStep.equals(target)) {
            closed.add(currentStep);
            addToOpenIfPossible(mover.fillGapByPieceFromUp(currentStep), currentStep, open, openHashSet, closed, currentCost);
            addToOpenIfPossible(mover.fillGapByPieceFromDown(currentStep), currentStep, open, openHashSet, closed, currentCost);
            addToOpenIfPossible(mover.fillGapByPieceFromLeft(currentStep), currentStep, open, openHashSet, closed, currentCost);
            addToOpenIfPossible(mover.fillGapByPieceFromRight(currentStep), currentStep, open, openHashSet, closed, currentCost);
            currentStep = open.poll();
            currentCost = costMap.get(currentStep);
        }

        return reconstructSteps(currentStep);
    }

    private List<Board> reconstructSteps(Board currentStep) {
        List<Board> steps = new ArrayList<>();
        Board b = currentStep;
        while (b != null) {
            steps.add(b);
            b = previousStates.get(b);
        }
        Collections.reverse(steps);
        return steps;
    }

    private void addToOpenIfPossible(Board currentState, Board previousState, Queue<Board> open,
                                     HashSet<Board> openHashSet, HashSet<Board> closed, int currentCost) {
        boolean isInOpen = openHashSet.contains(currentState);
        boolean isInClosed = closed.contains(currentState);
        currentCost++;
        if (!isInClosed) {
            if (!isInOpen || hasLowerCost(currentState, currentCost)) {
                costMap.put(currentState, currentCost);
                open.add(currentState);
                previousStates.put(currentState, previousState);
                openHashSet.add(currentState);
            }
        }
    }

    private boolean hasLowerCost(Board currentState, int currentCost) {
        return costMap.get(currentState) > currentCost;
    }

    private int compareBoards(Board b1, Integer b1Cost, Board b2, Integer b2Cost) {
        int b1Heuristic = b1Cost + heuristicEvaluator.evaluate(b1);
        int b2Heuristic = b2Cost + heuristicEvaluator.evaluate(b2);
        return b1Heuristic - b2Heuristic;
    }
}
