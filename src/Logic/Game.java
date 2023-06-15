package Logic;

import Acquaintance.IImmovable;
import Acquaintance.IItem;
import Acquaintance.INPC;
import Acquaintance.IRoom;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
// the Class that contains the specifics in the game and assigns values to the initialized constructors
public class Game {

    private String secondWord;  // refractior of the youse of the second word, its now d´the command given from the gui, the word have not been changed due to legacy reasons, alot of the logig resoved aroud this word therefor it was kept
    Room currentRoom;   // initialises a starting room
    Player player = new Player(100, 100);
    int inventorySpace = 2;
    private String dmgText;
    SaveFile save;
    
    
    private boolean monsterDefeatCheck = false;
    
    
    Room medbay, keyRoom, armoury, hallway, communicationRoom, airlock;
    ArrayList<IRoom> roomList = new ArrayList<>();
    Immovable counter, device, table, weaponCabinet, bookcase, 
            hiddenpanel, closet, lockedDoor, glassCabinet, airlockPanel, 
            doorLockPanel, radioArray;
    NPC britney, keyMonster;
    Item medkit, oxygen, gun, rifle, key, notes;
    Boolean question1 = false, question2 = false, question3 = false;
    private int enterRoomCounter1, enterRoomCounter2 = 0;

    // constructor for the game class    
    public Game() throws IOException {
        createRooms();
    }

    //initializing of the rooms objects needed in the game 
    public void createRooms() {   //asigning the room objects

        // The initialication of the room objects
        medbay = new Room("medbay", LogicFacade.getDescriptionText("medbay"));
        keyRoom = new Room("keyRoom", LogicFacade.getDescriptionText("keyRoom"));
        armoury = new Room("armoury", LogicFacade.getDescriptionText("armory"));
        hallway = new Room("hallway", LogicFacade.getDescriptionText("hallway"));
        communicationRoom = new Room("communicationRoom", LogicFacade.getDescriptionText("communicationRoom"));
        airlock = new Room("airlock", LogicFacade.getDescriptionText("airlock"));
        roomList.add(medbay);
        roomList.add(keyRoom);
        roomList.add(armoury);
        roomList.add(hallway);
        roomList.add(communicationRoom);
        roomList.add(airlock);
        
        
       setExits();

        /* Creating and setting immovables for all the rooms */
        counter = new Immovable("counter", "A medical counter. There's a medkit on the countertop.", "You can't use this.", false, false);
        device = new Immovable("device", "A strange medical device. There's an oxygen tank attatched to it", "you don't know how to use this.", false, false);

        hiddenpanel = new Immovable("panel", "Looks just like all the other panels, but with closer inspection "
                + "you find a small keypad near the floor. you might be able to find a code somewhere...", "The keypad flashes green and the panel slides to the side, opening up a hidden passage", false, true);
        table = new Immovable("table", "A small table. There are a bunch of notes on top", "You can't use this.", true, false);
        weaponCabinet = new Immovable("cabinet", "A weapon cabinet. There seems to be something inside", "It's locked", false, true);
        bookcase = new Immovable("bookcase", "A bookcase. There are no books left in it.", "You move the bookcase to the side, and unveil hole in the wall.", false, false);

        closet = new Immovable("closet", "A tall closet.", "You open the closet, and a bunch of knives slide out. One of them hits your oxygen line.", false, false);
        lockedDoor = new Immovable("lockeddoor", "A huge metal door", "The door is locked shut, however you can hear someone inside...", false, true);

        glassCabinet = new Immovable("cabinet", "A glass cabinet. There is an oxygen tank inside.", "You open the cabinet.", false, false);
        airlockPanel = new Immovable("switch", "A large red switch with a red light beside it. ", "You press the switch. The light turns red", false, false);

        doorLockPanel = new Immovable("panel", "A panel with a single lever on it. A label says \" door lock\" ", "You pull the lever, and a loud clunk is heard.", false, false);
        radioArray = new Immovable("radio", "A radio array. Maybe you can use this to call for help.", "Nothing happens, maybe the empty slot named keymodule has something to do with it", false, false);

        medbay.setImmovables(counter);
        medbay.setImmovables(device);

        armoury.setImmovables(table);
        armoury.setImmovables(weaponCabinet);
        armoury.setImmovables(bookcase);
        armoury.setImmovables(hiddenpanel);

        hallway.setImmovables(lockedDoor);
        hallway.setImmovables(closet);

        airlock.setImmovables(glassCabinet);
        airlock.setImmovables(airlockPanel);

        communicationRoom.setImmovables(doorLockPanel);
        communicationRoom.setImmovables(radioArray);

        /* Adding NPCs to rooms*/
        britney = new NPC("britney", "A blonde woman.", false, true);
        keyMonster = new NPC("monster", "A large alien creature.", true, false);

        communicationRoom.addNPC(britney);
        keyRoom.addNPC(keyMonster);
        keyMonster.setDamage(12);

//      Items bliver initialiseret
        medkit = new Item("medkit", "A medkit that can heal the user upon use.", 0, 40, 0);
        oxygen = new Item("oxygen", "An oxygen tank, that can refill the users own oxygen tank.", 0, 0, 35);
        rifle = new Item("rifle", "A rifle. It does 40 dmg.", 40, 0, 0);
        key = new Item("key", "A small electronic device with keymodule printed on it", 0, 0, 0);
        notes = new Item("notes", "The note reads: '...but if you want to get through the secret door, the password is 28374", 0, 0, 0);
        keyMonster.addItem(key);
//      Items bliver sat i de forskellige immovables, i de forskellige rum:
//      Medbay items
        counter.setItems(medkit);
        device.setItems(oxygen);

//      keyroom items:
//      Armoury items:
        weaponCabinet.setItems(rifle);
//        bookcase.setItems(sword);
        table.setItems(notes);
        notes.setFlag(false);
//      Hallway items:
//      Communicationroom items:
//      Airlock room items:
        glassCabinet.setItems(oxygen);

        //the current room is assigned a room object
        currentRoom = medbay;
    }

