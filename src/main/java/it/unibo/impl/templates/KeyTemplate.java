package it.unibo.impl.templates;

import it.unibo.api.doors.Door;
import it.unibo.api.key.Key;
import it.unibo.impl.Inventory;

/**
 * an implementation of {@link Key}
 * implements {@link java.io.Serializable}
 */
public class KeyTemplate implements Key, java.io.Serializable {
    
    /**
     * The key id
     */
    private String id;

    /**
     * The name of the key
     */
    private String name;

    /**
     * The associated door
     */
    private Door destination;

    
    /**
     * constructor
     * @param id key's id
     * @param name key's name
     * @param destination the door that the key unlock
     */
    public KeyTemplate(final String id,final String name,final Door destination){
        this.id=id;
        this.name=name;
        this.destination=destination;
    }
    /**
     * 0 argoments constructor 
     */
    public KeyTemplate(){ }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Door getDst() {
        return this.destination;
    }
    
    @Override
    public void openDoor() {
            this.destination.setOpen(true);
    }
    
    @Override
    public void addToInventory(){
        Inventory.addKey(this);
    }

    /**
     * sets the key id
     * @param newId the new id of the key
     */
    public void setId(final String newId) {
        this.id=newId;
    }

    /**
     * sets the key name
     * @param newName the new name of the key
     */
    public void setName(final String newName) {
        this.name=newName;
    }

    /**
     * sets the key destination
     * @param newDoor the new destination of the key
     */
    public void setDst(final Door newDoor) {
        this.destination=newDoor;
    }
}
