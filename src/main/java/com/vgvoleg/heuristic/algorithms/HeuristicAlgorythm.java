package com.vgvoleg.heuristic.algorithms;

import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public abstract class HeuristicAlgorythm {
    protected OptimizationProblem problem;
    protected OptimizationType type;

    public HeuristicAlgorythm(OptimizationProblem problem, OptimizationType type) {
        this.problem = problem;
        this.type = type;
    }

    public abstract OptimizationResult findResult();

    public abstract OptimizationDetailedResult findDetailedResult(int screenShotMaxNum);

}