       private boolean loseCondition() {
        if (player.getHp() <= 0) {
            return true;
        }
        return false;
    }

    //Method that print the welcome + long description information when game is started
    public String printWelcome() {
        return "Welcome to Mads Effect! Mads Effect is a new, incredibly exciting space-adventure game. \n"+ currentRoom.getLongDescription();
    }

   

    // method for printing help, 
    public String printHelp() {
        return "You are lost. You are alone. You wander around an abandoned spaceship.\n If you want to do something with anything remember to click it in the list on the right. ";
    }

    //method for moving between rooms
    public String goRoom(String secondWord) {

        String direction = secondWord; //direction is sat to be the second word from the Parser
        System.out.println(" go: " + secondWord);
        currentRoom.printHashmap();

        Room nextRoom = currentRoom.getRoomDirectionExit(direction);//initiates a new room object based on the Exit hashmap
        
        if (nextRoom == null) {                     //if no roomobject is found in the exit HashMap
            System.out.println("There is no door!");// this line is printed
            return "\nThere is no door!";
        } else {
            currentRoom = nextRoom;                                // else the new room is sat to be currentRoom
            System.out.println(currentRoom.getLongDescription());   // and the long description is printed
            return currentRoom.getLongDescription();
        }
    }

    // the quit method that returns wantToQuit true if quit has ben sent from the Parser
//    private boolean quit(Command command) {
//        if (command.hasSecondWord()) {           // if the Parser sends a second word along with quit
//            System.out.println("Quit what?");   //this line is printed and wantToQuit returns false
//            return false;
//
//        } else {
//            player.terminateAllPlayerThreads();
//            player.terminateAllPlayerTimers();
//            return true;
//        }
//    }

