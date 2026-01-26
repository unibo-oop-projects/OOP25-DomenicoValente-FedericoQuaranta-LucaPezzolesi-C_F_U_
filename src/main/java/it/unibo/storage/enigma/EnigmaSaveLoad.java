package it.unibo.storage.enigma;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import it.unibo.api.enigmas.Enigma;
import it.unibo.core.GameSettings;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* enigmas loading system
 */
public class EnigmaSaveLoad {

    List<Enigma> enigmas = new ArrayList<>();
    
    /**
     * 0 args constructor
     */
    public EnigmaSaveLoad() { }

    /**
     * loads the enigmas list from {@code enigmas.yml}
     */
    public void loadEnigmas() {
        final LoaderOptions loadOpt = new LoaderOptions();
        final TagInspector tagInsp = t -> t.getClassName().startsWith("it.unibo");
        loadOpt.setTagInspector(tagInsp);

        try(final InputStream fis = new FileInputStream(GameSettings.YAML_FILES_DEFAULT_PATH.getValue())) {
        final Yaml yamlFile = new Yaml(new Constructor(List.class, loadOpt));
            enigmas = yamlFile.load(fis);
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
