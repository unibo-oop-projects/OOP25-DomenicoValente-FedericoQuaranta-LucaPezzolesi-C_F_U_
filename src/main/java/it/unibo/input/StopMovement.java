package it.unibo.input;

import it.unibo.api.rooms.RoomManager;

/**
 * Command that intentionally does nothing and stops the player
 */
public class StopMovement implements Command {

    /**
     * Creates a StopMovement command.
     */
    public StopMovement() {
        // default constructor
    }

    @Override
    public void execute(RoomManager model) {
        // Intentionally does nothing: player is stopped
    }

}
