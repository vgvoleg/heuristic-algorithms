package com.vgvoleg.heuristic.algorithms.swarm;

import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.ArrayList;
import java.util.List;

import static com.vgvoleg.heuristic.util.Generator.generateIndex;
import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class DifferentialEvolution extends HeuristicAlgorithm {

    private int agentCount;
    private double[][] agents;

    private int maxIterations;
    private double F;
    private double CR;

    private double[][] tempAgents;
    private List<Integer> indexes;

    DifferentialEvolution(OptimizationProblem problem, int agentCount,
                          int maxIterations, double F, double CR) {
        super(problem, OptimizationType.MIN);
        this.agentCount = agentCount;
        this.maxIterations = maxIterations;
        this.F = F;
        this.CR = CR;
    }

    private void init() {
        agents = new double[agentCount][problem.getDimension()];
        for (int i = 0; i < agentCount; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                agents[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
        }
    }

    private double[] createNewAgent(double[] x1, double[] x2, double[] x3) {
        double[] result = new double[problem.getDimension()];
        double tempValue;
        for (int i = 0; i < problem.getDimension(); i++) {
            tempValue = x1[i] + F * (x2[i] - x3[i]);
            if (!(problem.getLeftEdge(i) <= tempValue && tempValue <= problem.getRightEdge(i))) {
                tempValue = uniformDistribution(problem.getLeftEdge(i), problem.getRightEdge(i));
            }
            result[i] = tempValue;
        }
        return result;
    }

    private double[] crossing(double[] x1, double[] x2) {
        for (int i = 0; i < problem.getDimension() - 1; i++) {
            if (uniformDistribution(0, 1) > CR) {
                x2[i] = x1[i];
            }
        }
        return x2;
    }

    private void generateNewPopulation() {
        tempAgents = new double[agentCount][problem.getDimension()];
        double[] newAgent;
        for (int i = 0; i < agentCount; i++) {
            indexes = new ArrayList<>();
            indexes.add(i);
            int newIndex;
            while (indexes.size() != 4) {
                newIndex = generateIndex(agentCount);
                if (!indexes.contains(newIndex)) {
                    indexes.add(newIndex);
                }
            }
            newAgent = createNewAgent(agents[indexes.get(1)],
                    agents[indexes.get(2)],
                    agents[indexes.get(3)]);
            newAgent = crossing(agents[i], newAgent);
            tempAgents[i] = function(newAgent) < function(agents[i]) ? newAgent : agents[i];
        }

        agents = tempAgents;
    }

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
