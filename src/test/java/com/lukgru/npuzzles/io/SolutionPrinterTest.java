package com.lukgru.npuzzles.io;

import com.lukgru.npuzzles.model.Board;
import com.lukgru.npuzzles.model.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Łukasz on 2017-02-03.
 */
@RunWith(MockitoJUnitRunner.class)
public class SolutionPrinterTest {

    @Mock
    private PrintStream printStream;

    private SolutionPrinter printer;

    @Before
    public void init() {
        printer = new SolutionPrinter(printStream);
    }

    @Test
    public void shouldPrintStepsProperly() {
        //given
        List<Step> solution = Arrays.asList(
                mockStep("1"),
                mockStep("2"),
                mockStep("3"),
                mockStep("4")
        );
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        doNothing().when(printStream).print(captor.capture());

        //when
        printer.print(solution);
        
        //then
        List<String> allValues = captor.getAllValues();
        assertEquals("1 -> ", allValues.get(0));
        assertEquals("2 -> ", allValues.get(1));
        assertEquals("3 -> ", allValues.get(2));
        assertEquals("4 -> ", allValues.get(3));
    }

    private Step mockStep(String s) {
        Board board = mock(Board.class);
        when(board.toString()).thenReturn(s);
        Step step = mock(Step.class);
        when(step.getState()).thenReturn(board);
        return step;
    }

}