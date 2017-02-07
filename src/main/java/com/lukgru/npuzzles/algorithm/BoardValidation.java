package com.lukgru.npuzzles.algorithm;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Lukasz on 07.02.2017.
 */
public class BoardValidation {

    public void validateSameSize(Board board, Board targetBoard) {
        if (board.getN() != targetBoard.getN()) {
            throw new RuntimeException("Board and target state have to be same size.");
        }
    }

    public void validateSameElements(Board board, Board targetBoard) {
        Set<String> targetElements = targetBoard.piecesStream().map(Piece::getValue).collect(toSet());
        boolean allMatch = board.piecesStream()
                .map(Piece::getValue)
                .allMatch(targetElements::contains);
        if (!allMatch) {
            throw new RuntimeException("Board and target state have to contain same elements.");
        }
    }

    public void validateNonRepeatedPieces(Board board) {
        long piecesAmount = board.piecesStream().count();
        long uniquePiecesAmount = board.piecesStream().map(Piece::getValue).distinct().count();
        if (piecesAmount != uniquePiecesAmount) {
            throw new RuntimeException("Every piece has to be unique.");
        }
    }

    public void validateOnlyOneEmpty(Board board) {
        long numberOfEmptyPieces = board.piecesStream()
                .map(Piece::getValue)
                .filter(v -> v.equals(Piece.EMPTY))
                .count();
        if (numberOfEmptyPieces != 1) {
            throw new RuntimeException("Board has to have one empty element.");
        }
    }

    public void validateSquare(Board board) {
        Piece[][] pieces = board.getBoardArray();
        int squareSize = pieces.length;
        boolean isSquare = Arrays.stream(pieces)
                .map(r -> r.length)
                .allMatch(length -> length == squareSize);
        if (!isSquare) {
            throw new RuntimeException("Board has to be NxN square.");
        }
    }
}
