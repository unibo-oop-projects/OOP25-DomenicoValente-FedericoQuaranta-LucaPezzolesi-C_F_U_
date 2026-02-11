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
import it.unibo.impl.DoorImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.KeyTemplate;
import it.unibo.impl.templates.RoomTemplate;
import it.unibo.storage.rooms.RoomSave;

public class RoomsGeneration {
    public static void main(String[] args) {
             
        List<Room> roomsTosave = new ArrayList<>();

        populateList(roomsTosave);

        final RoomSave storagSave = new RoomSave();
        storagSave.saveRooms(roomsTosave);
        
        System.out.println("Room saves: " + roomsTosave.size());
    }

    private static void populateList(final List<Room> list){
        final Room startRoom = new RoomTemplate("start_room");
        final Room secondRoom = new RoomTemplate("room_2");
        final Room thirdRoom = new RoomTemplate("room_3");

        //configurazione stanza 3
        final Door door3_to_2 = new DoorImpl("door_3_to_2", secondRoom);
        final Door door3_to_end = new DoorImpl("door_3_to_2", null);
        
        Map<Position, Door> doors3 = new HashMap<>();
        doors3.put(new Position(5, 14), door3_to_2); 
        doors3.put(new Position(13, 0), door3_to_end);

        Map<Position, Enigma> enigmas3 = new HashMap<>();

        enigmas3.put(new Position(5, 10),new EnigmaTemplate(
            "inf_03", 
            null, 
            "Quale di questi tipi di memoria è volatile?", 
            List.of("RAM", "ROM", "SSD", "HDD"),
            "RAM"
        ));
        enigmas3.put(new Position(3, 8), new EnigmaTemplate(
            "mat_03", 
            null, 
            "Quanto fa 2^5?", 
            List.of("10", "25", "32", "64"), 
            "32"
        ));
        enigmas3.put(new Position(9, 2), new EnigmaTemplate(
            "log_02", 
            new KeyTemplate(
                "1_to_2",
                "Chiave di bronzo",
                door3_to_end
            ), 
            "se durante una gara superi il secondo, in che posizione ti trovi?", 
            List.of("Prima", "Seconda", "Terza", "Penultima"), 
            "Seconda"
        ));
        enigmas3.put(new Position(13, 13), new EnigmaTemplate(
            "inf_04", 
            null, 
            "Qual è il metodo che indica punto di ingresso in un'applicazione Java?", 
            List.of("start()", "run()", "main()", "init()"), 
            "main()"
        ));

        thirdRoom.setLayout(15, doors3, enigmas3);

        
        //configurazione stanza 2
        final Door door2_to_1 = new DoorImpl("door2_to_1", startRoom);
        final Door door2_to_3 = new DoorImpl("door2_to_3", thirdRoom);
        
        Map<Position, Door> doors2 = new HashMap<>();
        doors2.put(new Position(10, 14), door2_to_3); 
        doors2.put(new Position(3, 0), door2_to_1);

        Map<Position, Enigma> enigmas2 = new HashMap<>();

        enigmas2.put(new Position(5, 10),new EnigmaTemplate(
            "inf_02", 
            new KeyTemplate(
                "2_to_3",
                "Chiave d'argento",
                door2_to_3
            ),  
            "Quale struttura dati segue il principio LIFO?", 
            List.of("Coda", "Lista", "Pila", "Albero"), 
            "Pila"
        ));
        enigmas2.put(new Position(11, 3), new EnigmaTemplate(
            "mat_02", 
            null, 
            "Quale tra i seguenti numeri è un numero primo?", 
            List.of("9", "15", "17", "21"), 
            "17"
        ));
        enigmas2.put(new Position(1, 11), new EnigmaTemplate(
            "log_01", 
            null, 
            "Il padre di Maria ha 5 figlie: Nana, Nene, Nini, Nono. Come si chiama la quinta?", 
            List.of("Nunu", "Nina", "Anna", "Maria"), 
            "Maria"
        ));

        secondRoom.setLayout(15, doors2, enigmas2);

        //configurazone stanza 1
        final Door door1_to_2 = new DoorImpl("door1_to_2", secondRoom);
        
        Map<Position, Door> doors1 = new HashMap<>();
        doors1.put(new Position(10, 14), door1_to_2);

        Map<Position, Enigma> enigmas1 = new HashMap<>();

        enigmas1.put(new Position(4, 4),new EnigmaTemplate(
            "inf_01", 
            null, 
            "A quanto equivale 1010 in base decimale?", 
            List.of("10", "12", "101", "20"), 
            "10"
        ));
        enigmas1.put(new Position(1, 11), new EnigmaTemplate(
            "mat_01", 
            new KeyTemplate(
                "3_to_end",
                "Chiave d'oro",
                door1_to_2
            ), 
            "Quanto fa 4 + 4 * 4?", 
            List.of("32", "20", "16", "64"), 
            "20"
        ));

        startRoom.setLayout(15, doors1, enigmas1);

        list.add(startRoom);
        list.add(secondRoom);
        list.add(thirdRoom);
    }

    @Test
    void testFileContent(){
        List<Room> expectedList= new ArrayList<>();
        populateList(expectedList);

        final RoomSave storagSave= new RoomSave();
        storagSave.saveRooms(expectedList);
        storagSave.loadRooms();

        List<Room> loadedList= storagSave.getRooms();

        assertEquals(expectedList.size(), loadedList.size());
        
        if (expectedList.get(0) instanceof RoomTemplate && loadedList instanceof RoomTemplate) {
            assertEquals(((RoomTemplate) expectedList.get(0)).getId(), ((RoomTemplate) loadedList.get(0)).getId());
        }
    }
   
}
