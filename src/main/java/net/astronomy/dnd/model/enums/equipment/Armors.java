package net.astronomy.dnd.model.enums.equipment;

import net.astronomy.dnd.model.Modifier;

public enum Armors {
    // Light Armor
    PADDED(
            "Padded",
            11,
            0
    ),
    LEATHER(
            "Leather",
            11,
            0
    ),
    STUDDED_LEATHER(
            "Studded Leather",
            12,
            0
    ),

    // Light Armor
    HIDE(
            "Hide",
            12,
            2
    ),
    CHAIN_SHIRT(
            "Chain Shirt",
            13,
            2
    ),
    SCALE_MAIL(
            "Scale Mail",
            14,
            2
    ),
    BREASTPLATE(
            "Breastplate",
            14,
            2
    ),
    HALF_PLATE(
            "Half Plate",
            15,
            2
    ),

    // Heavy Armor
    RING_MAIL(
            "Ring Mail",
            14,
            -1
    ),
    CHAIN_MAIL(
            "Chain Mail",
            16,
            -1
    ),
    SPLINT(
            "Splint",
            17,
            -1
    ),
    PLATE(
            "Plate",
            18,
            -1
    ),

    // Shield
    SHIELD(
            "Shield",
            2,
            -1
    );

    private final String displayName;
    private final int armorDefence;
    private final int dexCap;

    Armors(String displayName, int armorDefence, int dexCap) {
        this.displayName = displayName;
        this.armorDefence = armorDefence;
        this.dexCap = dexCap;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Calculates the Armor Class contribution of this armor,
     * applying Dexterity modifier caps correctly.
     *
     * @return total AC provided by this armor
     */
    public int getArmorDefence(Modifier modifiers) {
        int appliedDex;

        if (dexCap == -1) {
            appliedDex = 0;
        } else if (dexCap == 0) {
            appliedDex = modifiers.getDexterity();
        } else {
            appliedDex = 2;
        }

        return armorDefence + appliedDex;
    }

    /**
     * @return max Dexterity modifier allowed.
     *         2 means cap.
     *         0 means no cap.
     *         -1 means no modifier.
     */
    public int getDexCap() {
        return dexCap;
    }

    @Override
    public String toString() {
        return displayName;
    }
}