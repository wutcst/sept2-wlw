/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Acquaintance.ISaveGameObject;
import model.Dao.SaveGameDao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 保存游戏的实现类
 */
public class SaveGame {
    String loadfile;
    ISaveGameObject savegameDao =new SaveGameDao();
    public SaveGame() {
    }
    //A method, which saves the saveString, into a txt file
  public boolean saveGame(String saveString,String playerName){
      //保存文件定义
      PrintWriter save = null;
      //保存到本地
      boolean bool = false;
      String fileName="Gruppe 6 Semester Projekt/files/gamefile/SaveFile"+playerName+".txt";
        try {
            //以玩家名保存游戏
            save = new PrintWriter(fileName);
            //The save then prints the content from the string, into the file
            save.print(saveString);
            //Sets the boolean bool to true
            bool = true;
            } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            save.close();
        }

        int count=savegameDao.insert(playerName,fileName);
      //成功或失败重定向到哪里

      if(count >0 ) {
          bool=true;
      } else {
          bool=false;
      }
        return bool;
  }
}
