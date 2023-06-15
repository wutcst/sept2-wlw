/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import Logic.Game;
import java.util.ArrayList;

/**
 *
 * @author Rasmus Jensen
 */
public interface ILogic {

    void InjectData(IData datalayer);
    public boolean addNewScore(String name, int score);
    public boolean saveHighscore();
    public ArrayList<String> getHighscore();
    public void loadHighscore();
    public String getItemDescription(String secondWord);    //command.setSecondWord(secondWord)
    public String talk(String secondWord);                  //command.setSecondWord(secondWord)
    public String getHelpText();
    public int getCurrentOxygen();
    public int getCurrentHP();
    public String useItem(String secondWord);               //command.setSecondWord(secondWord)
    public String getCurrentRoomName();
    public void setPlayerName(String name);
    public String removeFromInventory(String secondWord);     //command.setSecondWord(secondWord)
    public void addInventory(String secondWord);            //command.setSecondWord(secondWord)
    public ArrayList<IImmovable> getCurrentRoomInteractList();
    public ArrayList<INPC> getCurrentRoomNPCList();
    public String goRoom(String secondWord);                  //command.setSecondWord(secondWord)
    public boolean saveGame();
    public String combat(String secondWord);
    public boolean getDefeated();
    public void setOxygen(int air);
    public String gameWelcome();
    public int getInventorySpace();
//    public void Play();
    public void monsterTravel(); 
    public ArrayList<IItem> getCurrentRoomItemList();
    public String awakenMonster();
    public INPC getCurrentRoomNPC(String npc);
    public void InjectGame(Game game1);
    public ArrayList<INPC> getRoomNPCList(String roomName);
    public void setOpenSecretExit(String direction, String opener);
    public String roomLogic();
    public ArrayList<IItem> getPlayerInventory();
     public void loadGame();
    public IRoom getCurrentRoom();
    public boolean checkPlayerItems(String string);
    public Boolean getPlayerCalledHelp();
    public void setPlayerCalledHelp(Boolean help);
    public void setPlayerWonGame(Boolean won);
    public String startQuiz(String string);
    public String getPlayerName();
    public int getPlayerScore(Boolean toldtoevacuate);
}
