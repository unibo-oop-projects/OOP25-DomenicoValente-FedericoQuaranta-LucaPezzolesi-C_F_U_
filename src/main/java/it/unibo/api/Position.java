package it.unibo.api;

/**
 * simple record to manage player position
 * @param x player's x
 * @param y player's y
 */
public record Position(int x, int y) implements java.io.Serializable {

    @Override 
    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

}
