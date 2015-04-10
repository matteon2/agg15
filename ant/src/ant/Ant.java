/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Instruction.SenseDir;
import Instruction.TurnDir;

/**
 *
 * @author Andrew
 */
public class Ant {
    private Color color;    //each ant should have color, either red or black, which is described by Color class 
    private int id; //A unique integer id that determines the order in which the ant takes its turn to move or sense during each round of gameplay.
    private int state;  //An integer between 0 and 9999 representing the current state of its brain.
    private int resting;    //An integer resting that keeps track of how long the ant has to rest after its last move before any other action. 
    private Dir direction;  //the current direction that the ant faces to
    private boolean hasFood;    //a boolean indicating whether the ant is currently carrying a food particle.(An ant can only hold a single unit of food at a time)
    private AntBrain brain;
    private Position position; //the current location of the ant
    
    public Ant(Color color, int id, Dir d){
        this.color = color;
        this.id = id;
        this.state = 0;
        this.resting = 0;
        this.direction = d;
        this.hasFood = false;
        this.position.setX(0);
        this.position.setY(0);
    }
    
    
    public int getID(){
        return id;
    }
    
    public void setPosition(Position p){
        this.position = p;
    }
    
    public Position getPosition(){
        return position;
    }
    
    
    /**
     * BIOLOGY
     * get the state of ant a
     * @param a
     * @return 
     */
    public int get_state(Ant a){
        return a.state;
    }
    
    /**
     * BIOLOGY
     * get the color of ant a
     * @param a
     * @return 
     */
    public Color get_color(Ant a){
        return a.color;
    }
    
    /**
     * BIOLOGY
     * get the length of the resting for ant a 
     * @param a
     * @return 
     */
    public int get_resting(Ant a){
        return a.resting;
    }
    
    /**
     * BIOLOGY
     * get the direction of the ant a faces to 
     * @param a
     * @return 
     */
    public Dir get_direction(Ant a){
        return a.direction;
    }
    
    /**
     * BIOLOGY
     * check whether the ant a has food(single unit of food)
     * @param a
     * @return 
     */
    public boolean has_food(Ant a){
        return a.hasFood;
    }
    
//    /**
//     * see if this ant is alive
//     * @return 
//     */
//    public boolean isAlive(){
//        return alive;
//    }
    
    /**
     * BIOLOGY
     * set the state of ant a to state s
     * @param a
     * @param s 
     */
    public void set_state(Ant a, int s){
        a.state = s;
    }
           
    /**
     * BIOLOGY
     * set the length of resting for ant a to r
     * @param a
     * @param r 
     */
    public void set_resting(Ant a, int r){
        a.resting = r;
    }
    
    /**
     * BIOLOGY
     * set the direction that the ant a faces to d
     * @param a
     * @param d
     * @throws Exception 
     */
    public void set_direction(Ant a, Dir d) throws Exception{
        a.direction = d;
    }
            
    /**
     * BIOLOGY
     * let ant a has food
     * @param a
     * @param b 
     */
    public void set_has_food(Ant a, boolean b){
        a.hasFood = b;
    }
         
    /**
     * BIOLOGY
     * Give one color, get the other color, aka enemy color
     * @param c
     * @return
     * @throws Exception 
     */
    public Color other_color(Color c) throws Exception{
        Color enemyColor = c;
        switch(enemyColor){
            case RED:
                enemyColor = Color.BLACK;
                break;
            case BLACK:
                enemyColor = Color.RED;
                break;
            default:
                throw new Exception("The color is invalid!");
        }
        return enemyColor;
    }
    
    /**
     * GEOMETRY
     * Calculates the position of the cell adjacent to position p in direction d.
     * @param p
     * @param d
     * @return Position
     * @throws Exception 
     */
    public Position adjacent_cell(Position p, Dir d) throws Exception{
        Position adjPos = new Position(0,0);
        switch(d.getDirection()){
            case 0:
                adjPos.setX(p.getX()+1);
                adjPos.setY(p.getY());
                break;
            case 1:
                if(p.getY()%2 == 0){
                    adjPos.setX(p.getX());
                    adjPos.setY(p.getY()+1);
                }
                else{
                    adjPos.setX(p.getX()+1);
                    adjPos.setY(p.getY()+1);
                }
                break;
            case 2:
                if(p.getY()%2 == 0){
                    adjPos.setX(p.getX()-1);
                    adjPos.setY(p.getY()+1);
                }
                else{
                    adjPos.setX(p.getX());
                    adjPos.setY(p.getY()+1);
                }
                break;
            case 3:
                adjPos.setX(p.getX()-1);
                adjPos.setY(p.getY());
                break;
            case 4:
                if(p.getY()%2 == 0){
                    adjPos.setX(p.getX()-1);
                    adjPos.setY(p.getY()-1);
                }
                else{
                    adjPos.setX(p.getX());
                    adjPos.setY(p.getY()-1);
                }
                break;
            case 5:
                if(p.getY()%2 == 0){
                    adjPos.setX(p.getX());
                    adjPos.setY(p.getY()-1);
                }
                else{
                    adjPos.setX(p.getX()+1);
                    adjPos.setY(p.getY()-1);
                }
                break;
            default:
                throw new Exception("adjacent position is invalid!");                
        }
        return adjPos;
    }
    
    /**
     * GEOMETRY
     * Takes an element of left or right and a direction and returns a suitably adjusted direction
     * @param lr
     * @param d
     * @return Dir
     * @throws Exception 
     */
    public Dir turn(TurnDir lr, Dir d) throws Exception{
        Dir enumDir = Dir.EAST;
        int dirInt = d.getDirection();
        switch(lr){
            case LEFT:
                dirInt = (dirInt + 5) % 6;
                break;
            case RIGHT:
                dirInt = (dirInt + 1) % 6;
                break;
            default:
                throw new Exception("The input turn direction is invalid!");
        }
        return enumDir.getDir(dirInt);
    }
    
    /**
     * GEOMETRY
     * calculates the coordinates of the cell being sensed.
     * @param p
     * @param d
     * @param sd
     * @return
     * @throws Exception 
     */
    public Position sensed_cell(Position p, Dir d, SenseDir sd) throws Exception{
        Position sensedPos = null;
        switch(sd){
            case HERE:
                sensedPos = p;
                break;
            case AHEAD:
                sensedPos = this.adjacent_cell(p, d);
                break;
            case LEFTAHEAD:
                sensedPos = this.adjacent_cell(p, turn(TurnDir.LEFT, d));
                break;
            case RIGHTAHEAD:
                sensedPos = this.adjacent_cell(p, turn(TurnDir.RIGHT, d));
                break;
            default:
                throw new Exception("no valid sensed cell!");
        }
        return sensedPos;
    }


    public static void main(String args[]) throws Exception{
        
//        Dir d = a.turn(TurnDir.RIGHT, Dir.NORTHEAST);
//        System.out.println(d);
        Position p = new Position(3,2);
        Ant a = new Ant(Color.RED, 0, Dir.EAST);
        Position sensedP = a.sensed_cell(p, Dir.WEST, SenseDir.RIGHTAHEAD);
        System.out.println(sensedP.getX() + ", " + sensedP.getY());
    }
}
