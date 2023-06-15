/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author Nick
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveSerializer extends StdSerializer<SaveFile> {
    
    public SaveSerializer(){
    this(null);
    }
    
    public SaveSerializer(Class<SaveFile> t){
        super(t);
    
    }
    //This is the custom serializaer method
    @Override
    public void serialize(SaveFile t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        //Here we starte the JsonGenerator for object
        jg.writeStartObject();
        //Here the player gets serialized, where the key for the player is "player2, and we get the player object, from the SaveFile class getter method.
        jg.writeObjectField("player", t.getPlayer());
        //here we serialize the Room objects, with the rooms name as the key
        jg.writeObjectField("Medbay", t.getGame().getMedbay());
        jg.writeObjectField("Hallway", t.getGame().getHallway());
        jg.writeObjectField("Keyroom", t.getGame().getKeyRoom());
        jg.writeObjectField("Communicationroom", t.getGame().getCommunicationRoom());
        jg.writeObjectField("Armoury", t.getGame().getArmoury());
        jg.writeObjectField("Airlock", t.getGame().getAirlock());
        jg.writeObjectField("Currentroom", t.getGame().getCurrentRoom());

       //Here we end the output stream
        jg.writeEndObject();
    }
    
}
