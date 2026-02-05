package it.unibo.storage.rooms;

import java.io.FileInputStream;
import java.io.FileWriter;
//import java.io.File;
import java.io.IOException;
import java.io.InputStream;
//import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.yaml.snakeyaml.DumperOptions;
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

public class RoomSave {
    private List<Room> rooms = new ArrayList<>();

    public RoomSave() {}
    
    /**
     * Saves the list of rooms to rooms.yml
     * @param list the list of rooms to save
     */
    public void saveRooms(final List<Room> list) {
        final List<DataForRooms> saveList = new ArrayList<>();

        list.stream().forEach(r -> {
            if (r instanceof RoomTemplate) {
                final RoomTemplate rt = (RoomTemplate) r;
                final DataForRooms dto = new DataForRooms();
                
                dto.setId(rt.getId());
                dto.setSize(rt.getSize());

                List<DataForRooms.DoorData> doorDataList = new ArrayList<>();
                rt.getDoorGrid().forEach((pos, door) -> {
                    Room dst = door.getDstRoom();
                    String dstId = (dst instanceof RoomTemplate) ? ((RoomTemplate) dst).getId() : null;

                    if (dstId != null) {
                        DataForRooms.DoorData dd = new DataForRooms.DoorData();
                        dd.x = pos.x();
                        dd.y = pos.y();
                        dd.data = new DataForDoor(door.getId(), dstId, door.isOpen());
                        doorDataList.add(dd);
                    }
                });
                dto.setDoors(doorDataList);

                List<DataForRooms.EnigmaData> enigmaDataList = new ArrayList<>();
                rt.getEnigmaGrid().forEach((pos, enigma) -> {
                    DataForRooms.EnigmaData ed = new DataForRooms.EnigmaData();
                    ed.x = pos.x();
                    ed.y = pos.y();
                    ed.data = new DataForEnigmas(
                        enigma.getId(), enigma.getQuestion(), enigma.getCorrectOption(), 
                        enigma.getOptions(), enigma.getKey().orElse(null)
                    );
                    enigmaDataList.add(ed);
                });
                dto.setEnigmas(enigmaDataList);

                saveList.add(dto);
            }
        });
//777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
        final Yaml yamlWrite = new Yaml();
        try(FileWriter fw = new FileWriter(GameSettings.ROOM_YAML_FILES_DEFAULTPATH.getValue())) {
            yamlWrite.dump(saveList, fw);
        } catch (IOException excep) {
            excep.printStackTrace();
        }
    }

    /**
     * Loads the rooms list from rooms.yml
     */
    public void loadRooms() {
        final LoaderOptions loadOpt = new LoaderOptions();
        final TagInspector tagInsp = t -> t.getClassName().startsWith("it.unibo");
        loadOpt.setTagInspector(tagInsp);

        try(final InputStream fis = new FileInputStream(GameSettings.ROOM_YAML_FILES_DEFAULTPATH.getValue())) {
            final Yaml yamlRead = new Yaml(new Constructor(List.class, loadOpt));
            final List<DataForRooms> rawData = yamlRead.load(fis);

            Optional.ofNullable(rawData).ifPresent(data -> {
                final Map<String, RoomTemplate> registry = createRoomShells(data);

                populateRoomsContent(data, registry);
                this.rooms.clear();
                this.rooms.addAll(registry.values());
            });

        } catch (final Exception excep) {
            excep.printStackTrace();
        }
       
    }

    public List<Room> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    private Map<String, RoomTemplate> createRoomShells(List<DataForRooms> rawData){
        Map<String,RoomTemplate> registry = new HashMap<>();
        for(DataForRooms data: rawData){
            registry.put(data.getId(), new RoomTemplate(data.getId()));
        }
        return registry;
    }

    private  Map<Position,Enigma> buildEnigmaMap(List<DataForRooms.EnigmaData> enigmaEntries){
        Map<Position,Enigma> enigmaMap= new HashMap<>();
       
        Optional.ofNullable(enigmaEntries)
        .orElse(Collections.emptyList())
        .forEach(entry -> {
            Position pos=new Position(entry.x, entry.y);
            Enigma enigma=new EnigmaTemplate(
                entry.data.getId(),
                entry.data.getKey(),
                entry.data.getQuestion(),
                entry.data.getOptions(),
                entry.data.getCorrectOption() 
            );
            enigmaMap.put(pos, enigma);
        });
        return enigmaMap;
    }

    private  Map<Position,Door> buildDoorMap(List<DataForRooms.DoorData> doorEntries, Map<String,RoomTemplate> registry){
        Map<Position,Door> doorMap= new HashMap<>();
       
        Optional.ofNullable(doorEntries)
        .orElse(Collections.emptyList())
        .forEach(entry -> {
            
            Room dstRoom=Optional.ofNullable(registry.get(entry.data.getDstRoomId()))
            .orElseThrow(() -> new IllegalArgumentException("the door "+entry.data.getId()+" point to a non-existent room"));

            Door door= new DoorImpl(entry.data.getId(), dstRoom);
            if(entry.data.isOpen()){
                door.setOpen(true);
            }
            doorMap.put(new Position(entry.x,entry.y), door);
        });
        return doorMap;
    }

    private void populateRoomsContent(List<DataForRooms> rawData,  Map<String,RoomTemplate> registry){
        for(DataForRooms data: rawData){
            RoomTemplate currenRoomTemplate=registry.get(data.getId());
            
            Map<Position,Enigma> enigmasMap=buildEnigmaMap(data.getEnigmas());
            Map<Position,Door> doorMap=buildDoorMap(data.getDoors(), registry);

            currenRoomTemplate.setLayout(data.getSize(), doorMap, enigmasMap);
        }
    }
}
