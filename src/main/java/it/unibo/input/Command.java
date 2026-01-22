package it.unibo.input;

import it.unibo.api.rooms.RoomManager;

/**
 * a basic command
 */
public interface Command {
    /**
     * executes the command using the given model
     * @param model the model on which the command operates
     */
    void execute(RoomManager model);

}
