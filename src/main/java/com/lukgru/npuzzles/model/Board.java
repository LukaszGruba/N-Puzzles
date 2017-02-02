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
        return boardArray;
    }

    public Stream<Piece> piecesStream() {
        return Arrays.stream(boardArray).flatMap(Arrays::stream);
    }
}
