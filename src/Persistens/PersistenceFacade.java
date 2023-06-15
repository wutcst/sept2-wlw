/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;
import java.io.IOException;
import java.util.ArrayList;
import Acquaintance.IData;

/**
 *facade on the peasistence package used to fasilitate comunication to other packages
 * @author Rasmus Jensen
 */

public class PersistenceFacade implements IData{
    DataHighscore dataHs = new DataHighscore();
     InputHashmap textIn = new InputHashmap();
     SaveGame save = new SaveGame();
    
    
    /**
     * method that other pacages can call to save a highscore list
     * @param list takes an arraylist of IPersonalScores   
     * @return a boolean that can be used too confim that the method has completed
     */
    @Override
    public boolean saveHighscore(ArrayList list){
        return dataHs.saveHigscore(list);
    }
    
    @Override
    public ArrayList loadHighscore(){
        return dataHs.loadHighscore();
    }
    
    @Override
    public String getDescriptionText(String key){
        return InputHashmap.getText(key);
    }

    @Override
    public boolean saveGame(String savestring) {
        save.saveGame(savestring);
        return true;
    }
    
    @Override
    public String getLoadGame(){
        return save.loadString();
    }
}
