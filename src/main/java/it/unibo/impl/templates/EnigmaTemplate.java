package it.unibo.impl.templates;

import java.util.List;
import java.util.Objects;

import it.unibo.api.enigmas.Enigma;

/**
 * an implementation of {@link Enigma}
 */
public class EnigmaTemplate implements Enigma, java.io.Serializable {

    private String id;
    private boolean completed;
    private boolean keyInside;

    private String question;
    private String correctOption;
    private List<String> options;

    /**
     * 0 args constructor for snakeYaml
     */
    public EnigmaTemplate() {}

    /**
     * constructor
     * @param id enigm's id
     * @param keyInside if this enigm has to return a key when completed
     * @param question the question
     * @param options the answers
     * @param correctOption the correct answer
     */
    public EnigmaTemplate(final String id, final boolean keyInside, 
        final String question, final List<String> options, final String correctOption
    ) {
        this.id = id;
        this.completed = false;
        this.keyInside = keyInside;

        if(! options.contains(correctOption)) {
            throw new IllegalArgumentException("no such option in the list");
        }
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

//getters

    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public boolean isCompleted() {
        return this.completed;
    }
    @Override
    public boolean isKeyInside() {
        return this.keyInside;
    }
    @Override
    public String getQuestion() {
        return this.question;
    }
    @Override
    public List<String> getOptions() {
        return this.options;
    }
    @Override
    public boolean solve(String answer) {
        if(answer.equals(this.correctOption)) {
            return true;
        }
        return false;
    }
    @Override
    public String getCorrectOption() {
        return this.correctOption;
    }

//

//setters

    /**
     * sets the enigma's id
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * sets if this enigma has to drop a key when completed
     * @param hasTheKey {@code true} if this enigma has a key
     */
    public void setKeyInside(final boolean hasTheKey) {
        this.keyInside = hasTheKey;
    }

    /**
     * sets this enigma's question
     * @param question the question
     */
    public void setQuestion(final String question) {
        this.question = question;
    }

    /**
     * sets the answers
     * @param options the answers
     */
    public void setOptions(final List<String> options) {
        this.options = options;
    }

    /**
     * sets the correct option
     * @param correctOption the correct answer
     */
    public void setCorrectOption(final String correctOption) {
        this.correctOption = correctOption;
    }

//

    /**
     * necessary in order to tell the tests to not check for memory addresses (for assertEquals)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EnigmaTemplate)) return false;
        EnigmaTemplate that = (EnigmaTemplate) obj;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question);
    }

}
