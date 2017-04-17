package com.vgvoleg.heuristic.problems.base;

import java.util.LinkedList;
import java.util.List;

public final class OptimizationDetailedResult {

    // TODO: refactor for saving population, not the only point

    private List<Double> extremumValues = new LinkedList<>();
    private List<double[]> extremumPoints = new LinkedList<>();

    public OptimizationDetailedResult() {
    }

    public void addExtremum(double extremumValue, double[] extremumPoint) {
        extremumValues.add(extremumValue);
        extremumPoints.add(extremumPoint);
    }

    public List<Double> getExtremumValues() {
        return extremumValues;
    }

    public List<double[]> getExtremumPoints() {
        return extremumPoints;
    }

    public void printResult() {
        for (int i = 0; i < extremumValues.size(); i++) {
            System.out.println("Screenshot #" + i);
            System.out.print("Extremum point: {");
            for (int j = 0; j < extremumPoints.get(i).length - 1; j++) {
                System.out.printf("%.6f, ", extremumPoints.get(i)[j]);
            }
            System.out.printf("%.6f}\n", extremumPoints.get(i)[extremumPoints.get(i).length - 1]);
            System.out.printf("Extremum value: %.6f\n", extremumValues.get(i));
            System.out.println("===========================");
        }
    }
}
