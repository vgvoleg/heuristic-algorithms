package com.vgvoleg.heuristic.algorithms;

import java.util.Random;

public class Generator {
    private static Random rnd = new Random();

    public static double uniformDistribution(double left, double right) {
        if (right < left) {
            throw new RuntimeException("Incorrect edges!");
        }
        return left + rnd.nextDouble() * (right - left);
    }
}
