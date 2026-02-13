package it.unibo.inventory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.api.key.Key;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.Inventory;
import it.unibo.impl.templates.KeyTemplate;

public class TestInventory {

    @Test
    void testAddKey(){
        Key dummyKey= new KeyTemplate("key-001", "gold key", new DoorImpl("door-001", "room-001")); 
        Inventory.addKey(dummyKey);

        assertTrue(Inventory.hasTheKey(dummyKey.getId()));
    }
}
