package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 07.02.2017.
 */
public class BoardValidationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BoardValidation validation = new BoardValidation();

    @Test
    public void shouldThrowForDifferentSize() {
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Board and target state have to be same size.");

        //given
        Board board = new InputParser().parse(new String[] {
                "1 2 3 4",
                "5 6 7 8",
                "9 10 11 12",
                "13 14 15 _"
        });
        Board target = new InputParser().parse(new String[] {
                "1 2 3",
                "4 5 6",
                "7 8 _"
        });

        //when
        validation.validateSameSize(board, target);
    }

    @Test
    public void shouldBeOkForSameSize() {
        //given
        Board board = new InputParser().parse(new String[] {
                "10 20 30",
                "40 50 60",
                "70 80 _"
        });
        Board target = new InputParser().parse(new String[] {
                "1 2 3",
                "4 5 6",
                "7 8 _"
        });

        //when
        validation.validateSameSize(board, target);
    }

    @Test
    public void shouldThrowForDifferentElements() {
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Board and target state have to contain same elements.");

        //given
        Board board = new InputParser().parse(new String[] {
                "10 2 3",
                "4 5 6",
                "7 8 _"
        });
        Board target = new InputParser().parse(new String[] {
                "1 2 3",
                "4 5 6",
                "7 8 _"
        });

        //when
        validation.validateSameElements(board, target);
    }

    @Test
    public void shouldBeOkForSameElements() {
        //given
        Board board = new InputParser().parse(new String[] {
                "_ 7 1",
                "8 6 4",
                "2 5 3"
        });
        Board target = new InputParser().parse(new String[] {
                "1 2 3",
                "4 5 6",
                "7 8 _"
        });

        //when
        validation.validateSameElements(board, target);
    }

    @Test
    public void shouldThrowForRepeatedElements() {

    }

    @Test
    public void shouldBeOkForUniqueElements() {

    }

    @Test
    public void shouldThrowForMultipleEmptyPieces() {

    }

    @Test
    public void shouldThrowForNoneEmptyPiece() {

    }

    @Test
    public void shouldBeOkForSingleEmptyPiece() {

    }

    @Test
    public void shouldThrowForNonSquareBoard() {

    }

    @Test
    public void shouldBeOkForSquareBoard() {

    }
}