package it.unibo.impl;

import it.unibo.api.Position;
import it.unibo.api.player.Player;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomCellsValues;
import it.unibo.api.rooms.RoomManager;

/**
 * implementation of {@link RoomManager} 
 */
public class RoomManagerImpl implements RoomManager{

    private final Player player;
    private Room currentRoom;

    /**
     * basic constructor
     */
    public RoomManagerImpl(final Player player) {
        this.player=player;
    }

    @Override
    public void enterNextRoom(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public boolean isPlayerColliding(final Position nextPosition) {
        if(currentRoom.getCellContent(nextPosition) == RoomCellsValues.FREE){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void computeMove(boolean canMove, final Position nextPosition) {
        if(canMove){
            this.player.move(nextPosition);
        }
    }

    @Override
    public boolean isEnteringAnEvent(final Position nextPosition) {
        if(this.currentRoom.getCellContent(nextPosition) == RoomCellsValues.ENIGMA){
            enterEnigma(nextPosition);
            return true;
        }else if(this.currentRoom.getCellContent(nextPosition) == RoomCellsValues.DOOR){
            enterDoor(nextPosition);
            return true;
        }else{
            return false;
        }
    }

    /**
     * get the question and the possible options to solve the enigma
     * @param posEnigma to get the obj enigma
     */
    private void enterEnigma(final Position posEnigma){
        this.currentRoom.getEnigma(posEnigma).getQuestion();
        this.currentRoom.getEnigma(posEnigma).getOptions();
    }

    /**
     * if the door is open enter to the next door
     * @param posDoor to ghet the obj door
     */
    private void enterDoor(final Position posDoor){ 
        if(this.currentRoom.getDoor(posDoor).isOpen()){
                enterNextRoom(this.currentRoom.getDoor(posDoor).getDstRoom());
        }
    }
}

