/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

/**
 * 物品类接口
 */
public interface IItem {
    int getDmg();
    int getHP();
    int getAir();
    String getName();
    String getDescription();
    void setFlag(boolean flag);
    boolean getFlag();
    void setDescription(String string);
}
