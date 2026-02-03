package it.unibo.storage.enigma;

import java.util.List;

/**
 * data template for Enigmas saving on yaml
 */
public class DataForEnigmas {
    
    /**
     * The enigma id
     */
    private String id;
    
    /**
     * The question
     */
    private String question;

    /**
     * The correct option
     */
    private String correctOption;

    /**
     * The options
     */
    private List<String> options;

    /**
     * stores the id of the key contained in this enigma, to not saturate yaml file.
     * {@code empty string} if the enigma does not have a key
     */
    private String keyId;

    /** 0 args constructor */
    public DataForEnigmas() {}

    /**
     * constructor
     * @param id enigma's id
     * @param question enigma's question
     * @param correctOption enigma's correct option
     * @param options all enigma possible answers
     * @param keyId the id of the door opened by this key, assuming this enigma has a key, {@code""} otherwise
     */
    public DataForEnigmas(final String id, final String question, final String correctOption, 
            final List<String> options, final String keyId
    ) {
        this.id = id;
        this.question = question;
        this.correctOption = correctOption;
        this.options = options;
        this.keyId = keyId;
    }


//getters

    /**
     * gets this enigma's id
     * @return enigma's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * gets only the id of the door opened by this key, to not saturate yaml file.
     * @return {@code empty string} if the enigma does not have a key, {@code the door id} otherwise
     */
    public String getKeyId() {
        return this.keyId;
    }

    /**
     * gets the enigma's question
     * @return the question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * gets the enigma's possible anwers
     * @return the {@link java.util.List} of answers
     */
    public List<String> getOptions() {
        return this.options;
    }

    /**
     * gets the correct answer
     * @return the corrent answer
     */
    public String getCorrectOption() {
        return this.correctOption;
    }    

//

//setters

    /**
     * sets this enigma's id
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * sets only the id of the door opened by this key, to not saturate yaml file.
     * @param keyId the id of the door opened by this key
     */
    public void setKeyId(final String keyId) {
        this.keyId = keyId;
    }

    /**
     * sets the enigma's question
     * @param question the question
     */
    public void setQuestion(final String question) {
        this.question = question;
    }

    /**
     * sets the enigma's possible anwers
     * @param options the answers
     */
    public void setOptions(final List<String> options) {
        this.options = options;
    }

    /**
     * sets the correct answer
     * @param correctOption the correct answer
     */
    public void setCorrectOption(final String correctOption) {
        this.correctOption = correctOption;
    }    

//

}
