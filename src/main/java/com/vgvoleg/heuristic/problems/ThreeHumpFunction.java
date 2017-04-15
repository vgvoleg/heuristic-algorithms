package com.vgvoleg.heuristic.problems;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class ThreeHumpFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX, // TODO: clarify
            2,
            x -> -2 * x[0] * x[0] + 1.05 * Math.pow(x[0], 4) - 1. * Math.pow(x[0], 6) / 6 - x[0] * x[1] - x[1] * x[1],
            new double[][]{
                    {-5, 5},
                    {-5, 5}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private ThreeHumpFunction() {
    }
}
