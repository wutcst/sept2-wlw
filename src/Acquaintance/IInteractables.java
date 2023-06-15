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
public interface IInteractables {
        public abstract String getName();
    public abstract String getDescription();
    public abstract String getUseDescription();
    public abstract boolean isPickupable();
    public abstract boolean getFlag();
    public abstract void setFlag(Boolean flag);
    public abstract void setDescription(String string);
}
