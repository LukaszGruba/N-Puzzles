package com.lukgru.npuzzles.algorithm;

import static com.lukgru.npuzzles.model.Piece.EMPTY;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Position;

/**
 * Created by Lukasz on 05.02.2017.
 */
public class SolvabilityVerification {

    public boolean isSolvable(Board board, Board target) {
        return canBeRearrangedToRegularEndState(board) == canBeRearrangedToRegularEndState(target);
    }

    private boolean canBeRearrangedToRegularEndState(Board board) {
        boolean solvable = false;
        int inversions = countInversions(board);
        if (inversions == 0) {
            return true;
        }
        int boardSize = board.getBoardArray().length;
        if (isOdd(boardSize) && isEven(inversions)) {
            solvable = true;
        } else if (isEven(boardSize)) {
            int emptyPositionFromDown = emptyPositionFromDown(board);
            if (isEven(inversions) && isOdd(emptyPositionFromDown)
                    || isOdd(inversions) && isEven(emptyPositionFromDown)) {
                solvable = true;
            }
        }
        return solvable;
    }

    private int emptyPositionFromDown(Board board) {
        Piece[][] boardArray = board.getBoardArray();
        Position emptyPosition = new PiecePositionFinder().getElementPosition(boardArray, EMPTY);
        return boardArray.length - emptyPosition.getY();
    }

    private int countInversions(Board board) {
        Piece[][] pieces = board.getBoardArray();
        int boardSize = pieces.length;
        int[] all = new int[boardSize * boardSize - 1];
        int index = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String value = pieces[i][j].getValue();
                if (!EMPTY.equals(value)) {
                    all[index] = Integer.parseInt(value);
                    index++;
                }
            }
        }

        int inversions = 0;
        for (int i = 0; i < all.length; i++) {
            for (int j = i + 1; j < all.length; j++) {
                if (all[j] < all[i]) {
                    inversions++;
                }
            }
        }

        return inversions;
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    private boolean isOdd(int n) {
        return !isEven(n);
    }

}
