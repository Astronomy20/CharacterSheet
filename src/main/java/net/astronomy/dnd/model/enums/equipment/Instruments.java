package net.astronomy.dnd.model.enums.equipment;

public enum Instruments {

    // Musical Instruments
    SHAWM("Shawm"),
    BAGPIPES("Bagpipes"),
    HORN("Horn"),
    DULCIMER("Dulcimer"),
    FLUTE("Flute"),
    PAN_FLUTE("Pan Flute"),
    LYRE("Lyre"),
    LUTE("Lute"),
    DRUM("Drum"),
    VIOL("Viol"),

    // Games
    DICE_SET("Dice Set"),
    PLAYING_CARD_SET("Playing Card Set"),
    DRAGONCHESS_SET("Dragonchess Set"),
    THREE_DRAGON_ANTE_SET("Three-Dragon Ante Set"),

    // General Objects / Tools
    FORGERY_KIT("Forgery Kit"),
    THIEVES_TOOLS("Thieves' Tools"),
    HERBALISM_KIT("Herbalism Kit"),
    POISONERS_KIT("Poisoner's Kit"),
    ALCHEMISTS_SUPPLIES("Alchemist's Supplies"),
    CALLIGRAPHERS_SUPPLIES("Calligrapher's Supplies"),
    BREWERS_SUPPLIES("Brewer's Supplies"),
    COBBLERS_TOOLS("Cobbler's Tools"),
    CARTOGRAPHERS_TOOLS("Cartographer's Tools"),
    TANNERS_TOOLS("Tanner's Tools"),
    MASONS_TOOLS("Mason's Tools"),
    SMITHS_TOOLS("Smith's Tools"),
    CARPENTERS_TOOLS("Carpenter's Tools"),
    JEWELERS_TOOLS("Jeweler's Tools"),
    WOODCARVERS_TOOLS("Woodcarver's Tools"),
    TINKERS_TOOLS("Tinker's Tools"),
    PAINTERS_SUPPLIES("Painter's Supplies"),
    GLASSBLOWERS_TOOLS("Glassblower's Tools"),
    WEAVERS_TOOLS("Weaver's Tools"),
    POTTERS_TOOLS("Potter's Tools"),
    COOKS_UTENSILS("Cook's Utensils"),
    NAVIGATORS_TOOLS("Navigator's Tools"),
    DISGUISE_KIT("Disguise Kit");

    private final String displayName;

    Instruments(String displayName) {
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