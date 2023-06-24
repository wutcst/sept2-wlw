/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

import java.util.ArrayList;

/**
 * IData接口 主要用于
 * 加载游戏  保存游戏  保存当前最高分 等
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
    public boolean saveGame(String savestring,String saveName);
    public String getLoadGame(String playerName);

}