    //Returns the description of the word after the commandWord.
    public String getItemDescription(String secondWord) {
        String inspectString = "";
        String item = secondWord;
//        if (command.hasSecondWord()) {
            if (item.equalsIgnoreCase("medkit")) {
                inspectString = medkit.getDescription();
            }
            else if (item.equalsIgnoreCase("oxygen")) {
                inspectString = oxygen.getDescription();
            }
            else if (item.equalsIgnoreCase("rifle")) {
                inspectString = rifle.getDescription();
            }
            else if (item.equalsIgnoreCase("key")) {
                inspectString = key.getDescription();
            }
            else if (item.equalsIgnoreCase("notes")) {
                inspectString = notes.getDescription();
            }
//            else if (item.equalsIgnoreCase("sword")) {
//                inspectString = sword.getDescription();
//            }
            for (IItem i : player.getInventory()) {
                if (i.getName().equals(item)) {
                    inspectString = i.getDescription();
                } 
            }
            for (IImmovable i : currentRoom.getInteractList()) {
                if (i.getName().equals(item)) {
                   inspectString = i.getDescription();
                }
//                else if (i.getItems().getName().equals(item)) {
//                    inspectString = i.getItems().getDescription();
//                }
            }
            for (INPC n : currentRoom.getNPCList()) {
                if (n.getName().equals(item)) {
                    inspectString = n.getDescription();
                } 
            }
            // These lines mess up the inspect button in the GUI
            /*if (item != currentRoom.getInteractList().toString() && item != player.getInventory().toString()) {
                //System.out.println("You can't inspect that!");
                inspectString = "You can't inspect that!";
            }*/
//            }
//        else {
//            //Hvis der ikke er to ord, understående bliver printet og man
//            //bliver bedt om at prøve igen.
//            inspectString = "Which item?";
//        }
        for (IImmovable i : currentRoom.getInteractList()) {
            if (i.getName().equals(item)) {
                //System.out.println(i.getDescription());
                inspectString = i.getDescription();
            }
        }
        for (INPC n : currentRoom.getNPCList()) {
            if (n.getName().equals(item)) {
                //System.out.println(n.getDescription());
                inspectString = n.getDescription();
            }
        }

        return inspectString;
    }

    //Breaks the specified object by running the breakTable method
    public void breakObject() {
        Item notes = new Item("notes", "The notes have a series of numbers written"
                + " on it. The numbers are 28374. You should probably "
                + "remember them.", 0, 0, 0);
        currentRoom.addItem(notes);

    }

