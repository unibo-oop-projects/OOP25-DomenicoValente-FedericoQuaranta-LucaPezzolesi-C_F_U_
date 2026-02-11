package it.unibo.view;

import java.io.IOException;


import javax.swing.*;

import it.unibo.api.Position;
import it.unibo.api.rooms.Room;
import it.unibo.input.Controller;
import it.unibo.input.MoveDown;
import it.unibo.input.MoveLeft;
import it.unibo.input.MoveRight;
import it.unibo.input.MoveUp;
import it.unibo.input.StopMovement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Game panel that displays the game world.
 * Extends {@link JPanel}.
 */
public class GamePanel extends JPanel implements KeyListener {


    /**
     * the controller
     */
    private Controller controller;

    /**
     * a room
     */
    private Room room;    

    /**
     * the player positon
     */
    private Position playerPosition; 

    /**
     * the images
     */
    private BufferedImage freeImage, obstacleImage, wallImage, doorImage, enigmaImage, playerImage;

    /**
     * constructor
     * @param room the room shown
     * @param playerPosition the positon of the player
     * @param controller the controller
     */
    public GamePanel(Room room, Position playerPosition, Controller controller) {
        this.controller = controller;
        this.room = room;
        this.playerPosition = playerPosition;
        this.setFocusable(true);
        this.addKeyListener(this);
        try {
            freeImage = ImageIO.read(getClass().getResource("/free.png"));
            obstacleImage = ImageIO.read(getClass().getResource("/obstacle.png"));
            wallImage = ImageIO.read(getClass().getResource("/wall.png"));
            doorImage = ImageIO.read(getClass().getResource("/door.png"));
            enigmaImage = ImageIO.read(getClass().getResource("/enigma.png"));
            playerImage = ImageIO.read(getClass().getResource("/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * sets the player position
     * @param playerPosition the positon of the player
     */
    public void setPlayerPosition(Position playerPosition) {
        this.playerPosition = playerPosition;
        repaint();
    }

    /**
     * sets the room
     * @param room the room
     */
    public void setRoom(Room room){
        this.room = room;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (room == null) return;

        int x = room.getSize();
        int y = room.getSize();

        double cellWidth = (double) getWidth() / x;
        double cellHeight = (double) getHeight() / y;

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                BufferedImage tile;
                switch(room.getCellContent(new Position(i, j))){
                    case OBSTACLE: tile = obstacleImage; break;
                    case WALL: tile = wallImage; break;
                    case DOOR: tile = doorImage; break;
                    case ENIGMA: tile = enigmaImage; break; 
                    default: tile = freeImage; break;
                }
                g.drawImage(tile, (int) (i*cellWidth), (int) (j*cellHeight), (int) cellWidth, (int) cellHeight, null);
            }
        }

        g.drawImage(playerImage, (int) (playerPosition.getX()*cellWidth), (int) (playerPosition.getY()*cellHeight), (int) cellWidth, (int) cellHeight, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38){
            controller.catchCommand(new MoveUp());
        } else if (e.getKeyCode() == 40){
            controller.catchCommand(new MoveDown());
        } else if (e.getKeyCode() == 39){
            controller.catchCommand(new MoveRight());	     		
        } else if (e.getKeyCode() == 37){
            controller.catchCommand(new MoveLeft());	     		
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        controller.catchCommand(new StopMovement());
    }

    /**
     * set the controller
     * @param controller the controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
        
}
