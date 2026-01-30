package net.astronomy.dnd.util;

/**
 * Interface for objects that need to be notified when a character levels up.
 */
public interface LevelUpListener {
    /**
     * Called when a level up occurs.
     */
    void onLevelUp();
}