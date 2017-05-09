package com.vgvoleg.heuristic.problems.base;

public final class OptimizationResult {

    private double extremumValue;
    private double[] extremumPoint;

    public OptimizationResult(double extremumValue, double[] extremumPoint) {
        this.extremumValue = extremumValue;
        this.extremumPoint = extremumPoint;
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
    }
}
