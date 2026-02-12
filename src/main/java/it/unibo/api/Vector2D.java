package it.unibo.api;

/**
 * a 2-dimensional vector
 */
public class Vector2D {
    
    private double x;
    private double y;

    /**
     * constructor
     * @param x x
     * @param y y
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * sum between two vectors
     * @param v the vector to sum to this vector
     * @return a new {@link Vector2D}
     */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    /**
     * multiplication between a vector and a factor
     * @param fact the factor to multiply this vector with
     * @return a new {@link Vector2D}
     */
    public Vector2D mul(double fact) {
        return new Vector2D(x * fact, this.getY() * fact);
    }

    /**
     * calculates the module (length of the vector)
     * @return the module of the vector
     */
    public double module() {
        return (Math.sqrt(x*x + y*y));
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
        return ("Vector2d: " + x + ", " + y);
    }
}
