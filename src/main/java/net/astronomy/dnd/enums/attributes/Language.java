package net.astronomy.dnd.enums.attributes;

import java.util.Set;

public enum Language {
    COMMON("Common"),
    DWARVISH("Dwarvish"),
    ELVISH("Elvish"),
    GIANT("Giant"),
    GNOMISH("Gnomish"),
    GOBLIN("Goblin"),
    HALFLING("Halfling"),
    ORC("Orc"),
    ABYSSAL("Abyssal"),
    CELESTIAL("Celestial"),
    DRACONIC("Draconic"),
    DEEP_SPEECH("Deep Speech"),
    INFERNAL("Infernal"),
    PRIMORDIAL("Primordial"),
    SYLVAN("Sylvan"),
    UNDERCOMMON("Undercommon");

    private final String displayName;
    private final Set<Language> languages = Set.of();

    Language(String displayName) {
        this.displayName = displayName;
    }

    public Language addLanguage(Language language) {
        return language;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}