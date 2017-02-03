package com.lukgru.npuzzles.model;

/**
 * Created by Łukasz on 2017-02-03.
 */
public class Step {

    private Board state;
    private Step previousState;

    public Step(Board state, Step previousState) {
        this.state = state;
        this.previousState = previousState;
    }

    public Board getState() {
        return this.state;
    }

    public Step getPreviousState() {
        return this.previousState;
    }
}
