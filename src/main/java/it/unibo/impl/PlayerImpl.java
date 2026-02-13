package it.unibo.impl;

import it.unibo.api.Position;
import it.unibo.api.player.Player;


/**
 * implementation of {@link Player}
 * implements {@link java.io.Serializable}
 */
public class PlayerImpl implements Player, java.io.Serializable {

     /**
     * The current player position
     */
    private Position position;

    /**
     * constructor
     * @param position the position (x,y) where the player born 
     */
    public PlayerImpl(Position position){
        this.position=position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void move(Position nextPosition) {
        this.position=nextPosition;       
    }
}