    //Adds the item comming after the commandWord to the players inventory.
    public void addInventory(String secondWord) {
        String object = secondWord;
        if (secondWord.isEmpty()) {
            System.out.println("Take what?");
            return;
        }

        for (IImmovable i : currentRoom.getInteractList()) {
            if (player.getInventory().size() < inventorySpace) {
                if (i.getItems() != null && i.getItems().getName().equals(object)) {
                    System.out.println("You have added " + i.getItems().getName() + " to your inventory.");
                    player.addToInventory((Item) i.getItems());
                    i.takeItem();
                    return;
                }
            }
        }
        for (IImmovable i : currentRoom.getInteractList()) {
            if (i.getItems() != null && player.getInventory().size() >= inventorySpace && i.getItems().getName().equals(object)) {
                System.out.println("No more space in the inventory!");
                return;
            }
        }

        for (IItem i : currentRoom.getItemList()) {
            if (player.getInventory().size() < inventorySpace) {
                if (currentRoom.getItem(i.getName()) != null && i.getName().equals(object)) {
                    System.out.println("You have added " + object + " to your inventory.");
                    player.addToInventory((Item) currentRoom.getItem(i.getName()));
                    currentRoom.removeItem(i);
                    return;
                }
            }
        }
        System.out.println("There is no " + object + " here");
    }

//    private void search(Command command) {
//        String closet = "closet";
//        if (!command.hasSecondWord()) {
//            //If there is only one word, this will be printed
//            currentRoom.searchRoom();
//            currentRoom.getItemList();
//            currentRoom.getNPCList();
//            return;
//        }
//        String searchTarget = command.getSecondWord();
//
//        for (IImmovable i : currentRoom.getInteractList()) {
//            if (i.getName().equalsIgnoreCase(closet) && searchTarget.equalsIgnoreCase(closet)) {
//                System.out.println(i.getDescription());
//                System.out.println(i.getUseDescription());
//                player.setAir(player.getAir() - i.getItemDmg());
//                return;
//            } else if (i.getItems() != null && searchTarget.equals(i.getName())) {
//                System.out.println("You search the " + i.getDescription());
//                System.out.println("You found the following in the " + searchTarget);
//                System.out.println(i.getItems().getName());
//                return;
//            }
//        }
//        System.out.println("You found nothing searching the " + searchTarget);
//    }

//    private void unlockDoor(Command command) {
//        if (!command.hasSecondWord()) {
//            //Hvis der ikke er to ord, understående bliver printet og man
//            //bliver bedt om at prøve igen.
//            System.out.println("Unlock what?");
//            return;
//        }
//        if (command.getSecondWord().equals("panel") && currentRoom.getImmovable("panel").getFlag() == true) {
//            System.out.println("You go down to the small keypad");
//            System.out.println("'Please type in the password'");
//            System.out.println("'You have 3 attempts'");
//            Scanner scanner = new Scanner(System.in);
//            int code = 0;
//            int attempts = 3;
//            while (true) {
//                code = scanner.nextInt();
//                if (attempts > 0 && code == 28374) {
//                    System.out.println("'Acces granted'");
//                    System.out.println("The panel slides to the side, opening up");
//                    currentRoom.getImmovable("panel").setFlag(false);
//                    currentRoom.setExit("north", currentRoom.getSecretDestination("notes"));
//                    currentRoom.getImmovable("panel").setDescription("Where the once was a panel, there now is a "
//                            + "small opening to another room");
//                    break;
//                } else if (attempts == 0) {
//                    System.out.println("'Out of attempts, the codelock will now"
//                            + "self-destruct'");
//                    currentRoom.getImmovable("panel").setFlag(false);
//                    break;
//                } else if (attempts > 0 && code != 28374) {
//                    System.out.println("Wrong pin");
//                    attempts--;
//                    System.out.println("'You have " + attempts + " attempts left'");
//                }
//
//            }
//        } else if (command.getSecondWord().equals("panel") && currentRoom.getImmovable("panel").getFlag() == false) {
//            System.out.println("The codelock is broken beyond repair...");
//        }
//    }

//    private void quizToOpenDoor(Command command) {
//        if (!command.hasSecondWord()) {
//            //Hvis der ikke er to ord, understående bliver printet og man
//            //bliver bedt om at prøve igen.
//            System.out.println("Unlock what?");
//            return;
//        }
//        if (command.getSecondWord().equals("lockeddoor") && currentRoom.getImmovable("lockeddoor").getFlag() == true) {
//            System.out.println("'What do you want?' A female voice questions");
//            System.out.println("'Listen i dont even know if you're human, so you have to answer my questions"
//                    + " correctly or you aint getting in here");
//            Scanner scanner = new Scanner(System.in);
//            String answer;
//            System.out.println("Do you even know how to speak english?");
//            answer = scanner.nextLine();
//            if (!answer.equals("yes")) {
//                System.out.println("Yea fuck off monster");
//                currentRoom.getImmovable("lockeddoor").setDescription("'No, you aint fooling me monster. Get out of here'");
//                currentRoom.getImmovable("lockeddoor").setFlag(Boolean.FALSE);
//                return;
//            }
//            System.out.println("What is 2 + 2?");
//            answer = scanner.nextLine();
//            if (!answer.equals("4")) {
//                System.out.println("Yea fuck off monster");
//                currentRoom.getImmovable("lockeddoor").setDescription("'No, you aint fooling me monster. Get out of here'");
//                currentRoom.getImmovable("lockeddoor").setFlag(Boolean.FALSE);
//                return;
//            }
//            System.out.println("What is our main objective?");
//            answer = scanner.nextLine();
//            if (!answer.equals("to survive")) {
//                System.out.println("Yea fuck off monster");
//                currentRoom.getImmovable("lockeddoor").setDescription("'No, you aint fooling me monster. Get out of here'");
//                currentRoom.getImmovable("lockeddoor").setFlag(Boolean.FALSE);
//                return;
//            }
//            System.out.println("'I guess you're alright. Get in fast'");
//            currentRoom.setExit("east", currentRoom.getSecretDestination("quiz"));
//            currentRoom.getImmovable("lockeddoor").setDescription("The door is now unlocked and open");
//        } else if (command.getSecondWord().equals("lockeddoor") && currentRoom.getImmovable("lockeddoor").getFlag() == false) {
//            System.out.println(currentRoom.getImmovable("lockeddoor").getDescription());
//        }
//    }

    /**Controls what is removed from the players inventory based on what the player
    has selected from their inventory.*/

    public String removeFromInventory(String secondWord) {
        String object = secondWord;
        String returnLn;
        if (secondWord.isEmpty()) {
            returnLn = "Drop what?";
            return returnLn;
        }

        for (IItem i : player.getInventory()) {
            if (i.getName().equalsIgnoreCase(object)) {
                currentRoom.setItem((Item) i);
                returnLn = "You put " + i.getName() + " on the floor.";
                player.removeFromInventory(i);
                return returnLn;
            }
        }
        if (object != player.getInventory().toString()) {
            returnLn = "Can't drop that!";
            return returnLn;
        }
        return null;
    }

