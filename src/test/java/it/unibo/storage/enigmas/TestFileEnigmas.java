package it.unibo.storage.enigmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.storage.enigma.*;

public class TestFileEnigmas {

    private EnigmaSaveWrite fileWriter;
    private EnigmaSaveLoad fileReader;

    @BeforeEach
    void initializeTest() {
        this.fileReader = new EnigmaSaveLoad();
        this.fileWriter = new EnigmaSaveWrite();
    }

    @Test
    void testWritingOnFile() {
        this.fileWriter.addEnigma("test enigma 1", "test question 1", "correct answ",
            List.of("wrong answ 1", "wrong answ 2", "correct answ") , false);

        this.fileWriter.writeOnFile();

        try {
            Thread.sleep(500);
        } catch (final Exception excep) {}

        this.fileReader.loadEnigmas();
        final List<Enigma> enigmas = this.fileReader.getEnigmas();

        assertEquals(1, enigmas.size());

        assertEquals(enigmas.get(0), new EnigmaTemplate("test enigma 1", false,  "test question 1",
            List.of("wrong answ 1", "wrong answ 2", "correct answ"), "correct answ"));

        this.fileWriter.eraseSave();
    }

    @Test
    void testExceptionsThrowing() {
        assertThrows(IllegalArgumentException.class, () -> this.fileWriter.addEnigma("test enigma 1", "test question 1", "correct answ",
            List.of("wrong answ 1", "wrong answ 2", "wrong String") , false));
    }

}
