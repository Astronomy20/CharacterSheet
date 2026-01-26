package net.astronomy.dnd.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Currency {

    /** Enum representing coin types with conversion factor to copper pieces (cp) */
    public enum Coin {
        COPPER("Copper", "cp", 1),
        SILVER("Silver", "sp", 10),
        ELECTRUM("Electrum", "ep", 50),
        GOLD("Gold", "gp", 100),
        PLATINUM("Platinum", "pp", 1000);

        private final String displayName;
        private final String abbreviation;
        private final int toCopper;

        Coin(String displayName, String abbreviation, int toCopper) {
            this.displayName = displayName;
            this.abbreviation = abbreviation;
            this.toCopper = toCopper;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public int toCopper(int quantity) {
            return quantity * toCopper;
        }
    }

    private final Map<Coin, Integer> coins;

    public Currency() {
        coins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }

    /** Add coins */
    public void addCoins(Coin coin, int quantity) {
        coins.put(coin, coins.getOrDefault(coin, 0) + quantity);
    }

    /** Remove coins */
    public boolean removeCoins(Coin coin, int quantity) {
        int current = coins.getOrDefault(coin, 0);
        if (current < quantity) return false;
        coins.put(coin, current - quantity);
        return true;
    }

    /** Get quantity of a specific coin */
    public int getQuantity(Coin coin) {
        return coins.getOrDefault(coin, 0);
    }

    /** Get a map of all coins and their quantities */
    public Map<Coin, Integer> getAllCoins() {
        return Collections.unmodifiableMap(coins);
    }

    /** Total value in copper pieces */
    public int getTotalValueInCopper() {
        int total = 0;
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            total += entry.getKey().toCopper(entry.getValue());
        }
        return total;
    }

    /** Get total value converted to a specified coin type */
    public double getValueInCoin(Coin coin) {
        if (coin == null) throw new IllegalArgumentException("Coin cannot be null");
        int totalCopper = getTotalValueInCopper();
        return (double) totalCopper / coin.toCopper(1);
    }
}