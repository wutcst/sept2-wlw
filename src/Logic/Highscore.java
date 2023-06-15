/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Acquaintance.IPersonalScore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasmus
 */
public class Highscore {
    static ArrayList<PersonalScore> highscoreList;
    static ArrayList<String> stringList = new ArrayList<>();
    
    Highscore(ArrayList<IPersonalScore> arrayList){
            Highscore.highscoreList = new ArrayList<>();
            ArrayList<IPersonalScore> dataHSL = arrayList;
            
            for (IPersonalScore i : dataHSL) {
                highscoreList.add(new PersonalScore(i.getName(), i.getScore()));
                highscoreList.sort(null);
            }
    }
    
    static ArrayList saveHighscore() {
        return highscoreList;
    }
      
    static boolean addNewScore(String name, int score){
        highscoreList.add(new PersonalScore(name, score));
        highscoreList.sort(null);
        while (highscoreList.size()>10){
                highscoreList.remove(10);
            }
        
        return true;
    }

    public static ArrayList getHighscoreString(){
            int i = 0;        
        for (PersonalScore s: highscoreList){
            stringList.add((i+1) + " " + s.toString());
            i++;
    }
            return stringList;
    }
    
    
    
}
