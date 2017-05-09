package com.vgvoleg.heuristic.problems.base;

import java.util.LinkedList;
import java.util.List;

public final class OptimizationDetailedResult {

    private List<double[][]> populations;
    private int screenshotMark;
    private int screenshotCurrNum;

    private double extremumValue;
    private double[] extremumPoint;

    public OptimizationDetailedResult(int iterations, int screenshotNumber) {
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

    public void setFinalResult(double extremumValue, double[] extremumPoint) {
        this.extremumPoint = extremumPoint;
        this.extremumValue = extremumValue;
    }

    public List<double[][]> getPopulations() {
        return populations;
    }

    public double getExtremumValue() {
        return extremumValue;
    }

    public double[] getExtremumPoint() {
        return extremumPoint;
    }

    public void printResult() {
        System.out.print("Extremum point: {");
        for (int i = 0; i < extremumPoint.length - 1; i++) {
            System.out.printf("%.6f, ", extremumPoint[i]);
        }
        System.out.printf("%.6f}\n", extremumPoint[extremumPoint.length - 1]);
        System.out.printf("Extremum value: %.6f", extremumValue);
        System.out.println();
        System.out.println("===================================");
    }
}
