package com.lukgru.npuzzles.model;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Piece {

    private int value;
    private Position position;

    public Piece(int value, Position position) {
        this.value = value;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
