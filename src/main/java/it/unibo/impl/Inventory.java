package it.unibo.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.api.key.Key;

/**
* Inventory system
* implements {@link java.io.Serializable}
*/
public class Inventory implements java.io.Serializable {

     /**
     * The list of the keys
     */
    private final List<Key> keys;

    /**
     * constructor
     */
    public Inventory(){
        this.keys=new ArrayList<>();
    }

    /**
     * gets the list of the keys
     * @return the keys
     */
    public List<Key> getKeys(){
        return new ArrayList<Key>(keys);
    }

    /**
     * add an obj Key to the list
     * @param opt optional param key
     */
    public void addKey(Optional<Key> opt){
        if(! opt.isEmpty()){
            this.keys.add(opt.get());
            System.out.println("You collected: "+opt.get().getName());
        }
    }

    /**
     * search in the list of the keys if there is a specific key
     * @param keyId the id of the key you are looking for
     * @return true if the key is in the inventory false if it is not
     */
    public boolean hasTheKey(String keyId){
        for(Key k:this.keys){
            if(k.getId().equals(keyId)){
                return true;
            }
        }
        return false;
    }
}
