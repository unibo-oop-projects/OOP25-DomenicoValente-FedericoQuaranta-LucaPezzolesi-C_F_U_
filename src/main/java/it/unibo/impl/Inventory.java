package it.unibo.impl;


import java.util.ArrayList;
import java.util.List;


import it.unibo.api.key.Key;

/**
* Inventory system
* implements {@link java.io.Serializable}
*/
public class Inventory implements java.io.Serializable {

     /**
     * The list of the keys
     */
    private static List<Key> keys = new ArrayList<>();

    /**
     * constructor
     */
    private Inventory(){ }

    /**
     * gets the list of the keys
     * @return the keys
     */
    public static List<Key> getKeys(){
        return keys;
    }

    /**
     * add an obj Key to the list
     * @param key optional param key
     */
    public static void addKey(Key key){
        keys.add(key);
        System.out.println("You collected: "+key.getName());
    }

    /**
     * search in the list of the keys if there is a specific key
     * @param keyId the id of the key you are looking for
     * @return true if the key is in the inventory false if it is not
     */
    public static boolean hasTheKey(String keyId){
        for(Key k:keys){
            if(k.getId()==keyId){
                return true;
            }
        }
        return false;
    }

    /**
     * resets the inventory to an empty list
     */
    public static void reset() {
        keys = new ArrayList<>();
    }
}
