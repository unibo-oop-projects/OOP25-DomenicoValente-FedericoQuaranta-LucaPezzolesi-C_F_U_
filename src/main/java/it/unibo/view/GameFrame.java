package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unibo.api.Position;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.key.Key;
import it.unibo.api.rooms.Room;
import it.unibo.impl.Inventory;
import it.unibo.input.Controller;
import it.unibo.input.StopMovement;
import it.unibo.storage.roommanager.RoomManagerStorage;

/**
 * Main window of the game application.
 * Extends {@link JFrame} and contains all GUI components of the game.
 */
public class GameFrame extends JFrame implements View {

    /**
     * the gamepanel
     */
    private GamePanel gamePanel;

    /**
     * the gamepanel
     */
    private InventoryPanel inventoryPanel;

    /**
     * the controller
     */
    private Controller controller;

    /**
     * flag for endgame
     */
    private boolean endGameShown;

    /**
     * constructor
     * @param room the room shown
     * @param playerPosition the positon of the player
     */
    public GameFrame(Room room, Position playerPosition) {
        setTitle("C. F. U.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);            
        setLocationRelativeTo(null);    
        setResizable(false);  
        setLayout(new BorderLayout());         

        endGameShown = false;
        this.gamePanel = new GamePanel(room, playerPosition, controller);
        this.gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel, BorderLayout.CENTER);

        this.inventoryPanel = new InventoryPanel();
        inventoryPanel.setPreferredSize(new Dimension(200, 600));
        inventoryPanel.setBackground(Color.DARK_GRAY);
        add(inventoryPanel, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void updateView(Room room, Position position, Optional<Enigma> enigma){
        if(room.getId().equals("room_finish") && !endGameShown) { 
            endGameShown = true;
            JOptionPane.showMessageDialog(null, "end game");
            RoomManagerStorage.deleteSave();
            dispose();
            System.exit(1);
            return;
        }
        gamePanel.setRoom(room);
        gamePanel.setPlayerPosition(position);
    	gamePanel.repaint();
        inventoryPanel.repaint();
        if(enigma.isPresent()) {
            Enigma realEnigma = enigma.get();
            controller.catchCommand(new StopMovement());
            if(!realEnigma.isCompleted()) {
                Object[] options = realEnigma.getOptions().toArray();
                String answer = (String) JOptionPane.showInputDialog(
                    this,
                    realEnigma.getQuestion(),
                    "enigma",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );
                if(answer == null) {
                    JOptionPane.showMessageDialog(null, "no answer");
                    controller.catchCommand(new StopMovement());
                } else {
                    boolean solve = realEnigma.solve(answer);
                    if (solve) {
                        Optional<Key> key = realEnigma.getKey();
                        if(key.isPresent()) {
                            Key realKey = key.get();
                            Inventory.addKey(realKey);
                            JOptionPane.showMessageDialog(null, "true, key found");
                            if (room.getDoorGrid() != null) {
                                for (Position pos : room.getDoorGrid().keySet()) {
                                    room.getDoor(pos).setOpen(true);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "true, there is no key");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "false, try again");
                    }
                    
                }
            }
            
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
        this.gamePanel.setController(controller);
    }
    
}
