/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

import java.util.ArrayList;
public interface IRoom {
     String getName();
     String getShortDescription();
     String getLongDescription();
     IImmovable getImmovable(String immovable);
     void removeItem(IItem item);
     INPC getNPC(String npc);
     ArrayList<INPC> fetchINPCList();
}
