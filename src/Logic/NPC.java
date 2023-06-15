package Logic;

/**
 * @author Wilde
 */

import Acquaintance.IItem;
import Acquaintance.INPC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class NPC implements INPC {

    private Item item;
    private String name = "";
    private String description = "";
    private boolean hostile = false; 
    private boolean movable = false;
    private int health = 100;
    private int baseDamage = 100;
    private ArrayList<Item> inventory;
    
    private boolean toldToEvacuate = false;
    private boolean defeated = false;
    
    
     public NPC() {
       
        }
    /**
     *
     * @param name The name of the NPC, this is the name that will be written in commands to interact.
     * @param description the description of the NPC given upon inspection.
     * @param hostile The hostility of the NPC. true if hostile, false if not hostile. Can be set.
     * @param movable If the NPC can move from the room.
     */
    public NPC(String name, String description, boolean hostile, boolean movable){
        this.movable = false;
        this.name = name;
        this.description = description;
        this.hostile = hostile;
        this.movable = movable;
        this.health = 100;
        this.baseDamage = 0;
       
        this.toldToEvacuate = false;
        this.defeated = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setToldToEvacuate(boolean toldToEvacuate) {
        this.toldToEvacuate = toldToEvacuate;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public boolean getHostility(){
        return this.hostile;
    }
    
    @Override
    public void setHostility(boolean hostile){
        this.hostile = hostile;
    }
    
    @Override
    public boolean getMovability(){
        return this.movable;
    }
    
    @Override
    public void setDamage(int dmg) {
        this.baseDamage = dmg;
    }
    
    @Override
    public int getDamage() {
        return this.baseDamage;
    }
    
    @Override
    public void addItem(IItem item) {
        this.item = (Item) item;    // have to cast the IItem object into a Item object;
    }
    
    @Override
    public IItem getItem() {
        return (IItem) item;
    }
    
    public void setHealth(int hp){
        this.health = hp;
    }
    @Override
    public int getHealth(){
        return this.health;
    }
    
    public void takeHit(int hit) {
        if (this.health > 0) {
            this.health -= hit;
        }
        if (this.health <= 0) {
            System.out.println(this.name + " has been killed.");
        }
    }

    public void setMovability(Boolean movable) {
        this.movable = movable;
    }
    
    public void setToldToEvacuate(Boolean evacuate) {
        this.toldToEvacuate = evacuate;
    }
    
    public Boolean gettoldToEvacuate() {
        return this.toldToEvacuate;
    }
    
    public boolean getDefeated() {
        return this.defeated;
    }
    
    public void setDefeated(Boolean defeat) {
        this.defeated = defeat;
    }
}
