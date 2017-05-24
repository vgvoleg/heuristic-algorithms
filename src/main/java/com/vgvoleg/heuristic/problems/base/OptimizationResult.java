package com.vgvoleg.heuristic.problems.base;

public class OptimizationResult {

    private OptimizationProblem problem;

    private double extremumValue;
    private double[] extremumPoint;

    public OptimizationResult(OptimizationProblem problem) {
        this.problem = problem;
    }

    public void setFinalResult(double extremumValue, double[] extremumPoint) {
        this.extremumPoint = extremumPoint;
        this.extremumValue = extremumValue;
    }

    public double getExtremumValue() {
        return extremumValue;
    }

    public double[] getExtremumPoint() {
        return extremumPoint;
    }

    public void printResult() {
        System.out.print("Extremum point: {");
        for (int i = 0; i < problem.getDimension() - 1; i++) {
            System.out.printf("%.6f, ", extremumPoint[i]);
        }
        System.out.printf("%.6f}\n", extremumPoint[problem.getDimension() - 1]);
        System.out.printf("Extremum value: %.6f", extremumValue);
        System.out.println();
    }
}
