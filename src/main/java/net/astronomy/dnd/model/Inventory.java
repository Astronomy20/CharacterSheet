package net.astronomy.dnd.model;

import net.astronomy.dnd.model.enums.equipment.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents a character's inventory.
 * Items are categorized and their quantities tracked.
 */
public class Inventory {
    /** Armor items and quantities */
    private final Map<Armor, Integer> armors;

    /** Weapon items and quantities */
    private final Map<Weapon, Integer> weapons;

    /** Adventure gear items and quantities */
    private final Map<AdventureGear, Integer> adventureGear;

    /** Instrument items and quantities */
    private final Map<Instrument, Integer> instruments;

    /** Miscellaneous items and quantities */
    private final Map<Miscellaneous, Integer> miscellaneous;

    /** Currently worn body armor (null if none) */
    private Armor wornArmor;

    /** Currently equipped shield (null if none) */
    private Armor equippedShield;

    /** Creates an empty inventory */
    public Inventory() {
        armors = new EnumMap<>(Armor.class);
        weapons = new EnumMap<>(Weapon.class);
        adventureGear = new EnumMap<>(AdventureGear.class);
        instruments = new EnumMap<>(Instrument.class);
        miscellaneous = new EnumMap<>(Miscellaneous.class);

        wornArmor = null;
        equippedShield = null;
    }

    /** Adds an item to a map */
    private <T extends Enum<T>> void addItem(Map<T, Integer> map, T item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Warning: Attempted to add non-positive quantity of " + item);
            return;
        }
        map.put(item, map.getOrDefault(item, 0) + quantity);
    }

    /** Removes an item from a map */
    private <T extends Enum<T>> boolean removeItem(Map<T, Integer> map, T item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Warning: Attempted to remove non-positive quantity of " + item);
            return false;
        }

        int current = map.getOrDefault(item, 0);
        if (current < quantity) {
            System.out.println("Warning: Attempted to remove " + quantity + " of " + item +
                    " but only " + current + " available.");
            return false;
        }

        if (current == quantity) {
            map.remove(item);
        } else {
            map.put(item, current - quantity);
        }

        return true;
    }

    /** Wear a body armor (not a shield) */
    public void wearArmor(Armor armor, Life life) {
        if (armor == Armor.SHIELD) {
            System.out.println("Use equipShield() to equip a shield.");
        }

        if (armors.getOrDefault(armor, 0) <= 0) {
            System.out.println("Cannot wear armor not present in inventory: " + armor);
        }

        wornArmor = armor;
        life.updateArmorClass();
    }

    /** Remove currently worn armor */
    public void removeArmor(Life life) {
        if (wornArmor == null) {
            System.out.println("No armor is currently worn.");
        }

        wornArmor = null;
        life.updateArmorClass();
    }

    /** Equip a shield */
    public void equipShield(Armor armor, Life life) {
        if (armors.getOrDefault(Armor.SHIELD, 0) <= 0) {
            System.out.println("No shield available in inventory.");
        }

        equippedShield = armor;
        life.updateArmorClass();
    }

    /** Unequip the shield */
    public void unequipShield(Life life) {
        if (equippedShield == null) {
            System.out.println("No shield is currently equipped.");
        }

        equippedShield = null;
        life.updateArmorClass();
    }

    /** Add armor to inventory */
    public void addArmor(Armor armor, int quantity) {
        addItem(armors, armor, quantity);
    }

    /** Add weapon to inventory */
    public void addWeapon(Weapon weapon, int quantity) {
        addItem(weapons, weapon, quantity);
    }

    /** Add adventure gear to inventory */
    public void addAdventureGear(AdventureGear gear, int quantity) {
        addItem(adventureGear, gear, quantity);
    }

    /** Add instrument to inventory */
    public void addInstrument(Instrument instrument, int quantity) {
        addItem(instruments, instrument, quantity);
    }

    /** Add miscellaneous item to inventory */
    public void addMiscellaneous(Miscellaneous misc, int quantity) {
        addItem(miscellaneous, misc, quantity);
    }

    /** Remove armor from inventory */
    public boolean removeArmor(Armor armor, int quantity) {
        return removeItem(armors, armor, quantity);
    }

    /** Remove weapon from inventory */
    public boolean removeWeapon(Weapon weapon, int quantity) {
        return removeItem(weapons, weapon, quantity);
    }

    /** Remove adventure gear from inventory */
    public boolean removeAdventureGear(AdventureGear gear, int quantity) {
        return removeItem(adventureGear, gear, quantity);
    }

    /** Remove instrument from inventory */
    public boolean removeInstrument(Instrument instrument, int quantity) {
        return removeItem(instruments, instrument, quantity);
    }

    /** Remove miscellaneous item from inventory */
    public boolean removeMiscellaneous(Miscellaneous misc, int quantity) {
        return removeItem(miscellaneous, misc, quantity);
    }

    /** Return an unmodifiable view of a map */
    private <T extends Enum<T>> Map<T, Integer> getItems(Map<T, Integer> map) {
        return Collections.unmodifiableMap(map);
    }

    /** Get all armors */
    public Map<Armor, Integer> getArmors() {
        return getItems(armors);
    }

    /** Get all weapons */
    public Map<Weapon, Integer> getWeapons() {
        return getItems(weapons);
    }

    /** Get all adventure gear */
    public Map<AdventureGear, Integer> getAdventureGear() {
        return getItems(adventureGear);
    }

    /** Get all instruments */
    public Map<Instrument, Integer> getInstruments() {
        return getItems(instruments);
    }

    /** Get all miscellaneous items */
    public Map<Miscellaneous, Integer> getMiscellaneous() {
        return getItems(miscellaneous);
    }

    /** Get quantity of a specific item */
    public <T extends Enum<T>> int getQuantity(Map<T, Integer> map, T item) {
        return map.getOrDefault(item, 0);
    }

    /** Get currently worn armor */
    public Armor getWornArmor() {
        return wornArmor;
    }

    /** Get defense provided by worn armor */
    public int getWearingArmorDefence(Armor armor, Modifier modifiers) {
        if (armor == null) return 0;
        return armor.getArmorDefence(modifiers);
    }

    /** Get currently equipped shield */
    public Armor getEquippedShield() {
        return equippedShield;
    }

    /** Get defense provided by equipped shield */
    public int getEquippedShieldDefence(Armor armor, Modifier modifiers) {
        if (equippedShield == null) return 0;
        return armor.getArmorDefence(modifiers);
    }
}