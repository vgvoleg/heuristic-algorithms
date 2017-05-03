package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class ParabaloidFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            (double[] x) -> -x[0] * x[0] - x[1] * x[1],
            new double[][]{
                    {-200, 200},
                    {-200, 200}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private ParabaloidFunction() {
    }
}
