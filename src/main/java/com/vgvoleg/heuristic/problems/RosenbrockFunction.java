package com.vgvoleg.heuristic.problems;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public final class RosenbrockFunction {

    private static final int a = 3;

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            "max", // TODO: clarify
            2,
            (x) -> -a * Math.pow(x[1] - Math.pow(x[0], 2), 2) - Math.pow(1 - x[0], 2),
            new double[][]{
                    {-2, 2},
                    {-2, 2}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{1, 1};

    private RosenbrockFunction() {
    }
}
