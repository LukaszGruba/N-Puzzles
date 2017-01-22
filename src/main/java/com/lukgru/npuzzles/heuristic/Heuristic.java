package com.lukgru.npuzzles.heuristic;

import com.lukgru.npuzzles.model.Position;

/**
 * Created by Lukasz on 22.01.2017.
 */
public interface Heuristic {

    int estimateDistance(Position p1, Position p2);
}
