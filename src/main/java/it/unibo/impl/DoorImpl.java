package it.unibo.impl;

import it.unibo.api.doors.Door;
import it.unibo.api.rooms.Room;

/**
 * implementation of {@link Door}
 * implements {@link java.io.Serializable}
 */
public class DoorImpl implements Door, java.io.Serializable {

     /**
     * The id of the door
     */
    private final String id;

     /**
     * The destination room
     */
    private final Room dstRoom;

     /**
     * indicates if the door is open
     */
    private boolean open;

    /**
     * constructor
     * @param id this door's id
     * @param dstRoom the destination {@link Room}
     */
    public DoorImpl(final String id, final Room dstRoom) {
        this.dstRoom = dstRoom;
        this.open = false;
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }

    @Override
    public Room getDstRoom() {
        return this.dstRoom;
    }

    @Override
    public void setOpen(boolean b) {
        this.open = true;
    }    

}
