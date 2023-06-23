/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

//An interface with the set methods our interactable objects use.
public interface Interactables {
    String getName();
    String getDescription();
    String getUseDescription();
    boolean isPickupable();
    boolean getFlag();
    void setFlag(boolean flag);
    void setDescription(String string);
    void setDestructible(boolean destructable);
}
