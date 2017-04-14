package com.vgvoleg.heuristic.algorithms;

import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;

public abstract class HeuristicAlgorythm {
    protected OptimizationProblem problem;

    public HeuristicAlgorythm(OptimizationProblem problem) {
        this.problem = problem;
    }

    public abstract OptimizationResult findResult();

    public abstract OptimizationDetailedResult findDetailedResult();

}
