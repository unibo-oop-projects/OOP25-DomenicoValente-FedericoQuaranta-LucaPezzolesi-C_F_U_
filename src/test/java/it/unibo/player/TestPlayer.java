package it.unibo.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.Position;
import it.unibo.api.player.Player;
import it.unibo.impl.PlayerImpl;

public class TestPlayer{
    private Player player;
    private Position startPosition;

    @BeforeEach
    void init(){
        this.startPosition = new Position(0,0);
        this.player = new PlayerImpl(this.startPosition);
    }

    @Test
    void testInitialization(){
        assertEquals(this.startPosition, this.player.getPosition());
    }

    @Test
    void testMovement(){
        Position newPos = new Position(2,3);
        this.player.move(newPos);

        assertEquals(newPos, this.player.getPosition());
        assertNotEquals(this.startPosition, this.player.getPosition());
    }
}