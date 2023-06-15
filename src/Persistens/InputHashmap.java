package Persistens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *The object of this class is to remove the items- and room- descriptions from the game code
 * and storing them in a separate file, to increase readability of the code.
 * we use a FileReader to read a file as a string input, and wrap it in the BufferedReader to get it line by line and then preforms a split at the first occurrence of : in the line
 this code forgoes any error detection based on Wrong syntax in the file, therefore it is important that each change in the file gets cheeked by the ImputFileHashmapTest class before the revisions are shipped
 * @author Rasmus
 */
public class InputHashmap {

    private final String filePath = "files/text.txt";     // sets the filepath based on the projectfolder else it can be set from scratch like C:/Users/Documents/......
    private static HashMap<String, String> textMap;  // crates a hasmap for storing keys and their corresponding string value
    String line;

    // Constructor that initiates the HashMap that is needed to import the text into the game.
    public InputHashmap(){
        createTextMap();
    }

    // the creations of the hasmap by reading the file
    private void createTextMap() {
        textMap = new HashMap<>();      // initiation of the hashmap
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath)); // the file is read by a FileReader which is wrapped in a bufferedReader to read one line of a time.
        
        while ((line = reader.readLine()) != null) { // while-loop that operands on each line of the text file
            String[] parts = line.split(":", 2);    // creates a string array that splits the line into 2 at the first occurrence of :
            {
                String key = parts[0];      // the part before the : is assigned as a key variable
                String value = parts[1];    // and the part after : is set as  value variable
                textMap.put(key, value);    // the key and varable is assigne to the hasmap
            }
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InputHashmap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputHashmap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // method for getting the value(the text that is the descriptions.) out of the hashmap
    static public String getText(String key) {
        return textMap.get(key);
    }
}
