/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import Acquaintance.IPersonalScore;

/**
 *
 * @author Rasmus
 */
public class PersonalScore implements IPersonalScore, Comparable<PersonalScore> {

    private String name;
    private int score;

    PersonalScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
public void setName(String name){
        this.name = name;
    }
    @Override
    public void setScore(int score){
        this.score = score;
    }
    @Override
    public String getName(){
            return name;
    }
    @Override
    public int getScore(){
        return score;
    }
    @Override
    public String toString(){
        return name + " : " + score;
        
    }

    @Override
        public int compareTo(PersonalScore sc) {
       return (sc.getScore() - this.score);
}
        
}
