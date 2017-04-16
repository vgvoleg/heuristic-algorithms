package com.vgvoleg.heuristic.problems.known;

import com.vgvoleg.heuristic.problems.base.OptimizationProblem;
import com.vgvoleg.heuristic.problems.base.OptimizationType;

public final class MultiFunction {

    public static final OptimizationProblem PROBLEM = new OptimizationProblem(
            OptimizationType.MAX,
            2,
            (x) -> x[0] * Math.sin(4 * Math.PI * x[0]) + x[1] * Math.sin(4 * Math.PI * x[1]),
            new double[][]{
                    {-2, 2},
                    {-2, 2}
            }
    );

    public static final double REAL_EXTREMUM_VALUE = 3.2539;
    public static final double[] REAL_EXTREMUM_POINT1 = new double[]{1.6288, 1.6288};
    public static final double[] REAL_EXTREMUM_POINT2 = new double[]{-1.6288, 1.6288};
    public static final double[] REAL_EXTREMUM_POINT3 = new double[]{1.6288, -1.6288};
    public static final double[] REAL_EXTREMUM_POINT4 = new double[]{-1.6288, -1.6288};

    private MultiFunction() {
    }
}
