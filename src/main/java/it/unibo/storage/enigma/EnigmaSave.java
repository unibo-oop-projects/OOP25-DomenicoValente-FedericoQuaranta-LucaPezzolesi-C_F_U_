package it.unibo.storage.enigma;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import it.unibo.api.enigmas.Enigma;
import it.unibo.core.GameSettings;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* enigmas loading system
 */
public class EnigmaSave {

    List<Enigma> enigmas = new ArrayList<>();
    
    /**
     * 0 args constructor
     */
    public EnigmaSave() { }

    /**
     * saves the {@code list} of enigmas in {@code enigmas.yml}
     * @param list the list of enigmas to save
     */
    public void saveEnigmas(final List<Enigma> list) {

        final List<DataForEnigmas> saveList = new ArrayList<>();
        list.stream().forEach(e -> {
            saveList.add(new DataForEnigmas(e.getId(), e.getQuestion(), 
            e.getCorrectOption(), e.getOptions(), 
            e.getKey().isEmpty() ? "" : e.getKey().get().getDst().getId()));
        });

        final Yaml yamlWrite = new Yaml();
        try(FileWriter fw = new FileWriter(GameSettings.YAML_FILES_DEFAULT_PATH.getValue())) {
            yamlWrite.dump(saveList, fw);
        } catch (IOException excep) {
            excep.printStackTrace();
        }
    }

    /**
     * loads the enigmas list from {@code enigmas.yml}
     */
    public void loadEnigmas() {
        final LoaderOptions loadOpt = new LoaderOptions();
        final TagInspector tagInsp = t -> t.getClassName().startsWith("it.unibo");
        loadOpt.setTagInspector(tagInsp);

        try(final InputStream fis = new FileInputStream(GameSettings.YAML_FILES_DEFAULT_PATH.getValue())) {
        final Yaml yamlRead = new Yaml(new Constructor(List.class, loadOpt));
            enigmas = yamlRead.load(fis);
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
