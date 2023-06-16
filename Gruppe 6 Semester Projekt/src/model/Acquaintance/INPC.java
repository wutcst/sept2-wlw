/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Acquaintance;

/**
 *
 * there ar more methods that is not included in the interface yet if they shuld be neseary
 */
//定义npc接口的功能实现，接口功能命名易读
public interface INPC {
    public String getName();
    public void setDescription(String description);
    public String getDescription();
    public boolean getHostility();
    public void setHostility(boolean hostile);
    public boolean getMovability();        
     public void setDamage(int dmg);
     public int getDamage();
     public void addItem(IItem item);
     public IItem getItem();
     public int getHealth();
    public boolean getDefeated();

}
