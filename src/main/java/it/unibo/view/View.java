package it.unibo.view;

import it.unibo.api.Position;
import it.unibo.api.rooms.Room;

/**
 * interface for view
 */
public interface View {

    /**
     * update the view with new parameter
     * @param room the actual room
     * @param playerPosition the actual positon of the player
     */
    public void updateView(Room room, Position playerPosition);
}
