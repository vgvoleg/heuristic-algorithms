package com.vgvoleg.heuristic.problems.base;

public class OptimizationProblem {
    private OptimizationType type;
    private int dimension;
    private Function function;
    private double[][] edges;

    public OptimizationProblem(OptimizationType type, int dimension, Function function, double[][] edges) {
        this.type = type;
        this.dimension = dimension;
        this.function = function;
        this.edges = edges;
    }

    public final OptimizationType getType() {
        return type;
    }

    public final int getDimension() {
        return dimension;
    }

    public final double f(double[] x) {
        return function.value(x);
    }

    public double getLeftEdge(int dimension) {
        return edges[dimension][0];
    }

    public double getRightEdge(int dimension) {
        return edges[dimension][1];
    }
}
