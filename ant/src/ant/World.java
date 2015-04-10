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
     * GEOGRAPHY
     * check if the cell at position p is rocky and false if p is clear
     * @param p
     * @return boolean
     */
    public boolean rocky(Position p){
        return antWorld[p.getX()][p.getY()].getRocky();
    }
    
    /**
     * GEOGRAPHY
     * true if there is an ant in the cell at position p
     * @param p
     * @return boolean
     */
    public boolean some_ant_is_at(Position p){
        return antWorld[p.getX()][p.getY()].hasAnt();
    }
    
    /**
     * GEOGRAPHY
     * return the ant in the cell at position p
     * @param p
     * @return Ant
     * @throws Exception 
     */
    public Ant ant_at(Position p) throws Exception{
        return antWorld[p.getX()][p.getY()].getAnt();
    }
    
    /**
     * GEOGRAPHY
     * record the fact that the given ant is at position p
     * @param p
     * @param a 
     */
    public void set_ant_at(Position p, Ant a){
        antWorld[p.getX()][p.getY()].setAnt(a);
        a.setPosition(p);
    }
    
    /**
     * GEOGRAPHY
     * record the fact that no ant is at position p
     * @param p 
     */
    public void clear_ant_at(Position p) throws Exception{
        antWorld[p.getX()][p.getY()].clearAnt();
    }
    
    /**
     * GEOGRAPHY
     * true if an ant with the given id exists somewhere in the world
     * @param id
     * @return boolean
     */
    public boolean ant_is_alive(int id){
        return ants.containsKey(id);
    }
    
    /**
     * GEOGRAPHY
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
     * GEOGRAPHY
     * kill the ant at position p means that the ant is not exist anymore, it should be removed from the ant list
     * @param p 
     */
    public void kill_ant_at(Position p) throws Exception{
        clear_ant_at(p);
        ants.remove(antWorld[p.getX()][p.getY()].getAnt().getID());
    }
    
    /**
     * GEOGRAPHY
     * return the amount of food in the cell at position p
     * @param p
     * @return int
     */
    public int food_at(Position p){
        return antWorld[p.getX()][p.getY()].getFoodNumber();
    }

    /**
     * GEOGRAPHY
     * record the fact that a given amount of food is at position p
     * @param p
     * @param f 
     */
    public void set_food_at(Position p, int f){
        antWorld[p.getX()][p.getY()].setFoodNumber(f);
    }
    
    /**
     * GEOGRAPHY
     * true if the cell at position p is in the anthill of color c
     * @param p
     * @param c
     * @return boolean
     */
    public boolean anthill_at(Position p, Color c){
        return antWorld[p.getX()][p.getY()].isAntHill();
    }
    
    /**
     * CHEMISTRY
     * set marker i of color c in cell p
     * @param p
     * @param c
     * @param marker
     * @throws Exception 
     */
    public void set_marker_at(Position p, Color c, int marker) throws Exception{
        antWorld[p.getX()][p.getY()].setMarker(c, marker);
    }
    
    /**
     * CHEMISTRY
     * clear marker i of color c in cell p
     * @param p
     * @param c
     * @param marker 
     */
    public void clear_marker_at(Position p, Color c, int marker) throws Exception{
        antWorld[p.getX()][p.getY()].clearMarker(c, marker);
    }
    
    /**
     * CHEMISTRY
     * true if marker i of color c is set in cell p
     * @param p
     * @param c
     * @param marker
     * @return boolean
     */
    public boolean check_marker_at(Position p, Color c, int marker){
        return antWorld[p.getX()][p.getY()].checkMarker(c, marker);
    }
 
    /**
     * CHEMISTRY
     * true if ANY marker of color c is set in cell p
     * @param p
     * @param c
     * @return boolean
     */
    public boolean check_any_marker_at(Position p, Color c){
        return antWorld[p.getX()][p.getY()].checkAnyMarker(c);
    }
    
    
    /**
     * PHENOMENOLOGY
     * takes a position p, a condition cond, and a color c (the color of the ant that is doing the sensing), 
     * and checks whether cond holds at p.
     * @param p
     * @param cond
     * @param c
     * @return boolean
     */
    public boolean cellMatches(Position p, Condition cond, Color c, int marker) throws Exception{
        boolean match;
        
        if(rocky(p)){
            match = cond.equals(Condition.ROCK);
        }
        else{
            switch(cond){
                case FRIEND:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color(ant_at(p)) == c);
                    break;
                case FOE:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color(ant_at(p)) != c);
                    break;
                case FRIENDWITHFOOD:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color(ant_at(p)) == c) && ant_at(p).has_food(ant_at(p));
                    break;
                case FOEWITHFOOD:
                    match = (some_ant_is_at(p)) && (ant_at(p).get_color(ant_at(p)) != c) && ant_at(p).has_food(ant_at(p));
                    break;
                case FOOD:
                    match = food_at(p) > 0;
                    break;
                case ROCK:
                    match = false;
                    break;
                case MARKER:
                    match = check_marker_at(p, c, marker);
                    break;
                case FOEMARKER:
                    match = check_any_marker_at(p, ant_at(p).other_color(c));
                    break;
                case HOME:
                    match = anthill_at(p, c);
                    break;
                case FOEHOME:
                    match = anthill_at(p, ant_at(p).other_color(c));
                    break;
                default:
                    match = false;
            }
        }
        return match;
    }
    
}
