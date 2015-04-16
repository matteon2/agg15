/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Game.Player;
import Instruction.Instruction;
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
    private Position antPosition; //the current location of the ant
    
    public Ant(Color color, Dir d){
        this.color = color;
        this.id = 0;
        id++;
        this.state = 0;
        this.resting = 0;
        this.direction = d;
        this.hasFood = false;
        this.antPosition.setX(0);
        this.antPosition.setY(0);
    }

    public Ant(Player player) {
        this.id = 0;
        id++;
        this.brain = player.getAntbrain();
        this.state = 0;
	this.antPosition.setX(0);
        this.antPosition.setY(0);
	this.direction = Dir.EAST;
	this.resting = 0;
	this.color = player.getColor();
	this.hasFood = false;
    }

    public int getID(){
        return id;
    }
    
    public void setPosition(Position p){
        this.antPosition = p;
    }
    
    public Position getPosition(){
        return antPosition;
    }
    
    
    /**
     * BIOLOGY
     * get the state of ant a
     * @param a
     * @return 
     */
    public int get_state(){
        return state;
    }
    
    /**
     * BIOLOGY
     * get the color of ant a
     * @param a
     * @return 
     */
    public Color get_color(){
        return color;
    }
    
    /**
     * BIOLOGY
     * get the length of the resting for ant a 
     * @param a
     * @return 
     */
    public int get_resting(){
        return resting;
    }
    
    /**
     * BIOLOGY
     * get the direction of the ant a faces to 
     * @param a
     * @return 
     */
    public Dir get_direction(){
        return direction;
    }
    
    /**
     * BIOLOGY
     * check whether the ant a has food(single unit of food)
     * @param a
     * @return 
     */
    public boolean has_food(){
        return hasFood;
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
    public void set_state(int s){
        state = s;
    }
           
    /**
     * BIOLOGY
     * set the length of resting for ant a to r
     * @param a
     * @param r 
     */
    public void set_resting(int r){
        resting = r;
    }
    
    /**
     * BIOLOGY
     * set the direction that the ant a faces to d
     * @param a
     * @param d
     * @throws Exception 
     */
    public void set_direction(Dir d) throws Exception{
        direction = d;
    }
            
    /**
     * BIOLOGY
     * let ant a has food
     * @param a
     * @param b 
     */
    public void set_has_food(boolean b){
        hasFood = b;
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
    
    /**
     * NEUROLOGY
     * This method uses to retrieve the instruction for state s in the brain of color c. 
     * @param c
     * @param state
     * @return Instruction
     * @throws Exception 
     */
    public Instruction get_instruction(Color c, int state) throws Exception{
        if(this.color == c){
            return brain.insList[state];
        }
        else{
            throw new Exception("cannot get the instruction of this kind of ant!");
        }
    }
    


    public static void main(String args[]) throws Exception{
        
//        Dir d = a.turn(TurnDir.RIGHT, Dir.NORTHEAST);
//        System.out.println(d);
        Position p = new Position(3,2);
        Ant a = new Ant(Color.RED, Dir.EAST);
        //Position sensedP = a.sensed_cell(p, Dir.WEST, SenseDir.RIGHTAHEAD);
        //System.out.println(sensedP.getX() + ", " + sensedP.getY());
    }

    
    public void simulate(World world){
        if(resting > 0){
            resting--;
        }else{
            brain.simulate(this, world);
        }
    }
}
