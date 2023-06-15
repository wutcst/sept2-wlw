/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author Rasmus
 */
public interface IImmovable {
    public String getName();
    public int getItemDmg();
    public String getDescription();
    public String getUseDescription();
    public IItem getItems();
    public boolean getDestructible() ;
    public boolean isPickupable();
    public void breakTable();    
public void openCabinet();
    public IItem takeItem();
    public boolean getFlag();

    public void setFlag(boolean b);

    public void setDescription(String string);

    public void setUseDescription(String string);
    public void setDestructible(boolean destructable);

}
