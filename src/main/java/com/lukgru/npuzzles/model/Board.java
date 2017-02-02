package com.lukgru.npuzzles.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Board {

    private Piece[][] boardArray;

    public Board(Piece[][] boardArray) {
        Objects.nonNull(boardArray);
        this.boardArray = boardArray;
    }

    public Piece[][] getBoardArray() {
        Piece[][] newPieces = new Piece[boardArray.length][boardArray[0].length];
        piecesStream().forEach(piece -> {
            String value = piece.getValue();
            int x = piece.getPosition().getX();
            int y = piece.getPosition().getY();
            newPieces[y][x] = new Piece(value, new Position(x, y));
        });
        return newPieces;
    }

    public Stream<Piece> piecesStream() {
        return Arrays.stream(boardArray).flatMap(Arrays::stream);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Board board = (Board) o;

        return Arrays.deepEquals(boardArray, board.boardArray);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardArray);
    }
}
