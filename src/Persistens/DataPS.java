/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistens;

import Acquaintance.IPersonalScore;

/**
 * Class used to assist in seriealising and deserializing IPersonalScore object with jackson the class hodls a name and a score variable with getter setter methods
 * @author Rasmus
 */
public class DataPS implements IPersonalScore{
    String name;
    int score;
    
    DataPS(String name, int score){
        this.name = name;
        this.score = score;
    }
    
    @Override
    public void setName(String name) {
    this.name = name;    
    }

    @Override
    public void setScore(int Score) {
    this.score = score;
    }

    @Override
    public String getName() {
       return name;
    }

    @Override
    public int getScore() {
        return score;
    }
    
}
