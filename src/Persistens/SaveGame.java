/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick
 */
public class SaveGame {
    String loadfile;
    
    public SaveGame() {
    }
    //A method, which saves the saveString, into a txt file
  public boolean saveGame(String saveString){
      //A printwriter called save being initialized
      PrintWriter save = null;
      boolean bool = false;
        try {
            //New save printwriter, being initialized, which reads from and to the file SaveFile.txt
            save = new PrintWriter("files/SaveFile.txt");
            //The save then prints the content from the string, into the file
            save.print(saveString);
            //Sets the boolean bool to true
            bool = true;
            } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            save.close();
        }
        return bool;
  }
                  //The method which, reads the content, from the SaveFile.txt, into a string, and returns it
public String loadString(){
        try {
            
            String filePath = "files/SaveFile.txt";
            //The content from the file, gets read, and encoded
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            //Then the contet, from the encoded byte, gets set into a string.
            loadfile = new String(encoded, "utf-8");

        } catch (IOException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return loadfile;
}
    
          
    
    
}
