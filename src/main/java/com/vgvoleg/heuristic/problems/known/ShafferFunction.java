package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class ShafferFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            (x) -> 0.5 - (Math.pow(Math.sin(Math.sqrt(Math.pow(x[0], 2) + Math.pow(x[1], 2))), 2) - 0.5) /
                    (1 + 0.0001 * (Math.pow(x[0], 2) + Math.pow(x[1], 2))),
            new double[][]{
                    {-10, 10},
                    {-10, 10}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 1;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private ShafferFunction() {
    }
}
