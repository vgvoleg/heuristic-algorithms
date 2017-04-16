package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public class GrivankaFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            x -> -1 - (x[0] * x[0] + x[1] * x[1]) / 4000 + Math.cos(x[0] / Math.sqrt(1.0)) * Math.cos(x[1] / Math.sqrt(2.0)),
            new double[][]{
                    {-600, 600},
                    {-600, 600}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 0;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private GrivankaFunction() {
    }
}
