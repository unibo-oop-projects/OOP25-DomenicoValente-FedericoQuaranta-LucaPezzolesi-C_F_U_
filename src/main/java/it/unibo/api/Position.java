package it.unibo.api;

/**
 * simple record to manage player position
 * @param x player's x
 * @param y player's y
 */
public record Position(int x, int y) {

    @Override 
    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

}
