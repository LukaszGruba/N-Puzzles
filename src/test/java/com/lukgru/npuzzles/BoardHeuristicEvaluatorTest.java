package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 02.02.2017.
 */
public class BoardHeuristicEvaluatorTest {

    private Heuristic heuristic = new ManhattanHeuristic();
    private BoardHeuristicEvaluator heuristicEvaluator = new BoardHeuristicEvaluator(heuristic);

    @Test
    public void shouldReturnZeroForIdenticalStates() {
        //given
        Board board = new InputParser().parse(new String[]{
            "1 2 3",
            "4 5 6",
            "7 8 X"
        });
        Board targetBoard = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 X"
        });

        //when
        heuristicEvaluator.setTargetState(targetBoard);
        int distance = heuristicEvaluator.evaluate(board);

        //then
        assertEquals(0, distance);
    }

    @Test
    public void shouldEvaluateProperlyForCloseStates() {
        //given
        Board board = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 X"
        });
        Board targetBoard = new InputParser().parse(new String[]{
                "1 4 3",
                "2 8 X",
                "7 5 6"
        });

        //when
        heuristicEvaluator.setTargetState(targetBoard);
        int distance = heuristicEvaluator.evaluate(board);

        //then
        assertEquals(8, distance);
    }

    @Test
    public void shouldEvaluateProperlyForDistantStates() {
        //given
        Board board = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 X"
        });
        Board targetBoard = new InputParser().parse(new String[]{
                "X 8 7",
                "6 5 4",
                "3 2 1"
        });

        //when
        heuristicEvaluator.setTargetState(targetBoard);
        int distance = heuristicEvaluator.evaluate(board);

        //then
        assertEquals(24, distance);
    }

}