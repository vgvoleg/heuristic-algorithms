package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.algorithms.HeuristicAlgorythm;
import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationResult;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import static com.vgvoleg.heuristic.algorithms.Generator.uniformDistribution;

class RealGeneticAlgorithm extends HeuristicAlgorythm {

    private Selection.Strategy selection;
    private Crossing.Strategy crossing;
    private Mutation.Strategy mutation;

    private int COEF;
    private int populationSize;
    private double[][] population;
    private int maxPopulationNumber;
    private double[] fitnesses;
    private double sumFitness;

    protected RealGeneticAlgorithm(OptimizationProblem problem, int populationSize, int maxPopulationNumber,
                                   Selection.Strategy selection, Crossing.Strategy crossing, Mutation.Strategy mutation) {
        super(problem, OptimizationType.MAX);
        COEF = problem.getType() == type ? 1 : -1;
        this.selection = selection;
        this.crossing = crossing;
        this.mutation = mutation;
        this.populationSize = populationSize;
        this.maxPopulationNumber = maxPopulationNumber;
    }

    private void createPopulation() {
        sumFitness = 0;
        population = new double[populationSize][problem.getDimension()];
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < problem.getDimension(); j++) {
                population[i][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
            fitnesses[i] = COEF * problem.f(population[i]);
            sumFitness += fitnesses[i];
        }
    }

    @Override
    public OptimizationResult findResult() {
        OptimizationResult result = null;

        int currentIteration = 1;
        int currentPopulation = 0;
        fitnesses = new double[populationSize];
        createPopulation();
        double[][] parents, childrens, mutants;
        boolean flag = true;
        while (flag) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            int indexFuckingRetard = 0;
            double minFittness = fitnesses[0];
            for (int i = 1; i < populationSize; i++) {
                if (minFittness > fitnesses[i]) {
                    indexFuckingRetard = i;
                    minFittness = fitnesses[i];
                }
            }

            population[indexFuckingRetard] = mutants[indexMutant];

            sumFitness -= fitnesses[indexFuckingRetard];
            fitnesses[indexFuckingRetard] = COEF * problem.f(population[indexFuckingRetard]);
            sumFitness += fitnesses[indexFuckingRetard];


            if (currentIteration < populationSize) {
                currentIteration++;
            } else {

                int indexOfWinner = 0;
                double maxFittness = fitnesses[0];
                for (int i = 1; i < populationSize; i++) {
                    if (maxFittness < fitnesses[i]) {
                        indexOfWinner = i;
                        maxFittness = fitnesses[i];
                    }
                }

                currentPopulation++;
                if (currentPopulation == maxPopulationNumber) {
                    flag = false;
                    result = new OptimizationResult(COEF * problem.f(population[indexOfWinner]), population[indexOfWinner]);
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
        sumFitness = 0;
        fitnesses = new double[populationSize];
        createPopulation();
        double[][] parents, childrens, mutants;
        boolean flag = true;
        while (flag) {
            parents = selection.execute(population, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);

            int indexMutant = (uniformDistribution(0, 1) < 0.5) ? 0 : 1;
            int indexFuckingRetard = 0;
            double minFittness = fitnesses[0];
            for (int i = 1; i < populationSize; i++) {
                if (minFittness > fitnesses[i]) {
                    indexFuckingRetard = i;
                    minFittness = fitnesses[i];
                }
            }

            population[indexFuckingRetard] = mutants[indexMutant];

            sumFitness -= fitnesses[indexFuckingRetard];
            fitnesses[indexFuckingRetard] = COEF * problem.f(population[indexFuckingRetard]);
            sumFitness += fitnesses[indexFuckingRetard];


            if (currentIteration < populationSize) {
                currentIteration++;
            } else {

                int indexOfWinner = 0;
                double maxFittness = fitnesses[0];
                for (int i = 1; i < populationSize; i++) {
                    if (maxFittness < fitnesses[i]) {
                        indexOfWinner = i;
                        maxFittness = fitnesses[i];
                    }
                }
                if (currentPopulation / screenshotMark >= screenshotCurrNum) {
                    screenshotCurrNum++;
                    result.addExtremum(COEF * problem.f(population[indexOfWinner]), population[indexOfWinner]);
                }
                currentPopulation++;
                if (currentPopulation == maxPopulationNumber) {
                    flag = false;
                    result.addExtremum(COEF * problem.f(population[indexOfWinner]), population[indexOfWinner]);
                } else {
                    currentIteration = 1;
                }
            }
        }

        return result;
    }
}
