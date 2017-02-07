package com.lukgru.npuzzles;

import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.io.SolutionPrinter;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Piece;
import com.lukgru.npuzzles.model.Step;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    //TODO: create validation utils

    public static void main(String[] args) {
        Board board = getBoard();
        Board targetBoard = getTarget();
        validateBoards(board, targetBoard);
        List<Step> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board, targetBoard);
        new SolutionPrinter(System.out).print(solution);
    }

    private static void validateBoards(Board board, Board targetBoard) {
        if (board.getN() != targetBoard.getN()) {
            throw new RuntimeException("Board and target state have to be same size.");
        }

        Set<String> collect = targetBoard.piecesStream().map(Piece::getValue).collect(toSet());
        boolean allMatch = board.piecesStream()
                .map(Piece::getValue)
                .allMatch(collect::contains);
        if (!allMatch) {
            throw new RuntimeException("Board and target state have to contain same elements.");
        }
    }

    private static Board getBoard() {
        System.out.println("Provide initial state of NxN matrix:");
        String[] lines = scanInput();
        return new InputParser().parse(lines);
    }

    private static Board getTarget() {
        System.out.println("\nProvide target state of NxN matrix:");
        String[] targetLines = scanInput();
        return new InputParser().parse(targetLines);
    }

    private static String[] scanInput() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> lines = new LinkedList<>();
        while (!line.isEmpty()) {
            line = scanner.nextLine();
            lines.add(line);
        }
        return lines.toArray(new String[lines.size()]);
    }

}
