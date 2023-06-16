package controller;

import model.Acquaintance.ILoadGameObject;
import model.Dao.loadGameDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadGame {
    String loadfile;
    ILoadGameObject loadGame=new loadGameDao();
    //The method which, reads the content, from the SaveFile.txt, into a string, and returns it
    public String loadString(String playerName){
        List<String> filenames=new ArrayList<>();
        filenames=loadGame.query(playerName);
        String filename=filenames.get(0);
        try {

            //The content from the file, gets read, and encoded
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            //Then the contet, from the encoded byte, gets set into a string.
            loadfile = new String(encoded, "utf-8");

        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loadfile;
    }

}
