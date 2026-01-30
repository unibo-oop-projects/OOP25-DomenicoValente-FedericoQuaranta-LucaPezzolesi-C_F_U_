package it.unibo.storage.dataGenerators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.KeyTemplate;
import it.unibo.impl.templates.RoomTemplate;
import it.unibo.storage.enigma.EnigmaSave;

public class EnigmasGenerator {

    public static void main(String[] args) {
        
        List<Enigma> enigmasToSave = new ArrayList<>();
        populateList(enigmasToSave);
        
        final EnigmaSave storage = new EnigmaSave();
        storage.saveEnigmas(enigmasToSave);
    }


    @Test
    void testFileContent() {
        List<Enigma> checkList = new ArrayList<>();
        populateList(checkList);
        final EnigmaSave storage = new EnigmaSave();

        storage.loadEnigmas();
        assertEquals(checkList.stream().map(e -> e.getId()).toList(), storage.getEnigmas().stream().map(e -> e.getId()).toList());
    }

    private static void populateList(final List<Enigma> list) {
        it.unibo.impl.templates.KeyTemplate k = new KeyTemplate("key1", "door key test", new DoorImpl("door test", new RoomTemplate("room id"))); 
        list.add(new EnigmaTemplate("testId", k, "qestion1", List.of("opt1", "opt2"), "opt1"));
    }

}
