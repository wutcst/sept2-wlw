package semesterprojektfx;

import Acquaintance.IImmovable;
import Acquaintance.IItem;
import Acquaintance.ILogic;
import Acquaintance.INPC;
import Logic.Game;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static java.util.Optional.empty;
import java.util.logging.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author goope
 */
public class FXMLDocumentController implements Initializable {
    GUIFacade gui;
    ILogic logic;
    INPC keyMonster;
    IItem rifle;
    private IImmovable table;
    private GUIFacade scene = new GUIFacade();
    int attempts = 3;
    
    private boolean flagcheck, monsterDefeatCheck = false;
    
    @FXML
    TextArea textOutArea;
    @FXML
    private Button northButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button southButton;
    @FXML
    private Button westButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button inspectButton;
    @FXML
    ImageView mainMap;
    @FXML
    private ProgressBar AirBar;
    @FXML
    private ProgressBar HPbar;
    @FXML
    private ImageView miniMap;
    @FXML
    private ListView<String> roomInventory = new ListView<>();
    private ObservableList<String> roomInv = FXCollections.observableArrayList();
    @FXML
    private ListView<String> playerInventory = new ListView<>();
    private ObservableList<String> playerInv = FXCollections.observableArrayList();
    
    @FXML
    private ListView<String> highScoreList = new ListView<>();
    private ObservableList<String> highScoreView = FXCollections.observableArrayList();
    
    ListProperty<String> listPropertyRoom = new SimpleListProperty<>();
    ListProperty<String> listProperty2 = new SimpleListProperty<>();    
    ListProperty<String> listProperty3 = new SimpleListProperty<>();    
    @FXML
    private Button takeButton;
    @FXML
    private Button dropButton;
    @FXML 
    private Button talkButton;
    @FXML
    private Button saveButton;
    @FXML
    private ImageView medkit;
    @FXML
    private ImageView oxygen;
    @FXML
    private ImageView mainMap1;
    @FXML
    private ImageView playerImg1;
    @FXML
    private ImageView monster;
    @FXML
    private ImageView eastDoor;
    @FXML
    private ImageView northDoor1;
    @FXML
    private ImageView southDoor;
    @FXML
    private Pane medbay;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView playerImg;
    @FXML
    private ImageView tableImg;
    @FXML
    private ImageView northDoor;
    @FXML
    private Pane keyRoom;
    @FXML
    private Pane armory;
    @FXML
    private ImageView secretDoor;
    @FXML
    private ImageView secretDoor1;
    @FXML
    private ImageView breakableTable;
    @FXML
    private ImageView rifleImg;
    @FXML
    private ImageView noteImg;
    @FXML
    private Pane hallway;
    @FXML
    private Pane airlock;
    @FXML
    private Pane communicationRoom;
    @FXML
    private Button attackButton;
    @FXML
    private Button useButton;
    @FXML
    private Button statusButton;
    @FXML
    private Pane splashScreen;
    @FXML
    private Label enterNameLabel;
    @FXML
    private TextField playerNameEnterField;
    @FXML
    private Button startButton;
    @FXML
    private Label warningLabel;
    @FXML
    private Button highScoreButton;
    @FXML
    private Button loadButton;
    @FXML
    private TextField password;
    @FXML
    private Button passwordSubmit;
    @FXML
    private Pane passwordPane;
    @FXML
    private ImageView playerDot;
    @FXML
    private ImageView monsterDot;
    @FXML
    private ImageView britneyDot;
    @FXML
    private ImageView keyImg;
    @FXML
    private ImageView airlockMonster;
    @FXML
    private ImageView hallwayMonster;
    @FXML
    private ImageView armouryMonster;
    @FXML
    private ImageView medbayMonster;
    @FXML
    private ImageView keyRoomMonster;
    @FXML
    private ImageView brokenTable;
    @FXML
    private ImageView weaponCabinet;
    @FXML
    private ImageView tableLeg;
    @FXML
    private Pane endSplash;
    @FXML
    private ImageView endSplashImage;
    
    //This method controlls the functions of the player movement buttons, and the
    //help button.
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        String toAppend = "";
        