    // a command that prints out the status, of the player <----- Denne metode er sådan set useless---------------------------------
    private void checkStatus() {
        System.out.println("Your air tank is at: " + player.getAir() + "%");
        System.out.println("Your current HP is: " + player.getHp());

    }

    // This method, handles using ones items, that are in the players inventory
    public String useItem(String secondWord) {
        String object = secondWord;
        int air = player.getAir();
        int HP = player.getHp();
        String medkit = "medkit";
        String oxygen = "oxygen";
        if (secondWord.isEmpty() && secondWord==null) {
            System.out.println("Use what?");
            return "Use what?";
        }
        // it checks for the items in the inventory
        for (IItem i : player.getInventory()) {
            //here it checks if, an item is in the inventory
            if (object.equalsIgnoreCase("keymodule") && radioArray.getFlag() == false && currentRoom == communicationRoom) {
                radioArray.setFlag(true);
                player.removeFromInventory(i);
                System.out.println("You take the keymodule and slot it into the radio, as soon as the module clicks in place the radio come to life with a static buzz.");
                return "You take the keymodule and slot it into the radio, as soon as the module clicks in place the radio come to life with a static buzz.";
            }
            if (i.getName().equalsIgnoreCase(object)) {
                //here it checks an statement, that checks for either the 
                // players hp or air, for the item that the user wants to use, 
                // and then uses the item, if the statement is fulfilled, and 
                // removes the item from the users inventory
                if (air < 65 && i.getName().equalsIgnoreCase(oxygen)) {
                    System.out.println("You used the " + object + ". It gave you " + i.getAir() + " air.");
                    player.setAir(air + i.getAir());
                    player.removeFromInventory(i);
                    return "You used the " + object + ". It gave you " + i.getAir() + " air.";
                } 
                if (HP < 60 && i.getName().equalsIgnoreCase(medkit)) {
                    System.out.println("You used the " + object + ". It gave you " + i.getHP() + " HP.");
                    player.setHp(HP + i.getHP());
                    player.removeFromInventory(i);
                    return "You used the " + object + ". It gave you " + i.getHP() + " HP.";
                } 
                //these two, makes sure that, when the player uses a medkit/
                // oxygen, that the players air/HP cant go over 100
                else if (air > 65 && air != 100 || HP > 60 && HP != 100) {
                    if (i.getName().equalsIgnoreCase(medkit)) {
                        System.out.println("You used the: " + object);
                        player.setHp(100);
                        player.removeFromInventory(i);
                        return "You used the " + object + ". It gave you " + i.getHP() + " HP.";
                    }
                    else if (i.getName().equalsIgnoreCase(oxygen)) {
                        System.out.println("You used the: " + object);
                        player.setAir(100);
                        return "You used the " + object + ". It gave you " + i.getAir() + " air.";
                    }
                } 
                //Here it checks, if the players hp or air is already full, 
                // that the player cant use the medkits or oxygen tanks.
                else if (air >= 100 && i.getName().equalsIgnoreCase(oxygen)) {
                    System.out.println("Your oxygen-tank is already full");
                    return "Your oxygen-tank is already full";
                } else if (HP >= 100 && i.getName().equalsIgnoreCase(medkit)) {
                    System.out.println("Your HP is already full");
                    return "Your HP is already full";
                }
                // use the key at the radio to get it to be able to call help. 
                // And removes the key from inventory.
            }
        }
        // usikker på om jeg stadig har brug for denne failsafe
        for (IItem i : player.getInventory()) {
            if (!object.equalsIgnoreCase(i.getName())) {
                System.out.println("Use what??");
                return "Use what?";
            }
        }
        // usikker på om jeg har brug for denne failsafe, eller den lige over
        System.out.println("That item isnt in your inventory");
        return "That item isn't in your inventory!";
    }

