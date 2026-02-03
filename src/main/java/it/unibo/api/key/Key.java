package it.unibo.api.key;

import it.unibo.api.doors.Door;

/**
 * a basic key
 */
public interface Key {

    /**
     * gets  the id of the key
     * @return the id 
    */
    public String getId();

    /**
     * gets the name of the key
     * @return the name 
    */
    public String getName();

    /**
     * gets the door that the key unlock
    * @return the door 
    */
    public Door getDst();

    /**
     * open the door of the room
     */
    public void openDoor();

    /**
     * add a  key to the inventory
     */
    public void addToInventory();
} 