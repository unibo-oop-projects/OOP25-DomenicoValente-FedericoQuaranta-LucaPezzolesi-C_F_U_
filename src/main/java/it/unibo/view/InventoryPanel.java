package it.unibo.view;

import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import it.unibo.impl.Inventory;

/**
 * Inventory panel that displays the inventory.
 * Extends {@link JPanel}.
 */
public class InventoryPanel extends JPanel {

    /**
     * the images
     */
    private BufferedImage keyImage;

    /**
     * constructor
     */
    public InventoryPanel() {
        try {
            keyImage = ImageIO.read(getClass().getResource("/key.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int elem = Inventory.getActualSize();

        double cellWidth = (double) getWidth();
        double cellHeight = (double) getHeight() / Inventory.getMaxSize();

        for(int i=0; i<elem; i++){
            g.drawImage(keyImage, (int) (0*cellWidth), (int) (i*cellHeight), (int) cellWidth, (int) cellHeight, null);
        }
    }

}
