package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.equipment.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Character's inventory.
 * Items are categorized into Armors, Weapons, Adventure Gear, Instruments, and Miscellaneous items.
 * Quantities are tracked for each item type.
 */
public class Inventory {
    /** Map storing Armor items and their quantities */
    private final Map<Armor, Integer> armors;

    /** Map storing Weapon items and their quantities */
    private final Map<Weapon, Integer> weapons;

    /** Map storing Adventure Gear items and their quantities */
    private final Map<AdventureGear, Integer> adventureGear;

    /** Map storing Instrument items and their quantities */
    private final Map<Instrument, Integer> instruments;

    /** Map storing Miscellaneous items and their quantities */
    private final Map<Miscellaneous, Integer> miscellaneous;

    /**
     * Constructs an empty Inventory with all categories initialized.
     */
    public Inventory() {
        armors = new EnumMap<>(Armor.class);
        weapons = new EnumMap<>(Weapon.class);
        adventureGear = new EnumMap<>(AdventureGear.class);
        instruments = new EnumMap<>(Instrument.class);
        miscellaneous = new EnumMap<>(Miscellaneous.class);
    }

    /**
     * Adds a specified quantity of an Armor item to the inventory.
     *
     * @param armor the Armor item to add
     * @param quantity the number of items to add
     */
    public void addArmor(Armor armor, int quantity) {
        armors.put(armor, armors.getOrDefault(armor, 0) + quantity);
    }

    /**
     * Adds a specified quantity of a Weapon item to the inventory.
     *
     * @param weapon the Weapon item to add
     * @param quantity the number of items to add
     */
    public void addWeapon(Weapon weapon, int quantity) {
        weapons.put(weapon, weapons.getOrDefault(weapon, 0) + quantity);
    }

    /**
     * Adds a specified quantity of Adventure Gear to the inventory.
     *
     * @param gear the Adventure Gear item to add
     * @param quantity the number of items to add
     */
    public void addAdventureGear(AdventureGear gear, int quantity) {
        adventureGear.put(gear, adventureGear.getOrDefault(gear, 0) + quantity);
    }

    /**
     * Adds a specified quantity of an Instrument to the inventory.
     *
     * @param instrument the Instrument item to add
     * @param quantity the number of items to add
     */
    public void addInstrument(Instrument instrument, int quantity) {
        instruments.put(instrument, instruments.getOrDefault(instrument, 0) + quantity);
    }

    /**
     * Adds a specified quantity of a Miscellaneous item to the inventory.
     *
     * @param misc the Miscellaneous item to add
     * @param quantity the number of items to add
     */
    public void addMiscellaneous(Miscellaneous misc, int quantity) {
        miscellaneous.put(misc, miscellaneous.getOrDefault(misc, 0) + quantity);
    }

    /**
     * Removes a specified quantity of an Armor item from the inventory.
     *
     * @param armor the Armor item to remove
     * @param quantity the number of items to remove
     * @return true if the removal was successful, false if not enough items
     */
    public boolean removeArmor(Armor armor, int quantity) {
        return removeItem(armors, armor, quantity);
    }

    /**
     * Removes a specified quantity of a Weapon item from the inventory.
     *
     * @param weapon the Weapon item to remove
     * @param quantity the number of items to remove
     * @return true if the removal was successful, false if not enough items
     */
    public boolean removeWeapon(Weapon weapon, int quantity) {
        return removeItem(weapons, weapon, quantity);
    }

    /**
     * Removes a specified quantity of Adventure Gear from the inventory.
     *
     * @param gear the Adventure Gear item to remove
     * @param quantity the number of items to remove
     * @return true if the removal was successful, false if not enough items
     */
    public boolean removeAdventureGear(AdventureGear gear, int quantity) {
        return removeItem(adventureGear, gear, quantity);
    }

    /**
     * Removes a specified quantity of an Instrument from the inventory.
     *
     * @param instrument the Instrument item to remove
     * @param quantity the number of items to remove
     * @return true if the removal was successful, false if not enough items
     */
    public boolean removeInstrument(Instrument instrument, int quantity) {
        return removeItem(instruments, instrument, quantity);
    }

    /**
     * Removes a specified quantity of a Miscellaneous item from the inventory.
     *
     * @param misc the Miscellaneous item to remove
     * @param quantity the number of items to remove
     * @return true if the removal was successful, false if not enough items
     */
    public boolean removeMiscellaneous(Miscellaneous misc, int quantity) {
        return removeItem(miscellaneous, misc, quantity);
    }

    /**
     * Generic helper method to remove items from a map, ensuring quantity does not go negative.
     *
     * @param map the map containing items and their quantities
     * @param item the item to remove
     * @param quantity the quantity to remove
     * @param <T> the type of Enum representing the item
     * @return true if the removal was successful, false if not enough quantity
     */
    private <T extends Enum<T>> boolean removeItem(Map<T, Integer> map, T item, int quantity) {
        int current = map.getOrDefault(item, 0);
        if (current < quantity) {
            System.out.println("Warning: Attempted to remove " + quantity + " of " + item +
                                " but only " + current + " available.");
            return false;
        }
        if (current == quantity) map.remove(item);
        else map.put(item, current - quantity);
        return true;
    }

    /** @return an unmodifiable map of Armor items and quantities */
    public Map<Armor, Integer> getArmors() {
        return Collections.unmodifiableMap(armors);
    }

    /** @return an unmodifiable map of Weapon items and quantities */
    public Map<Weapon, Integer> getWeapons() {
        return Collections.unmodifiableMap(weapons);
    }

    /** @return an unmodifiable map of Adventure Gear items and quantities */
    public Map<AdventureGear, Integer> getAdventureGear() {
        return Collections.unmodifiableMap(adventureGear);
    }

    /** @return an unmodifiable map of Instruments and quantities */
    public Map<Instrument, Integer> getInstruments() {
        return Collections.unmodifiableMap(instruments);
    }

    /** @return an unmodifiable map of Miscellaneous items and quantities */
    public Map<Miscellaneous, Integer> getMiscellaneous() {
        return Collections.unmodifiableMap(miscellaneous);
    }
}