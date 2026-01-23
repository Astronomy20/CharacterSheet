package net.astronomy.dnd.util.dice;

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

    public static int[] getAbilitiesValueRolls() {
        int[] ability_values = new int[6];

        for (int i = 0; i < 6; i++) {
            DiceRoll rolls = DiceRoll.roll(Dice.D6, 4);

            DiceRoll sortedRolls = rolls.sort();

            ability_values[i] = sortedRolls.getRoll(1) + sortedRolls.getRoll(2) + sortedRolls.getRoll(3);
        }

        return ability_values;
    }
}
