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
        RoomSave storagSave = new RoomSave();
        storagSave.loadRooms();

        List<Room> rooms = storagSave.getRooms();

        if(rooms.isEmpty()){
            System.err.println("NO SUCH FILE YAML");
        }

        //config
        Room startRoom = rooms.get(0);
        Player player = new PlayerImpl(new Position(1, 1));
        RoomManager roomManager = new RoomManagerImpl(player);

        roomManager.enterNextRoom(startRoom);
        Inventory.setMaxSize(3);

        GameFrame mainWindow = new GameFrame(startRoom, player.getPosition());
        GameEngine mainEngine = new GameEngine(mainWindow, roomManager);

        mainWindow.setController(mainEngine);
        mainEngine.run();
    }
}
