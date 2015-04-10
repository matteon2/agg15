/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Instruction.Condition;
import java.util.HashMap;

/**
 *
 * @author Andrew
 */
public class World {
    
    private final Cell[][] antWorld;
    private HashMap<Integer, Ant> ants; //a list of alived ants with id as the key and the Ant object as the object
    
    public World(int antNum){
        antWorld = new Cell[150][150];
        ants = new HashMap<>();
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
    public boolean some_ant_is_at(Position p){
        return antWorld[p.getX()][p.getY()].hasAnt();
    }
    
    /**
     * return the ant in the cell at position p
     * @param p
     * @return Ant
     * @throws Exception 
     */
    public Ant ant_at(Position p) throws Exception{
        return antWorld[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * record the fact that the given ant is at position p
     * @param p
     * @param a 
     */
    public void set_ant_at(Position p, Ant a){
        antWorld[p.getX()][p.getY()].setAnt(a);
        a.setPosition(p);
    }
    
    /**
     * record the fact that no ant is at position p
     * @param p 
     */
    public void clear_ant_at(Position p) throws Exception{
        antWorld[p.getX()][p.getY()].clearAnt();
    }
    
    /**
     * true if an ant with the given id exists somewhere in the world
     * @param id
     * @return boolean
     */
    public boolean ant_is_alive(int id){
        return ants.containsKey(id);
    }
    
    /**
     * return current position of the ant with the given id
     * @param id
     * @return Position
     * @throws java.lang.Exception
     */
    public Position find_ant(int id) throws Exception{
        if(ant_is_alive(id)){
            Ant a = ants.get(id);
            return a.getPosition();
        }else{
            throw new Exception("The ant is not exist!");
        }
    }
    
    /**
     * kill the ant at position p means that the ant is not exist anymore, it should be removed from the ant list
     * @param p 
     */
    public void kill_ant_at(Position p) throws Exception{
        clear_ant_at(p);
        ants.remove(antWorld[p.getX()][p.getY()].getAnt().getID());
    }
    
    /**
     * return the amount of food in the cell at position p
     * @param p
     * @return int
     */
    public int food_at(Position p){
        return antWorld[p.getX()][p.getY()].getFoodNumber();
    }

    /**
     * record the fact that a given amount of food is at position p
     * @param p
     * @param f 
     */
    public void set_food_at(Position p, int f){
        antWorld[p.getX()][p.getY()].setFoodNumber(f);
    }
    
    /**
     * true if the cell at position p is in the anthill of color c
     * @param p
     * @param c
     * @return boolean
     */
    public boolean anthill_at(Position p, Color c){
        return antWorld[p.getX()][p.getY()].isAntHill();
    }
 
    /**
     * takes a position p, a condition cond, and a color c (the color of the ant that is doing the sensing), 
     * and checks whether cond holds at p.
     * @param p
     * @param cond
     * @param c
     * @return boolean
     */
    public boolean cellMatches(Position p, Condition cond, Color c) throws Exception{
        boolean match;
        
        if(rocky(p)){
            return cond == Condition.ROCK;
        }
        else{
            switch(cond){
                case FRIEND:
                    match = (some_ant_is_at(p)) && (ant_at(p).getColor(ant_at(p)) == c);
                    break;
                case FOE:
                    match = (some_ant_is_at(p)) && (ant_at(p).getColor(ant_at(p)) != c);
                    break;
            }
        }
        return false;
    }
    
}
