package it.unibo.inventory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.key.Key;
import it.unibo.impl.Inventory;

public class TestInventory {
    private Inventory inventory;
    
    @BeforeEach
    void init(){
        this.inventory = new Inventory();
    }

    @Test
    void testInitialization(){
        assertNotNull(this.inventory);
    }

    @Test
    void testAddKey(){
        Key dummyKey = mock(Key.class);
        this.inventory.addKey(Optional.of(dummyKey));

        assertTrue(this.inventory.hasTheKey(dummyKey.getId()));
    }
}
