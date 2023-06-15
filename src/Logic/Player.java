/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Acquaintance.IItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import static java.lang.Math.E;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Nick, Jonas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    private int hp;
    private int air;
    private ArrayList<Item> inventory;
    private boolean hasCalledHelp = false;
    private boolean wonGame = false;
    public String playerName = "Mads"; // Non-negotiable
    public int awesomePoints = 0;
    public int totalTimePlayed = 0;
    
    // public int currentHP = maxHP;
    
    // public int currentOxygen = maxOxygen; 
    

    @Override
    public String toString() {
        return "Player{" + "hp=" + hp + ", air=" + air + ", inventory=" + inventory.toString() + ", hasCalledHelp=" + hasCalledHelp + ", wonGame=" + wonGame + ", awesomePoints=" + awesomePoints + ", totalTimePlayed=" + totalTimePlayed + ", terminateThreads=" + terminateThreads + ", stopThreadOxygen=" + stopThreadOxygen + ", stopThreadHP=" + stopThreadHP + '}';
    }

    
    
    public Player() {
        
    }
    
     public Player(int newHP, int newAir) {
        this.hp = newHP;
        this.air = newAir;
        inventory = new ArrayList<>();
        oxygenTimer();
        totalTimePlayed();
    }
    
    public String getName() {
        return this.playerName;
    }

    public void setName(String playerName) {
       this.playerName = playerName;
    }
    
    
    //Return the players remaining air.
    public int getAir(){
    return air;
    }
    //Sets the players air. Adding to it or reducing it.
    public void setAir(int newAir){
    air = newAir;
    }
    //Returns the players HP.
    public int getHp(){
    return hp;
    }
    //Sets the players HP. Adding to it or reducing it.
    public void setHp(int hp){
    this.hp = hp;
    }
    public ArrayList<Item> getInventory(){
    return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        
        this.inventory = inventory;
       
    }
    
    
    public void addToInventory(Item newItem){
            inventory.add(newItem);          
    }
    public void removeFromInventory(IItem newItem){
    inventory.remove(newItem);
    }

    
    public boolean gethasCalledHelp(){
        return hasCalledHelp;
    }

    public void sethasCalledHelp(boolean hasCalledHelp) {
        this.hasCalledHelp = hasCalledHelp;
    }
    
    
    
    boolean gethasWonGame(){
        return this.wonGame;
    }
    void setWonGame(boolean b) {
        this.wonGame = true;
    }
    
    /* By Mads */
    public int getCurrentHP(){
        return hp;
    }
    
    public void setCurrentHP(int add, int sub){
        this.hp = hp + add - sub;
    }

    public int getCurrentOxygen(){
        return air;
    }
    public void setCurrentOxygen(int add, int sub){
        this.air = air + add - sub;
    }
    
   

    public int getTotalTimePlayed() {
        return totalTimePlayed;
    }

    public void setTotalTimePlayed(int totalTimePlayed) {
        this.totalTimePlayed = totalTimePlayed;
    }
    public void setPlayerName(String newName){
        this.playerName = newName;
    }

    public boolean isStopThreadOxygen() {
        return stopThreadOxygen;
    }

    public boolean isStopThreadHP() {
        return stopThreadHP;
    }
    
    
    
    /* 
    Subtracts one Oxygen-point per sek
    If currentOxygen == 0 => subtract one Hp per sek 
    */
    
    static Timer timerOxygen;
    static Timer timerHP;
    static Timer timePlayed;
    
    
    
    public boolean terminateThreads = false;
    private volatile boolean stopThreadOxygen = false;
    private volatile boolean stopThreadHP = false;

    public boolean isTerminateThreads() {
        return terminateThreads;
    }

    public void setTerminateThreads(boolean terminateThreads) {
        this.terminateThreads = terminateThreads;
    }
    
   
    public boolean isWonGame() {
        return wonGame;
    }

       
    
    
    public void terminateAllPlayerThreads() {
        stopThreadHP = true;
        stopThreadOxygen = true;
        terminateThreads = true;     
    }
    
    public void oxygenTimer(){
        
        /*Operation to increment counter*/
        TimerTask timerTaskOxygen = new TimerTask() { 
            @Override
            public void run() {
                // System.out.println("You have " + air + " oxygen remaining.");
                setCurrentOxygen(0,1);
                //currentOxygen--;
            }
        };
        
        final TimerTask timerTaskHP = new TimerTask() { 
            @Override
            public void run() {
                // System.out.println("you are at " + hp + " health");
                setCurrentHP(0,1);
                // currentHP--;
            }
        };
        
        /* Create thread to print counter value */
        
        Thread threadOxygen = new Thread(new Runnable(){
            
                public void terminateThreadOxygen() {
                    stopThreadOxygen = true;
                }
            
            @Override
            public void run() {
                while(!stopThreadOxygen || !terminateThreads){
                    try{
                        if (air == 0 ){
                            System.out.println("You've ran out of oxygen!!!");
                            timerOxygen.cancel();
                            timerHP.scheduleAtFixedRate(timerTaskHP, 30, 1000); // timerHP starts when timerOxygen stops.
                            break;
                        }
                        Thread.sleep(1000);
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        terminateThreadOxygen();
                    }
                }
            }
        });
        
        
        Thread threadHP = new Thread(new Runnable(){

            public void terminateThreadHP(){
                stopThreadHP = true;
            }        
        
            @Override
            public void run() {
                while(!stopThreadHP || !terminateThreads){
                    try{
                        if (hp == 0){
                            // System.out.println("You've ran out of health!");
                            //System.out.println("You died");
                            
                            timerHP.cancel();
                            
                            break;
                        }
                        Thread.sleep(1000);
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                        terminateThreadHP();
                    }
                }
            }
        });
    
        
        /* Builds the timers for oxygen & HP */
        timerOxygen = new Timer("MyTimerOxygen");
        timerOxygen.scheduleAtFixedRate(timerTaskOxygen, 30, 1000); 
        threadOxygen.start();

        timerHP = new Timer("MyTimerHP");
        threadHP.start();
        
    }

    
    /* Total play time. */
    public int totalTimePlayed(){
        
        class playedTimeCounter extends TimerTask {
            public void run() {
                totalTimePlayed++; 
            }
        }

        timePlayed = new Timer();
        timePlayed.schedule(new playedTimeCounter(), 10, 1000);
        
        return totalTimePlayed;
    }

    
    /* End game points */
    public int getAwesomePoint(boolean toldtoevacuate){
        int bonus = 0;
        if (toldtoevacuate){
            bonus = 100;
        }
        double A = 4.6;
        double B = 200;
        double doubleTimePoints = Math.pow(E, A - totalTimePlayed / B);
        int intTimePoints = ( (Double) Math.ceil( doubleTimePoints ) ).intValue() + bonus;

        // End game points 
        int awesomePoints = hp + air + intTimePoints;

        return awesomePoints;
    }

    public void setAwesomePoints(int awesomePoints) {
        this.awesomePoints = awesomePoints;
    }
    
    
    /* Kills of threads */
    public void terminateAllPlayerTimers(){
        timerOxygen.cancel();
        timerHP.cancel();
        timePlayed.cancel();
    }  
}
