/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Logic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

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

    /**
     * 单独获取玩家名
     * @return
     */
    public String getPlayerName(){
        return player.getName();
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

    
}
