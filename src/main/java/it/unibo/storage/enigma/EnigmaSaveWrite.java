package it.unibo.storage.enigma;

import org.yaml.snakeyaml.Yaml;

import it.unibo.api.enigmas.Enigma;
import it.unibo.impl.templates.EnigmaTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * enigmas saving system
 */
public class EnigmaSaveWrite {

    private final static String filePath = "./src/main/resources/enigmas.yml";

    private List<Enigma> enigmas = new ArrayList<>();

    /**
     * 0 arguments constructor
     */
    public EnigmaSaveWrite() { }

    /**
     * adds a new enigma in the list
     * @param id the id
     * @param question the question
     * @param correctOption the correct answer
     * @param options all options
     * @param hasTheKey if this enigma has to drop a key when completed
     */
    public void addEnigma(final String id, final String question,
            final String correctOption, final List<String> options, final boolean hasTheKey) {

        if(options.contains(correctOption)) {
            this.enigmas.add(new EnigmaTemplate(id, hasTheKey, question, options, correctOption));
        } else {
            throw new IllegalArgumentException("wrong answers");
        }
    }

    /**
     * writes on the .yaml file the new enigma data
     */
    public void writeOnFile() {        
        try (final FileWriter fw = new FileWriter(filePath)) {      
            final Yaml yamlFile = new Yaml();      
            yamlFile.dump(this.enigmas, fw);
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }

    /**
     * erases all informations saved in {@code enigmas.yml}
     */
    public void eraseSave() {
        try (final FileWriter fw = new FileWriter(filePath)) {            
            fw.write("");
        } catch (final IOException excep) {
            excep.printStackTrace();
        }
    }

}
