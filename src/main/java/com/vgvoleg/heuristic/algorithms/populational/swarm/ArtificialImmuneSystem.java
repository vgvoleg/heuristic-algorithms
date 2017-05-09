package com.vgvoleg.heuristic.algorithms.populational.swarm;

import com.vgvoleg.heuristic.algorithms.populational.PopulationalAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.Arrays;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class ArtificialImmuneSystem extends PopulationalAlgorithm {

    private int parentCount;
    private int refreshCount;
    private double beta;
    private double r;

    private int[] cloneNum;

    ArtificialImmuneSystem(OptimizationProblem problem, int agentCount, int maxIterations,
                           int parentCount, int refreshCount, double beta, double r) {
        super(problem, OptimizationType.MIN, agentCount, maxIterations);
        this.parentCount = parentCount;
        this.refreshCount = refreshCount;
        this.beta = beta;
        this.r = r;
    }

    @Override
    protected void init() {
        agents = new double[agentCount][problem.getDimension() + 1];
        for (int i = 0; i < agentCount; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                agents[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
            agents[i][problem.getDimension()] = function(agents[i]);
        }
        bestPosition = agents[0].clone();
        bestSolution = function(bestPosition);

        cloneNum = new int[parentCount];
        for (int i = 0; i < parentCount; i++) {
            cloneNum[i] = (int) ((beta * agentCount) / (i + 1));
        }
    }

    private void sortAgents() {
        Arrays.sort(agents, (doubles, t1) -> {
            if (doubles[problem.getDimension()] < t1[problem.getDimension()]) {
                return -1;
            }

            if (doubles[problem.getDimension()] > t1[problem.getDimension()]) {
                return 1;
            }
            return 0;
        });
    }

    private void updateParent(int indexParent) {
        double[][] clones = new double[cloneNum[indexParent]][problem.getDimension() + 1];
        double u;
        boolean success;

        double[] clone = new double[problem.getDimension() + 1];
        for (int i = 0; i < cloneNum[indexParent]; i++) {
            clones[i] = agents[indexParent].clone();

            for (int j = 0; j < problem.getDimension(); j++) {
                success = false;
                while (!success) {
                    u = uniformDistribution(0, 1);
                    if (u > 0.5) {
                        clone[j] = clones[i][j] + uniformDistribution(0,
                                problem.getRightEdge(j) - agents[indexParent][j]) * r;
                    } else {
                        clone[j] = clones[i][j] - uniformDistribution(0,
                                agents[indexParent][j] - problem.getLeftEdge(j)) * r;
                    }
                    if (problem.getLeftEdge(j) <= clone[j] && clone[j] <= problem.getRightEdge(j)) {
                        success = true;
                        clones[i][j] = clone[j];
                    }
                }
            }

            clones[i][problem.getDimension()] = function(clones[i]);
        }

        for (int i = 0; i < cloneNum[indexParent]; i++) {
            if (function(clones[i]) < function(agents[indexParent])) {
                agents[indexParent] = clones[i].clone();
            }
        }
    }

    private void refreshWorstAgents() {
        for (int i = agentCount - 1; i >= agentCount - 1 - refreshCount; i--) {
            for (int j = 0; j < problem.getDimension(); j++) {
                agents[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
            agents[i][problem.getDimension()] = function(agents[i]);
        }
    }

    @Override
    protected void generateNewPopulation() {
        sortAgents();
        for (int i = 0; i < parentCount; i++) {
            updateParent(i);
        }
        refreshWorstAgents();
    }
}
