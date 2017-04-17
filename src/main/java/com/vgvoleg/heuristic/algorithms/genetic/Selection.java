package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.algorithms.base.Population;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

import static com.vgvoleg.heuristic.algorithms.Generator.uniformDistribution;

class Selection {

    interface Strategy {
        double[][] execute(Population population, OptimizationProblem problem);
    }

    static Strategy PANMIXIA = (population, problem) -> {
        double[][] parents = new double[2][problem.getDimension()];
        int firstParentIndex = (int) uniformDistribution(0, population.getSize());
        int secondParentIndex;
        while (true) {
            secondParentIndex = (int) uniformDistribution(0, population.getSize());
            if (secondParentIndex != firstParentIndex) {
                break;
            }
        }

        parents[0] = population.getElement(firstParentIndex);
        parents[1] = population.getElement(secondParentIndex);

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
