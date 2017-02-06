package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.Heuristic;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;
import org.junit.Test;

import java.util.List;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
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
                "2 " + EMPTY,
                "1 3"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2",
                "3 " + EMPTY
        });

        //when
        List<Board> solution = solver.solve(board, target);
        System.out.println("SOLUTION");
        solution.stream().forEach(System.out::print);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0));
        assertEquals(target, solution.get(solution.size() - 1));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotSolve2x2WhenNotPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "1 " + EMPTY,
                "2 3"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2",
                "3 " + EMPTY
        });

        //when
        solver.solve(board, target);
    }

    @Test
    public void shouldSolve3x3WhenPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "6 2 " + EMPTY,
                "7 8 1",
                "3 4 5"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 " + EMPTY
        });

        //when
        List<Board> solution = solver.solve(board, target);
        System.out.println("SOLUTION");
        solution.stream().forEach(System.out::print);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0));
        assertEquals(target, solution.get(solution.size() - 1));
    }

    @Test
    public void shouldSolve4x4WhenPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "6 2 " + EMPTY + " 10",
                "7 8 1 15",
                "11 4 5 9",
                "3 14 13 12"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3 4",
                "5 6 7 8",
                "9 10 11 12",
                "13 14 15 " + EMPTY
        });

        //when
        List<Board> solution = solver.solve(board, target);
        System.out.println("SOLUTION");
        solution.stream().forEach(System.out::print);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0));
        assertEquals(target, solution.get(solution.size() - 1));
    }

    @Test
    public void shouldSolve5x5WhenPossible() {
        //given
        Board board = new InputParser().parse(new String[]{
                "24 14 3 8 23",
                "7 2 11 19 20",
                "16 9 " + EMPTY + " 22 12",
                "17 13 5 21 6",
                "1 15 4 10 18"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3 4 5",
                "6 7 8 9 10",
                "11 12 13 14 15",
                "16 17 18 19 20",
                "21 22 23 24 " + EMPTY
        });

        //when
        List<Board> solution = solver.solve(board, target);
        System.out.println("SOLUTION");
        solution.stream().forEach(System.out::print);

        //then
        assertNotNull(solution);
        assertEquals(board, solution.get(0));
        assertEquals(target, solution.get(solution.size() - 1));
    }
}