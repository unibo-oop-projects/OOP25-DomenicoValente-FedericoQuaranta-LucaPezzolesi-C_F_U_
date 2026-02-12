package it.unibo.storage.dataGenerators;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.junit.jupiter.api.Test;

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
    public static void main(String[] args) {
        EnigmaSave enigmaLoader = new EnigmaSave();
        enigmaLoader.loadEnigmas();
        
        Map<String, Enigma> enigmaLibrary = new HashMap<>();
        for (Enigma e : enigmaLoader.getEnigmas()) {
            enigmaLibrary.put(e.getId(), e);
        }

        List<Room> roomsTosave = new ArrayList<>();

        populateList(roomsTosave, enigmaLibrary);

        final RoomSave storagSave = new RoomSave();
        storagSave.save(roomsTosave);
        
        System.out.println("Room saves: " + roomsTosave.size());
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
        final Door door3_to_2 = new DoorImpl("door_3_to_2", secondRoom);
        final Door door3_to_end = new DoorImpl("door_3_to_end", finishRoom);
        
        Map<Position, Door> doors3 = new HashMap<>();
        doors3.put(new Position(5, 14), door3_to_2); 
        doors3.put(new Position(13, 0), door3_to_end);

        Map<Position, Enigma> enigmas3 = new HashMap<>();

        Key keyGold = new KeyTemplate("key_gold", "Chiave d'oro", door3_to_end);

        addEnigmaToMap(enigmas3, library, "inf_03", new Position(5, 10), null);
        addEnigmaToMap(enigmas3, library, "mat_03", new Position(3, 8), null);
        addEnigmaToMap(enigmas3, library, "log_02", new Position(9, 2), keyGold); 
        addEnigmaToMap(enigmas3, library, "inf_04", new Position(13, 13), null);

        thirdRoom.setLayout(15, doors3, enigmas3);

        
        //configurazione stanza 2
        final Door door2_to_1 = new DoorImpl("door2_to_1", startRoom);
        final Door door2_to_3 = new DoorImpl("door2_to_3", thirdRoom);
        
        Map<Position, Door> doors2 = new HashMap<>();
        doors2.put(new Position(10, 14), door2_to_3); 
        doors2.put(new Position(3, 0), door2_to_1);

        Map<Position, Enigma> enigmas2 = new HashMap<>();

        Key keySilver = new KeyTemplate("key_silver", "Chiave d'argento", door2_to_3);
       
        addEnigmaToMap(enigmas2, library, "inf_02", new Position(5, 10), keySilver);
        addEnigmaToMap(enigmas2, library, "mat_02", new Position(11, 3), null);
        addEnigmaToMap(enigmas2, library, "log_01", new Position(1, 11), null);

        secondRoom.setLayout(15, doors2, enigmas2);

        //configurazone stanza 1
        final Door door1_to_2 = new DoorImpl("door1_to_2", secondRoom);
        
        Map<Position, Door> doors1 = new HashMap<>();
        doors1.put(new Position(10, 14), door1_to_2);

        Map<Position, Enigma> enigmas1 = new HashMap<>();

        Key keyBronze = new KeyTemplate("key_bronze", "Chiave di bronzo", door1_to_2);

        addEnigmaToMap(enigmas1, library, "inf_01", new Position(4, 4), null);
        addEnigmaToMap(enigmas1, library, "mat_01", new Position(1, 11), keyBronze);

        startRoom.setLayout(15, doors1, enigmas1);
        
        list.add(finishRoom);
        list.add(thirdRoom);
        list.add(secondRoom);
        list.add(startRoom);
    }

    /*@Test
    void testFileContent(){
        List<Room> expectedList= new ArrayList<>();
        populateList(expectedList, );

        final RoomSave storagSave= new RoomSave();
        storagSave.save(expectedList);
        storagSave.loadRooms();

        List<Room> loadedList= storagSave.getRooms();

        assertEquals(expectedList.size(), loadedList.size());
        
        if (expectedList.get(0) instanceof RoomTemplate && loadedList instanceof RoomTemplate) {
            assertEquals(((RoomTemplate) expectedList.get(0)).getId(), ((RoomTemplate) loadedList.get(0)).getId());
        }
    }*/
   
}
