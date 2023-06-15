/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

/**
 *
 * @author Rasmus Jensen
 */
public interface IItem {
    public int getDmg();
    public int getHP();
    public int getAir();
    public String getName();
    public String getDescription();
    public String getUseDescription();
    public boolean isPickupable();
    public void setFlag(boolean flag);
    public boolean getFlag();
    public void setDescription(String string);
}
