package it.unibo.core;

/**
 * All game settings
 */
public enum GameSettings {

    /** fps cap */
    FPS_CAP("60");

    private final String value;

    /** 
     * constructor
     */
    GameSettings(final String value) {
        this.value = value;
    }

    /**
     * gets the value as String
     * @return {@code value} as String
     */
    public String getValue() {
        return this.value;
    }

    /**
    * gets the value as int
    * @return {@code value} as int
    */ 
    public int getValueAsInteger() {
        return Integer.valueOf(this.value);
    }
}
