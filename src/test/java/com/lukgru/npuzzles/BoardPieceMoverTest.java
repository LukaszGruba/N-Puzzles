package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardPieceMover;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;
import org.junit.Test;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class BoardPieceMoverTest {

    private BoardPieceMover pieceMover = new BoardPieceMover();
    
    @Test
    public void shouldMoveFromUpWhenPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(0, 1));
        Piece pX = new Piece(EMPTY, new Position(1, 1));
        Piece[][] pieces = {
                {p1, p2},
                {p3, pX}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromUp(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 1, board.getBoardArray());
        assertPiecePosition("3", 0, 1, board.getBoardArray());
        assertPiecePosition("X", 1, 0, board.getBoardArray());
    }

    @Test
    public void shouldMoveFromDownWhenPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece pX = new Piece(EMPTY, new Position(1, 0));
        Piece p2 = new Piece("2", new Position(0, 1));
        Piece p3 = new Piece("3", new Position(1, 1));
        Piece[][] pieces = {
                {p1, pX},
                {p2, p3}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromDown(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 0, 1, board.getBoardArray());
        assertPiecePosition("3", 1, 0, board.getBoardArray());
        assertPiecePosition("X", 1, 1, board.getBoardArray());
    }

    @Test
    public void shouldMoveFromLeftWhenPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(0, 1));
        Piece pX = new Piece(EMPTY, new Position(1, 1));
        Piece[][] pieces = {
                {p1, p2},
                {p3, pX}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromLeft(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 0, board.getBoardArray());
        assertPiecePosition("3", 1, 1, board.getBoardArray());
        assertPiecePosition("X", 0, 1, board.getBoardArray());
    }

    @Test
    public void shouldMoveFromRightWhenPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(1, 1));
        Piece pX = new Piece(EMPTY, new Position(0, 1));
        Piece[][] pieces = {
                {p1, p2},
                {pX, p3}
        };

        //when
        Board inputBoard = new Board(pieces);
        Board board = pieceMover.fillGapByPieceFromRight(inputBoard);

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 0, board.getBoardArray());
        assertPiecePosition("3", 0, 1, board.getBoardArray());
        assertPiecePosition("X", 1, 1, board.getBoardArray());
    }

    @Test
    public void shouldNotMoveFromUpWhenNotPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 1));
        Piece p3 = new Piece("3", new Position(0, 1));
        Piece pX = new Piece(EMPTY, new Position(1, 0));
        Piece[][] pieces = {
                {p1, pX},
                {p3, p2}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromUp(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 1, board.getBoardArray());
        assertPiecePosition("3", 0, 1, board.getBoardArray());
        assertPiecePosition("X", 1, 0, board.getBoardArray());
    }

    @Test
    public void shouldNotMoveFromDownWhenNotPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(0, 1));
        Piece pX = new Piece(EMPTY, new Position(1, 1));
        Piece[][] pieces = {
                {p1, p2},
                {p3, pX}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromDown(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 0, board.getBoardArray());
        assertPiecePosition("3", 0, 1, board.getBoardArray());
        assertPiecePosition("X", 1, 1, board.getBoardArray());
    }

    @Test
    public void shouldNotMoveFromLeftWhenNotPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(1, 1));
        Piece pX = new Piece(EMPTY, new Position(0, 1));
        Piece[][] pieces = {
                {p1, p2},
                {pX, p3}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromLeft(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 0, board.getBoardArray());
        assertPiecePosition("3", 1, 1, board.getBoardArray());
        assertPiecePosition("X", 0, 1, board.getBoardArray());
    }

    @Test
    public void shouldNotMoveFromRightWhenNotPossible() {
        //given
        Piece p1 = new Piece("1", new Position(0, 0));
        Piece p2 = new Piece("2", new Position(1, 0));
        Piece p3 = new Piece("3", new Position(0, 1));
        Piece pX = new Piece(EMPTY, new Position(1, 1));
        Piece[][] pieces = {
                {p1, p2},
                {p3, pX}
        };

        //when
        Board board = pieceMover.fillGapByPieceFromRight(new Board(pieces));

        //then
        assertPiecePosition("1", 0, 0, board.getBoardArray());
        assertPiecePosition("2", 1, 0, board.getBoardArray());
        assertPiecePosition("3", 0, 1, board.getBoardArray());
        assertPiecePosition("X", 1, 1, board.getBoardArray());
    }

    private void assertPiecePosition(String value, int x, int y, Piece[][] pieces) {
        assertEquals(value, pieces[y][x].getValue());
        assertEquals(new Position(x, y), pieces[y][x].getPosition());
    }
}