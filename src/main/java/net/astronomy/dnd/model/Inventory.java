package net.astronomy.dnd.model;

import net.astronomy.dnd.enums.equipment.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Inventory {
    private final Map<Armor, Integer> armors;
    private final Map<Weapon, Integer> weapons;
    private final Map<AdventureGear, Integer> adventureGear;
    private final Map<Instrument, Integer> instruments;
    private final Map<Miscellaneous, Integer> miscellaneous;

    public Inventory() {
        armors = new EnumMap<>(Armor.class);
        weapons = new EnumMap<>(Weapon.class);
        adventureGear = new EnumMap<>(AdventureGear.class);
        instruments = new EnumMap<>(Instrument.class);
        miscellaneous = new EnumMap<>(Miscellaneous.class);
    }

    /** Add item to inventory methods */
    public void addArmor(Armor armor, int quantity) {
        armors.put(armor, armors.getOrDefault(armor, 0) + quantity);
    }

    public void addWeapon(Weapon weapon, int quantity) {
        weapons.put(weapon, weapons.getOrDefault(weapon, 0) + quantity);
    }

    public void addAdventureGear(AdventureGear gear, int quantity) {
        adventureGear.put(gear, adventureGear.getOrDefault(gear, 0) + quantity);
    }

    public void addInstrument(Instrument instrument, int quantity) {
        instruments.put(instrument, instruments.getOrDefault(instrument, 0) + quantity);
    }

    public void addMiscellaneous(Miscellaneous misc, int quantity) {
        miscellaneous.put(misc, miscellaneous.getOrDefault(misc, 0) + quantity);
    }

    /** Remove item from inventory methods */
    public boolean removeArmor(Armor armor, int quantity) {
        return removeItem(armors, armor, quantity);
    }

    public boolean removeWeapon(Weapon weapon, int quantity) {
        return removeItem(weapons, weapon, quantity);
    }

    public boolean removeAdventureGear(AdventureGear gear, int quantity) {
        return removeItem(adventureGear, gear, quantity);
    }

    public boolean removeInstrument(Instrument instrument, int quantity) {
        return removeItem(instruments, instrument, quantity);
    }

    public boolean removeMiscellaneous(Miscellaneous misc, int quantity) {
        return removeItem(miscellaneous, misc, quantity);
    }

    /** Remove item helper */
    private <T extends Enum<T>> boolean removeItem(Map<T, Integer> map, T item, int quantity) {
        int current = map.getOrDefault(item, 0);
        if (current < quantity) return false;
        if (current == quantity) map.remove(item);
        else map.put(item, current - quantity);
        return true;
    }

    /** Inventory item type getters */
    public Map<Armor, Integer> getArmors() {
        return Collections.unmodifiableMap(armors);
    }
    public Map<Weapon, Integer> getWeapons() {
        return Collections.unmodifiableMap(weapons);
    }
    public Map<AdventureGear, Integer> getAdventureGear() {
        return Collections.unmodifiableMap(adventureGear);
    }
    public Map<Instrument, Integer> getInstruments() {
        return Collections.unmodifiableMap(instruments);
    }
    public Map<Miscellaneous, Integer> getMiscellaneous() {
        return Collections.unmodifiableMap(miscellaneous);
    }
}