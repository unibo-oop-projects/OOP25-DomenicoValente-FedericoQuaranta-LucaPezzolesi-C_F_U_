package it.unibo.storage.rooms;

import java.util.Map;

import it.unibo.api.Position;
import it.unibo.storage.enigma.DataForEnigmas;

    /**
     * data template for Rooms saving on yaml
     */
public class DataForRooms {
    /**
     *the id of the room
     */
    private String id;

    /**
     *the size of the room
     */
    private int size;

    /**
     * the list of the enigma data of the room
     */
    private Map<Position, DataForEnigmas> enigmas;
    /**
     * the list of the door data of the room
     */
    private Map<Position, DataForDoor> doors;

    /**
     *  0 constructor
     */
    public DataForRooms() {}

    /**
     * constructor with args
     * @param id the id of the room
     * @param size the size of the room
     * @param enigmas the enigmas list of the room
     * @param doors the doors list of the room
     */
    public DataForRooms( String id, int size, Map<Position, DataForEnigmas> enigmas, Map<Position, DataForDoor> doors) {
        this.doors=doors;
        this.enigmas=enigmas;
        this.id=id;
        this.size=size;
    }

    //getter
    /**
     * gets the id of the room
     * @return the id
     */
    public String getId() {
        return this.id;
    }
    /**
     * gets the size of the room
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * gets the list of the enigmas of the room
     * @return the enigmas list
     */
    public Map<Position, DataForEnigmas> getEnigmas() {
        return this.enigmas;
    }
    
    /**
     * gets the list of the doors of the room
     * @return the roooms list
     */
    public Map<Position, DataForDoor> getDoors() {
        return this.doors;
    }

    //setter
    /**
     * sets the id of the room
     * @param id new id of the room
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * sets the size of the room
     * @param size new size of the room
     */
    public void setSize(final int size) {
        this.size = size;
    }

    /**
     * sets the enigmas list of the room
     * @param enigmas new enigmas of the room
     */
    public void setEnigmas(final Map<Position, DataForEnigmas> enigmas) {
        this.enigmas = enigmas;
    }

    /**
     * sets the doors list of the room
     * @param doors new doors list of the room
     */
    public void setDoors(final Map<Position, DataForDoor> doors) {
        this.doors = doors;
    }

}
