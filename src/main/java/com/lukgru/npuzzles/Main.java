package com.lukgru.npuzzles;

import com.lukgru.npuzzles.algorithm.BoardValidation;
import com.lukgru.npuzzles.heuristic.ManhattanHeuristic;
import com.lukgru.npuzzles.io.InputParser;
import com.lukgru.npuzzles.io.SolutionPrinter;
import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Main {

    private BoardValidation validation = new BoardValidation();

    public static void main(String[] args) {
        boolean success = false;
        while (!success) {
            Main main = new Main();
            try {
                Board board = main.getBoard();
                Board target = main.getTarget();
                main.validation.validateSameSize(board, target);
                main.validation.validateSameElements(board, target);
                List<Step> solution = new NPuzzlesSolver(new ManhattanHeuristic()).solve(board, target);
                new SolutionPrinter(System.out).print(solution);
                success = true;
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Board getBoard() {
        System.out.println("Provide initial state of NxN matrix:");
        String[] lines = scanInput();
        return new InputParser().parse(lines);
    }

    private Board getTarget() {
        System.out.println("\nProvide target state of NxN matrix:");
        String[] targetLines = scanInput();
        return new InputParser().parse(targetLines);
    }

    private String[] scanInput() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> lines = new LinkedList<>();
        while (!line.isEmpty()) {
            lines.add(line);
            line = scanner.nextLine();
        }
        return lines.toArray(new String[lines.size()]);
    }

}
