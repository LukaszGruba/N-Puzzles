package com.lukgru.npuzzles.algorithm;

import static com.lukgru.npuzzles.model.Piece.EMPTY;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class BoardPieceMover {

    private PiecePositionFinder positionFinder = new PiecePositionFinder();

    public Board fillGapByPieceFromLeft(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = positionFinder.getElementPosition(boardArray, EMPTY);
        int emptyX = emptyPosition.getX();
        int emptyY = emptyPosition.getY();
        int newX = emptyX - 1;
        int newY = emptyY;
        if (newX >= 0) {
            Piece pieceToMove = boardArray[newY][newX];
            Piece empty = boardArray[emptyY][emptyX];
            boardArray[newY][newX] = empty;
            empty.setPosition(pieceToMove.getPosition());
            boardArray[emptyY][emptyX] = pieceToMove;
            pieceToMove.setPosition(emptyPosition);
        }
        return new Board(boardArray);
    }

    public Board fillGapByPieceFromRight(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = positionFinder.getElementPosition(boardArray, EMPTY);
        int emptyX = emptyPosition.getX();
        int emptyY = emptyPosition.getY();
        int newX = emptyX + 1;
        int newY = emptyY;
        if (newX < boardArray[0].length) {
            Piece pieceToMove = boardArray[newY][newX];
            Piece empty = boardArray[emptyY][emptyX];
            boardArray[newY][newX] = empty;
            empty.setPosition(pieceToMove.getPosition());
            boardArray[emptyY][emptyX] = pieceToMove;
            pieceToMove.setPosition(emptyPosition);
        }
        return new Board(boardArray);
    }

    public Board fillGapByPieceFromUp(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = positionFinder.getElementPosition(boardArray, EMPTY);
        int emptyX = emptyPosition.getX();
        int emptyY = emptyPosition.getY();
        int newX = emptyX;
        int newY = emptyY - 1;
        if (newY >= 0) {
            Piece pieceToMove = boardArray[newY][newX];
            Piece empty = boardArray[emptyY][emptyX];
            boardArray[newY][newX] = empty;
            empty.setPosition(pieceToMove.getPosition());
            boardArray[emptyY][emptyX] = pieceToMove;
            pieceToMove.setPosition(emptyPosition);
        }
        return new Board(boardArray);
    }

    public Board fillGapByPieceFromDown(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = positionFinder.getElementPosition(boardArray, EMPTY);
        int emptyX = emptyPosition.getX();
        int emptyY = emptyPosition.getY();
        int newX = emptyX;
        int newY = emptyY + 1;
        if (newY < boardArray.length) {
            Piece pieceToMove = boardArray[newY][newX];
            Piece empty = boardArray[emptyY][emptyX];
            boardArray[newY][newX] = empty;
            empty.setPosition(pieceToMove.getPosition());
            boardArray[emptyY][emptyX] = pieceToMove;
            pieceToMove.setPosition(emptyPosition);
        }
        return new Board(boardArray);
    }
}
