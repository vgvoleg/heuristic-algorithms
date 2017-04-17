package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

import static com.vgvoleg.heuristic.algorithms.Generator.uniformDistribution;

class Crossing {

    interface Strategy {
        double[][] execute(double[][] population, OptimizationProblem problem);
    }

    public static final Strategy SIMPLE_CROSSOVER = (population, problem) -> {
        double[][] childrens = new double[2][problem.getDimension()];
        int p = (int) uniformDistribution(0, problem.getDimension());
        for (int i = 0; i < p; i++) {
            childrens[0][i] = population[1][i];
            childrens[1][i] = population[0][i];
        }
        for (int i = p; i < problem.getDimension(); i++) {
            childrens[0][i] = population[0][i];
            childrens[1][i] = population[1][i];
        }

        return childrens;
    };

    public static final Strategy FLAT_CROSSOVER = (population, problem) -> {
        // TODO: add realization
        return null;
    };

    public static final Strategy ARITHMETICAL = (population, problem) -> {
        // TODO: add realization
        return null;
    };

    private Crossing() {
    }
}
