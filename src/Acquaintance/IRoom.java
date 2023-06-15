/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.ArrayList;

/**
 *
 * @author Rasmus Jensen
 */
public interface IRoom {
    public String getName();
    public String getShortDescription();
    public String getLongDescription();
    public IImmovable getImmovable(String immovable);
    public void removeItem(IItem item);
    public INPC getNPC(String npc);
    public ArrayList<INPC> fetchINPCList();
}
