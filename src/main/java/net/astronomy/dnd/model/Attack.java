package net.astronomy.dnd.model;

public class Attack {
    private String name;
    private int attackBonus;
    private String damage;

    public Attack(String name, int attackBonus, String damage) {
        this.name = name;
        this.attackBonus = attackBonus;
        this.damage = damage;
    }
}