package com.lukgru.npuzzles.io;

import static java.util.stream.Collectors.joining;

import com.lukgru.npuzzles.model.Step;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

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
                .map(Step::getState)
                .map(b -> b.toString()
                        + Stream.generate(() -> "---")
                                .limit(b.getN())
                                .collect(joining())
                        + "--\n")
                .forEach(printStream::print);
    }
}
