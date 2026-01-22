package net.astronomy.dnd.model;

public class CombatStats {
    private int armorClass;
    private int initiative;
    private int speed;

    private int maxHitPoints;
    private int currentHitPoints;
    private int temporaryHitPoints;

    private String hitDice;
    private int deathSaveSuccesses;
    private int deathSaveFailures;

    public CombatStats(int armorClass, int initiative, int speed,
                       int maxHitPoints, String hitDice) {
        this.armorClass = armorClass;
        this.initiative = initiative;
        this.speed = speed;
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
        this.hitDice = hitDice;
    }

    public int getArmorClass() { return armorClass; }
    public int getCurrentHitPoints() { return currentHitPoints; }
}