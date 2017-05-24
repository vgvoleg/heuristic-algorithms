package com.vgvoleg.heuristic.algorithms.populational.swarm;

import com.vgvoleg.heuristic.algorithms.populational.PopulationalAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class ArtificialImmuneSystem extends PopulationalAlgorithm {

    private static String METHOD_NAME = "Artificial Immune System";

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
        super.init();
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
            if (clones[i][problem.getDimension()] < agents[indexParent][problem.getDimension()]) {
                agents[indexParent] = clones[i].clone();
            }
        }
    }

    private void refreshAgent(int agentIndex){
        for (int j = 0; j < problem.getDimension(); j++) {
            agents[agentIndex][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
        }
        agents[agentIndex][problem.getDimension()] = function(agents[agentIndex]);
    }

    @Override
    protected void generateNewPopulation() {
        sortAgents();
        IntStream.range(0, parentCount).parallel().forEach(i->{
            updateParent(i);
            if (i<refreshCount){
                refreshAgent(agentCount-1-i);
            }
        });
    }

    @Override
    public String getName() {
        return METHOD_NAME;
    }
}
