package it.unibo.api.player;

import java.util.Optional;

import it.unibo.api.Position;
import it.unibo.api.key.Key;
import it.unibo.impl.Inventory;

/**
 * player
 */
public interface Player {
    /**
     * gets the current position of the player
     * @return the position
     */
    Position getPosition();

    /**
     * update the current position of the player to the next position
     * @param nextPosition the position where the player wants to move
     */
    void move(Position nextPosition);

    /**
     * update the score that the player holds
     */
    void addPoints();

    /**
     * gets the current points of the player
     * @return the points
     */
    int getPoints();

    /**
     * gets the inventory
     * @return the inventory
     */
    Inventory getInventory();

    /**
     * add a new key to the inventory
     */
    void addKeyToInventory(Optional<Key> newKey);
};