    public void monsterTravel() {
        int rngRoom = (int) (4 * Math.random());
        if (keyMonster.getMovability() && keyMonster.getHostility()) {
            ArrayList<Room> allowedRooms = new ArrayList<>();
            allowedRooms.add(airlock);
            allowedRooms.add(hallway);
            allowedRooms.add(keyRoom);
            allowedRooms.add(armoury);
            allowedRooms.add(medbay);
            for (Room r : allowedRooms) {
                r.removeNPC(keyMonster);
            }
            keyMonster.setHealth(200);
            allowedRooms.get(rngRoom).addNPC(keyMonster);
        }
      }

//    private boolean activate(Command command) {
//        // failsafe for if there is no second word
//        String object = command.getSecondWord();
//        if (!command.hasSecondWord()) {
//            System.out.println("Activate what?");
//            return false;
//        }
//        // this for loop helps to only acces imovables that are in the current room
//        for (IImmovable i : currentRoom.getInteractList()) {
//
//            // logic for what hapends when switch is activated
//            if (command.getSecondWord().equals("switch") && i.getName().equals("switch")) {
//                if (player.hasCalledHelp() == true) {                                       // the call for help bool is changed when the radio in com-room is used
//                    player.setWonGame(true);
//                    System.out.println(LogicFacade.getDescriptionText("airlocksuccess"));
//                    return true;
//
//                } else {
//                    System.out.println(LogicFacade.getDescriptionText("airlockfail"));
//                    return true;
//
//                } //logic for what hapends when radio is activated
//            } else if (command.getSecondWord().equals("radio") && i.getName().equals("radio")) {
//                if (currentRoom.getImmovable("radio").getFlag() == false) {
//                    System.out.println(currentRoom.getImmovable("radio").getUseDescription());
//                    return false;
//
//                } else if (currentRoom.getImmovable("radio").getFlag() == true) {
//                    System.out.println("You use the radio to call for help, a nerby spacecaft responds to your sos and wil be there to pick you up shortly.");
//                    player.setCallHelp(true);
//                    return false;
//                }
//                return false;
//            }
//
//        }
//        System.out.println("there is no " + object + " here");
//        return false;
//    }

