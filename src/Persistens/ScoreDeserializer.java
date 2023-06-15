/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import Acquaintance.IPersonalScore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;

/**
 *Custom serializer used to desearialize a DataPS object which implements the interface IPersonScore
 * @author Rasmus
 */
public class ScoreDeserializer extends StdDeserializer<DataPS>{
    
    ScoreDeserializer() { 
        this(null); 
    }
    ScoreDeserializer(Class<?> vc) { 
    super(vc);
    }

    @Override       // This method is used to tell the deserializer what it needs to do with the variable and how it shuld return them as an object.
    public DataPS deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        int score = (Integer) ((IntNode) node.get("score")).numberValue();
        return new DataPS(name,score);
    }
    
}
