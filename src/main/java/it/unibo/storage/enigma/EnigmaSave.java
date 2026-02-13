package it.unibo.storage.enigma;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import it.unibo.api.enigmas.Enigma;
import it.unibo.core.GameSettings;
import it.unibo.impl.templates.EnigmaTemplate;

import java.io.File;
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
            e.getKey().isEmpty() ? null : e.getKey().get()));
        });

        final Yaml yamlWrite = new Yaml();
        try(FileWriter fw = new FileWriter(GameSettings.ENIGMAS_YAML_FILES_DEFAULT_PATH.getValue())) {
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

        try(final InputStream fis = new FileInputStream(GameSettings.ENIGMAS_YAML_FILES_DEFAULT_PATH.getValue())) {
            final Yaml yamlRead = new Yaml(new Constructor(List.class, loadOpt));
            final List<DataForEnigmas> raw = yamlRead.load(fis);

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

    /**
     * deletes the file
     */
    public void deleteFile() {
        final File file = new File(GameSettings.ENIGMAS_YAML_FILES_DEFAULT_PATH.getValue());
        file.delete();
    }
}
