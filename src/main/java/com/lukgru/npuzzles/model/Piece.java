package com.lukgru.npuzzles.model;

/**
 * Created by Lukasz on 22.01.2017.
 */
public class Piece {

    public static final String EMPTY = "_";

    private String value;
    private Position position;

    public Piece(String value, Position position) {
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Piece piece = (Piece) obj;

        if (value != null ? !value.equals(piece.value) : piece.value != null) {
            return false;
        }
        return position != null ? position.equals(piece.position) : piece.position == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Piece{" + "value='" + value + '\'' + ", position=" + position + '}';
    }
}
