package it.unibo.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.player.Player;
import it.unibo.api.key.Key;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.Inventory;
import it.unibo.impl.PlayerImpl;
import it.unibo.impl.templates.KeyTemplate;

public class TestPlayer{
    private Player player;
    private Position startPosition;

    @BeforeEach
    void init(){
        this.startPosition = new Position(0,0);
        this.player = new PlayerImpl(this.startPosition);
    }

    @Test
    void testInitiaziation(){
        assertEquals(this.startPosition, this.player.getPosition());
        assertEquals(0,this.player.getPoints());
        assertNotNull(this.player.getInventory());
    }

    @Test
    void testMovement(){
        Position newPos = new Position(2,3);
        this.player.move(newPos);

        assertEquals(newPos, this.player.getPosition());
        assertNotEquals(this.startPosition, this.player.getPosition());
    }

    @Test
    void testScore(){
        assertEquals(0, this.player.getPoints());
        
        this.player.addPoints();
        assertEquals(1, this.player.getPoints());

        this.player.addPoints();
        assertEquals(2, this.player.getPoints());
    }

    @Test
    void testInventoryInteractions(){
        assertNotNull(this.player.getInventory());

        
    }
}