        roomInventory.getItems().clear(); 
        if (event.getSource() == northButton) {
            textOutArea.clear();
            String secondWord = "north"; // string
            toAppend = logic.goRoom(secondWord); // room description (hvad go room returner)
            logic.monsterTravel();
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == eastButton) {
            textOutArea.clear();
            String secondWord = "east";
            toAppend = logic.goRoom(secondWord);
            logic.monsterTravel();
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == westButton) {
            textOutArea.clear();
            String secondWord = "west";
            toAppend = logic.goRoom(secondWord);
            logic.monsterTravel();
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == southButton) {
            textOutArea.clear();
            String secondWord = "south";
            toAppend = logic.goRoom(secondWord);
            logic.monsterTravel();
            roomChange();
            minimapAction();
        }
        else if (event.getSource() == helpButton){
            textOutArea.clear();
            toAppend = helpText();
        }
        toAppend += System.lineSeparator();
        textOutArea.appendText(toAppend);
        monsterAttack();
        hpBarAction();
        AirBarAction();
    }
    
    //This method is used by the search funktion in the game. It gets the items
    //in the room the player is currently in, and adds their names to the GUI's
    //listviews.
    @FXML
    private void listAction(ActionEvent event){
        if (event.getSource() == searchButton){
            roomInv.clear();
            for (IItem i : logic.getCurrentRoomItemList()) {
                roomInv.add(i.getName());
            }
            for (IImmovable i : logic.getCurrentRoomInteractList()){
                if(i.getFlag()==true){
                    roomInv.add(i.getName()); 
                    if (i.getItems() != null) {
                        if(i.getItems().getFlag()==true){
                            roomInv.add(i.getItems().getName());
                        }        
                    }
                }
            }
            for(INPC n : logic.getCurrentRoomNPCList()){
                if (!logic.getCurrentRoomNPCList().isEmpty()){
                    roomInv.add(n.getName());
                }  
            }
            minimapAction();
            listPropertyRoom.set(FXCollections.observableList(roomInv));
            roomInventory.itemsProperty().bind(listPropertyRoom); 
        }
    }
    
    @FXML
    private void talkButtonAction(ActionEvent event) {
        String talkTarget = "";
        if (event.getSource() == talkButton) {
            talkTarget = roomInventory.getSelectionModel().getSelectedItem();
            textOutArea.appendText("\n" + talkText(talkTarget));
        }
    }
    
    /**
    *   This method deals with the inspect button.
    */
    @FXML
    private void inspectSelectionAction(ActionEvent event){
        String selectName = "selectName empty";
        if (event.getSource() == inspectButton) {
            // if, else if statements check which inventory the item is selected from.
            if (!roomInventory.getSelectionModel().isEmpty()){
                selectName = roomInventory.getSelectionModel().getSelectedItem();
            } else if (!playerInventory.getSelectionModel().isEmpty()) {
                selectName = playerInventory.getSelectionModel().getSelectedItem();
            }
            //textOutArea.clear();
            //System.out.println(inspectText(command));
            textOutArea.appendText("\n" + inspectText(selectName) + "."); // inspect
        }
    }
    
    //This method controlls the take buttons actions, on how to deal with the items
    //in the room and player inventory.
    @FXML
    private void takeInventoryAction(ActionEvent event){
        String itemName = roomInventory.getSelectionModel().getSelectedItem();
        if (event.getSource() == takeButton) {
                if (itemName == null) {
                    return;
                }
                else if (playerInv.size() < logic.getInventorySpace()) {
                    if (!itemName.equalsIgnoreCase("monster") && !itemName.equalsIgnoreCase("counter") && !itemName.equalsIgnoreCase("device") &&
                        !itemName.equalsIgnoreCase("closet") && !itemName.equalsIgnoreCase("table") && !itemName.equalsIgnoreCase("bookcase") 
                        && !itemName.equalsIgnoreCase("hiddenpanel") && !itemName.equalsIgnoreCase("lockedDoor") && !itemName.equalsIgnoreCase("airlockPanel") 
                        && !itemName.equalsIgnoreCase("doorLockPanel") && !itemName.equalsIgnoreCase("radioArray") && !itemName.equalsIgnoreCase("panel") 
                        && !itemName.equalsIgnoreCase("cabinet") && !itemName.equalsIgnoreCase("switch")) {
                        playerInv.add(itemName);
                        logic.addInventory(itemName);
                        roomInventory.getItems().remove(itemName);
                        textOutArea.appendText("\nYou have added " + itemName + " to your inventory.");
                        if (itemName.equalsIgnoreCase(medkit.getId())) {
                            medkit.setVisible(false);
                        }
                        else if (itemName.equalsIgnoreCase(oxygen.getId())) {
                            oxygen.setVisible(false);
                        }
                        else if (itemName.equalsIgnoreCase("key")) {
                            keyImg.setVisible(false);
                        }
                        else if (itemName.equalsIgnoreCase("rifle")){
                            rifleImg.setVisible(false);
                        }
                        listProperty2.set(FXCollections.observableList(playerInv));
                        playerInventory.itemsProperty().bind(listProperty2);
                        return;
                    }
                    else {
                        textOutArea.appendText("\nCan't take that.");
                        return;
                    }
                }
                else if (playerInv.size() >= logic.getInventorySpace()) {
                    textOutArea.appendText("\nNo more space in your inventoy.");
                }
//                else {
//                    textOutArea.appendText("\nCan't take that.");
//                }
                
        }
        listProperty2.set(FXCollections.observableList(playerInv));
        playerInventory.itemsProperty().bind(listProperty2);
    }
    
    //Gets the help text string from the game class, so it can be used by the GUI
    private String helpText(){
        return "Attacking: "
                + "\nTo attack the monster you need to have the rifel in your "
                + "inventory. Then, you need to click the monster and press "
                + "the 'attack' button."
                + "\nUsing items: \n"
                + "To use an item, you need to first click on the item, then click use. "
                + "Some items you need to have in your inventory to use eg. medkit."
                + "\nInspecting objects: \n"
                + "To inspect an object, you need to click the object, and then"
                + " click the 'use' button."
                + "\nTaking items: \n"
                + "To take an item, you need to search the room you are in, click "
                + "the item you want to take, and click the 'take' button.";
    }
    //Gets the item description of a specific item from the game class
    private String inspectText(String secondWord){
        return logic.getItemDescription(secondWord);
    }
    //Gets the different string responses from the game class, when you are 'talking'
    //whith Britney
    
    private String talkText(String secondWord) {
        return logic.talk(secondWord);
    }
    
    //This method initializes all the relevant classes that are needed by the GUI
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        logic = gui.getInstance().getLogic();   // gives acces to caling method on the logic fasade throu a singletoon institiation of the guifacade 
        logic.loadHighscore();                  // call that leads the highscore from its file into memory
        textOutArea.appendText("\n");
        textOutArea.appendText(logic.gameWelcome());
        textOutArea.appendText("\n");
        textOutArea.appendText("\n");                      
        
        hpBarAction();
        AirBarAction();
        
        splashScreen.setVisible(true);
        keyRoomMonster.setVisible(false);
        airlockMonster.setVisible(false);
        armouryMonster.setVisible(false);
        hallwayMonster.setVisible(false);
    } 
    //This method controls the function of the 'drop' button. It takes and sets
    //items in the two different lists.
    @FXML
    private void dropButtonAction(ActionEvent event) {
        String itemName = "";
        if (playerInventory.getSelectionModel().getSelectedItem() != null) {
            itemName = playerInventory.getSelectionModel().getSelectedItem();
        }
        if (itemName != "") {
            logic.removeFromInventory(itemName);
            playerInventory.getItems().remove(itemName);
            roomInv.add(itemName);
        }
        listPropertyRoom.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listPropertyRoom);
        
