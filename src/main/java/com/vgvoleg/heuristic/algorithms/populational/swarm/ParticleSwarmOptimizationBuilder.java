package com.vgvoleg.heuristic.algorithms.populational.swarm;

import com.vgvoleg.heuristic.algorithms.AlgorithmBuilder;
import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public class ParticleSwarmOptimizationBuilder implements AlgorithmBuilder {
    private int agentCount = 20;
    private int maxIterations = 1000;

    private double omega = 0.7298, c1 = 2, c2 = 2;

    public ParticleSwarmOptimizationBuilder setAgentCount(int agentCount) {
        this.agentCount = agentCount;
        return this;
    }

    public ParticleSwarmOptimizationBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public ParticleSwarmOptimizationBuilder setParameters(double omega, double c1, double c2) {
        this.omega = omega;
        this.c1 = c1;
        this.c2 = c2;
        return this;
    }

    @Override
    public HeuristicAlgorithm buildForProblem(OptimizationProblem problem) {
        return new ParticleSwarmOptimization(problem, agentCount,
                maxIterations, omega, c1, c2);
    }
}
