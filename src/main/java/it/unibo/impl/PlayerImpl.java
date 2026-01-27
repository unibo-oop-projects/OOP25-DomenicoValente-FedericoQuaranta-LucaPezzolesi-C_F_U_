package it.unibo.impl;

import it.unibo.api.Position;
import it.unibo.api.player.Player;


/**
 * implementation of {@link Player}
 */
public class PlayerImpl implements Player, java.io.Serializable {
    private Position position;
    private Integer score;

    /**
     * constructor
     * @param position the position (x,y) where the player born 
     */
    public PlayerImpl(Position position){
        this.position=position;
        this.score=0;
    }

    @Override
    public void addPoints() {
        this.score++;
        
    }

    @Override
    public int getPoints() {
        return this.score;
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