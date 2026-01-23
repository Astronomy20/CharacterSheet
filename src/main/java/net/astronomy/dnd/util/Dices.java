package net.astronomy.dnd.util;

import java.util.Arrays;
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

    public static int rollSingle(Dice dice) {
        int value = RANDOM.nextInt(dice.getSides()) + 1;
        if (dice == Dice.D100) {
            return value * 10;
        }

        return value;
    }

    public static int[] rollMultiple(Dice dice, int times) {
        return rollMultipleSum(dice, times, false);
    }

    public static int[] rollMultipleSum(Dice dice, int times, boolean sum) {
        int[] results = new int[times];
        int total = 0;

        for (int i = 0; i < times; i++) {
            int value = rollSingle(dice);
            results[i] = value;
            total += value;
        }

        if (sum) return new int[]{ total };
        return results;
    }

    public static int[] getAbilitiesValueRolls() {
        int[] ability_values = new int[6];

        for (int i = 0; i < 6; i++) {
            int[] rolls = rollMultiple(Dice.D6, 4);

            Arrays.sort(rolls);

            ability_values[i] = rolls[1] + rolls[2] + rolls[3];
        }

        return ability_values;
    }
}
