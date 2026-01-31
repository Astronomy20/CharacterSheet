package net.astronomy.dnd.model.enums.equipment;

public enum Weapons {
    // Simple Melee Weapons
    HANDAXE("Handaxe"),
    QUARTERSTAFF("Quarterstaff"),
    SICKLE("Sickle"),
    JAVELIN("Javelin"),
    SPEAR("Spear"),
    LIGHT_HAMMER("Light Hammer"),
    MACE("Mace"),
    DAGGER("Dagger"),
    CLUB("Club"),
    GREATCLUB("Greatclub"),

    // Simple Ranged Weapons
    SHORTBOW("Shortbow"),
    LIGHT_CROSSBOW("Light Crossbow"),
    DART("Dart"),
    SLING("Sling"),

    // Martial Melee Weapons
    HALBERD("Halberd"),
    GREATAxe("Greataxe"),
    BATTLEAXE("Battleaxe"),
    GLAIVE("Glaive"),
    WHIP("Whip"),
    LANCE("Lance"),
    MAUL("Maul"),
    WARHAMMER("Warhammer"),
    FLAIL("Flail"),
    MORNINGSTAR("Morningstar"),
    PIKE("Pike"),
    WAR_PICK("War Pick"),
    SCIMITAR("Scimitar"),
    SHORTSWORD("Shortsword"),
    LONGSWORD("Longsword"),
    GREATSWORD("Greatsword"),
    RAPIER("Rapier"),
    TRIDENT("Trident"),

    // Martial Ranged Weapons
    LONGBOW("Longbow"),
    HAND_CROSSBOW("Hand Crossbow"),
    HEAVY_CROSSBOW("Heavy Crossbow"),
    BLOWGUN("Blowgun"),
    NET("Net");

    private final String displayName;

    Weapons(String displayName) {
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