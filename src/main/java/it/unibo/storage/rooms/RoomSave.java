package it.unibo.storage.rooms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.inspector.TagInspector;
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.api.Position;
import it.unibo.api.doors.Door;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
import it.unibo.core.GameSettings;
import it.unibo.impl.DoorImpl;
import it.unibo.impl.templates.EnigmaTemplate;
import it.unibo.impl.templates.RoomTemplate;
import it.unibo.storage.enigma.DataForEnigmas;

/**
* Rooms loading system
 */
public class RoomSave {
    /**
     * the list of the rooms
     */
    private List<Room> rooms = new ArrayList<>();

    private final static String FILE_PATH = "src/main/resources/" + GameSettings.ROOM_YAML_FILES_NAME.getValue();

    /**
     * 0 args constructor
     */
    public RoomSave() {}
    
    
    /**
     * gets the rooms 
     * @return the list of the rooms
     */
    public List<Room> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    /**
     * serializes the porvided list of rooms and saves them to the file YAML "rooms.yml"
     * @param list the list of rooms to be saved
     */
    public void save(final List<Room> list){
        final List<DataForRooms> rooms = new ArrayList<>();

        list.stream().forEach(r->{
            final RoomTemplate roomTemplate = (RoomTemplate) r;
            final DataForRooms dataForRooms = new DataForRooms();

            dataForRooms.setId(roomTemplate.getId());
            dataForRooms.setSize(roomTemplate.getSize());

            Map<Position, DataForDoor> doorDataList = new HashMap<>();
            roomTemplate.getDoorGrid().forEach((pos, door) -> {

                String dstId = door.getDstRoomId();
                DataForDoor doorData = new DataForDoor(
                    door.getId(),
                    dstId,
                    door.isOpen(),
                    pos
                );
                doorDataList.put(pos, doorData);
            });
            dataForRooms.setDoors(doorDataList);

            Map<Position, DataForEnigmas> enigmaDataList = new HashMap<>();
            roomTemplate.getEnigmaGrid().forEach((pos, enigma) -> {
                DataForEnigmas ed = new DataForEnigmas(
                    enigma.getId(), 
                    enigma.getQuestion(), 
                    enigma.getCorrectOption(), 
                    enigma.getOptions(), 
                    enigma.getKey().orElse(null)
                    
                );
                enigmaDataList.put(pos, ed);
            });
            dataForRooms.setEnigmas(enigmaDataList);

            rooms.add(dataForRooms);
        });

        final Yaml yamlWrite = new Yaml();
        try(FileWriter fw = new FileWriter(GameSettings.ROOM_YAML_FILES_NAME.getValue())) {
            yamlWrite.dump(rooms, fw);
        } catch (IOException excep) {
            excep.printStackTrace();
        }

    }
    
    /**
     * loads and parses room data from the file YAML "rooms.yml"
     */
    public void loadRooms() {
        final LoaderOptions loadOpt = new LoaderOptions();
        final TagInspector tagInsp = t -> t.getClassName().startsWith("it.unibo");
        loadOpt.setTagInspector(tagInsp);

        InputStream inputStream = null;

        try{
            //searches from user's saves
            final File userSave = new File(GameSettings.ROOM_YAML_FILES_NAME.getValue());
            if(userSave.exists()) {
                System.out.println("loading resources from " + userSave.getAbsolutePath());
                inputStream = new FileInputStream(userSave);
            }

            //if the file does not exist -> searches from Jar
            if(inputStream == null) {
                inputStream = getClass().getResourceAsStream("/" + GameSettings.ROOM_YAML_FILES_NAME.getValue());                
                if(inputStream != null) {
                    System.out.println("loading resources from " + GameSettings.ROOM_YAML_FILES_NAME.getValue());
                } 
            }
                    
            //if the user file does not exist and the Jar file is not updated -> searches from the default ide path (src/main/resources/)
            if(inputStream == null) {
                File ideFile = new File(FILE_PATH);
                if(ideFile.exists()) {
                    System.out.println("loading resources from " + FILE_PATH);
                    inputStream = new FileInputStream(ideFile);
                }
            }

            if(inputStream == null) {
                throw new IOException("File loading error: " + FILE_PATH + " does not exist");
            }

            //MIO
            try(InputStream fis = inputStream){
                final Yaml yamlRead = new Yaml(new Constructor(List.class, loadOpt));
                final List<DataForRooms> rawData = yamlRead.load(inputStream);


                Optional.ofNullable(rawData).ifPresent(data -> {
                    final Map<String, RoomTemplate> registry = createRoomShells(data);

                    populateRoomsContent(data, registry);
                    this.rooms.clear();
                    this.rooms.addAll(registry.values());
                }); 
            } 
        } catch (final Exception excep) {
            excep.printStackTrace();
        }
    }

