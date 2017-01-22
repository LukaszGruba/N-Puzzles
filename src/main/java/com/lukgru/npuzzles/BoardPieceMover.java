package com.lukgru.npuzzles;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;

import java.util.Arrays;

import static com.lukgru.npuzzles.model.Piece.EMPTY;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class BoardPieceMover {

    public boolean fillGapByPieceFromLeft(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = getEmptyPosition(boardArray);
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
            return true;
        }
        return false;
    }

    public boolean fillGapByPieceFromRight(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = getEmptyPosition(boardArray);
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
            return true;
        }
        return false;
    }

    public boolean fillGapByPieceFromUp(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = getEmptyPosition(boardArray);
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
            return true;
        }
        return false;
    }

    public boolean fillGapByPieceFromDown(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = getEmptyPosition(boardArray);
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
            return true;
        }
        return false;
    }

    private Position getEmptyPosition(Piece[][] pieces) {
        return Arrays.stream(pieces)
                .flatMap(Arrays::stream)
                .filter(piece -> piece.getValue().equals(EMPTY))
                .map(Piece::getPosition)
                .findFirst()
                .orElse(null);
    }
}
