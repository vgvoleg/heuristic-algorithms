package com.vgvoleg.heuristic.algorithms.populational.genetic;

class Stop {

    interface Strategy {
        boolean execute(int currIteration, int maxIterations);
    }

    static final Strategy COUNT_ITERATIONS = new Strategy() {
        @Override
        public boolean execute(int currIteration, int maxIterations) {
            return currIteration == maxIterations;
        }
    };
}
