/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.ArrayList;

/**
 *
 * @author Rasmus Jensen
 */
public interface IData {

    public boolean saveHighscore(ArrayList list);
    public ArrayList loadHighscore();
    /**
     *
     * @param key
     * @return
     */
    public String getDescriptionText(String key);
    public boolean saveGame(String savestring);
    public String getLoadGame();

}
