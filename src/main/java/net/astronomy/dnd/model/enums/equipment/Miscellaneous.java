package net.astronomy.dnd.model.enums.equipment;

public enum Miscellaneous {

    // Simple everyday items
    FLOWER("Flower"),
    RING("Ring"),
    NECKLACE("Necklace"),
    BRACELET("Bracelet"),
    BACKPACK("Backpack"),
    BELT_POUCH("Belt Pouch"),
    FLASK("Flask"),
    INK_QUILL("Ink & Quill"),
    CHALK("Chalk"),
    MIRROR("Mirror (steel or polished)"),

    // Consumables
    POTION_HEALING("Potion of Healing"),
    POTION_HEALING_GREATER("Potion of Greater Healing"),
    POTION_HEALING_SUPERIOR("Potion of Superior Healing"),
    POTION_HEALING_SUPREME("Potion of Supreme Healing"),
    POTION_INVISIBILITY("Potion of Invisibility"),
    POTION_SPEED("Potion of Speed"),
    POTION_FIRE_RESISTANCE("Potion of Fire Resistance"),
    POTION_WATER_BREATHING("Potion of Water Breathing"),
    ELIXIR_HEALTH("Elixir of Health"),
    ANTITOXIN("Antitoxin"),
    POISON_BASIC("Poison, Basic"),
    POISON_ADVANCED("Poison, Advanced"),
    POISON_RARE("Poison, Rare"),

    // Quest / Adventure Items
    MAP_FRAGMENT("Map Fragment"),
    ANCIENT_KEY("Ancient Key"),
    SEALED_LETTER("Sealed Letter"),
    SCROLL("Sealed Letter"),
    GEMSTONE_SMALL("Gemstone (small)"),
    GEMSTONE_MEDIUM("Gemstone (medium)"),
    GEMSTONE_LARGE("Gemstone (large)"),
    LOCKED_CHEST("Locked Chest / Box"),

    // Tools / Utility Items
    IRON_CHAIN("Iron Chain"),
    GRAPPLING_HOOK("Grappling Hook"),
    CROWBAR("Crowbar"),
    LANTERN_OIL("Lantern (oil)"),
    SPYGLASS("Spyglass"),
    MAGNIFYING_GLASS("Magnifying Glass"),
    SIGNAL_WHISTLE("Signal Whistle"),

    // Exotic / Rare Items
    DRAGONS_TOOTH("Dragon's Tooth"),
    DRAGONS_CLAW("Dragon's Claw"),
    PHOENIX_FEATHER("Phoenix Feather"),
    UNICORN_HAIR("Unicorn Hair"),
    ELEMENTAL_SHARD("Elemental Shard"),
    ALCHEMICAL_COMPONENT("Alchemical Component"),
    MAGICAL_DUST("Magical Dust / Powder");

    private final String displayName;

    Miscellaneous(String displayName) {
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