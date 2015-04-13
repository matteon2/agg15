/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

/**
 *
 * @author Andrew
 */
public class Stat {
    
    private int foodCollected;
    private int deadAnt;
    
    public Stat(){
        this.foodCollected = 0;
        this.deadAnt = 0;
    }

    /**
     * @return the foodCollected
     */
    public int getFoodCollected() {
        return foodCollected;
    }

    /**
     * @param foodCollected the foodCollected to set
     */
    public void setFoodCollected(int foodCollected) {
        this.foodCollected = foodCollected;
    }

    /**
     * @return the deadAnt
     */
    public int getDeadAnt() {
        return deadAnt;
    }

    /**
     * @param deadAnt the deadAnt to set
     */
    public void setDeadAnt(int deadAnt) {
        this.deadAnt = deadAnt;
    }
    
}
