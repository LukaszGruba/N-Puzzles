package com.lukgru.npuzzles.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    @Override
    public String toString() {
        return "Step{\n" + state  + '}';
    }

    public List<Step> getAllSteps() {
        List<Step> steps = new LinkedList<>();
        Step currentStep = this;
        while (currentStep != null) {
            steps.add(currentStep);
            currentStep = currentStep.previousState;
        }
        Collections.reverse(steps);
        return steps;
    }
}
