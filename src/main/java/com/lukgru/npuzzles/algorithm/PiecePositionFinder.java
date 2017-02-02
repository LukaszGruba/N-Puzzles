package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;

import java.util.Arrays;

/**
 * Created by Lukasz on 02.02.2017.
 */
public class PiecePositionFinder {

    public Position getElementPosition(Piece[][] pieces, String toFind) {
        return Arrays.stream(pieces)
                .flatMap(Arrays::stream)
                .filter(piece -> piece.getValue().equals(toFind))
                .map(Piece::getPosition)
                .findFirst()
                .orElse(null);
    }

}
