package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.algorithms.genetic.base.Population;
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
    private Population population;

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

        this.population = new Population(populationSize, problem, COEF);
    }

    @Override
    public OptimizationResult findResult() {
        OptimizationResult result = null;
        int currentIteration = 1;
        int currentPopulation = 0;
        population.init();

        double[][] parents, childrens, mutants;
        while (!stop.execute(currentPopulation, maxPopulationNumber)) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            population.changeWorstElement(mutants[indexMutant]);

            if (currentIteration < populationSize) {
                currentIteration++;
            } else {
                currentPopulation++;
                if (currentPopulation == maxPopulationNumber) {
                    int indexOfWinner = population.getBestElementIndex();
                    result = new OptimizationResult(population.getFitness(indexOfWinner), population.getElement(indexOfWinner));
                } else {
                    currentIteration = 1;
                }
            }
        }
        return result;
    }

    @Override
    public OptimizationDetailedResult findDetailedResult(int screenshotMaxNum) {
        OptimizationDetailedResult result = new OptimizationDetailedResult();
        int screenshotMark = (maxPopulationNumber >= screenshotMaxNum) ? maxPopulationNumber / screenshotMaxNum : maxPopulationNumber;
        int screenshotCurrNum = 0;
        int currentIteration = 1;
        int currentPopulation = 0;
        population.init();

        double[][] parents, childrens, mutants;
        while (currentPopulation != maxPopulationNumber) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            population.changeWorstElement(mutants[indexMutant]);

            if (currentIteration < populationSize) {
                currentIteration++;
            } else {
                if (currentPopulation / screenshotMark > screenshotCurrNum) {
                    screenshotCurrNum++;
                    result.addPopulation(population.getScreenshot());
                }
                currentPopulation++;
                if (currentPopulation == maxPopulationNumber) {
                    result.addPopulation(population.getScreenshot());
                } else {
                    currentIteration = 1;
                }
            }
        }
        return result;
    }
}
