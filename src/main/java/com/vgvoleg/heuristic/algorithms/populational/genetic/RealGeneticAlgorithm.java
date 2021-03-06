package com.vgvoleg.heuristic.algorithms.populational.genetic;

import com.vgvoleg.heuristic.algorithms.populational.PopulationalAlgorithm;
import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

import java.util.stream.IntStream;

import static com.vgvoleg.heuristic.util.Generator.uniformDistribution;

class RealGeneticAlgorithm extends PopulationalAlgorithm {

    private static String METHOD_NAME = "Genetic Algorithm";

    private Selection.Strategy selection;
    private Crossing.Strategy crossing;
    private Mutation.Strategy mutation;

    RealGeneticAlgorithm(OptimizationProblem problem, int agentCount, int maxIterations,
                         Selection.Strategy selection, Crossing.Strategy crossing, Mutation.Strategy mutation) {

        super(problem, OptimizationType.MAX, agentCount, maxIterations);
        this.selection = selection;
        this.crossing = crossing;
        this.mutation = mutation;
    }

    private void changeWorstElement(double[] x) {
        int indexWorst = 0;
        double worstValue = agents[0][problem.getDimension()];
        for (int i = 1; i < agentCount; i++) {
            if (agents[i][problem.getDimension()] < worstValue) {
                worstValue = agents[i][problem.getDimension()];
                indexWorst = i;
            }
        }
        agents[indexWorst] = x;
    }

    @Override
    protected void generateNewPopulation() {
        IntStream.range(0, agentCount).parallel().forEach(i->{
            double[][] parents, childrens, mutants;
            parents = selection.execute(agents, problem);
            childrens = crossing.execute(parents, problem);
            mutants = mutation.execute(childrens, problem);
            int indexMutant = (int) uniformDistribution(0, mutants.length);
            mutants[indexMutant][problem.getDimension()] = function(mutants[indexMutant]);
            changeWorstElement(mutants[indexMutant]);
        });
    }

    @Override
    public String getName() {
        return METHOD_NAME;
    }
}