    public String awakenMonster() {
        if (keyMonster.getMovability() == false && currentRoom.getName().equals("keyRoom")) {
            keyMonster.setMovability(false);
            System.out.println("The monster awakens and growls at you, but it doesn't attack..");
            return "The monster awakens and growls at you, but it doesn't attack..";
        }
        return "";
    }
    public String startQuiz(String answer) {
        String response = "";
        if (currentRoom.getImmovable("lockeddoor").getFlag() == true && !question1) {
            response = "'What do you want?' A female voice questions" + "\n'Listen i dont even know if you're human, "
                    + "so you have to answer my questions"
                    + " correctly or you aint getting in here"
                    + "\nDo you even know how to speak english?"
                    + "\nWhat is 2 + 2?\"";
            question1 = true;
        return response;
        }
        if (answer.equals("4") && question1 && !question2) {
            response = "\nWhat do we need to do?";
            question2 = true;
            return response;
        } else if (answer.equals("survive") && question1 && question2){
            response = "\n'I guess you're alright. Get in fast'";
            currentRoom.setExit("east", currentRoom.getSecretDestination("quiz"));
            currentRoom.getImmovable("lockeddoor").setDescription("The door is now unlocked and open");
            currentRoom.getImmovable("lockeddoor").setFlag(false);
            
        } else if (!answer.equals("4") || !answer.equals("survive"))
            response="\nYea fuck off monster";
            currentRoom.getImmovable("lockeddoor").setDescription("'No, you aint fooling me monster. Get out of here'");
            currentRoom.getImmovable("lockeddoor").setFlag(Boolean.FALSE);
            return response;
        }
    public String combat(String secondWord) {
        if (currentRoom.getNPC("monster") == keyMonster) {
            System.out.println("You are attacked by the monster!");
            boolean yourTurn = true;
            while (true) {
                if (secondWord.equalsIgnoreCase("flee") && yourTurn == true) {
                    player.setAir(player.getAir() - 40);
                    currentRoom.removeNPC(keyMonster);      //is it correct to remove the keaymonster when you flee?
                    return "You fled from battle but lost a lot of oxygen /n The monster moved to another room";
                } 
                else if (secondWord.equalsIgnoreCase("status") && yourTurn == true) {
                    checkStatus();
                } 
                else if (yourTurn == true) {
                    for (IItem i : player.getInventory()) {
                        if (secondWord.equalsIgnoreCase(i.getName())) {
                            keyMonster.setHealth(keyMonster.getHealth() - i.getDmg());

                            yourTurn = false;
                            dmgText = "You attacked the monster with "
                                    + i.getName() + " and damaged it for "
                                    + i.getDmg();
                            break;
//                            continue;
                        }
                        else {
                            yourTurn = false;
                            return "You are using " + i.getName();
                        }
                    }
                }
                if (keyMonster.getHealth() <= 0) {
                    System.out.println("The monster is defeated");
                    keyMonster.setHostility(true);
                    keyMonster.setMovability(true);
                    keyMonster.setDefeated(true);
                    if (keyMonster.getDefeated() && keyMonster.getItem().getName().equalsIgnoreCase("key") && monsterDefeatCheck == false) {
                        System.out.println("A key drops from the monsters corpse"
                                + " and unto the floor");
                        currentRoom.addItem((Item) keyMonster.getItem());
                        currentRoom.removeNPC(keyMonster);
                        //break;
                        monsterDefeatCheck = true;
                        return "\nThe monster is defeated! \nA key drops from the monsters corpse"
                                + " and unto the floor";
                    } 
                    else if (keyMonster.getDefeated()) {
                        currentRoom.removeNPC(keyMonster);
                        break;
                    }
                } 

                System.out.println(yourTurn);
                if (yourTurn == false) {
                    player.setHp(player.getHp() - keyMonster.getDamage());
//                    System.out.println("The monster damages you for "
//                            + keyMonster.getDamage());
                    yourTurn = true;
                    if (player.getCurrentHP() <= 0) {
                        break;
                    }
                    return "The monster damages you for " + keyMonster.getDamage();
                }
            }
        }

        if (keyMonster.getDefeated() == true) {
            return "\nThe monster is defeated!";
        } 
        else {
            return dmgText;
        }
    }


// method for the commandword talk
    public String talk(String secondWord) {
        String talkString = "";
        if (!secondWord.isEmpty()) { //What happens if no second word is given
            talkString = LogicFacade.getDescriptionText("talkNoArgument");
            // logic for how britneay responds
            // maby current room argument can be omittet? 
        } else if (secondWord.equalsIgnoreCase("britney") && currentRoom.getNPC("britney") == britney) {
            if (currentRoom == communicationRoom && currentRoom.getImmovable("radio").getFlag() == false) { //responds befor radi is fixed
                talkString = LogicFacade.getDescriptionText("britney1");
            } else if (currentRoom == communicationRoom && currentRoom.getImmovable("radio").getFlag() == true && player.gethasCalledHelp() == false) { //respons after radi is fixed
                talkString = LogicFacade.getDescriptionText("britney2");
            } else if (currentRoom == communicationRoom && player.gethasCalledHelp()) {    //responds after help is called sets evacuate boolean true
                talkString = LogicFacade.getDescriptionText("britney3");
                britney.setToldToEvacuate(Boolean.TRUE);
            } else if (currentRoom == airlock) {    // response in airlock
                talkString = LogicFacade.getDescriptionText("britney4");
            } else {
                talkString = "Britney isn't here.";
            }
        } else {    // respans for all other posible second words (EVERYTHIN THAT IS INPUTTET INTO THE CONSOLE AS SECONDWORD)
            talkString = "You are trying to talk to something that can't respond. Maybe the lack of oxygen is affecting your brain.";
        }
        return talkString;
    }

