package net.astronomy.dnd.enums.attributes;

/**
 * Character backgrounds.
 * Each background has a human-readable display name.
 */
public enum Background {

    ACOLYTE("Acolyte"),
    CHARLATAN("Charlatan"),
    CRIMINAL("Criminal"),
    ENTERTAINER("Entertainer"),
    FOLK_HERO("Folk Hero"),
    GUILD_ARTISAN("Guild Artisan"),
    HERMIT("Hermit"),
    NOBLE("Noble"),
    OUTLANDER("Outlander"),
    SAGE("Sage"),
    SAILOR("Sailor"),
    SOLDIER("Soldier"),
    URCHIN("Urchin");

    private final String displayName;

    Background(String displayName) {
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