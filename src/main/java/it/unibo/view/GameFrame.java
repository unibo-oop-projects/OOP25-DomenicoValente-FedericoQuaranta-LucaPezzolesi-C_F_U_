package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.api.Position;
import it.unibo.api.rooms.Room;
import it.unibo.input.Controller;

public class GameFrame extends JFrame{

    private GamePanel gamePanel;
    private Controller controller;

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

    public void render(){
    	gamePanel.repaint();
    }
    
}
