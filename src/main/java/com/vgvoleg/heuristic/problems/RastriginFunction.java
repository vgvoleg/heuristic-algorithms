package com.vgvoleg.heuristic.problems;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class RastriginFunction {

    private static int dimension = 3;

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MIN,
            dimension,
            x -> {
                double result = 10 * dimension;
                for (int i = 0; i < dimension; i++) {
                    result += x[i] * x[i] - 10 * Math.cos(2 * Math.PI * x[i]);
                }
                return result;
            },
            new double[][]{
                    {-5, 5}
            }
    ) {
        @Override
        public double getLeftEdge(int dimension) {
            return super.getLeftEdge(0);
        }

        @Override
        public double getRightEdge(int dimension) {
            return super.getRightEdge(0);
        }
    };

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private RastriginFunction() {
    }
}
