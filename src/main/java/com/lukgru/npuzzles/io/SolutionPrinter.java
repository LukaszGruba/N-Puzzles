package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.model.Step;

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

    public void print(List<Step> solution) {
        printStream.println("Solution:");
        solution.stream()
                .map(Step::toString)
                .map(m -> m + " -> ")
                .forEach(printStream::print);
    }
}
