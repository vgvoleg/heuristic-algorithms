package com.vgvoleg.heuristic.problems;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class SinusoidalShvefelFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            (x) -> x[0] * Math.sin(Math.sqrt(Math.abs(x[0]))) + x[1] * Math.sin(Math.sqrt(Math.abs(x[1]))),
            new double[][]{
                    {-500, 500},
                    {-500, 500}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 837.9657;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{420.9687, 420.9687};

    private SinusoidalShvefelFunction() {
    }
}
