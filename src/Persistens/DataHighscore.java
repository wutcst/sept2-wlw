package Persistens;

import Acquaintance.IPersonalScore;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Class used for handling the actions of transforming arraylists of IpersonalScore object into and back from files.
 * @author Rasmus Jensen
 */
public class DataHighscore {

    private ArrayList<IPersonalScore> scoreList = new ArrayList();

    public DataHighscore() {

    }
/**
 * Method that recives an arraylist of IPersonalScore objects and useses the Jackson mappper to write it into a json file.
 * @param highscoreList an arraylist og Ipersonalscore objects 
 * @return returns a boolean in the case one wishes to confirm the operation have succeded.
 * @author Rasmus
 */
    boolean saveHigscore(ArrayList highscoreList){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(DataPS.class, new ScoreSerializer());
        mapper.registerModule(module);

        try {
            mapper.writeValue(new File("files/highscore.json"), highscoreList);
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
/**
 * method that uses a Jackson mapper to read a json file and tun it into an arraylist of IPersonalScore objects
 * @return an arraylist of IPersonalScore objects.
 */
    ArrayList loadHighscore() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ScoreSerializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(DataPS.class, new ScoreDeserializer());
        mapper.registerModule(module);


        try {
            scoreList = mapper.readValue(new File("files/highscore.json"), new TypeReference<ArrayList<DataPS>>(){});
            
        } catch (IOException ex) {
            Logger.getLogger(DataHighscore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scoreList;
    }

}
