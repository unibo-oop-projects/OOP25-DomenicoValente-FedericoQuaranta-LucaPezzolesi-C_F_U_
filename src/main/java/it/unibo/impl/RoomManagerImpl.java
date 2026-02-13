package it.unibo.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import it.unibo.api.Position;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.player.Player;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomCellsValues;
import it.unibo.api.rooms.RoomManager;
import it.unibo.storage.roommanager.RoomManagerStorage;

/**
 * implementation of {@link RoomManager} 
 * this class also implements {@link java.io.Serializable}
 */
public class RoomManagerImpl implements RoomManager, java.io.Serializable {

    /**
     * The player managed by this RoomManager
     */
    private final Player player;

     /**
     * The current room where the player is located
     */
    private Room currentRoom;

    /**
     * basic constructor
     * @param player the player
     */
    public RoomManagerImpl(final Player player) {
        this.player=player;
    }

    @Override
    public void enterNextRoom(Room nextRoom) {
        this.currentRoom = nextRoom;
        try {
            RoomManagerStorage.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public Position getCurrentPosition() {
        return this.player.getPosition();
    }

    @Override
    public boolean isPlayerColliding(final Position nextPosition) {
        if(currentRoom.getCellContent(nextPosition) == RoomCellsValues.FREE){
            return false;
        }else{
            return true;
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
        if(this.currentRoom.getCellContent(nextPosition) == RoomCellsValues.ENIGMA || this.currentRoom.getCellContent(nextPosition) == RoomCellsValues.DOOR){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Enigma> enterEnigma(final Position posEnigma){
        if(this.currentRoom.getCellContent(posEnigma) == RoomCellsValues.ENIGMA) {
            return Optional.of(this.currentRoom.getEnigma(posEnigma));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void enterDoor(final Position posDoor, final List<Room> rooms){ 
        if(this.currentRoom.getCellContent(posDoor) == RoomCellsValues.DOOR) {
            
            // 1. La porta è aperta?
            if(this.currentRoom.getDoor(posDoor).isOpen()){
                
                // Prendo l'ID e tolgo eventuali spazi vuoti iniziali/finali
                String targetRoomId = this.currentRoom.getDoor(posDoor).getDstRoomId().trim();
                Room nextRoom = null;    
                
                // 2. Cerco la stanza nella lista usando .equals()
                for(Room r: rooms){
                    if(targetRoomId.equals(r.getId().trim())){
                        nextRoom = r; 
                        break;
                    }
                }
                
                // 3. Entro nella stanza se l'ho trovata
                computeMove(true, new Position(1, 1));
                enterNextRoom(nextRoom);   
            }
        }
    }

}

