package it.unibo.storage.roommanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.unibo.api.rooms.RoomManager;

/**
 * {@link RoomManager} storage system
 */
public class RoomManagerStorage {

    private static final String FILENAME = "./src/main/resources/roommanager.save.dat";

    private RoomManagerStorage() {}

    /**
     * Saves a {@link RoomManager} to a file.
     *
     * @param model the object to save
     * @throws IOException if an I/O error occurs during saving
     */
    public static void save(RoomManager model) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(model);
        }
    }

    /**
     * Loads a {@link RoomManager} from a file.
     *
     * @return a {@link RoomManager}, or null if the file does not exist or is empty
     * @throws IOException if an I/O error occurs during loading
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static RoomManager load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (RoomManager) in.readObject();
        }
    }
}
