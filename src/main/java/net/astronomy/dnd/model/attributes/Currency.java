package net.astronomy.dnd.model.attributes;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Character's currencies.
 * Supports standard coin types and provides conversion between them.
 */
public class Currency {

    /**
     * Enum representing the coin types.
     * Each coin type has a display name, abbreviation, and conversion factor to copper pieces (cp).
     */
    public enum Coin {
        COPPER("Copper", "cp", 1),
        SILVER("Silver", "sp", 10),
        ELECTRUM("Electrum", "ep", 50),
        GOLD("Gold", "gp", 100),
        PLATINUM("Platinum", "pp", 1000);

        /** Human-readable name of the coin */
        private final String displayName;
        /** Abbreviation of the coin (e.g., cp, sp) */
        private final String abbreviation;
        /** Conversion factor to copper pieces */
        private final int toCopper;

        /**
         * Constructs a coin type with the given display name, abbreviation, and copper conversion.
         *
         * @param displayName human-readable name of the coin
         * @param abbreviation abbreviation of the coin
         * @param toCopper conversion factor to copper pieces
         */
        Coin(String displayName, String abbreviation, int toCopper) {
            this.displayName = displayName;
            this.abbreviation = abbreviation;
            this.toCopper = toCopper;
        }

        /** @return the human-readable name of the coin */
        public String getDisplayName() {
            return displayName;
        }

        /** @return the abbreviation of the coin */
        public String getAbbreviation() {
            return abbreviation;
        }

        /**
         * Converts a quantity of this coin type to its value in copper pieces.
         *
         * @param quantity number of coins
         * @return equivalent value in copper pieces
         */
        public int toCopper(int quantity) {
            return quantity * toCopper;
        }
    }

    /** Map storing coin types and their quantities */
    private final Map<Coin, Integer> coins;

    /**
     * Constructs a new Currency object with all coin quantities initialized to 0.
     */
    public Currency() {
        coins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }

    /**
     * Adds a specified quantity of a coin type to the currency.
     *
     * @param coin the coin type to add
     * @param quantity the number of coins to add
     */
    public void addCoins(Coin coin, int quantity) {
        coins.put(coin, coins.getOrDefault(coin, 0) + quantity);
    }

    /**
     * Removes a specified quantity of a coin type from the currency.
     *
     * @param coin the coin type to remove
     * @param quantity the number of coins to remove
     * @return true if the removal was successful, false if not enough coins
     */
    public boolean removeCoins(Coin coin, int quantity) {
        int current = coins.getOrDefault(coin, 0);
        if (current < quantity) return false;
        coins.put(coin, current - quantity);
        return true;
    }

    /**
     * Returns the quantity of a specific coin type.
     *
     * @param coin the coin type to check
     * @return quantity of that coin type
     */
    public int getQuantity(Coin coin) {
        return coins.getOrDefault(coin, 0);
    }

    /**
     * Returns an unmodifiable map of all coins and their quantities.
     *
     * @return map of coin types to quantities
     */
    public Map<Coin, Integer> getAllCoins() {
        return Collections.unmodifiableMap(coins);
    }

    /**
     * Calculates the total value of all coins in copper pieces.
     *
     * @return total currency value in copper
     */
    public int getTotalValueInCopper() {
        int total = 0;
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            total += entry.getKey().toCopper(entry.getValue());
        }
        return total;
    }

    /**
     * Converts the total currency value to a specified coin type.
     *
     * @param coin the coin type to convert to
     * @return total value expressed in the specified coin type
     * @throws IllegalArgumentException if coin is null
     */
    public double getValueInCoin(Coin coin) {
        if (coin == null) throw new IllegalArgumentException("Coin cannot be null");
        int totalCopper = getTotalValueInCopper();
        return (double) totalCopper / coin.toCopper(1);
    }
}