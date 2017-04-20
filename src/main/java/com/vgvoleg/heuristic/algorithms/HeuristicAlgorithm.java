package com.vgvoleg.heuristic.algorithms;

import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public abstract class HeuristicAlgorithm {
    protected OptimizationProblem problem;
    protected OptimizationType type;
    private int COEF;

    public HeuristicAlgorithm(OptimizationProblem problem, OptimizationType type) {
        this.problem = problem;
        this.type = type;
        this.COEF = problem.getType() == type ? 1 : -1;

    }

    protected double function(double[] x) {
        return COEF * problem.f(x);
    }

    public abstract OptimizationResult findResult();

    public abstract OptimizationDetailedResult findDetailedResult(int screenshotMaxNum);

}
