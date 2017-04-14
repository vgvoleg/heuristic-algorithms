package com.vgvoleg.heuristic.problems.base;

public final class OptimizationProblem {
    private String type;
    private int dimension;
    private Function function;
    private double[][] edges;

    public OptimizationProblem(String type, int dimension, Function function, double[][] edges) {
        this.type = type;
        this.dimension = dimension;
        this.function = function;
        this.edges = edges;
    }

    public String getType() {
        return type;
    }

    public int getDimension() {
        return dimension;
    }

    public Function getFunction() {
        return function;
    }

    public double[][] getEdges() {
        return edges;
    }
}
