package it.unibo.api;

/**
 * simple class that models a 2-dimensional point
 */
public class Position  implements java.io.Serializable {

    /** the x param */
    private double x;
    
    /** the y param */
    private double y;
    
    /**
     * constructor
     * @param x the x
     * @param y the y
     */
    public Position(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * sums this position with the {@link Vector2D} specified
     * @param v the vector to sum with
     * @return the new Position2d
     */
    public Position sum(final Vector2D v) {
        return new Position(x + v.getX(), y + v.getY());
    }

    /**
     * subtracts this position with the {@link Vector2D} specified
     * @param v the vector to subtract
     * @return the new Position2d
     */
    public Position sub(final Vector2D v) {
        return new Position(x - v.getX(), y - v.getY());
    }

    /**
     * gets the x param
     * @return {@code x}
     */
    public double getX() {
        return this.x;
    }

     /**
     * gets the y param
     * @return {@code y}
     */
    public double getY() {
        return this.y;
    }

    @Override 
    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
    
}
