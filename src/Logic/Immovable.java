package Logic;

import Acquaintance.IImmovable;
import Acquaintance.IItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Wilde
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Immovable implements Interactables, IImmovable{
    private String name, description, useDescription;
    private boolean destructible, flag;
    private final boolean isPickupable = false;
    private Item item;
    private int itemDmg;

    public Immovable() {
    }
    
    /**
     *
     * @param name is the name of the Immovable being made, e.g. "table".
     * @param description is the description of the Immovable, e.g. "this is a table. There are some notes on the table".
     * @param useDescription is the description printed when you use the object, e.g. "you open the cabinet door".
     * @param destructible is the boolean that is true if the object can be the atarget of the "break" command.
     * @param flag is a boolean that is set to true, if the immovable is locked in some way.
     */
    
    
    
    public Immovable(String name, String description, String useDescription, boolean destructible, boolean flag){
        this.name = name; 
        this.description = description; 
        this.useDescription = useDescription; 
        this.destructible = destructible; 
        this.flag = true;
        this.itemDmg = 80;
    }
    
    //public method to open cabinets (amoury/ hallway/ airlock)
    @Override
    public void openCabinet(){
        if (this.flag == true) {
            System.out.println("You can't open this. It's locked. ");
        }
        else {
            System.out.println(useDescription);
            /*
            Open cabinet event (knives/ weapon inside/ oxygen-bottle access)
            */
        }
    }   
    
    //public method to break the table in the armoury
    @Override
    public void breakTable(){
    
    }
    public String getItemDescription(){
        
        
        if(item == null){
        return "null";
        }
        else {
        return item.getDescription();
        }
        
    }

        
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public int getItemDmg() {
        return itemDmg;
    }
        
    @Override
    public String getDescription(){
        return description;
    }
        
    @Override
    public String getUseDescription(){
        return useDescription;
    }
    
    @Override
    public void setUseDescription(String useDescription) {
        this.useDescription = useDescription;
    }
    
    // method to unlock radio array and weapon cabinet (break the lock - use this method)
    public void unlock(){
        this.flag = false;
    }

    // sets any items the immovable might contain
    public void setItems(Item item){
        this.item = item;
    }
    @Override
    public IItem getItems(){
        return item;
    }
    
    @Override
    public IItem takeItem(){
        Item returnItem = this.item;
        this.item = null;
        return returnItem;
    }
    
    @Override
    public boolean getDestructible() {
        return this.destructible;
    }

    @Override
    public boolean isPickupable() {
        return this.isPickupable; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getFlag() {
        return this.flag;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }


    public void setItem(Item item) {
        this.item = item;
    }

    public void setItemDmg(int itemDmg) {
        this.itemDmg = itemDmg;
    }

 
    
    
    
}
