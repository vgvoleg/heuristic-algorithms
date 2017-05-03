package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import static com.vgvoleg.heuristic.algorithms.Generator.uniformDistribution;

class RealGeneticAlgorithm extends HeuristicAlgorithm {

    private Selection.Strategy selection;
    private Crossing.Strategy crossing;
    private Mutation.Strategy mutation;
    private Stop.Strategy stop;

    private int populationSize;
    private int maxPopulationNumber;
    private double[][] population;

    RealGeneticAlgorithm(OptimizationProblem problem, int populationSize, int maxPopulationNumber,
                         Selection.Strategy selection, Crossing.Strategy crossing, Mutation.Strategy mutation,
                         Stop.Strategy stop) {

        super(problem, OptimizationType.MAX);
        this.selection = selection;
        this.crossing = crossing;
        this.mutation = mutation;
        this.stop = stop;
        this.populationSize = populationSize;
        this.maxPopulationNumber = maxPopulationNumber;
    }

    private void init() {
        population = new double[populationSize][problem.getDimension()];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                population[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
        }
    }

    private void changeWorstElement(double[] x) {
        int indexWorst = 0;
        double worstValue = function(population[0]);
        for (int i = 1; i < populationSize; i++) {
            if (function(population[i]) < worstValue) {
                worstValue = function(population[i]);
                indexWorst = i;
            }
        }
        population[indexWorst] = x;
    }

    private int getBestElementIndex() {
        int indexBest = 0;
        double bestValue = function(population[0]);
        for (int i = 1; i < populationSize; i++) {
            if (function(population[i]) > bestValue) {
                bestValue = function(population[i]);
                indexBest = i;
            }
        }
        return indexBest;
    }

    @Override
    public OptimizationResult findResult() {
        OptimizationResult result = null;
        int currentIteration = 1;
        int currentPopulation = 0;
        init();

        double[][] parents, childrens, mutants;
        while (!stop.execute(currentPopulation, maxPopulationNumber)) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            changeWorstElement(mutants[indexMutant]);

            if (currentIteration < populationSize) {
                currentIteration++;
            } else {
                currentPopulation++;
                if (currentPopulation == maxPopulationNumber) {
                    int indexOfWinner = getBestElementIndex();
                    result = new OptimizationResult(function(population[indexOfWinner]),
                            population[indexOfWinner]);
                } else {
                    currentIteration = 1;
                }
            }
        }
        return result;
    }

    @Override
    public OptimizationDetailedResult findDetailedResult(int screenshotMaxNum) {
        OptimizationDetailedResult result = new OptimizationDetailedResult(maxPopulationNumber, screenshotMaxNum);

        int currentIteration = 1;
        int currentPopulation = 0;
        init();

        double[][] parents, childrens, mutants;
        while (!stop.execute(currentPopulation, maxPopulationNumber)) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            changeWorstElement(mutants[indexMutant]);

            if (currentIteration < populationSize) {
                currentIteration++;
            } else {
                currentPopulation++;
                if (currentPopulation != maxPopulationNumber) {
                    currentIteration = 1;
                }
                result.addPopulation(population, currentPopulation);
            }
        }

        return result;
    }
}
