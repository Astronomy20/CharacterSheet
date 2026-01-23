package net.astronomy.dnd.util;

import java.util.Random;

public class Dices {
    private static final Random RANDOM = new Random();

    public enum Dice {
        D4(4),
        D6(6),
        D8(8),
        D10(10),
        D20(20),
        D100(10);

        private final int sides;

        Dice(int sides) {
            this.sides = sides;
        }

        public int getSides() {
            return sides;
        }
    }

    public static int roll(Dice dice) {
        int roll = RANDOM.nextInt(dice.getSides()) + 1;

        if(dice == Dice.D100) {
            return roll*10;
        }

        return roll;
    }

    public static int[] roll(Dice dice, int times) {
        int[] results = new int[times];
        for (int i = 0; i < times; i++) {
            results[i] = roll(dice);
        }

        return results;
    }
}
