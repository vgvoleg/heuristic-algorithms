package com.vgvoleg.heuristic.algorithms.populational;

import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

public abstract class PopulationalAlgorithm extends HeuristicAlgorithm {

    protected int agentCount;
    protected double[][] agents;

    protected int maxIterations;

    public PopulationalAlgorithm(OptimizationProblem problem, OptimizationType type,
                                 int agentCount, int maxIterations) {
        super(problem, type);
        this.agentCount = agentCount;
        this.maxIterations = maxIterations;
    }

    protected void init() {
        agents = new double[agentCount][problem.getDimension()];
        for (int i = 0; i < agentCount; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                agents[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
        }
    }

    protected abstract void generateNewPopulation();

    @Override
    public OptimizationResult findResult() {
        OptimizationResult result;
        int currentIteration = 0;

        init();
        while (currentIteration != maxIterations) {
            generateNewPopulation();
            currentIteration++;
        }

        double[] bestAgent = agents[0];
        double bestValue = function(bestAgent);
        double temp;
        for (int i = 1; i < agentCount; i++) {
            temp = function(agents[i]);
            if (temp < bestValue) {
                bestAgent = agents[i];
                bestValue = temp;
            }
        }
        result = new OptimizationResult(bestValue, bestAgent);
        return result;
    }

    @Override
    public OptimizationDetailedResult findDetailedResult(int screenshotMaxNum) {
        OptimizationDetailedResult result = new OptimizationDetailedResult(maxIterations, screenshotMaxNum);
        int currentIteration = 0;
        init();
        while (currentIteration != maxIterations) {
            generateNewPopulation();
            result.addPopulation(agents, currentIteration);
            currentIteration++;
        }
        return result;
    }
}
