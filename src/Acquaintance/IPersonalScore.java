package Acquaintance;

/**
 *interface used to move a personalscore objekt betwen logic and persistence
 */
public interface IPersonalScore {
    
    /**
     *
     * @param name
     */
    void setName(String name);
    
    void setScore (int Score);
    
    String getName();
    
    int getScore();
    
    @Override
    String toString();
}
