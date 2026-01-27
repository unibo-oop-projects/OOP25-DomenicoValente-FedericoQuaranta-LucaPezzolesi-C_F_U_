package it.unibo.impl.templates;

import it.unibo.api.doors.Door;
import it.unibo.api.key.Key;

/**
 * an implementation of {@link Key}
 * implements {@link java.io.Serializable}
 */
public class KeyTemplate implements Key, java.io.Serializable {
    
    /**
     * The key id
     */
    private final String id;

    /**
     * The name of the key
     */
    private final String name;

    /**
     * The associated door
     */
    private final Door destination;

    
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
}
