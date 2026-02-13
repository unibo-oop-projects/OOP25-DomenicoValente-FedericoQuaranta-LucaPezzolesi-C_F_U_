package it.unibo.api.doors;


/**
 * a basic door
 */
public interface Door {

    /**
     * gets the id of this door
     * @return the id
     */
    String getId();

    /**
     * tells if the door is open or not
     * @return {@code} true if open, {@code false} if not
     */
    boolean isOpen();

    /**
     * gets the room that the door brings to
     * @return the room on the other side of the door
     */
    String getDstRoomId();

    /**
     * opens the door. 
     * once a door is open, it cannot be closed anymore
     * @param b {@code true} if the door should open, {@code false} otherwise
     */
    void setOpen(boolean b);

}
