package net.astronomy.dnd.model;

import java.util.List;
import java.util.Map;

public class Spellcasting {
    private String spellcastingAbility;
    private int spellSaveDC;
    private int spellAttackBonus;

    private Map<Integer, Integer> spellSlots; // level → slots
    private List<String> spellsKnown;

    public Spellcasting(String spellcastingAbility,
                        int spellSaveDC,
                        int spellAttackBonus,
                        Map<Integer, Integer> spellSlots,
                        List<String> spellsKnown) {
        this.spellcastingAbility = spellcastingAbility;
        this.spellSaveDC = spellSaveDC;
        this.spellAttackBonus = spellAttackBonus;
        this.spellSlots = spellSlots;
        this.spellsKnown = spellsKnown;
    }
}