package com.vgvoleg.heuristic.algorithms.populational.genetic;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class Selection {

    interface Strategy {
        double[][] execute(double[][] population, OptimizationProblem problem);
    }

    static Strategy PANMIXIA = (population, problem) -> {
        double[][] parents = new double[2][problem.getDimension() + 1];
        int firstParentIndex = (int) uniformDistribution(0, population.length);
        int secondParentIndex;
        while (true) {
            secondParentIndex = (int) uniformDistribution(0, population.length);
            if (secondParentIndex != firstParentIndex) {
                break;
            }
        }

        parents[0] = population[firstParentIndex];
        parents[1] = population[secondParentIndex];

        return parents;
    };

    static Strategy ROULETTE = (population, problem) -> {
        // TODO: add realization
        return null;
    };

    static Strategy TOURNAMENT = (population, problem) -> {
        // TODO: add realization
        return null;
    };


    private Selection() {
    }
}
