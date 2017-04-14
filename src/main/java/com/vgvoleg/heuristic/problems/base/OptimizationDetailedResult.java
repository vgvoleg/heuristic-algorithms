package com.vgvoleg.heuristic.problems.base;

import java.util.LinkedList;
import java.util.List;

public final class OptimizationDetailedResult {
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
}
