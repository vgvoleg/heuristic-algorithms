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

    protected double bestSolution;
    protected double[] bestPosition;

    protected int maxIterations;

    public PopulationalAlgorithm(OptimizationProblem problem, OptimizationType type,
                                 int agentCount, int maxIterations) {
        super(problem, type);
        this.agentCount = agentCount;
        this.maxIterations = maxIterations;
    }

    protected void init() {
        this.successIteration = -1;
        agents = new double[agentCount][problem.getDimension() + 1];
        for (int i = 0; i < agentCount; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                agents[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
            agents[i][problem.getDimension()] = function(agents[i]);
        }
        bestPosition = agents[0].clone();
        bestSolution = bestPosition[problem.getDimension()];
    }

    protected void updateBestPosition() {
        if (type == OptimizationType.MAX) {
            for (int i = 0; i < agentCount; i++) {
                if (agents[i][problem.getDimension()] > bestSolution) {
                    bestPosition = agents[i].clone();
                    bestSolution = agents[i][problem.getDimension()];
                }
            }
        } else {
            for (int i = 0; i < agentCount; i++) {
                if (agents[i][problem.getDimension()] < bestSolution) {
                    bestPosition = agents[i].clone();
                    bestSolution = agents[i][problem.getDimension()];
                }
            }
        }
    }

    protected abstract void generateNewPopulation();

    @Override
    public OptimizationResult findResult() {
        OptimizationResult result = new OptimizationResult(problem);
        int currentIteration = 0;

        init();
        while (currentIteration != maxIterations) {
            generateNewPopulation();
            updateBestPosition();
            updateSuccessIteration(currentIteration, getNormalResult(bestSolution)); //TODO: just for statistic
            currentIteration++;
        }
        updateBestPosition();
        result.setFinalResult(getNormalResult(bestSolution), bestPosition);
        return result;
    }

    @Override
    public OptimizationDetailedResult findDetailedResult(int screenshotMaxNum) {
        OptimizationDetailedResult result = new OptimizationDetailedResult(problem, maxIterations, screenshotMaxNum);
        int currentIteration = 0;

        init();
        while (currentIteration != maxIterations) {
            generateNewPopulation();
            result.addPopulation(agents, currentIteration);
            currentIteration++;
        }
        updateBestPosition();
        result.setFinalResult(getNormalResult(bestSolution), bestPosition);
        return result;
    }
}
