package it.unibo.storage.dataGenerators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.templates.EnigmaTemplate;
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

        assertEquals(checkList, storage.getEnigmas());
    }

    private static void populateList(final List<Enigma> list) {
        
        //----informatica----//

        list.add(new EnigmaTemplate(
            "inf_01", 
            null, 
            "A quanto equivale 1010 in base decimale?", 
            List.of("10", "12", "101", "20"), 
            "10"
        ));
        
        list.add(new EnigmaTemplate(
            "inf_02", 
            null, 
            "Quale struttura dati segue il principio LIFO?", 
            List.of("Coda", "Lista", "Pila", "Albero"), 
            "Pila"
        ));

        list.add(new EnigmaTemplate(
            "inf_03", 
            null, 
            "Quale di questi tipi di memoria è volatile?", 
            List.of("RAM", "ROM", "SSD", "HDD"),
            "RAM"
        ));

        list.add(new EnigmaTemplate(
            "inf_04", 
            null, 
            "Qual è il metodo che indica punto di ingresso in un'applicazione Java?", 
            List.of("start()", "run()", "main()", "init()"), 
            "main()"
        ));


        //----matematica----//

        list.add(new EnigmaTemplate(
            "mat_01", 
            null, 
            "Quanto fa 4 + 4 * 4?", 
            List.of("32", "20", "16", "64"), 
            "20"
        ));

        list.add(new EnigmaTemplate(
            "mat_02", 
            null, 
            "Quale tra i seguenti numeri è un numero primo?", 
            List.of("9", "15", "17", "21"), 
            "17"
        ));

        list.add(new EnigmaTemplate(
            "mat_03", 
            null, 
            "Quanto fa 2^5?", 
            List.of("10", "25", "32", "64"), 
            "32"
        ));


        //----logica----//
    
        list.add(new EnigmaTemplate(
            "log_01", 
            null, 
            "Il padre di Maria ha 5 figlie: Nana, Nene, Nini, Nono. Come si chiama la quinta?", 
            List.of("Nunu", "Nina", "Anna", "Maria"), 
            "Maria"
        ));

        list.add(new EnigmaTemplate(
            "log_02", 
            null, 
            "se durante una gara superi il secondo, in che posizione ti trovi?", 
            List.of("Prima", "Seconda", "Terza", "Penultima"), 
            "Seconda"
        ));
    }
}
