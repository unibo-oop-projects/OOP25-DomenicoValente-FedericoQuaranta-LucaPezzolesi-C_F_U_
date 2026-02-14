package it.unibo.core;

/**
 * All game settings
 */
public enum GameSettings {

    /** fps cap */
    FPS_CAP("60"),

    /** default path for  enigma yml files */
<<<<<<< HEAD
    ENIGMAS_YAML_FILE_NAME("enigmas.yml"),
=======
    ENIGMAS_YAML_FILES_DEFAULT_PATH("src/main/resources/enigmas.yml"),
>>>>>>> 8312832b2c20b090a9e021d6cf0a3d3ae7d74f6d
    
    /** default path for room yml files */
    ROOM_YAML_FILES_DEFAULTPATH("./rooms.yml");

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
