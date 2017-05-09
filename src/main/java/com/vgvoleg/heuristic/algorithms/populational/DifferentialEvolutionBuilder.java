package com.vgvoleg.heuristic.algorithms.populational;

import com.vgvoleg.heuristic.algorithms.AlgorithmBuilder;
import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public class DifferentialEvolutionBuilder implements AlgorithmBuilder {
    private int agentCount = 20;
    private int maxIterations = 1000;

    private double F = 0.5;
    private double CR = 0.8;

    public DifferentialEvolutionBuilder setAgentCount(int agentCount) {
        this.agentCount = agentCount;
        return this;
    }

    public DifferentialEvolutionBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public DifferentialEvolutionBuilder setParameters(double F, double CR) {
        this.F = F;
        this.CR = CR;
        return this;
    }

    @Override
    public HeuristicAlgorithm buildForProblem(OptimizationProblem problem) {
        return new DifferentialEvolution(problem, agentCount,
                maxIterations, F, CR);
    }
}
