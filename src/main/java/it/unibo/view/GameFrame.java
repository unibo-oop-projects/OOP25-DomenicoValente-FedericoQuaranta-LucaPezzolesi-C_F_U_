package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.api.Position;
import it.unibo.api.rooms.Room;
import it.unibo.input.Controller;

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
     * the controller
     */
    private Controller controller;

    /**
     * constructor
     * @param room the room shown
     * @param playerPosition the positon of the player
     * @param controller the controller
     */
    public GameFrame(Room room, Position playerPosition, Controller controller) {
        setTitle("C. F. U.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);            
        setLocationRelativeTo(null);    
        setResizable(false);  
        setLayout(new BorderLayout());         

        this.controller = controller;

        this.gamePanel = new GamePanel(room, playerPosition, controller);
        this.gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel, BorderLayout.CENTER);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setPreferredSize(new Dimension(200, 600));
        inventoryPanel.setBackground(Color.DARK_GRAY); // solo per vedere il pannello
        add(inventoryPanel, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void updateView(Room room, Position position){
    	gamePanel.repaint();
    }
    
}
