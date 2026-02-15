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
import it.unibo.storage.enigma.EnigmaSave;
import it.unibo.storage.rooms.RoomSave;

public class RoomsGeneration {
    
    @Test
    void testFileContent(){
        EnigmaSave enigmaLoader = new EnigmaSave();
        enigmaLoader.loadEnigmas();
        
        Map<String, Enigma> enigmaLibrary = new HashMap<>();
        for (Enigma e : enigmaLoader.getEnigmas()) {
            enigmaLibrary.put(e.getId(), e);
        }
        List<Room> expectedList= new ArrayList<>();
        populateList(expectedList, enigmaLibrary);

        final RoomSave storagSave= new RoomSave();
        storagSave.save(expectedList);
        storagSave.loadRooms();

        List<Room> loadedList= storagSave.getRooms();

        assertEquals(expectedList.size(), loadedList.size());
        
        if (expectedList.get(0) instanceof RoomTemplate && loadedList instanceof RoomTemplate) {
            assertEquals(((RoomTemplate) expectedList.get(0)).getId(), ((RoomTemplate) loadedList.get(0)).getId());
        }
    }
    
    private static void addEnigmaToMap(Map<Position, Enigma> map, Map<String,Enigma> library, String enigmaId, Position pos, Key key){
        Enigma base = library.get(enigmaId);

        if(base != null){
            Enigma instance = new EnigmaTemplate(
                base.getId(),
                key,
                base.getQuestion(),
                base.getOptions(),
                base.getCorrectOption()
            );
            map.put(pos, instance);
        } else {
            System.err.println("ATTENZIONE: Enigma con ID " + enigmaId + " non trovato nel dizionario!");
        }
    }

    private static void populateList(final List<Room> list, Map<String, Enigma> library){
        final Room startRoom = new RoomTemplate("start_room");
        final Room secondRoom = new RoomTemplate("room_2");
        final Room thirdRoom = new RoomTemplate("room_3");
        final Room finishRoom = new RoomTemplate("room_finish");

        //configurazione stanza 4
        finishRoom.setLayout(15, new HashMap<Position, Door>(), new HashMap<Position, Enigma>());

        //configurazione stanza 3
        final Door door3_to_end = new DoorImpl("door_3_to_end", finishRoom.getId());
        
        Map<Position, Door> doors3 = new HashMap<>();
        doors3.put(new Position(5, 9), door3_to_end);

        Map<Position, Enigma> enigmas3 = new HashMap<>();

        Key keyGold = new KeyTemplate("key_gold", "golden key", door3_to_end);

        addEnigmaToMap(enigmas3, library, "inf_03", new Position(5, 7), null);
        addEnigmaToMap(enigmas3, library, "mat_03", new Position(3, 8), null);
        addEnigmaToMap(enigmas3, library, "log_02", new Position(7, 2), keyGold); 
        addEnigmaToMap(enigmas3, library, "inf_04", new Position(6, 6), null);

        thirdRoom.setLayout(10, doors3, enigmas3);

        
        //configurazione stanza 2
        final Door door2_to_3 = new DoorImpl("door2_to_3", thirdRoom.getId());

        Map<Position, Door> doors2 = new HashMap<>();
        doors2.put(new Position(7, 9), door2_to_3); 

        Map<Position, Enigma> enigmas2 = new HashMap<>();

        Key keySilver = new KeyTemplate("key_silver", "silver key", door2_to_3);
       
        addEnigmaToMap(enigmas2, library, "inf_02", new Position(5, 8), keySilver);
        addEnigmaToMap(enigmas2, library, "mat_02", new Position(7, 3), null);
        addEnigmaToMap(enigmas2, library, "log_01", new Position(1, 7), null);

        secondRoom.setLayout(10, doors2, enigmas2);

        //configurazone stanza 1
        final Door door1_to_2 = new DoorImpl("door1_to_2", secondRoom.getId());
        
        Map<Position, Door> doors1 = new HashMap<>();
        doors1.put(new Position(6, 9), door1_to_2);

        Map<Position, Enigma> enigmas1 = new HashMap<>();

        Key keyBronze = new KeyTemplate("key_bronze", "bronze key", door1_to_2);

        addEnigmaToMap(enigmas1, library, "inf_01", new Position(4, 4), null);
        addEnigmaToMap(enigmas1, library, "mat_01", new Position(1, 7), keyBronze);

        startRoom.setLayout(10, doors1, enigmas1);
        
        list.add(finishRoom);
        list.add(thirdRoom);
        list.add(secondRoom);
        list.add(startRoom);
    }
   
}
