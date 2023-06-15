/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Acquaintance.IItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author bruger
 */
//The item class.
//This is where the pickupable item objects is created.
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Interactables, IItem {
    //Name, description, and use description is defined.
    private String itemName, useDescription;
    String itemDescription = null; 
    private boolean flag;
    private final boolean isPickupable = true;
    private int dmg, HP, air; 

    public Item() {
    }
    
    
    
    //This is the method that created the item object.
    public Item(String itemName, String itemDescription, int dmg, int HP, int air) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.dmg = dmg;
        this.HP = HP;
        this.air = air;
        this.flag = true;
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    

    @Override
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
           
    @Override
    public int getAir() {
        return air;
    }

    public void setAir(int air) {
        this.air = air;
    }
    
    
    
    //The following methods are the methods implemented from our interactables
    //interface. These are also used in the Destructables class.
    @Override
    //returns the name of the object
    public String getName() {
        return itemName;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }
    
    

    @Override
    //returns the description of the object
    public String getDescription() {
        return itemDescription;
    }

        
     @Override
    public void setDescription(String description) {
        this.itemDescription = description;
    }
    
    @Override
    //returns the use description of the object
    public String getUseDescription() {
       return useDescription;
    }

    public void setUseDescription(String useDescription) {
        this.useDescription = useDescription;
    }

    @Override
    //returns a 
    public boolean isPickupable() {
       return this.isPickupable;
    }
    
    
    @Override
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    
    @Override
    public boolean getFlag() {
        return this.flag; //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", useDescription=" + useDescription + ", itemDescription=" + itemDescription + ", flag=" + flag + ", isPickupable=" + isPickupable + ", dmg=" + dmg + ", HP=" + HP + ", air=" + air + '}';
    }

    @Override
    public void setDestructible(boolean destructable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
