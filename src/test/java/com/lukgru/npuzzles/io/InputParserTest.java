package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.algorithm.BoardValidation;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lukasz on 27.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class InputParserTest {

    @Mock
    private BoardValidation validation;

    @InjectMocks
    private InputParser inputParser;

    @Test
    public void createCorrect1x1Board() {
        //given
        String[] lines = {"1"};

        //when
        Board board = inputParser.parse(lines);

        //then
        Piece[][] pieces = board.getBoardArray();
        assertEquals(1, pieces.length);
        assertEquals(1, pieces[0].length);
        assertEquals(pieces[0][0], new Piece("1", new Position(0, 0)));
    }

    @Test
    public void createCorrect2x2Board() {
        //given
        String[] lines = {
            "1 2",
            "3 " + EMPTY
        };

        //when
        Board board = inputParser.parse(lines);

        //then
        Piece[][] pieces = board.getBoardArray();
        assertEquals(pieces[0][0], new Piece("1", new Position(0, 0)));
        assertEquals(pieces[0][1], new Piece("2", new Position(1, 0)));
        assertEquals(pieces[1][0], new Piece("3", new Position(0, 1)));
        assertEquals(pieces[1][1], new Piece(EMPTY, new Position(1, 1)));
    }

    @Test
    public void createCorrect3x3Board() {
        //given
        String[] lines = {
            "1 2 3",
            "4 5 6",
            "7 8 " + EMPTY
        };

        //when
        Board board = inputParser.parse(lines);

        //then
        Piece[][] pieces = board.getBoardArray();
        assertEquals(pieces[0][0], new Piece("1", new Position(0, 0)));
        assertEquals(pieces[0][1], new Piece("2", new Position(1, 0)));
        assertEquals(pieces[0][2], new Piece("3", new Position(2, 0)));
        assertEquals(pieces[1][0], new Piece("4", new Position(0, 1)));
        assertEquals(pieces[1][1], new Piece("5", new Position(1, 1)));
        assertEquals(pieces[1][2], new Piece("6", new Position(2, 1)));
        assertEquals(pieces[2][0], new Piece("7", new Position(0, 2)));
        assertEquals(pieces[2][1], new Piece("8", new Position(1, 2)));
        assertEquals(pieces[2][2], new Piece(EMPTY, new Position(2, 2)));
    }

}