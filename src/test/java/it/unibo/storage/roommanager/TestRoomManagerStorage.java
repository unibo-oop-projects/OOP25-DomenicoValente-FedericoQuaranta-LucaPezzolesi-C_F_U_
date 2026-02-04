package it.unibo.storage.roommanager;

import it.unibo.impl.Inventory;
import it.unibo.impl.PlayerImpl;
import it.unibo.impl.RoomManagerImpl;
import it.unibo.impl.templates.KeyTemplate;
import it.unibo.impl.templates.RoomTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.Position;
import it.unibo.api.rooms.RoomManager;

public class TestRoomManagerStorage {

    private RoomManager model;

    @BeforeEach
    void initializeTest() {
        model = new RoomManagerImpl(new PlayerImpl(new Position(0, 0)));
        model.enterNextRoom(new RoomTemplate("id"));
        Inventory.addKey(new KeyTemplate());
        Inventory.addKey(new KeyTemplate());
    }

    @AfterEach
    void cleanup() {
        new File("./src/main/resources/roommanager.save.dat").delete();
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        RoomManagerStorage.save(model);
        File file = new File("./src/main/resources/roommanager.save.dat");
        assertTrue(file.exists());
        RoomManager loaded = RoomManagerStorage.load();
        assertNotNull(loaded);
        assertEquals(model.getCurrentPosition(), loaded.getCurrentPosition());
        assertEquals(Inventory.getKeys().size(), 2);
    }

    @Test
    void testLoadWithoutFile() throws IOException, ClassNotFoundException {
        assertThrows(FileNotFoundException.class , () -> RoomManagerStorage.load());
    }
}
