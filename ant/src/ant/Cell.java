
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
    private HashMap<Color, Marker> markerWithColor; //the marker with color label shows which ant team leaves this marker
    
    //assume all cell start from clear
    public Cell(Position p){
        this.cellPosition = p;
        isRocky = false;
        foodNumber = 0;
        markerWithColor = null;
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
    * set marker with color in the current cell
    * @param c
    * @param i 
    */
    public void setMarker(Color c, int i) throws Exception{
        markerWithColor = new HashMap();
        markerWithColor.put(c, new Marker(i));
    }
    
    
    public static void main(String args[]) throws Exception{
        Position p = new Position(0,0);
        Cell c1 = new Cell(p);
        
        c1.setMarker(Color.RED, 5);
        c1.setMarker(Color.RED, 0);
        c1.setMarker(Color.BLACK, 4);
        System.out.println(c1.markerWithColor.containsValue(5));
    }
}
