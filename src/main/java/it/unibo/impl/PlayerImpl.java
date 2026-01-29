package it.unibo.impl;

import java.util.Optional;

import it.unibo.api.Position;
import it.unibo.api.key.Key;
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
     * The score of the player
     */
    private Integer score;

    /**
     *The inventory
     */
    private Inventory inventory;

    /**
     * constructor
     * @param position the position (x,y) where the player born 
     */
    public PlayerImpl(Position position){
        this.position=position;
        this.score=0;
        this.inventory=new Inventory();
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

    @Override
    public Inventory getInventory(){
        return this.inventory;
    }

    @Override
    public void addKeyToInventory(Optional<Key> newKey){
        this.inventory.addKey(newKey);
    }
}