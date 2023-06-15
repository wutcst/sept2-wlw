/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Nick
 */
@JsonInclude(Include.NON_NULL)
public class SaveFile {

    Player player;
    private Game game;
    Room room;
    String Savestring;
    String loadString;
    
    public SaveFile(Game game, Player player) {
        this.game = game;
        this.player = player;

    }
    public Game getGame(){
        return game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    //Here is the method, which gets the save string, that then, gets send to the Persistens layer
    public String getSaveString() {
        ObjectMapper mapper = new ObjectMapper();
        
        SimpleModule module = new SimpleModule();
        //The custom serializer being added to the module
        module.addSerializer(SaveFile.class, new SaveSerializer());
        mapper.registerModule(module);
        
        try {
            //The seriealized game object, where we defined the things we want serialized in the custom serializer, gets serialized into Savestring.
            Savestring = mapper.writeValueAsString(this);

            
           } catch (JsonProcessingException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Savestring;
    }
 
    
    //The method, that loads the save string, and inizialize the new game
    public void LoadSaveString(String loadString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            this.loadString = loadString;
            mapper.registerModule(module);
            //New instance of the JSONObject, being initialized, from the loadString
            JSONObject json = new JSONObject(loadString);
            //all the instances of rooms, and the player, which where serialized and saved in a file, gets read, as single objects, and their info, put into a string, containing only the that info
            String playerPart = json.getJSONObject("player").toString();
            String medbayPart = json.getJSONObject("Medbay").toString();
            String Hallway = json.getJSONObject("Hallway").toString();
            String Keyroom = json.getJSONObject("Keyroom").toString();
            String Communicationroom = json.getJSONObject("Communicationroom").toString();
            String Armoury = json.getJSONObject("Armoury").toString();
            String Airlock = json.getJSONObject("Airlock").toString();
            String Currentroom = json.getJSONObject("Currentroom").toString();
            
            //Here a new player1, gets initialized, from the string playerPart, and deserialized into a Player.class.
            Player player1 = mapper.readValue(playerPart, Player.class);
            
            //Here the Rooms get initialized from their respective strings, and deserialized, into the Room.class
            Room medbay = mapper.readValue(medbayPart, Room.class);
            Room Hallway1 = mapper.readValue(Hallway, Room.class);
            Room Keyroom1 = mapper.readValue(Keyroom, Room.class);
            Room Communicationroom1 = mapper.readValue(Communicationroom, Room.class);
            Room Armoury1 = mapper.readValue(Armoury, Room.class);
            Room Airlock1 = mapper.readValue(Airlock, Room.class);
            String Currentroom1 = mapper.readValue(Currentroom, Room.class).getName();
            
            //Here the deserialized player and rooms, get set into the game class, as the new player and rooms, with all the info, from when the user saved the game
            game.setPlayer(player1);
            game.setMedbay(medbay);
            game.setHallway(Hallway1);
            game.setKeyRoom(Keyroom1);
            game.setCommunicationRoom(Communicationroom1);
            game.setArmoury(Armoury1);
            game.setAirlock(Airlock1);
            game.setCurrentRoom(Currentroom1);

        } catch (JSONException | IOException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
