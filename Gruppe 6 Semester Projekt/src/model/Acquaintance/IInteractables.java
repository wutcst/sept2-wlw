/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

public interface IInteractables {
    String getName();
    String getDescription();
    String getUseDescription();
    boolean isPickupable();
    boolean getFlag();
    void setFlag(Boolean flag);
    void setDescription(String string);
}
