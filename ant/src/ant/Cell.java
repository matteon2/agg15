
package ant;

import java.util.HashMap;
import java.util.HashSet;



/**
 *  This cell represent the normal cell instead of antHill cell
 * @author Andrew
 */
public class Cell {
    
    //private Position cellPosition;
    private int foodNumber;
    private HashMap<Color, HashSet<Integer>> markerWithColor; //the marker with color label shows which ant team leaves this marker
    private Ant ant;
    private boolean containRock;
    private boolean isClear;    //no rock, no food, no ants
    private boolean containsRedAnt;
    private boolean containsBlackAnt;
    private boolean hasFood;
    
    
    
    //assume all cell start from clear
    public Cell(int foodNum){
        //this.cellPosition = p;
        foodNumber = foodNum;
        this.markerWithColor = new HashMap<Color, HashSet<Integer>>();
        markerWithColor.put(Color.RED, new HashSet<Integer>());
        markerWithColor.put(Color.BLACK, new HashSet<Integer>());
        ant = null;
        isClear = true;
        containsRedAnt = false;
        containsBlackAnt = false;
        hasFood = true;
    }
    
    public Cell(){
        this(0);
        this.isClear = true;
        this.containRock = false;
        containsRedAnt = false;
        containsBlackAnt = false;
        hasFood = false;
    }
    
    public boolean isAntHill(){
        return false;
    }
 
    public boolean hasFood(){
        return foodNumber > 0;
    }
    
    /**
     * get the food numebr of this cell
     * @return 
     */
    public int getFoodNumber(){
        return foodNumber;
    }


    
    /**
     * set the number of food in this cell, the food numebr is non-negative
     * @param foodNum 
     */
    public void setFoodNumber(int foodNum){
        this.foodNumber = foodNum;
    }
    
    /**
    * set marker i of color c in the current cell 
    * @param c
    * @param marker 
    */
    public void setMarker(Color c, int marker) throws Exception{
        if(marker > 5 || marker < 0){
            throw new Exception("invalid marker!");
        }else{
            markerWithColor.get(c).add(marker);
        }  
    }
    
    /**
     * clear marker i of color c in the current cell
     * @param c
     * @param marker
     * @throws Exception 
     */
    public void clearMarker(Color c, int marker) throws Exception{
        if(marker > 5 || marker < 0){
            throw new Exception("invalid marker number!");
        }else{
            if(checkMarker(c, marker))
                markerWithColor.get(c).remove(marker);   
            else{
                throw new Exception("the marker is not set in the cell!");
            }
        } 
    }
    
    /**
     * true if marker i of color c is set in the current cell
     * @param c
     * @param marker
     * @return boolean
     */
    public boolean checkMarker(Color c, int marker){
        return markerWithColor.get(c).contains(marker);
    }
    
    /**
     * true if ANY marker of color c is set in the current cell
     * @param c
     * @return boolean
     */
    public boolean checkAnyMarker(Color c){
        return !markerWithColor.get(c).isEmpty();
    }
    
    /**
     * check if the current cell has ant
     * @return boolean
     */
    public boolean hasAnt(){
        return (ant != null);
    }
    
    /**
     * get the ant if there is an ant in the current cell
     * @return Ant 
     * @throws Exception 
     */
    public Ant getAnt() throws Exception{
        if(!this.hasAnt()){
            throw new Exception("No ant to get!");
        }else{
            return ant;
        }
    }
    
    /**
     * Set an ant for the current cell
     * @param a 
     */
    public void setAnt(Ant a){
        this.ant = a;
    }
    
    /**
     * clear the ant in the current cell
     */
    public void clearAnt(){
        setAnt(null);
    }
    
    
    public static void main(String args[]) throws Exception{

        Cell c1 = new Cell(0);
//        c1.setMarker(Color.RED, 0);
//        c1.setMarker(Color.RED, 1);
//        c1.setMarker(Color.RED, 2);
//        c1.setMarker(Color.RED, 3);
//        c1.setMarker(Color.RED, 4);
//        c1.setMarker(Color.RED, 5);
//        c1.setMarker(Color.RED, 0);
//        c1.setMarker(Color.BLACK, 4);
//        c1.clearMarker(Color.RED, 0);
//        c1.clearMarker(Color.RED, 1);
//        c1.clearMarker(Color.RED, 2);
//        c1.clearMarker(Color.RED, 3);
//        c1.clearMarker(Color.RED, 4);
//        //c1.clearMarker(Color.RED, 5);
//        System.out.println(c1.checkAnyMarker(Color.RED));
//        System.out.println(c1.checkAnyMarker(Color.BLACK));
        System.out.println(c1.hasAnt());
    }

    /**
     * @return the containRock
     */
    public boolean isContainRock() {
        return containRock;
    }

    /**
     * @param containRock the containRock to set
     */
    public void setContainRock(boolean containRock) {
        this.containRock = containRock;
    }

    /**
     * @return the isClear
     */
    public boolean isIsClear() {
        return isClear;
    }

    /**
     * @param isClear the isClear to set
     */
    public void setIsClear(boolean isClear) {
        this.isClear = isClear;
    }

    /**
     * @return the containsAnt
     */
    public boolean isContainsRedAnt() {
        return containsRedAnt;
    }

    /**
     * @param containsAnt the containsAnt to set
     */
    public void setContainsRedAnt(boolean containsAnt) {
        this.containsRedAnt = containsAnt;
    }

    /**
     * @return the containsBlackAnt
     */
    public boolean isContainsBlackAnt() {
        return containsBlackAnt;
    }

    /**
     * @param containsBlackAnt the containsBlackAnt to set
     */
    public void setContainsBlackAnt(boolean containsBlackAnt) {
        this.containsBlackAnt = containsBlackAnt;
    }

    /**
     * @return the hasFood
     */
    public boolean isHasFood() {
        return hasFood;
    }

    /**
     * @param hasFood the hasFood to set
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

   
}
