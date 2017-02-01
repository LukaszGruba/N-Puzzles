package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.algorithm.Move;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class SolutionPrinter {

    private PrintStream printStream;

    public SolutionPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void print(List<Move> solution) {
        printStream.println("Solution:");
        solution.stream()
                .map(Move::toString)
                .map(m -> m + " -> ")
                .forEach(printStream::print);
    }
}
