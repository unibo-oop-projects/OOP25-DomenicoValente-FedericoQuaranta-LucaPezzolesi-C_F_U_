package it.unibo.impl;

import it.unibo.api.doors.Door;
import it.unibo.api.rooms.Room;

/**
 * implementation of {@link Door}
 */
public class DoorImpl implements Door, java.io.Serializable {

    private final String id;
    private final Room dstRoom;
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
