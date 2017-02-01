package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.Move;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.io.SolutionPrinter;
import com.lukgru.npuzzles.model.Board;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        Board board = getBoard();
        Board targetBoard = getTarget();
        List<Move> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board, targetBoard);
        new SolutionPrinter(System.out).print(solution);
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
