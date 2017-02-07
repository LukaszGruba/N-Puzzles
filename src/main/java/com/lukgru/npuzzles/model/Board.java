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
            int posX = piece.getPosition().getX();
            int posY = piece.getPosition().getY();
            newPieces[posY][posX] = new Piece(value, new Position(posX, posY));
        });
        return newPieces;
    }

    public int getN() {
        return boardArray.length;
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

    @Override
    public String toString() {
        StringBuilder rows = new StringBuilder();
        for (Piece[] row : boardArray) {
            Arrays.stream(row)
                    .map(Piece::getValue)
                    .forEach(value -> rows.append(String.format("%1$3s", value)));
            rows.append("\n");
        }
        return rows.toString();
    }
}
