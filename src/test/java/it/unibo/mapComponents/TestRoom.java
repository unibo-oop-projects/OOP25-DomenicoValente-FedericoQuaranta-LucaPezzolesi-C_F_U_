package it.unibo.mapComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.impl.DoorImpl;
import it.unibo.api.rooms.Room;
import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.RoomTemplate;

public class TestRoom {

    private Room room;
    Map<Position, Enigma> enigmaMap = this.initializeEnigmMap();
    Map<Position, Door> doorMap = this.initializeDoorMap();


    @BeforeEach
    void init() {
        this.room = new RoomTemplate("testRoom");
    }

    @Test
    void testRoomGeneration() {
        this.enigmaMap = this.initializeEnigmMap();
        this.doorMap = this.initializeDoorMap();
        
        this.room.setLayout(4, doorMap, enigmaMap);
    
        for(Position pos: enigmaMap.keySet()) {
            assertEquals(pos, room.getEnigmaPosition(enigmaMap.get(pos)));
        }
    }

    @Test
    void testExeptionsThrowing() {
        this.doorMap = new HashMap<>();
        this.doorMap.put(new Position(90, 54), new DoorImpl("testId", "testIDRoom"));

        this.enigmaMap = new HashMap<>();
        this.enigmaMap.put(new Position(90, 54), new EnigmaTemplate("testId", null , "q", List.of("1"), "1"));

        assertThrows(IllegalArgumentException.class, (() -> this.room.setLayout(3, doorMap, enigmaMap)));

    }


    private Map<Position, Enigma> initializeEnigmMap() {
        final Map<Position, Enigma> enigmaMap = new HashMap<>();
        enigmaMap.put(new Position(0, 2), new EnigmaTemplate("testEnigma1", null, "testQuestion1", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst4"));
        enigmaMap.put(new Position(3, 3), new EnigmaTemplate("testEnigma2", null, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst2"));
        return enigmaMap;
    }

    private Map<Position, Door> initializeDoorMap() {
        final Map<Position, Door> doorrMap = new HashMap<>();
        doorrMap.put(new Position(0, 3), new DoorImpl("testDoor1", "dstRoom1"));
        doorrMap.put(new Position(3, 2), new DoorImpl("testDoor2", "dstRoom2"));
        return doorrMap;
    }

}
