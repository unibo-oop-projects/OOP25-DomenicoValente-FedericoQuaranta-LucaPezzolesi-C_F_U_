package it.unibo.view;

import java.util.List;

import it.unibo.api.Position;
import it.unibo.api.player.Player;
import it.unibo.api.rooms.Room;
import it.unibo.api.rooms.RoomManager;
import it.unibo.core.GameEngine;
import it.unibo.impl.Inventory;
import it.unibo.impl.PlayerImpl;
import it.unibo.impl.RoomManagerImpl;
import it.unibo.storage.rooms.RoomSave;

public class GameLauncher {
    
    public static void main(String[] args){
        //load world
        final RoomSave storagSave = new RoomSave();
        storagSave.loadRooms();

        final List<Room> rooms = storagSave.getRooms();

        if(rooms.isEmpty()){
            System.err.println("NO SUCH FILE YAML");
        }

        //config
        final Room startRoom = rooms.get(1);
        final Player player = new PlayerImpl(new Position(1, 1));
        final RoomManager roomManager = new RoomManagerImpl(player);

        roomManager.enterNextRoom(startRoom);
        Inventory.setMaxSize(3);

        final GameFrame mainWindow = new GameFrame(startRoom, player.getPosition());
        final GameEngine mainEngine = new GameEngine(mainWindow, roomManager, rooms);

        mainWindow.setController(mainEngine);
        mainEngine.run();
    }
}
