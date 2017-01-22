package com.lukgru.npuzzles.heuristic;

import static java.lang.Math.abs;

import com.lukgru.npuzzles.model.Position;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class ManhattanHeuristic implements Heuristic {

    public int estimateDistance(Position p1, Position p2) {
        return abs(p1.getX() - p2.getX()) + abs(p1.getY() - p2.getY());
    }
    
}
