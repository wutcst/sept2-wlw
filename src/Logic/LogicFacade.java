/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Acquaintance.IPersonalScore;
import java.io.IOException;
import java.util.ArrayList;
import Acquaintance.ILogic;
import Acquaintance.IData;
import Acquaintance.IImmovable;
import Acquaintance.IItem;
import Acquaintance.INPC;
import Acquaintance.IRoom;
import GlueCode.Starter;

/**
 *
 * @author Rasmus Jensen
 */
public class LogicFacade implements ILogic {

    static IData data;
    Highscore score;
    Game game;
    SaveFile save;

    public LogicFacade() {

    }

    @Override
    public void InjectData(IData persistenceLayer) {
        this.data = persistenceLayer;
    }

    @Override
    public void InjectGame(Game game) {  // the game is created in the starter class(gluecode) and is injected here so we can call methods on it.
        this.game = game;
    }

    @Override
    public void loadHighscore() {
        score = new Highscore(data.loadHighscore());
    }

    @Override
    public boolean saveHighscore() {
        return data.saveHighscore(Highscore.saveHighscore());
    }

    @Override
    public boolean addNewScore(String name, int score) {
        return this.score.addNewScore(name, score);
    }

    @Override
    public ArrayList<String> getHighscore() {
        return score.getHighscoreString();
    }

    static public String getDescriptionText(String key) {
        return data.getDescriptionText(key);
    }

    @Override
    public String getItemDescription(String secondWord) {
        return game.getItemDescription(secondWord);
    }

    @Override
    public String talk(String secondWord) {
        return game.talk(secondWord);
    }

    @Override
    public String getHelpText() {
        return game.printHelp();
    }

    @Override
    public int getCurrentOxygen() {
        return game.player.getCurrentOxygen();
    }

    @Override
    public int getCurrentHP() {
        return game.player.getCurrentHP();
    }

    @Override
    public String useItem(String secondWord) {
        return game.useItem(secondWord);
    }

    @Override
    public String getCurrentRoomName() {
        return game.currentRoom.getName();
    }

    @Override
    public void setPlayerName(String name) {
        game.player.setPlayerName(name);
    }

    @Override
    public String removeFromInventory(String secondWord) {      // har endret returntype er muligvis ikke korrekt....
        return game.removeFromInventory(secondWord);
    }

    @Override
    public void addInventory(String secondWord) {
        game.addInventory(secondWord);
    }

    @Override
    public ArrayList<IImmovable> getCurrentRoomInteractList() {
        ArrayList<IImmovable> IList = new ArrayList();
        for (Immovable i : game.currentRoom.getInteractList()) {
            IList.add(i);
        }
        return IList;

    }

    @Override
    public ArrayList<INPC> getCurrentRoomNPCList() {
        ArrayList<INPC> IList = new ArrayList();
        for (NPC i : game.currentRoom.getNPCList()) {
            IList.add(i);
        }
        return IList;
    }

    @Override
    public ArrayList<IItem> getCurrentRoomItemList() {
        ArrayList<IItem> IList = new ArrayList();
        for (Item i : game.currentRoom.getItemList()) {
            IList.add(i);
        }
        return IList;
    }

    @Override
    public String goRoom(String secondWord) {
        return game.goRoom(secondWord);
    }

    @Override
    public String combat(String secondWord) {
        return game.combat(secondWord);
    }

    @Override
    public boolean getDefeated() {
        return game.keyMonster.getDefeated();
    }

    @Override
    public void setOxygen(int air) {
        game.player.setAir(air);
    }

    @Override
    public String gameWelcome() {
        return game.printWelcome();
    }

    @Override
    public int getInventorySpace() {
        return game.inventorySpace;
    }

    @Override
    public void monsterTravel() {
        game.monsterTravel();
    }

    @Override
    public ArrayList<INPC> getRoomNPCList(String roomName) {
        ArrayList<INPC> npcList = new ArrayList<>();
        for (IRoom r : game.roomList) {
            if (r.getName().equals(roomName)){
                npcList = r.fetchINPCList();
            }
        }
        return npcList;
    }

    @Override
    public String awakenMonster() {
        return game.awakenMonster();
    }

    @Override
    public void setOpenSecretExit(String direction, String opener) {
        game.currentRoom.setExit(direction, game.currentRoom.getSecretDestination(opener));
    }

    @Override
    public ArrayList<IItem> getPlayerInventory() {
        ArrayList<IItem> IList = new ArrayList();
        for (Item i : game.player.getInventory()) {
            IList.add(i);
        }
        return IList;
    }

    @Override
    public String roomLogic() {
        return game.roomLogic();
    }

    @Override
    public boolean saveGame() {
        this.save = new SaveFile(game, game.player);
        return data.saveGame(save.getSaveString());

    }

    @Override
    public void loadGame() {
        this.save = new SaveFile(game, game.player);
        save.LoadSaveString(data.getLoadGame());

    }

    @Override
    public INPC getCurrentRoomNPC(String npc) {
        return game.currentRoom.getNPC(npc);
    }

    @Override
    public IRoom getCurrentRoom() {
        return game.currentRoom;
    }

    @Override
    public boolean checkPlayerItems(String string) {
        for(IItem i : game.player.getInventory()){
            if(i.getName().equals(string)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean getPlayerCalledHelp() {
        return game.player.gethasCalledHelp();
    }

    @Override
    public void setPlayerCalledHelp(Boolean help) {
        game.player.sethasCalledHelp(help);
    }

    @Override
    public void setPlayerWonGame(Boolean won) {
        game.player.setWonGame(won);
    }

    @Override
    public String startQuiz(String string) {
        return game.startQuiz(string);
    }
    @Override
    public String getPlayerName(){
        return game.player.getName();
    }
    @Override
    public int getPlayerScore(Boolean toldtoevacuate){
        return game.player.getAwesomePoint(toldtoevacuate);
    }
}
