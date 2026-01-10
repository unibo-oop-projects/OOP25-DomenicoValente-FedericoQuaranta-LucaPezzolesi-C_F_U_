package it.unibo.api.rooms;

/**
 * possible Room cells contents
 */
public enum RoomCellsValues {

    /** free cell */
    FREE,
    
    /** blocked cell */
    OBSTACLE,

    /** cell blocked by the wall */
    WALL,

    /** door */
    DOOR,

    /** enigma */
    ENIGMA;

}
