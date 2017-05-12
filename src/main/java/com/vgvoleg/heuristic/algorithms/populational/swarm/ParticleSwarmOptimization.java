package com.vgvoleg.heuristic.algorithms.populational.swarm;

import com.vgvoleg.heuristic.algorithms.populational.PopulationalAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.stream.IntStream;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class ParticleSwarmOptimization extends PopulationalAlgorithm {

    private double[][] velocities;

    private double[][] bestParticlePosition;
    private double[] bestSwarmPosition;

    private double omega, c1, c2;

    ParticleSwarmOptimization(OptimizationProblem problem, int agentCount, int maxIterations,
                              double omega, double c1, double c2) {
        super(problem, OptimizationType.MIN, agentCount, maxIterations);
        this.omega = omega;
        this.c1 = c1;
        this.c2 = c2;
    }


    protected void init() {
        super.init();

        velocities = new double[agentCount][problem.getDimension()];
        bestSwarmPosition = new double[problem.getDimension()];
        bestParticlePosition = new double[agentCount][problem.getDimension()];

        double low = 0, high = 0;
        for (int i = 0; i < agentCount; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                low = problem.getLeftEdge(j);
                high = problem.getRightEdge(j);
                velocities[i][j] = uniformDistribution(-(high - low), (high - low));
            }
            bestParticlePosition[i] = agents[i].clone();
        }
        bestSwarmPosition = bestParticlePosition[0].clone();
        updateBestPosition();
    }

    @Override
    protected void updateBestPosition() {
        for (int i = 0; i < agentCount; i++) {
            if (bestParticlePosition[i][problem.getDimension()] < bestSwarmPosition[problem.getDimension()]) {
                bestSwarmPosition = bestParticlePosition[i].clone();
                bestPosition = bestSwarmPosition.clone();
                bestSolution = bestPosition[problem.getDimension()];
            }
        }
    }

    @Override
    protected void generateNewPopulation() {
        IntStream.range(0, agentCount).parallel().forEach(i->{
            double rand1, rand2;
            for (int j = 0; j < problem.getDimension(); j++) {
                rand1 = uniformDistribution(0, 1);
                rand2 = uniformDistribution(0, 1);
                velocities[i][j] = omega * velocities[i][j]
                        + c1 * rand1 * (bestParticlePosition[i][j] - agents[i][j])
                        + c2 * rand2 * (bestSwarmPosition[j] - agents[i][j]);
                agents[i][j] += velocities[i][j];

                if (agents[i][j] < problem.getLeftEdge(j)) {
                    agents[i][j] = problem.getLeftEdge(j);
                }
                if (agents[i][j] > problem.getRightEdge(j)) {
                    agents[i][j] = problem.getRightEdge(j);
                }
            }
            agents[i][problem.getDimension()] = function(agents[i]);
            if (agents[i][problem.getDimension()] < bestParticlePosition[i][problem.getDimension()]) {
                bestParticlePosition[i] = agents[i].clone();
            }
        });
        updateBestPosition();
    }
}
