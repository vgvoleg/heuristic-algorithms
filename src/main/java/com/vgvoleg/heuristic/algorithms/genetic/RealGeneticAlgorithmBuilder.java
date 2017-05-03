package com.vgvoleg.heuristic.algorithms.genetic;

import com.vgvoleg.heuristic.algorithms.AlgorithmBuilder;
import com.vgvoleg.heuristic.algorithms.HeuristicAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public class RealGeneticAlgorithmBuilder implements AlgorithmBuilder {

    private Selection.Strategy selection = Selection.PANMIXIA;
    private Crossing.Strategy crossing = Crossing.SIMPLE_CROSSOVER;
    private Mutation.Strategy mutation = Mutation.RANDOM;
    private Stop.Strategy stop = Stop.COUNT_ITERATIONS;

    private int populationSize = 20;
    private int maxPopulationNumber = 1000;


    public RealGeneticAlgorithmBuilder setSelectionType(int type) {
        switch (type) {
            case 0:
                this.selection = Selection.PANMIXIA;
                break;
            case 1:
                this.selection = Selection.ROULETTE;
                break;
            case 2:
                this.selection = Selection.TOURNAMENT;
                break;
        }
        return this;
    }

    public RealGeneticAlgorithmBuilder setCrossingType(int type) {
        switch (type) {
            case 0:
                this.crossing = Crossing.SIMPLE_CROSSOVER;
                break;
            case 1:
                this.crossing = Crossing.FLAT_CROSSOVER;
                break;
            case 2:
                this.crossing = Crossing.ARITHMETICAL;
                break;
        }
        return this;
    }

    public RealGeneticAlgorithmBuilder setMutationType(int type) {
        switch (type) {
            case 0:
                this.mutation = Mutation.RANDOM;
                break;
            case 1:
                this.mutation = Mutation.IRREGULAR;
                break;
        }
        return this;
    }

    public RealGeneticAlgorithmBuilder setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public RealGeneticAlgorithmBuilder setMaxPopulationNumber(int maxPopulationNumber) {
        this.maxPopulationNumber = maxPopulationNumber;
        return this;
    }

    @Override
    public HeuristicAlgorithm buildForProblem(OptimizationProblem problem) {
        return new RealGeneticAlgorithm(problem, populationSize, maxPopulationNumber,
                selection, crossing, mutation, stop);
    }
}
