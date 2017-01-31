package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;

import java.util.Objects;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class InputParser {

    public Board parse(String[] lines) {
        Objects.nonNull(lines);

        Piece[][] pieces = new Piece[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] rowElements = lines[i].split("\\s+");
            pieces[i] = new Piece[rowElements.length];
            for (int j = 0; j < rowElements.length; j++) {
                pieces[i][j] = new Piece(rowElements[j], new Position(j, i));
            }
        }
        return new Board(pieces);
    }
}
