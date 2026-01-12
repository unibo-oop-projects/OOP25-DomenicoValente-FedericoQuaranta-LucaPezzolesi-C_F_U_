package it.unibo.api.rooms;

import it.unibo.api.Position;

/**
 * a simple manager for player movement in the rooms
 */
public interface RoomManager {

    /**
     * unloads the current room and loads the next
     * @param nextRoom the room to load
     * @return {@code true} if the operation succeded, {@code false} otherwise
     */
    boolean enterNextRoom(Room nextRoom);

    /**
     * gets the current room
     * @return the current room
     */
    Room getCurrentRoom();

    /**
     * checks if the cell in wich the player wants to move is free or has an {@link RoomCellsValues} parameter
     * @return {@code true} if the cell is {@link RoomCellsValues}.free
     */
    boolean isPlayerColliding();

    /**
     * performs the move
     * @param canMove false if the player is blocked
     * @return the new position
     */
    Position computeMove(boolean canMove);

    /**
     * checks if the player is about to enter an event (door, enigm)
     * @return {@code true} if the player is entering an event, {@code false} otherwise
     */
    boolean isEnteringAnEvent();



}
