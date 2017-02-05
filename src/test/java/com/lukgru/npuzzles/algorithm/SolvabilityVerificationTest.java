package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import org.junit.Test;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 05.02.2017.
 */
public class SolvabilityVerificationTest {

    private SolvabilityVerification verification = new SolvabilityVerification();

    @Test
    public void shouldBeSolvableWhenOddSizeAndEvenInversions() {
        //given
        Board board = new InputParser().parse(new String[]{
                "1 8 2",
                EMPTY + " 4 3",
                "7 6 5"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3",
                "4 5 6",
                "7 8 " + EMPTY
        });

        //when
        boolean solvable = verification.isSolvable(board, target);

        //then
        assertTrue(solvable);
    }

    @Test
    public void shouldBeSolvableWhenEvenSizeAndOddInversionsAndEvenEmptyPositionFromBottom() {
        //given
        Board board = new InputParser().parse(new String[]{
                "13 2 10 3",
                "1 12 8 4",
                "5 " + EMPTY + " 9 6",
                "15 14 11 7"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3 4",
                "5 6 7 8",
                "9 10 11 12",
                "13 14 15 " + EMPTY
        });

        //when
        boolean solvable = verification.isSolvable(board, target);

        //then
        assertTrue(solvable);
    }

    @Test
    public void shouldBeSolvableWhenEvenSizeAndEvenInversionsAndOddEmptyPositionFromBottom() {
        //given
        Board board = new InputParser().parse(new String[]{
                "6 13 7 10",
                "8 9 11 " + EMPTY,
                "15 2 12 5",
                "14 3 1 4"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3 4",
                "5 6 7 8",
                "9 10 11 12",
                "13 14 15 " + EMPTY
        });

        //when
        boolean solvable = verification.isSolvable(board, target);

        //then
        assertTrue(solvable);
    }

    @Test
    public void shouldNotBeSolvableWhenEvenSizeAndEvenInversionsAndEvenEmptyPositionFromBottom() {
        //given
        Board board = new InputParser().parse(new String[]{
                "3 9 1 15",
                "14 11 4 6",
                "13 " + EMPTY + " 10 12",
                "2 7 8 5"
        });
        Board target = new InputParser().parse(new String[]{
                "1 2 3 4",
                "5 6 7 8",
                "9 10 11 12",
                "13 14 15 " + EMPTY
        });

        //when
        boolean solvable = verification.isSolvable(board, target);

        //then
        assertFalse(solvable);
    }
}