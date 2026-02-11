package it.unibo.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomManager;
import it.unibo.core.GameEngine;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.Inventory;
import it.unibo.impl.PlayerImpl;
import it.unibo.impl.RoomManagerImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.KeyTemplate;
import it.unibo.impl.templates.RoomTemplate;

public class TestGuiStart {

    public static void main(String[] args) {
        Room room = new RoomTemplate("id");
        room.setLayout(8, initializeDoorMap(), initializeEnigmMap());
        RoomManager model = new RoomManagerImpl(new PlayerImpl(new Position(1, 3)));
        model.enterNextRoom(room);
        Inventory.setMaxSize(2);
        GameFrame gf = new GameFrame(room, new Position(1, 3));
        GameEngine ge = new GameEngine(gf, model);
        gf.setController(ge);
        ge.run();
    }

    static private Map<Position, Enigma> initializeEnigmMap() {
        final Map<Position, Enigma> enigmaMap = new HashMap<>();
        enigmaMap.put(new Position(3, 1), new EnigmaTemplate("testEnigma1", new KeyTemplate(), "testQuestion1", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst4"));
                enigmaMap.put(new Position(6, 6), new EnigmaTemplate("testEnigma2", null, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst1"));
        enigmaMap.put(new Position(2, 5), new EnigmaTemplate("testEnigma3", null, "testQuestion2", 
                List.of("qst1", "qst2", "qst3", "qst4"), "qst2"));
        return enigmaMap;
    }

    static private Map<Position, Door> initializeDoorMap() {
        final Map<Position, Door> doorMap = new HashMap<>();
        Room room2 = new RoomTemplate("id");
        room2.setLayout(8, doorMap, initializeEnigmMap());
        Door door = new DoorImpl("testDoor1", room2);
        door.setOpen(true);
        doorMap.put(new Position(4, 7), door);
        return doorMap;
    }
}
