package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class DoubleExtremumFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            x -> -3 * x[0] * x[0] - 4 * x[1] * x[1] - 23 * Math.cos(x[0] - 0.5),
            new double[][]{
                    {-6, 6},
                    {-6, 6}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 6.4892;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{-2.0709, 0};

    private DoubleExtremumFunction() {
    }
}
