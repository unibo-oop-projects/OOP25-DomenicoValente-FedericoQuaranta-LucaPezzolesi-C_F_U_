package it.unibo.storage.dataGenerators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.api.rooms.Room;
import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.key.Key;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.KeyTemplate;
import it.unibo.impl.templates.RoomTemplate;
import it.unibo.storage.rooms.RoomSave;

public class RoomsGeneration {
    public static void main(String[] args){
        List<Room> roomsTosave= new ArrayList<>();

        final RoomSave storagSave = new RoomSave();
        storagSave.saveRooms(roomsTosave);
    }

    private static void populateList(final List<Room> list){
        final Room startRoom = new RoomTemplate("start_rooom");
        final Room secondRoom = new RoomTemplate("second_room");

        //configuartion start room
        //a door that link the start room to the second room
        final Door doorToSecond = new DoorImpl("start_to_second", secondRoom);

        //a key that open the door
        final Key keyToSecond = new KeyTemplate("key_second", "key_second", doorToSecond);

        //a enigma that contains the key
        final Enigma enigmaStart = new EnigmaTemplate("enigma_math", keyToSecond, "2+2=?", List.of("3", "4", "5"), "4");

        //maps fot start_room
        Map<Position, Door> doorsMap = new HashMap<>();
        doorsMap.put(new Position(0,5), doorToSecond);

        Map<Position, Enigma> enigmasMap = new HashMap<>();
        enigmasMap.put(new Position(5,3), enigmaStart);

        startRoom.setLayout(10, doorsMap, enigmasMap);

        //configuration secondRoom
        Map<Position, Door> secondDoors = new HashMap<>();
        Map<Position, Enigma> secondEnigmas = new HashMap<>();

        secondRoom.setLayout(15, secondDoors, secondEnigmas);

        list.add(secondRoom);
        list.add(startRoom);
    }

    @Test
    void testFileContent(){
        List<Room> expectedList= new ArrayList<>();
        populateList(expectedList);

        final RoomSave storagSave= new RoomSave();
        storagSave.saveRooms(expectedList);

        List<Room> loadedList= storagSave.getRooms();

        assertEquals(expectedList.size(), loadedList.size());
        
        if (expectedList instanceof RoomTemplate && loadedList instanceof RoomTemplate) {
            assertEquals(((RoomTemplate) expectedList.get(0)).getId(), ((RoomTemplate) loadedList.get(0)).getId());
        }
    }
   
}
