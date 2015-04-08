/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

/**
 *
 * @author Andrew
 */
public class World {
    
    private final Cell[][] antWorld;
    
    public World(){
        antWorld = new Cell[150][150];
    }
    
    /**
     * check if the cell at position p is rocky and false if p is clear
     * @param p
     * @return boolean
     */
    public boolean rocky(Position p){
        return antWorld[p.getX()][p.getY()].getRocky();
    }
    
    /**
     * true if there is an ant in the cell at position p
     * @param p
     * @return boolean
     */
    public boolean someAntIsAt(Position p){
        return antWorld[p.getX()][p.getY()].hasAnt();
    }
    
    /**
     * return the ant in the cell at position p
     * @param p
     * @return Ant
     * @throws Exception 
     */
    public Ant antAt(Position p) throws Exception{
        return antWorld[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * record the fact that the given ant is at position p
     * @param p
     * @param a 
     */
    public void setAntAt(Position p, Ant a){
        antWorld[p.getX()][p.getY()].setAnt(a);
    }
    
    /**
     * record the fact that no ant is at position p
     * @param p 
     */
    public void clearAntAt(Position p){
        antWorld[p.getX()][p.getY()].clearAnt();
    }
    
    /**
     * true if an ant with the given id exists somewhere in the world
     * @param id
     * @return boolean
     */
    public boolean antIsAlive(int id) throws Exception{
        boolean isAlive = false;
        for(int i= 0; i < 150; i++){
            for(int j = 0; i < 150; i++){
                if (antWorld[i][j].getAnt().getID() == id){
                    if(antWorld[i][j].getAnt().isAlive()){
                        isAlive = true;
                    }else{
                        isAlive = false;
                    }
                }
                else{
                    throw new Exception("There is not ant with this id!");
                }
            }
        }
        return isAlive;
    }
    
    /**
     * return current position of the ant with the given id
     * @param id
     * @return Position
     * @throws java.lang.Exception
     */
//    public Position findAnt(int id) throws Exception{
//        Position p = new Position(0,0);
//        if(antIsAlive(id)){
//            
//        }
//    }
    

    
}
