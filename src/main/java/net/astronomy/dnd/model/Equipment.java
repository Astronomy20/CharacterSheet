package net.astronomy.dnd.model;

import java.util.List;

public class Equipment {
    private List<String> items;
    private List<Attack> attacks;

    public Equipment(List<String> items, List<Attack> attacks) {
        this.items = items;
        this.attacks = attacks;
    }
}