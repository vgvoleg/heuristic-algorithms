package com.vgvoleg.heuristic.util;

import java.util.Random;

public class Generator {
    private static Random rnd = new Random();

    public static double uniformDistribution(double left, double right) {
        if (right < left) {
            throw new RuntimeException("Incorrect edges!");
        }
        return left + rnd.nextDouble() * (right - left);
    }

    public static int generateIndex(int maxIndex) {
        if (maxIndex <= 0) {
            throw new RuntimeException("Incorrect index!");
        }
        return rnd.nextInt(maxIndex);
    }

    private Generator() {
    }
}
