package com.lukgru.npuzzles.heuristic;

import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import org.junit.Test;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
import static org.junit.Assert.assertEquals;

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
            "7 8 " + EMPTY
        });
        Board targetBoard = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 " + EMPTY
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
                "7 8 " + EMPTY
        });
        Board targetBoard = new InputParser().parse(new String[]{
                "1 4 3",
                "2 8 " + EMPTY,
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
                "7 8 " + EMPTY
        });
        Board targetBoard = new InputParser().parse(new String[]{
                EMPTY + " 8 7",
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