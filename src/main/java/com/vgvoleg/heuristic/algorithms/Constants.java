package com.vgvoleg.heuristic.algorithms;

public class Constants {

    public abstract static class Selection {
        public static int PANMIXIA = 0;
        public static int ROULETTE = 1;
        public static int TOURNAMENT = 2;
    }

    public abstract static class Crossing {
        public static int SIMPLE_CROSSOVER = 0;
        public static int FLAT_CROSSOVER = 1;
        public static int ARITHMETICAL = 2;
    }

    public abstract static class Mutation {
        public static int RANDOM = 0;
        public static int IRREGULAR = 1;
    }

    private Constants() {
    }
}
