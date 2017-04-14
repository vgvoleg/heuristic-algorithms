package com.vgvoleg.heuristic.problems;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;

public final class ParabaloidFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            "max",
            2,
            (double[] x) -> -x[0] - x[1],
            new double[][]{
                    {-2, 2},
                    {-2, 2}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private ParabaloidFunction() {
    }
}
