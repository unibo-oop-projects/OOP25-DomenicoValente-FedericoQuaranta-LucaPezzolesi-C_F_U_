package it.unibo.core;

/**
 * All game settings
 */
public enum GameSettings {

    /** fps cap */
    FPS_CAP("60"),

    /** default path for  enigma yml files */
    ENIGMAS_YAML_FILES_DEFAULT_PATH("/enigmas.yml"),
    
    /** default path for room yml files */
    ROOM_YAML_FILES_DEFAULTPATH("/rooms.yml");

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
