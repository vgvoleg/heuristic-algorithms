package com.vgvoleg.heuristic.algorithms;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public interface AlgorithmBuilder {
    HeuristicAlgorithm buildForProblem(OptimizationProblem problem);
}
