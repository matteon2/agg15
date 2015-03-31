/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

/**
 *
 * @author mk448
 */
public class Ant {
    private Cell position;
    private boolean hasFood;
    private boolean alive;
    
    public Ant(Cell startPos){
        position = startPos;
        alive = true;
        hasFood = false;
    }
    
    public Cell getPos(){return position;}
    
    public void move(int direction){
        //update position cell based on direction
    }
    
    
}
