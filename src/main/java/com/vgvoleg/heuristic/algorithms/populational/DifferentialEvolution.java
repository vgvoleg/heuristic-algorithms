package com.vgvoleg.heuristic.algorithms.populational;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.ArrayList;
import java.util.List;

import static com.vgvoleg.heuristic.util.Generator.generateIndex;
import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class DifferentialEvolution extends PopulationalAlgorithm {

    private double F;
    private double CR;

    DifferentialEvolution(OptimizationProblem problem, int agentCount,
                          int maxIterations, double F, double CR) {
        super(problem, OptimizationType.MIN, agentCount, maxIterations);
        this.F = F;
        this.CR = CR;
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

    @Override
    protected void updateBestPosition() {
        for (int i = 0; i < agentCount; i++) {
            if (function(agents[i]) < bestSolution) {
                bestPosition = agents[i].clone();
                bestSolution = function(bestPosition);
            }
        }
    }

    @Override
    protected void generateNewPopulation() {
        double[][] tempAgents = new double[agentCount][problem.getDimension()];
        double[] newAgent;
        for (int i = 0; i < agentCount; i++) {
            List<Integer> indexes = new ArrayList<>();
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
        updateBestPosition();
    }
}
