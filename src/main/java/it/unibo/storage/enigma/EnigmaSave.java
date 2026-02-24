package it.unibo.storage.enigma;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import it.unibo.api.enigmas.Enigma;
import it.unibo.core.GameSettings;
import it.unibo.impl.templates.EnigmaTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* enigmas loading system
 */
public class EnigmaSave {

    private List<Enigma> enigmas = new ArrayList<>();
    
    /**
     * 0 args constructor
     */
    public EnigmaSave() { }

    /**
     * loads the enigmas list from {@code enigmas.yml}
     */
    public void loadEnigmas() {
        final LoaderOptions loadOpt = new LoaderOptions();
        final TagInspector tagInsp = t -> t.getClassName().startsWith("it.unibo");
        loadOpt.setTagInspector(tagInsp);


        try (final InputStream is = getClass().getResourceAsStream("/" + GameSettings.ENIGMAS_YAML_FILE_NAME.getValue())) {
            //searches from jar
            if(is == null) {
                throw new IOException("enigmas file not found in jar");
            }
                    
            final Yaml yamlRead = new Yaml(new Constructor(List.class, loadOpt));
            final List<DataForEnigmas> raw = yamlRead.load(is);

            if(raw == null) {
                throw new IOException("file njot found");
            }

            raw.stream().forEach(e -> {
                this.enigmas.add(new EnigmaTemplate(e.getId(), e.getKey(), e.getQuestion(), e.getOptions(), e.getCorrectOption()));
            });

        } catch (final Exception excep) {
            excep.printStackTrace();
        }
    }

    /**
     * gets the list of the enigmas in the file
     * @return the list of enigmas
     */
    public List<Enigma> getEnigmas() {
        return this.enigmas;
    }
}
