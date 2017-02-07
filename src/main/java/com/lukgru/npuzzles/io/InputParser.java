package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;
import sun.security.util.Length;

import java.util.Arrays;
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
        Board board = new Board(pieces);
        validateSquare(board);
        validateOnlyOneEmpty(board);
        validateNonRepeatedPieces(board);
        return board;
    }

    private void validateNonRepeatedPieces(Board board) {
        long piecesAmount = board.piecesStream().count();
        long uniquePiecesAmount = board.piecesStream().map(Piece::getValue).distinct().count();
        if (piecesAmount != uniquePiecesAmount) {
            throw new RuntimeException("Every piece has to be unique.");
        }
    }

    private void validateOnlyOneEmpty(Board board) {
        long numberOfEmptyPieces = board.piecesStream()
                .map(Piece::getValue)
                .filter(v -> v.equals(Piece.EMPTY))
                .count();
        if (numberOfEmptyPieces != 1) {
            throw new RuntimeException("Board has to have one empty element.");
        }
    }

    private void validateSquare(Board board) {
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
