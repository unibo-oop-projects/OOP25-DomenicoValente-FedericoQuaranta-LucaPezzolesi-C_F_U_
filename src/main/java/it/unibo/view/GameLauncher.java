package it.unibo.view;

import java.io.IOException;
import java.util.List;

import it.unibo.api.Position;
import it.unibo.api.player.Player;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomManager;
import it.unibo.core.GameEngine;
import it.unibo.impl.Inventory;
import it.unibo.impl.PlayerImpl;
import it.unibo.impl.RoomManagerImpl;
import it.unibo.storage.roommanager.RoomManagerStorage;
import it.unibo.storage.rooms.RoomSave;

/**
 * the launcher of the game
 */
public class GameLauncher {

    /**
     * 0 args constructor
     */
    public GameLauncher(){}
    
    /**
     * the main to start the game
     * @param args unused
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //load world
        final RoomSave storagSave = new RoomSave();
        storagSave.loadRooms();

        Inventory.setMaxSize(3);

        final List<Room> rooms = storagSave.getRooms();
        final RoomManager roomManager;
        final Room startRoom;
        final Player player;
        RoomManager loaded = RoomManagerStorage.load();

        if(loaded != null) {
            roomManager = loaded;
            startRoom = loaded.getCurrentRoom();
            player = new PlayerImpl(loaded.getCurrentPosition());
        } else {
            startRoom = rooms.get(1);
            player = new PlayerImpl(new Position(1, 1));
            roomManager = new RoomManagerImpl(player);
            roomManager.enterNextRoom(startRoom); 
        }

        final GameFrame mainWindow = new GameFrame(startRoom, player.getPosition());
        final GameEngine mainEngine = new GameEngine(mainWindow, roomManager, rooms);

        mainWindow.setController(mainEngine);
        mainEngine.run();
    }
}
