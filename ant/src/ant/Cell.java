
package ant;

import java.util.HashMap;
import java.util.HashSet;



/**
 *
 * @author Andrew
 */
public class Cell {
    
    private Position cellPosition;
    private boolean isRocky;
    private int foodNumber;
    private HashMap<Color, HashSet<Integer>> markerWithColor; //the marker with color label shows which ant team leaves this marker
    
    //assume all cell start from clear
    public Cell(Position p){
        this.cellPosition = p;
        isRocky = false;
        foodNumber = 0;
        this.markerWithColor = new HashMap<Color, HashSet<Integer>>();
        markerWithColor.put(Color.RED, new HashSet<Integer>());
        markerWithColor.put(Color.BLACK, new HashSet<Integer>());
        assert (foodNumber >= 0);
    }
       
    /**
     * check whether this cell is rocky
     * @return boolean
     */
    public boolean getRocky(){
        return isRocky;
    }
    
    /**
     * make the current cell rocky;
     * @param rocky
     */
    public void setRocky(boolean rocky){
        this.isRocky = rocky;
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
        assert (this.foodNumber >= 0);
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
    
    public static void main(String args[]) throws Exception{
        Position p = new Position(0,0);
        Cell c1 = new Cell(p);
        c1.setMarker(Color.RED, 0);
        c1.setMarker(Color.RED, 1);
        c1.setMarker(Color.RED, 2);
        c1.setMarker(Color.RED, 3);
        c1.setMarker(Color.RED, 4);
        c1.setMarker(Color.RED, 5);
        c1.setMarker(Color.RED, 0);
        c1.setMarker(Color.BLACK, 4);
        c1.clearMarker(Color.RED, 0);
        c1.clearMarker(Color.RED, 1);
        c1.clearMarker(Color.RED, 2);
        c1.clearMarker(Color.RED, 3);
        c1.clearMarker(Color.RED, 4);
        //c1.clearMarker(Color.RED, 5);
        System.out.println(c1.checkAnyMarker(Color.RED));
        System.out.println(c1.checkAnyMarker(Color.BLACK));
    }
}
