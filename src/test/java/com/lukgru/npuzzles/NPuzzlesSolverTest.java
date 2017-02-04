package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Lukasz on 03.02.2017.
 */
public class NPuzzlesSolverTest {

    private Heuristic heuristic = new ManhattanHeuristic();
    private NPuzzlesSolver solver = new NPuzzlesSolver(heuristic);

    @Test
    public void shouldSolve2x2WhenPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "2 X",
                "1 3"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2",
                "3 X"
        });

        //when
        List<Step> solution = solver.solve(board, target);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0).getState());
        assertEquals(target, solution.get(solution.size() - 1).getState());
    }

    @Test
    public void shouldNotSolve2x2WhenNotPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "1 X",
                "2 3"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2",
                "3 X"
        });

        //when
        List<Step> solution = solver.solve(board, target);

        //then
        assertNotNull(solution);
        assertEquals(0, solution.size());
    }


    @Test
    public void shouldSolve3x3WhenPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "6 2 X",
                "7 8 1",
                "3 4 5"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 X"
        });

        //when
        List<Step> solution = solver.solve(board, target);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0).getState());
        assertEquals(target, solution.get(solution.size() - 1).getState());
    }
}