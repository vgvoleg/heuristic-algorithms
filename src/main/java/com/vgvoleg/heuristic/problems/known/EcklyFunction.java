package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class EcklyFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            x -> -Math.E + 20 * Math.exp(-Math.sqrt((x[0] * x[0] + x[1] * x[1]) / 50)) +
                    Math.exp(0.5 * (Math.cos(2 * Math.PI * x[0]) + Math.cos(2 * Math.PI * x[1]))),
            new double[][]{
                    {-10, 10},
                    {-10, 10}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 20;
    public static final double[] REAL_EXTREMUM_POINT = new double[]{0, 0};

    private EcklyFunction() {
    }
}
