package it.unibo.api;

/**
 * a generic Pair
 * @param <X> the type of the first element
 * @param <Y> the type of the second element
 */
public class Pair<X, Y> implements java.io.Serializable {

    private final X x;
    private final Y y;

    /**
     * constructor
     * @param x the first element
     * @param y the second element
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * gets the first element
     * @return the first element
     */
    public X getX() {
		return x;
	}

    /**
     * gets the second element
     * @return the second element
     */
	public Y getY() {
		return y;
	}


}
