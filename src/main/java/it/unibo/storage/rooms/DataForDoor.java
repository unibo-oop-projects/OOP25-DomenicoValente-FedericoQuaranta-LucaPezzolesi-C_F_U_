package it.unibo.storage.rooms;

import it.unibo.api.Position;

/**
 * data template for Doors saving on yaml
 */
public class DataForDoor {
    private  Position positionDoor;

    /**
    * thw id of the door
    */
    private String id;

    /**
     * the id of the destination room
     */
    private String dstRoomId;

    /**
     * if the door is open
     */
    private boolean open;

    /**
     * 0 args constructor
     */
    public DataForDoor() {}

    /**
     * constructor
     * @param id the id of the door
     * @param dstRoomId the id of the the destination room
     * @param open if the door is open
     * @param positionDoor the position of the door
     */
    public DataForDoor(final String id, final String dstRoomId, final boolean open, final Position positionDoor) {
        this.id = id;
        this.dstRoomId = dstRoomId;
        this.open = open;
        this.positionDoor=positionDoor;
    }

    /**
     * gets the id of the door
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * sets the id of the door
     * @param id the new id of the door
     */
    public void setId(final String id) {
        this.id = id;
    }
    
    /**
     * gets the destination room
     * @return the id
     */
    public String getDstRoomId() {
        return dstRoomId;
    }

    /**
     * sets the destination room
     * @param dstRoomId the new dstRoomid of the door
     */
    public void setDstRoomId(final String dstRoomId) {
        this.dstRoomId = dstRoomId;
    }

    /**
     * gets the state of the door
     * @return the state
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * sets the state of the door
     * @param open the new state of the door
     */
    public void setOpen(final boolean open) {
        this.open = open;
    }
    public Position getPositionDoor() {
        return positionDoor;
    }

    public void setPositionDoor(Position positionDoor) {
        this.positionDoor = positionDoor;
    }
   
}
