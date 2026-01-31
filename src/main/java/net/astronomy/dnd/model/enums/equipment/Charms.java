package net.astronomy.dnd.model.enums.equipment;

public enum Charms {
    TOTEM("Totem / Relic"),
    SENDING_STONE("Sending Stone"),
    DECK_OF_ILLUSIONS("Deck of Illusions"),
    BAG_OF_TRICKS("Bag of Tricks"),
    FIGURINE_OF_WONDROUS_POWER("Figurine of Wondrous Power"),
    LUCKSTONE("Stone of Good Luck"),
    CRYSTAL_BALL("Crystal Ball"),
    ROD_OF_RULERSHIP("Rod of Rulership");

    private final String displayName;

    Charms(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}