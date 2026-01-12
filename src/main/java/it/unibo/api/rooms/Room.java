package it.unibo.api.rooms;

import java.util.Map;

import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;

/**
 * a basic room, divided in cells
 */
public interface Room {

    /**
     * gets the entity in the {@code pos} cell
     * @param pos the position
     * @return the entity in that position of the grid
     */
    RoomCellsValues getCellContent(Position pos);

    /**
     * gets the position of the {@code door} specified
     * @param door the door
     * @return the position of that door
     */
    Position getDoorPosition(Door door);

    /**
     * gets the position of the {@code enigm} specified
     * @param enigm the enigm
     * @return the position of that enigm
     */
    Position getEnigmaPosition(Enigma enigm);

    /**
     * generates a Room implementation with the given layout
     * @param size the size of the room (square) 
     * @param doorMap a map of every door position
     * @param enigmaMap a map of every enigma position
     */
    public void setLayout(final int size, 
        final Map<Position, Door> doorMap, final Map<Position, Enigma> enigmaMap);

}
