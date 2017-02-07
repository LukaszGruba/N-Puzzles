package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
                "1 6 8",
                "7 3 2",
                "_ 4 5"
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
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Every piece has to be unique.");

        //given
        Board board = new InputParser().parse(new String[] {
                "10 2 3",
                "4 5 10",
                "7 8 _"
        });

        //when
        validation.validateNonRepeatedPieces(board);
    }

    @Test
    public void shouldBeOkForUniqueElements() {
        //given
        Board board = new InputParser().parse(new String[] {
                "10 2 3",
                "4 5 6",
                "7 8 _"
        });

        //when
        validation.validateNonRepeatedPieces(board);
    }

    @Test
    public void shouldThrowForMultipleEmptyPieces() {
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Board has to have one empty element.");

        //given
        Board board = new InputParser().parse(new String[] {
                "10 2 3",
                "4 _ 5",
                "7 8 _"
        });

        //when
        validation.validateOnlyOneEmpty(board);
    }

    @Test
    public void shouldThrowForNoneEmptyPiece() {
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Board has to have one empty element.");

        //given
        Board board = new InputParser().parse(new String[] {
                "9 2 3",
                "4 1 5",
                "7 8 6"
        });

        //when
        validation.validateOnlyOneEmpty(board);
    }

    @Test
    public void shouldBeOkForSingleEmptyPiece() {
        //given
        Board board = new InputParser().parse(new String[] {
                "_ 2 3",
                "4 1 5",
                "7 8 6"
        });

        //when
        validation.validateOnlyOneEmpty(board);
    }

    @Test
    public void shouldThrowForNonSquareBoard() {
        //expect
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Board has to be NxN square.");

        //given
        Board board = new InputParser().parse(new String[] {
                "9 2 3",
                "4 1 5",
                "7 8 6",
                "11 _ 10"
        });

        //when
        validation.validateSquare(board);
    }

    @Test
    public void shouldBeOkForSquareBoard() {
        //given
        Board board = new InputParser().parse(new String[] {
                "_ 2 3",
                "4 1 5",
                "7 8 6",
        });

        //when
        validation.validateSquare(board);
    }
}