    public String roomLogic() {
        /* This method is meant to hold all the logic that should be checked for
        ** each command cycle. These checks will run without needing any user 
        ** input
         */
        // All rooms
        
       String returnString = null;
        
        // medbay
        if (currentRoom == medbay) {
            if (counter.getItems() == null) {
                counter.setDescription("A medical counter.");
            }
            if (device.getItems() == null) {
                device.setDescription("A strange medical device.");
            }
        }

        // keyRoom
        if (currentRoom == keyRoom) {
            if (keyMonster.getDefeated()) {
                currentRoom.removeNPC(keyMonster);
            }
        }

        // armoury
        if (currentRoom == armoury) {
            if (!currentRoom.getImmovable("table").getDestructible()) {
                currentRoom.getImmovable("table").setDescription("There is a "
                        + "leg missing from the table");
            }
        }

        // communicationRoom
        if (currentRoom == communicationRoom) {
            // Positive NPC response
            if (!doorLockPanel.getFlag() && currentRoom.getFirstTimeEntered()) {
                returnString = "Britney: Hi " + player.getName() + ", so "
                        + "great to see you're alright. Can you help me with "
                        + "this radio? we need a key for it to call for help.";
                communicationRoom.setFirstTimeEntered(false);
            } // Negative NPC response
            else if (currentRoom.getImmovable("panel").getFlag()
                    && currentRoom.getFirstTimeEntered()) {
                returnString = "Britney: Oh my god, " + player.getName()
                        + " i almost shot you! Do something useful for a change "
                        + "and fix this radio already!";
                communicationRoom.setFirstTimeEntered(false);
            }
            if (player.gethasCalledHelp() && britney.gettoldToEvacuate()
                    && currentRoom.getNPCList().contains(britney)) {
                currentRoom.removeNPC(britney);
            }
        }

        // hallway
        if (currentRoom == hallway) {
            if (currentRoom.getImmovable("closet").getFlag()) {
                currentRoom.getImmovable("closet").setUseDescription("Probably "
                        + "shouldn't have opened this.");
            }
        }

        // airlock
        if (currentRoom == airlock) {
            if (player.gethasCalledHelp()) {
                currentRoom.getImmovable("switch").setUseDescription("You press"
                        + " the switch. The light turns green");
                // Message printed after help has been called
                if (!britney.gettoldToEvacuate()) {
                    for (; enterRoomCounter1 < 1; enterRoomCounter1++) {
                        returnString = "Another ship has been attached to the "
                                + "airlock";
                    }
                }
                else {
                    for (;enterRoomCounter2 < 1; enterRoomCounter2++) {
                        currentRoom.addNPC(britney);
                        System.out.println("Britney: There you are! The help has "
                            + "already arrived, now let's get off this ship!");
                    }
                }    
            }            
        }
        return returnString;
        // end of method.
    } 
    
    ArrayList<IRoom> getRoomList(){
        return roomList;
    }
       public Room getCurrentRoom() {
        return currentRoom;

    }

    Room getMedbay() {
        return medbay;
    }

    public Room getKeyRoom() {
        return keyRoom;
    }

    public Room getArmoury() {
        return armoury;
    }

    public Room getHallway() {
        return hallway;
    }

    public Room getCommunicationRoom() {
        return communicationRoom;
    }

    public Room getAirlock() {
        return airlock;
    }
    
    public Room getRoomPos(){
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        for (IRoom r: roomList){
            if (r.getName().equalsIgnoreCase(currentRoom)){
                this.currentRoom = (Room) r;
            }
    }
    }

    public void setPlayer(Player player) {
        this.player = player;
        
    }

    public void setMedbay(Room medbay) {
        this.medbay = medbay;
    }

    public void setKeyRoom(Room keyRoom) {
        this.keyRoom = keyRoom;
    }

    public void setArmoury(Room armoury) {
        this.armoury = armoury;
    }

    public void setHallway(Room hallway) {
        this.hallway = hallway;
    }

    public void setCommunicationRoom(Room communicationRoom) {
        this.communicationRoom = communicationRoom;
    }

    public void setAirlock(Room airlock) {
        this.airlock = airlock;
    }
    
    public int getPlayerHP(){
        return player.getHp();
    }
    //A method, that sets alle the exits, to the room, with the corresponding key and value, in the hashmap
    void setExits(){
         // assigning the room exits by using the exits HashMap to couple a sting "direction" with a room object
        medbay.setExit("north", keyRoom);

        keyRoom.setExit("south", medbay);
        keyRoom.setExit("east", armoury);
        keyRoom.setExit("north", hallway);

        //armoury.setExit("north", communicationRoom);  Can only be accessed after the secret passage is found
        armoury.setExit("west", keyRoom);

        //hallway.setExit("east", communicationRoom); Can only be accessed after getting quiz right, or unlocking from the inside
        hallway.setExit("north", airlock);
        hallway.setExit("south", keyRoom);

        //communicationRoom.setExit("west", hallway); Can only be accessed after unlocking the door (lever)
        communicationRoom.setExit("south", armoury);
        communicationRoom.setExit("west", hallway);

        airlock.setExit("south", hallway);
        //creating immovables

        //Exits and pathways that need to be unlocked
        armoury.addSecretExit("notes", communicationRoom);
        hallway.addSecretExit("quiz", communicationRoom);
        communicationRoom.addSecretExit("lever", hallway);
    }
}
