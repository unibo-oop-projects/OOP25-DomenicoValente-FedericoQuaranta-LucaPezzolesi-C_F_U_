package it.unibo.api.player;


import it.unibo.api.Position;

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
};
