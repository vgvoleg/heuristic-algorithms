package com.vgvoleg.heuristic.algorithms.base;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

import java.util.Arrays;
import java.util.Comparator;

import static com.vgvoleg.heuristic.algorithms.Generator.uniformDistribution;

public class Population implements Cloneable {
    private double[][][] population;

    private int size;
    private int dimension;
    private OptimizationProblem problem;
    private int COEF;

    private int sumFitness;

    public Population(int size, OptimizationProblem problem, int COEF) {
        population = new double[size][2][dimension];
        this.size = size;
        this.dimension = problem.getDimension();
        this.problem = problem;
        this.COEF = COEF;
        this.sumFitness = 0;
    }

    public Population getScreenshot() {
        Population screenshot = new Population(size, problem, COEF);
        screenshot.setPopulation(population);
        return screenshot;
    }

    private void setPopulation(double[][][] population) {
        this.population = new double[size][2][dimension];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dimension; j++) {
                this.population[i][0][j] = population[i][0][j];
            }
            this.population[i][1][0] = population[i][1][0];
        }
    }

    public void init() {
        sumFitness = 0;
        population = new double[size][2][dimension];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dimension; j++) {
                population[i][0][j] = uniformDistribution(problem.getLeftEdge(j), problem.getRightEdge(j));
            }
            population[i][1][0] = COEF * problem.f(population[i][0]);
            sumFitness += population[i][1][0];
        }
    }

    public int getSize() {
        return size;
    }

    public int getDimension() {
        return dimension;
    }

    public double[] getElement(int index) {
        return population[index][0];
    }

    public double getFitness(int index) {
        return population[index][1][0];
    }

    public void changeWorstElement(double[] newElem) {
        int indexWorstElem = 0;
        double minFittness = population[0][1][0];
        for (int i = 1; i < size; i++) {
            if (minFittness > population[i][1][0]) {
                indexWorstElem = i;
                minFittness = population[i][1][0];
            }
        }
        population[indexWorstElem][0] = newElem;
        sumFitness -= population[indexWorstElem][1][0];
        population[indexWorstElem][1][0] = COEF * problem.f(population[indexWorstElem][0]);
        sumFitness += population[indexWorstElem][1][0];
    }

    public void sort(Comparator<double[][]> comparator) {
        Arrays.sort(population, comparator);
    }

    public int getBestElementIndex() {
        int indexOfWinner = 0;
        double maxFittness = population[0][1][0];
        for (int i = 1; i < size; i++) {
            if (maxFittness < population[i][1][0]) {
                indexOfWinner = i;
                maxFittness = population[i][1][0];
            }
        }
        return indexOfWinner;
    }
}
