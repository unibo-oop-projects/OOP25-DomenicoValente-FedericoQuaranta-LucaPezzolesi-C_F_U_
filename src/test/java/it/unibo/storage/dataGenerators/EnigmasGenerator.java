package it.unibo.storage.dataGenerators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.storage.enigma.EnigmaSave;

public class EnigmasGenerator {

    @Test
    void testFileContent() {
        final List<Enigma> checkList = new ArrayList<>();
        populateList(checkList);
        final EnigmaSave storage = new EnigmaSave();

        storage.saveEnigmas(checkList);

        try {
            Thread.sleep(300);
        } catch (final Exception e) {}
        storage.loadEnigmas();

        assertEquals(checkList, storage.getEnigmas());

        storage.deleteFile();
    }

    private static void populateList(final List<Enigma> list) {
        
        //----informatica----//

        list.add(new EnigmaTemplate(
            "inf_01", 
            null, 
            "1010 in decimal base equals to:", 
            List.of("10", "12", "101", "20"), 
            "10"
        ));
        
        list.add(new EnigmaTemplate(
            "inf_02", 
            null, 
            "Which of these data structures follows the LIFO principle?", 
            List.of("Queue", "List", "Stack", "Tree"), 
            "Stack"
        ));

        list.add(new EnigmaTemplate(
            "inf_03", 
            null, 
            "Which of this memory types is volatile?", 
            List.of("RAM", "ROM", "SSD", "HDD"),
            "RAM"
        ));

        list.add(new EnigmaTemplate(
            "inf_04", 
            null, 
            "What is the method that indicates the entry point in a Java application?", 
            List.of("start()", "run()", "main()", "init()"), 
            "main()"
        ));


        //----matematica----//

        list.add(new EnigmaTemplate(
            "mat_01", 
            null, 
            "What is 4 + 4 * 4?", 
            List.of("32", "20", "16", "64"), 
            "20"
        ));

        list.add(new EnigmaTemplate(
            "mat_02", 
            null, 
            "Which of this numbers is a prime number?", 
            List.of("9", "15", "17", "21"), 
            "17"
        ));

        list.add(new EnigmaTemplate(
            "mat_03", 
            null, 
            "WHat is 2^5?", 
            List.of("10", "25", "32", "64"), 
            "32"
        ));


        //----logica----//
    
        list.add(new EnigmaTemplate(
            "log_01", 
            null, 
            "Maria's father has five daughters: Nana, Nene, Nini, and Nina. What's the fifth one's name?", 
            List.of("Nunu", "Nina", "Anna", "Maria"), 
            "Maria"
        ));

        list.add(new EnigmaTemplate(
            "log_02", 
            null, 
            "If you overtake the person in the second place in a race, what position are you in?", 
            List.of("First", "Second", "Third", "Last"), 
            "Second"
        ));
    }
}