//        if (itemName.equalsIgnoreCase(medkit.getId())) {
//            medkit.setVisible(true);
//        }
//        else if (itemName.equalsIgnoreCase(oxygen.getId())){
//            oxygen.setVisible(true);
//        }
//        else if (itemName.equalsIgnoreCase("rifle")){
//            rifleImg.setVisible(true);
//        }
    }
    //Controls which pane the GUI should show, based on where the player is.
    //It also runs the method 'awakenMonster', which makes the monster move from
    //room to room
    private void roomChange() throws IOException{
        String roomName = logic.getCurrentRoomName();
        if (roomName.equalsIgnoreCase("medbay")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(true);
            hallway.setVisible(false);
            airlock.setVisible(false);
        }
        else if (roomName.equalsIgnoreCase("keyRoom")) {
            armory.setVisible(false);
            keyRoom.setVisible(true);
            medbay.setVisible(false);
            hallway.setVisible(false);
            airlock.setVisible(false);   
            if (flagcheck == false) {
                logic.awakenMonster();
                textOutArea.appendText("\n" + logic.awakenMonster());
                flagcheck = true;
            }
        }
        else if (roomName.equalsIgnoreCase("armoury")) {
            armory.setVisible(true);
            keyRoom.setVisible(false);
            medbay.setVisible(false);
            hallway.setVisible(false);
            airlock.setVisible(false);            
        }
        else if (roomName.equalsIgnoreCase("hallway")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(true);
            airlock.setVisible(false);
        }
        else if (roomName.equalsIgnoreCase("airlock")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(false);
            airlock.setVisible(true);
        }
        else if (roomName.equalsIgnoreCase("communicationRoom")) {
            armory.setVisible(false);
            keyRoom.setVisible(false);
            medbay.setVisible(false); 
            hallway.setVisible(false);
            airlock.setVisible(false);  
            communicationRoom.setVisible(true);
        }
                logic.roomLogic();
    }
    //Controls what happens when the player thies to use different items and
    //immovables (via the 'use' button). 
    @FXML
    private void useAction(ActionEvent event){
        String newWord = playerInventory.getSelectionModel().getSelectedItem();
        String newWord2 = roomInventory.getSelectionModel().getSelectedItem();
        if (!playerInv.isEmpty() && newWord == "medkit" || newWord == "oxygen") {
            textOutArea.appendText("\n" + logic.useItem(newWord));
            playerInventory.getItems().remove(newWord);
        } else if(newWord2=="panel"){
            textOutArea.appendText("\nYou go down to the small keypad"
                    + "\nPlease type in the password\n3 attemps remaining");
            passwordPane.setVisible(true); 
            return;
        } else if(newWord2=="lockeddoor"){
            textOutArea.appendText(logic.startQuiz(""));
            passwordPane.setVisible(true);
            return;
        } else if(newWord2=="radio") {
            if(logic.checkPlayerItems("key") && !playerInv.isEmpty()){
                textOutArea.appendText("\nYou insert the keymodule into the radio"
                        + "and the static is replaced by a beeping sound"
                        + "\nAfter some time a voice is heard"
                        + "\n'We have recieved your distress call'"
                        + "\n'A rescue ship has been sent your way and will arrive shortly'");
                        logic.setPlayerCalledHelp(true);
                        
            
            } else if((!playerInv.isEmpty() && logic.checkPlayerItems("key") && playerInv.isEmpty())){
                textOutArea.appendText("The radio seem to be missing some sort of key");
            }
        } else if(newWord2=="switch"){
            if(logic.getPlayerCalledHelp()){
                textOutArea.appendText(logic.getCurrentRoom().getImmovable("switch").getUseDescription());
                endSplash.setVisible(true);
                logic.addNewScore(logic.getPlayerName(), logic.getPlayerScore(true));
                logic.saveHighscore();
                passwordPane.setVisible(false);
                
                //game.finished = true;
            }
        } else if(!playerInv.isEmpty() && newWord == "medkit" || newWord == "oxygen") {
            textOutArea.appendText("\n" + logic.useItem(newWord));
            logic.useItem(newWord);  
            playerInventory.getItems().remove(newWord);
            return;
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }
        
        hpBarAction();
        AirBarAction();
    }
    @FXML
    private void submitPassword(ActionEvent event){
        String answer = password.getText();
        String door = "";
        int pass = 0;
        if(password.getText()!=null && !password.getText().isEmpty() && logic.getCurrentRoomName().equals("armoury")){
            pass = Integer.parseInt(password.getText());
            if(pass==28374 && attempts > 0){
                textOutArea.appendText("\nAccess Granted");
                textOutArea.appendText("\nThe door slides open, leaving a opening to another room");
                secretDoor1.setVisible(true);
                logic.setOpenSecretExit("north", "notes");
                passwordPane.setVisible(false);
                logic.getCurrentRoom().getImmovable("panel").setFlag(false);
                searchButton.fire();
            } else if (attempts == 0) {
                textOutArea.appendText("\nAccess Denied"
                        + "\nSelf-Destruct imminent");
                logic.getCurrentRoom().getImmovable("panel").setFlag(false);
                door = "\nThe keypad is beyond repair";
                searchButton.fire();
            } else if(pass!=28374 && attempts > 0){
                textOutArea.appendText("\nAccess Denied"
                        + "\n" + attempts + "attempts remaining");
                attempts--;
            }
        }else if(password.getText()!=null && !password.getText().isEmpty()&&logic.getCurrentRoomName().equals("hallway") &&
                logic.getCurrentRoom().getImmovable("lockeddoor").getFlag()==true){
            textOutArea.appendText(logic.startQuiz(password.getText()));
            
        }
    }
    //This method controls how the player can attack, and what the player can attack
    @FXML
    private void attackFunction(ActionEvent event){
        for (INPC npc : logic.getCurrentRoomNPCList()) {
            if (npc.getName().equalsIgnoreCase("monster")){
                keyMonster = npc;
                continue;
            }      
        }
         for (IImmovable imov : logic.getCurrentRoomInteractList()) {
            if (imov.getName().equalsIgnoreCase("table")){
                table = imov;
                continue;
            }      
        }
        boolean control = false;
        String newWord = roomInventory.getSelectionModel().getSelectedItem();
        if (logic.getCurrentRoomNPCList().contains(keyMonster) && newWord == "monster") {
            String secondWord = "monster";
            logic.combat(secondWord);

            if (playerInv.contains("rifle") && control == false) {
                textOutArea.appendText("\nYou attacked the monster with your rifle for 40 damage.");
                textOutArea.appendText("\n" + logic.combat("rifle") /*game.combat(command), don't know if this replacement works*/);
                if (logic.getDefeated() == true && monsterDefeatCheck == false) {
                    monster.setVisible(false);
                    roomInv.add("key");
                    keyImg.setVisible(true);
                    monsterDefeatCheck = true;
                }
                control = true;
                hpBarAction();
                AirBarAction();
                listPropertyRoom.set(FXCollections.observableList(roomInv));
                roomInventory.itemsProperty().bind(listPropertyRoom);
                return;
            }
            else {
                textOutArea.appendText("\nNo rifle.");
            }
        minimapAction();
        }

        if(newWord == "table" && logic.getCurrentRoomInteractList().contains(table)){
            if (table.getDestructible() == true) {
            textOutArea.appendText("\nYou break the leg off the table \nA bunch of "
                    + "notes fall on the floor.");
            table.setDestructible(false); // since the immovable is broken, it can't be broken more.
            table.getItems().setFlag(true);
            roomInv.add(table.getItems().getName());
            breakableTable.setVisible(false);
            brokenTable.setVisible(true);
            tableLeg.setVisible(true);
            noteImg.setLayoutX(111);
            noteImg.setLayoutY(148);
            return;
            } else if(table.getDestructible()==false) {
                textOutArea.appendText("\nThe table is destroyed");
                return;
            }
        }
        else if (!logic.getCurrentRoomNPCList().contains(keyMonster)) {
            textOutArea.appendText("\nNo monster here.");
        }
        else {
            textOutArea.appendText("\nYou can't do that.");
        }     
        listPropertyRoom.set(FXCollections.observableList(roomInv));
        roomInventory.itemsProperty().bind(listPropertyRoom);
    }
    
    //Controls how the player HP bar functions
    private void hpBarAction(){
        double hpProgress = logic.getCurrentHP() / 100.0;
        HPbar.setProgress(hpProgress);
    }
    //Controls how the players air bar functions
    private void AirBarAction(){
        double airProgress = logic.getCurrentOxygen() / 100.0;
        AirBar.setProgress(airProgress);
    }
    //Updates the players airbar and HPbar
    @FXML
    private void statusButtonAction(){
        hpBarAction();
        AirBarAction();
    }
    //Controls how the splashscreen functions, and how it should respond if the 
    //player does something wrong
    @FXML
    private void splashScreenAction(ActionEvent event){
        String playerName = playerNameEnterField.getText();
        if (!playerName.equalsIgnoreCase("") && !playerName.equalsIgnoreCase(null)) {
            logic.setPlayerName(playerName);
            splashScreen.setVisible(false);
            medbay.setVisible(true);
            logic.setOxygen(100);
        }
        else {
            warningLabel.setVisible(true);
            warningLabel.setText("You need to input a name");
        }
    }
    @FXML
    private void highScoreLoad(ActionEvent event) {
        for (String i: logic.getHighscore()){   // takes all elements of highscorelist and ads them to a the observable list "highScoreView"
            highScoreView.add(i);
        }
        
        listProperty3.set(FXCollections.observableList(highScoreView));
        highScoreList.itemsProperty().bind(listProperty3);
    }
    //Displays and moves the different indicator on the games minimap, and controls
    //where and when the monster image must be displayed
    private void minimapAction() {
        String roomName = logic.getCurrentRoomName();
        if (roomName.equalsIgnoreCase("medbay")) {
            playerDot.setLayoutX(68);
            playerDot.setLayoutY(175);
        }
        else if (roomName.equalsIgnoreCase("keyRoom")) {
            playerDot.setLayoutX(78);
            playerDot.setLayoutY(129);
        }
        else if (roomName.equalsIgnoreCase("armoury")) {
            playerDot.setLayoutX(120);
            playerDot.setLayoutY(129);            
        }
        else if (roomName.equalsIgnoreCase("hallway")) {
            playerDot.setLayoutX(79);
            playerDot.setLayoutY(85);             
        }
        else if (roomName.equalsIgnoreCase("airlock")) {
            playerDot.setLayoutX(79);
            playerDot.setLayoutY(41);             
        }
        else if (roomName.equalsIgnoreCase("communicationRoom")) {
            playerDot.setLayoutX(105);
            playerDot.setLayoutY(86);             
        }  
        if (logic.getRoomNPCList("keyRoom").contains(keyMonster)) {
            monsterDot.setLayoutX(59);
            monsterDot.setLayoutY(129); 
                if (monsterDefeatCheck == true) {
                    keyRoomMonster.setVisible(true);
                }
       }
        else if (logic.getRoomNPCList("armoury").contains(keyMonster)) {
            monsterDot.setLayoutX(105);
            monsterDot.setLayoutY(130);
            armouryMonster.setVisible(true);
        }   
        else if (logic.getRoomNPCList("hallway").contains(keyMonster)) {
            monsterDot.setLayoutX(59);
            monsterDot.setLayoutY(85);
            hallwayMonster.setVisible(true);
        }                
        else if (logic.getRoomNPCList("airlock").contains(keyMonster)) {
            monsterDot.setLayoutX(59);
            monsterDot.setLayoutY(40);
            airlockMonster.setVisible(true);
        } 
        if (!logic.getRoomNPCList("keyRoom").contains(keyMonster)) {
            keyRoomMonster.setVisible(false);
        }
        if (!logic.getRoomNPCList("armoury").contains(keyMonster)) {
            armouryMonster.setVisible(false);
        }        
        if (!logic.getRoomNPCList("hallway").contains(keyMonster)) {
            hallwayMonster.setVisible(false);
        }
        if (!logic.getRoomNPCList("airlock").contains(keyMonster)) {
            airlockMonster.setVisible(false);
        }
    }
    
    @FXML
    private void loadButtonAction(ActionEvent event) {
        try {
            logic.loadGame();
            for (IItem i : logic.getPlayerInventory()){
                playerInv.add(i.getName());
            }
            listProperty2.set(FXCollections.observableList(playerInv));
            playerInventory.itemsProperty().bind(listProperty2);
            splashScreen.setVisible(false);
            roomChange();
            minimapAction();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void saveButtonAction(ActionEvent event) {
        logic.saveGame();
        if (logic.saveGame()) {
            textOutArea.appendText("\nGame saved successfully.");
        }
    }
    private void monsterAttack () {
        if (logic.getCurrentRoomNPCList().contains(logic.getCurrentRoomNPC("monster"))) {
            if (logic.getCurrentRoomNPC("monster").getHostility() && logic.getCurrentRoomNPC("monster").getDefeated()) {
                    textOutArea.appendText("\nThe monster attacks you for 12 damage.");
                    logic.combat("rifle");
            }
        }    
    }   
}
