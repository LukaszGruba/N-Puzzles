package com.lukgru.npuzzles.heuristic;

import com.lukgru.npuzzles.model.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class ManhattanHeuristicTest {

    private ManhattanHeuristic heuristic = new ManhattanHeuristic();
    
    @Test
    public void shouldReturnZeroForSamePositions() {
        //given
        Position p1 = new Position(3, 4);
        Position p2 = new Position(3, 4);

        //when
        int distance = heuristic.estimateDistance(p1, p2);

        //then
        assertEquals(0, distance);
    }

    @Test
    public void shouldReturnHorizontalDistanceIfSameY() {
        //given
        Position p1 = new Position(1, 4);
        Position p2 = new Position(5, 4);

        //when
        int distance = heuristic.estimateDistance(p1, p2);

        //then
        assertEquals(4, distance);
    }

    @Test
    public void shouldReturnVerticalDistanceIfSameX() {
        //given
        Position p1 = new Position(1, 2);
        Position p2 = new Position(1, 5);

        //when
        int distance = heuristic.estimateDistance(p1, p2);

        //then
        assertEquals(3, distance);
    }

    @Test
    public void shouldReturnSumOfVerticalAndHorizontalDistance() {
        //given
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 5);

        //when
        int distance = heuristic.estimateDistance(p1, p2);

        //then
        assertEquals(5, distance);
    }

    @Test
    public void shouldReturnSameDistanceCalculatingAtoBandBtoA() {
        //given
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 5);

        //when
        int distance1 = heuristic.estimateDistance(p1, p2);
        int distance2 = heuristic.estimateDistance(p1, p2);

        //then
        assertEquals(distance1, distance2);
    }
}