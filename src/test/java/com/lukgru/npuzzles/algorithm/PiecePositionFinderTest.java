package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lukasz on 02.02.2017.
 */
public class PiecePositionFinderTest {
    
    @Test
    public void shouldFindPosition() {
        //given
        Board board = new InputParser().parse(new String[]{
            "1 2 3",
            "4 5 6",
            "7 8 X"
        });

        //when
        Position p1 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "1");
        Position p2 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "2");
        Position p3 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "3");
        Position p4 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "4");
        Position p5 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "5");
        Position p6 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "6");
        Position p7 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "7");
        Position p8 = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "8");
        Position pX = new PiecePositionFinder().getElementPosition(board.getBoardArray(), "X");


        //then
        assertEquals(new Position(0, 0), p1);
        assertEquals(new Position(1, 0), p2);
        assertEquals(new Position(2, 0), p3);
        assertEquals(new Position(0, 1), p4);
        assertEquals(new Position(1, 1), p5);
        assertEquals(new Position(2, 1), p6);
        assertEquals(new Position(0, 2), p7);
        assertEquals(new Position(1, 2), p8);
        assertEquals(new Position(2, 2), pX);
    }
}