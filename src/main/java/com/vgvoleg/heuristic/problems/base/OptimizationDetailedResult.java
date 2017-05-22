package com.vgvoleg.heuristic.problems.base;

import java.util.LinkedList;
import java.util.List;

public final class OptimizationDetailedResult extends OptimizationResult {

    private List<double[][]> populations;
    private int screenshotMark;
    private int screenshotCurrNum;

    public OptimizationDetailedResult(OptimizationProblem problem, int iterations, int screenshotNumber) {
        super(problem);
        screenshotMark = (iterations >= screenshotNumber) ?
                iterations / screenshotNumber : iterations;
        screenshotCurrNum = 0;
        populations = new LinkedList<>();
    }

    public void addPopulation(double[][] population, int iteration) {
        if (iteration / screenshotMark > screenshotCurrNum) {
            screenshotCurrNum++;
            double[][] save = new double[population.length][population[0].length];
            for (int i = 0; i < population.length; i++) {
                save[i] = population[i].clone();
            }
            populations.add(save);
        }
    }

    public List<double[][]> getPopulations() {
        return populations;
    }
}
