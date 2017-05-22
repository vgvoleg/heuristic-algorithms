package com.vgvoleg.heuristic.algorithms;

import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public abstract class HeuristicAlgorithm {
    protected OptimizationProblem problem;
    protected OptimizationType type;

    private int COEF;

    protected int successIteration = -1;
    private double EPS = 0.1;

    public HeuristicAlgorithm(OptimizationProblem problem, OptimizationType type) {
        this.problem = problem;
        this.type = type;
        this.COEF = problem.getType() == type ? 1 : -1;

    }

    public void setProblem(OptimizationProblem problem) {
        this.problem = problem;
        this.COEF = problem.getType() == type ? 1 : -1;
    }

    protected double function(double[] x) {
        return COEF * problem.f(x);
    }

    protected double getNormalResult(double x) {
        return COEF * x;
    }

    public abstract OptimizationResult findResult();

    public abstract OptimizationDetailedResult findDetailedResult(int screenshotMaxNum);

    // TODO: just for statistic
    protected void updateSuccessIteration(int iteration, double extremumValue) {
        if (successIteration == -1 && Math.abs(extremumValue - problem.getExpectedResult()) <= EPS) {
            successIteration = iteration + 1;
        }
    }

    public abstract String getName();

    public int getSuccessIteration() {
        return successIteration;
    }

    public void reset() {
        successIteration = -1;
    }

}
