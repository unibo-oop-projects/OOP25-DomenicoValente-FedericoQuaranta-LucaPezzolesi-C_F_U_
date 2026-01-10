package it.unibo.mapComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @BeforeEach
    void init() {
        this.room = new RoomTemplate("testRoom");
    }

    @Test
    void testRoomGeneration() {
        final Map<Position, Enigma> enigmaMap = this.initializeEnigmMap();
        final Map<Position, Door> doorMap = this.initializeDoorMap();
        
        this.room.setLayout(4, doorMap, enigmaMap);
    
        for(Position pos: enigmaMap.keySet()) {
            assertEquals(pos, room.getEnigmaPosition(enigmaMap.get(pos)));
        }
    }

    private Map<Position, Enigma> initializeEnigmMap() {
        final Map<Position, Enigma> enigmaMap = new HashMap<>();
        enigmaMap.put(new Position(0, 2), new EnigmaTemplate("testEnigma1", true, "testQuestion1", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst4"));
        enigmaMap.put(new Position(3, 3), new EnigmaTemplate("testEnigma2", false, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst2"));
        return enigmaMap;
    }

    private Map<Position, Door> initializeDoorMap() {
        final Map<Position, Door> doorrMap = new HashMap<>();
        doorrMap.put(new Position(0, 3), new DoorImpl("testDoor1", new RoomTemplate("dstRoom1")));
        doorrMap.put(new Position(3, 2), new DoorImpl("testDoor2", new RoomTemplate("dstRoom2")));
        return doorrMap;
    }

}
