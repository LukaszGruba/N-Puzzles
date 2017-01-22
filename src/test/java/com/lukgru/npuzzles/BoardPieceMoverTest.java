package com.lukgru.npuzzles;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;
import org.junit.Test;

import static com.lukgru.npuzzles.model.Piece.EMPTY;
import static org.junit.Assert.*;

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
        boolean moved = pieceMover.fillGapByPieceFromUp(new Board(pieces));

        //then
        assertTrue(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 1, pieces);
        assertPiecePosition(p3, 0, 1, pieces);
        assertPiecePosition(pX, 1, 0, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromDown(new Board(pieces));

        //then
        assertTrue(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 0, 1, pieces);
        assertPiecePosition(p3, 1, 0, pieces);
        assertPiecePosition(pX, 1, 1, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromLeft(new Board(pieces));

        //then
        assertTrue(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 0, pieces);
        assertPiecePosition(p3, 1, 1, pieces);
        assertPiecePosition(pX, 0, 1, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromRight(new Board(pieces));

        //then
        assertTrue(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 0, pieces);
        assertPiecePosition(p3, 0, 1, pieces);
        assertPiecePosition(pX, 1, 1, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromUp(new Board(pieces));

        //then
        assertFalse(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 1, pieces);
        assertPiecePosition(p3, 0, 1, pieces);
        assertPiecePosition(pX, 1, 0, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromDown(new Board(pieces));

        //then
        assertFalse(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 0, pieces);
        assertPiecePosition(p3, 0, 1, pieces);
        assertPiecePosition(pX, 1, 1, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromLeft(new Board(pieces));

        //then
        assertFalse(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 0, pieces);
        assertPiecePosition(p3, 1, 1, pieces);
        assertPiecePosition(pX, 0, 1, pieces);
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
        boolean moved = pieceMover.fillGapByPieceFromRight(new Board(pieces));

        //then
        assertFalse(moved);
        assertPiecePosition(p1, 0, 0, pieces);
        assertPiecePosition(p2, 1, 0, pieces);
        assertPiecePosition(p3, 0, 1, pieces);
        assertPiecePosition(pX, 1, 1, pieces);
    }

    private void assertPiecePosition(Piece p, int x, int y, Piece[][] pieces) {
        assertEquals(pieces[y][x], p);
        assertEquals(new Position(x, y), p.getPosition());
    }
}