    /**
     * Initializes the room registry by creating empty room objects based on the provided IDs.
     * This prepares the instances for the subsequent content population and linking phase.
     * @param rawData The deserialized data from the YAML file.
     * @return A registry Map linking IDs to Room instances.
     */
    private Map<String, RoomTemplate> createRoomShells(List<DataForRooms> rawData){
        Map<String,RoomTemplate> registry = new HashMap<>();
        for(DataForRooms data: rawData){
            registry.put(data.getId(), new RoomTemplate(data.getId()));
        }
        return registry;
    }

    /**
     * Constructs a map of enigmas positioned within the room based on the provided data.
     * @param enigmaEntries The list of enigma data objects to be converted.
     * @return A map associating each {@link Position} with its corresponding {@link Enigma}.
     */
    private  Map<Position,Enigma> buildEnigmaMap(Map<Position,DataForEnigmas> enigmaEntries){
        Map<Position,Enigma> enigmaMap= new HashMap<>();
       
        Optional.ofNullable(enigmaEntries)
        .orElse(Collections.emptyMap())
        .forEach((pos,entry) -> { 
            Enigma enigma=new EnigmaTemplate(
                entry.getId(),
                entry.getKey(),
                entry.getQuestion(),
                entry.getOptions(),
                entry.getCorrectOption()
            );
            enigmaMap.put(pos, enigma);
        });
        return enigmaMap;
    }

    /**
     * Constructs a map of doors positioned within the room based on the provided data.
     * @param doorEntries The list of door data objects to be converted.
     * @param registry The map of all available room templates, used to resolve destinations.
     * @return A map associating each {@link Position} with its corresponding {@link Door}.
     */
    private  Map<Position,Door> buildDoorMap(Map<Position, DataForDoor> doorEntries, Map<String,RoomTemplate> registry){
        Map<Position,Door> doorMap= new HashMap<>();
       
        Optional.ofNullable(doorEntries)
        .orElse(Collections.emptyMap())
        .forEach((pos, entry) -> {
            Door door= new DoorImpl(entry.getId(), entry.getDstRoomId());
            if(entry.isOpen()){
                door.setOpen(true);
            }
            doorMap.put(pos, door);
        });
        return doorMap;
    }

    /**
     * Populates the previously created room shells with their specific content (dimensions, doors, and enigmas)
     * @param rawData The list of data transfer objects containing the room details.
     * @param registry The map of existing room instances (shells) to be populated.
     */
    private void populateRoomsContent(List<DataForRooms> rawData,  Map<String,RoomTemplate> registry){
        for(DataForRooms data: rawData){
            RoomTemplate currenRoomTemplate=registry.get(data.getId());
            
            Map<Position,Enigma> enigmasMap=buildEnigmaMap(data.getEnigmas());
            Map<Position,Door> doorMap=buildDoorMap(data.getDoors(), registry);

            currenRoomTemplate.setLayout(data.getSize(), doorMap, enigmasMap);
        }
    }
}
