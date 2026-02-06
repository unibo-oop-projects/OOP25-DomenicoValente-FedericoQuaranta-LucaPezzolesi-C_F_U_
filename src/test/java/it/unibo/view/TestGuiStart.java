package it.unibo.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.RoomTemplate;

public class TestGuiStart {

    public static void main(String[] args) {
        Room room = new RoomTemplate("id");
        room.setLayout(8, initializeDoorMap(), initializeEnigmMap());
        GameFrame gm = new GameFrame(room, new Position(1, 3), null);
    }

    static private Map<Position, Enigma> initializeEnigmMap() {
        final Map<Position, Enigma> enigmaMap = new HashMap<>();
        enigmaMap.put(new Position(3, 1), new EnigmaTemplate("testEnigma1", null, "testQuestion1", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst4"));
                enigmaMap.put(new Position(6, 6), new EnigmaTemplate("testEnigma2", null, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst1"));
        enigmaMap.put(new Position(2, 5), new EnigmaTemplate("testEnigma3", null, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst2"));
        return enigmaMap;
    }

    static private Map<Position, Door> initializeDoorMap() {
        final Map<Position, Door> doorMap = new HashMap<>();
        Door door = new DoorImpl("testDoor1", new RoomTemplate("id2"));
        doorMap.put(new Position(4, 7), door);
        return doorMap;
    }
}
