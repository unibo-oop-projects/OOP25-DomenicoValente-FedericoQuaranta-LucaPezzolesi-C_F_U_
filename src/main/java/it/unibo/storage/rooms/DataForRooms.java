package it.unibo.storage.rooms;

import java.util.List;

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
    private List<EnigmaData> enigmas;
    /**
     * the list of the door data of the room
     */
    private List<DoorData> doors;

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
    public DataForRooms( String id, int size, List<EnigmaData> enigmas, List<DoorData> doors) {
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
    public List<EnigmaData> getEnigmas() {
        return this.enigmas;
    }
    
    /**
     * gets the list of the doors of the room
     * @return the roooms list
     */
    public List<DoorData> getDoors() {
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
    public void setEnigmas(final List<EnigmaData> enigmas) {
        this.enigmas = enigmas;
    }

    /**
     * sets the doors list of the room
     * @param doors new doors list of the room
     */
    public void setDoors(final List<DoorData> doors) {
        this.doors = doors;
    }

    /**
     * data template for enigma saving on rooms.yaml
     */
    public static class EnigmaData{
        /**
         * coordinate x of the enigma
         */
        public double x;
        /**
         * coordinate y of the enigma
         */
        public double y;
        /**
         * the enigmas data
         */
        public DataForEnigmas data;

        /**
         * 0 args constructor
         */
        public EnigmaData(){}
    }
    /**
     * data template for door saving on rooms.yaml
     */
    public static class DoorData{
        /**
         * coordinate x of the door
         */
        public double x;
        /**
         * coordinate x of the door
         */
        public double y;
        /**
         * the enigmas data
         */
        public DataForDoor data;

        /**
         * 0 args constructor
         */
        public DoorData(){}
    }

}
