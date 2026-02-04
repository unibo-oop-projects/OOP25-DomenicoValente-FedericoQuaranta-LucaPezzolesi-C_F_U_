package it.unibo.impl.templates;

import java.util.HashMap;
import java.util.Map;

import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomCellsValues;

/**
 * an implementation of {@link Room}
 * implements {@link java.io.Serializable}
 */
public class RoomTemplate implements Room, java.io.Serializable {

    private final static int MAXIMUM_DOORS_COUNT = 4;
    private final static int MAXIMUM_ENIGMAS_COUNT = 3;
    private final static int MAXIMUM_SIZE = 20;

     /**
     * The id of the room
     */
    private String id;

     /**
     * The game map
     */
    private Map<Position, RoomCellsValues> grid;

     /**
     * The position of the enigmas
     */
    private Map<Position, Enigma> enigmaGrid;

     /**
     * The position of the doors
     */
    private Map<Position, Door> doorGrid;

    /** 0-args constructor */
    public RoomTemplate() {}

    /**
     * constructor
     * @param id this id
     */
    public RoomTemplate(final String id){
        this.doorGrid = new HashMap<>();
        this.enigmaGrid = new HashMap<>();
        this.id = id;
        this.grid = new HashMap<>();
    }

    @Override
    public RoomCellsValues getCellContent(final Position pos) {
        return this.grid.get(pos);
    }

    @Override
    public Position getDoorPosition(final Door door) {
        for(Position pos: this.doorGrid.keySet()) {
            if(this.doorGrid.get(pos).equals(door)) {
                return pos;
            }
        }
        throw new IllegalArgumentException("no such door in this room");
    }

    @Override
    public Position getEnigmaPosition(final Enigma enigm) {
        for(Position pos: this.enigmaGrid.keySet()) {
            if(this.enigmaGrid.get(pos).equals(enigm)) {
                return pos;
            }
        }
        throw new IllegalArgumentException("no such enigm in this room");
    }

    /**
     * gets the id of this room
     * @return this id
     */
    public String getId() {
        return this.id;
    }

    /**
     * places a new door in the specified position
     * @param pos the position
     * @param door the door to place
     */
    private void setNewDoor(final Position pos, final Door door) {
        if(canPlaceDoor(pos)) {
            this.grid.put(pos, RoomCellsValues.DOOR);
            this.doorGrid.put(pos, door);
        } else {
            throw new IllegalArgumentException("cannot place a new door here");
        }
    }

    /**
     * places a new enigma in the specified position
     * @param pos the position
     * @param door the enigma to place
     */
    private void setNewEnigma(final Position pos, final Enigma enigma) {
        if(canPlaceEnigma(pos)) {
            this.grid.put(pos, RoomCellsValues.ENIGMA);
            this.enigmaGrid.put(pos, enigma);
        }else {
            throw new IllegalArgumentException("cannot place a new enigma here");
        }
    }

    /**
     * decides if the door can be placed: the new door has to be in a wall
     * @param pos the position where the dooe should be
     * @return {@code true} if the door can be placed, {@code false} otherwise
     */
    private boolean canPlaceDoor(final Position pos) {
        if(getElementNumber(RoomCellsValues.DOOR) < MAXIMUM_DOORS_COUNT &&
            this.grid.get(pos) == RoomCellsValues.WALL
        ) {
            return true;
        }
        return false;
    }

    /**
     * decides if the enigma can be placed: the new enigma has to be in an empty cell or in a wall
     * @param pos the position where the enigma should be
     * @return {@code true} if the enigma can be placed, {@code false} otherwise
     */
    private boolean canPlaceEnigma(final Position pos) {
        if(getElementNumber(RoomCellsValues.ENIGMA) < MAXIMUM_ENIGMAS_COUNT &&
            (this.grid.get(pos) == RoomCellsValues.WALL || this.grid.get(pos) == RoomCellsValues.FREE)
        ) {
            return true;
        }
        return false;
    }


    /**
     * gets the number of elements in the map of this type
     * @param element the type of the elements
     * @return the number of {@code elements}
     */
    private int getElementNumber(RoomCellsValues element) {
        int count = 0;
        for(Position pos: this.grid.keySet()) {
            if(this.grid.get(pos).equals(element)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void setLayout(final int size, final Map<Position, Door> doorMap, final Map<Position, Enigma> enigmaMap) {
            if(size <= MAXIMUM_SIZE && 
                doorMap.size() <= (MAXIMUM_DOORS_COUNT - getElementNumber(RoomCellsValues.DOOR)) &&
                enigmaMap.size() <= (MAXIMUM_ENIGMAS_COUNT - getElementNumber(RoomCellsValues.ENIGMA))
            ) {
                this.setWalls(size);

                for(Position pos: doorMap.keySet()) {
                    this.setNewDoor(pos, doorMap.get(pos));
                }
                for(Position pos: enigmaMap.keySet()) {
                    this.setNewEnigma(pos, enigmaMap.get(pos));
                }
            } 
        }
    
    /**
     * sets the walls on the room border and marks the other cells as {@code FREE}
     * @param size the size of the room
     */
    private void setWalls(final int size) {
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                final Position pos = new Position(x, y);
                if(x == 0 || x == size - 1 || y == 0 || y == size - 1) {
                    this.grid.put(pos, RoomCellsValues.WALL);
                } else {
                    this.grid.put(pos, RoomCellsValues.FREE);
                }
                
            }
        }
    }

    @Override
    public Door getDoor(Position pos) {
        return this.doorGrid.get(pos);
    }

    @Override
    public Enigma getEnigma(Position pos) {
        return this.enigmaGrid.get(pos);
    }
    
    @Override
    public Map<Position, Enigma> getEnigmaGrid() {
        return this.enigmaGrid;
    }
    
    @Override
    public Map<Position, Door> getDoorGrid() {
        return this.doorGrid;
    }

    
//setters and getters for yaml//

    /**
     * setter for snakeYaml
     * @param id .
     */
    private void setId(final String id) {
        this.id = id;
    }

    /**
     * setter for snakeYaml
     * @param grid .
     */
    private void setGrid(final Map<Position, RoomCellsValues> map) {
        this.grid = map;
    }
    
    /**
     * setter for snakeYaml
     * @param map .
     */
    private void setEnigmaGrid(final Map<Position, Enigma> map) {
        this.enigmaGrid = map;
    }

    /**
     * setter for snakeYaml
     * @param map .
     */
    private void setDoorGrid(final Map<Position, Door> map) {
        this.doorGrid = map;
    }

    /**
     * getter for snakeYaml
     * @return .
     */
    private Map<Position, RoomCellsValues> getGrid() {
        return this.grid;
    }
//
}
