package it.unibo.api.enigmas;

import java.util.List;
import java.util.Optional;

import it.unibo.api.key.Key;

/**
 * a basic enigm
 */
public interface Enigma {

    /**
     * gets the id of this enigm
     * @return the id
     */
    String getId();

    /**
     * tells if the enigm is completed
     * @return {@code true} if is completed, {@code false} otherwise
     */
    boolean isCompleted();

    /**
     * tells if this enigm has the key
     * @return  {@code true} if contains the key, {@code false} otherwise
     */
    boolean isKeyInside();

    /**
     * gets the enigm question 
     * @return the question text
     */
    String getQuestion();

    /**
     * gets the list of the options
     * @return the list of options
     */
    List<String> getOptions();

    /**
     * tries to solve the question
     * @param answer the answer 
     * @return the result
     */
    boolean solve(String answer);

    /**
     * gets the correct answer
     * @return the correct answer
     */
    String getCorrectOption();

    /**
     * gets the contained in this enigma
     * @return an {@code Optional} of {@link Key} if this enigma has a key, an {@code empty Optional} otherwise
     */
    Optional<Key> getKey();
}
