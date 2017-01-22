package com.lukgru.npuzzles.model;

import java.util.Objects;

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
}
