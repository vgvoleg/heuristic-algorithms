package com.vgvoleg.heuristic.algorithms.populational.swarm;

import com.vgvoleg.heuristic.algorithms.AlgorithmBuilder;
import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public class ArtificialImmuneSystemBuilder implements AlgorithmBuilder {

    private int agentCount = 20;
    private int maxIterations = 1000;
    private int parentCount = 10;
    private int refreshCount = 5;
    private double beta = 5;
    private double r = 0.1;

    public ArtificialImmuneSystemBuilder setAgentCount(int agentCount) {
        this.agentCount = agentCount;
        return this;
    }

    public ArtificialImmuneSystemBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public ArtificialImmuneSystemBuilder setParentCount(int parentCount) {
        this.parentCount = parentCount;
        return this;
    }

    public ArtificialImmuneSystemBuilder setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
        return this;
    }

    public ArtificialImmuneSystemBuilder setParameters(double beta, double r) {
        this.beta = beta;
        this.r = r;
        return this;
    }

    @Override
    public HeuristicAlgorithm buildForProblem(OptimizationProblem problem) {
        return new ArtificialImmuneSystem(problem, agentCount, maxIterations, parentCount,
                refreshCount, beta, r);
    }
}
