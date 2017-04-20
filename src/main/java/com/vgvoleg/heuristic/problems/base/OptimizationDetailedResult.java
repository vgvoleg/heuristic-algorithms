package com.vgvoleg.heuristic.problems.base;

import com.vgvoleg.heuristic.algorithms.genetic.base.Population;

import java.util.LinkedList;
import java.util.List;

public final class OptimizationDetailedResult {

    private List<Population> populations;

    public OptimizationDetailedResult() {
        populations = new LinkedList<>();
    }

    public void addPopulation(Population population) {
        populations.add(population);
    }

    public Population getPopulation(int index) {
        return populations.get(index);
    }

    public void printResult() {
        for (int i = 0; i < populations.size(); i++) {
            System.out.println("Screenshot #" + i);
            System.out.print("Extremum point: {");
            int bestIndex = populations.get(i).getBestElementIndex();
            int j;
            for (j = 0; j < populations.get(i).getDimension() - 1; j++) {
                System.out.printf("%.6f, ", populations.get(i).getElement(bestIndex)[j]);
            }
            System.out.printf("%.6f}\n", populations.get(i).getElement(bestIndex)[j]);
            System.out.printf("Extremum value: %.6f\n", populations.get(i).getFitness(bestIndex));
            System.out.println("===========================");
        }
    }
}
