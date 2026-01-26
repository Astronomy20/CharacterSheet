package net.astronomy.dnd.util.dice;

/**
 * Utility class representing various dice types and helper methods
 * for generating dice rolls.
 */
public class Dices {

    /**
     * Enum representing standard dice types used in tabletop games.
     * Each dice type has a specific number of sides.
     */
    public enum Dice {
        D4(4),
        D6(6),
        D8(8),
        D10(10),
        D20(20),
        D100(10);

        /** Number of sides on the dice */
        private final int sides;

        /**
         * Constructor for a dice type.
         *
         * @param sides the number of sides this dice has
         */
        Dice(int sides) {
            this.sides = sides;
        }

        /**
         * Returns the number of sides of this dice.
         *
         * @return number of sides
         */
        public int getSides() {
            return sides;
        }
    }

    /**
     * Rolls 4 D6 dice six times to generate values for character abilities
     * using the standard "roll 4d6, drop the lowest" method.
     *
     * @return an array of 6 integers representing ability scores
     */
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