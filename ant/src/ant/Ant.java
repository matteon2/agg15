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
    
    private Position position; //the current location of the ant
    private boolean alive;
    
    public Ant(Color color, int id, Dir d){
        this.color = color;
        this.id = id;
        this.state = 0;
        this.resting = 0;
        this.direction = d;//Must be Dir.EAST
        this.hasFood = false;
        //position.setX(0);
        //position.setY(0);
        this.alive = true;       
    }
    
    /**
     * get the state of ant a
     * @param a
     * @return 
     */
    public int getState(Ant a){
        return a.state;
    }
    
    /**
     * get the color of ant a
     * @param a
     * @return 
     */
    public Color getColor(Ant a){
        return a.color;
    }
    
    /**
     * get the length of the resting for ant a 
     * @param a
     * @return 
     */
    public int getResting(Ant a){
        return a.resting;
    }
    
    /**
     * get the direction of the ant a faces to 
     * @param a
     * @return 
     */
    public Dir getDirection(Ant a){
        return a.direction;
    }
    
    /**
     * check whether the ant a has food(single unit of food)
     * @param a
     * @return 
     */
    public boolean hasFood(Ant a){
        return a.hasFood;
    }
    
    /**
     * see if this ant is alive
     * @return 
     */
    public boolean isAlive(){
        return alive;
    }
    
    /**
     * set the state of ant a to state s
     * @param a
     * @param s 
     */
    public void setState(Ant a, int s){
        a.state = s;
    }
           
    /**
     * set the length of resting for ant a to r
     * @param a
     * @param r 
     */
    public void setResting(Ant a, int r){
        a.resting = r;
    }
    
    /**
     * set the direction that the ant a faces to d
     * @param a
     * @param d
     * @throws Exception 
     */
    public void setDirection(Ant a, Dir d) throws Exception{
        a.direction = d;
    }
            
    /**
     * let ant a has food
     * @param a
     * @param b 
     */
    public void setHasFood(Ant a, boolean b){
        a.hasFood = b;
    }
         
    /**
     * Give one color, get the other color, aka enemy color
     * @param c
     * @return
     * @throws Exception 
     */
    public Color getEnemyColor(Color c) throws Exception{
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
     * Calculates the position of the cell adjacent to position p in direction d.
     * @param p
     * @param d
     * @return Position
     * @throws Exception 
     */
    public Position adjacentCell(Position p, Dir d) throws Exception{
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
     * calculates the coordinates of the cell being sensed.
     * @param p
     * @param d
     * @param sd
     * @return
     * @throws Exception 
     */
    public Position sensedCell(Position p, Dir d, SenseDir sd) throws Exception{
        Position sensedPos = null;
        switch(sd){
            case HERE:
                sensedPos = p;
                break;
            case AHEAD:
                sensedPos = this.adjacentCell(p, d);
                break;
            case LEFTAHEAD:
                sensedPos = this.adjacentCell(p, turn(TurnDir.LEFT, d));
                break;
            case RIGHTAHEAD:
                sensedPos = this.adjacentCell(p, turn(TurnDir.RIGHT, d));
                break;
            default:
                throw new Exception("no valid sensed cell!");
        }
        return sensedPos;
    }
    
    /**
     * Let ant set marker to a cell in the world
     * @param p
     * @param c
     * @param i 
     */
    public void setMarkerAt(Position p, Color c, Marker i){
        Cell mCell = new Cell(p);
//        mCell.setMarker(c, );
    }
    
    
    
    

    public static void main(String args[]) throws Exception{
        Ant a = new Ant(Color.RED, 0, Dir.EAST);
////        Dir d = a.turn(TurnDir.RIGHT, Dir.NORTHEAST);
////        System.out.println(d);
//        Position p = new Position(3,2);
//        Position sensedP = a.sensedCell(p, Dir.WEST, SenseDir.RIGHTAHEAD);
//        System.out.println(sensedP.getX() + ", " + sensedP.getY());
        
//        System.out.println(a.alive);
//        System.out.println(a.color);
//        System.out.println(a.direction);
//        System.out.println(a.hasFood);
//        System.out.println(a.id);
//        System.out.println(a.resting);
//        System.out.println(a.state);
//        Position p = a.adjacentCell(new Position(6,8), Dir.NORTHWEST);
//        System.out.println("Adjacent: "+p.getX() + ","+ p.getY());
//        System.out.println(a.isAlive());
//        a.setHasFood(a, false);
//        System.out.println("a has food: "+a.hasFood(a));
//        System.out.println("Enemy color: "+a.getEnemyColor(Color.BLACK));
//        System.out.println("Turn: "+a.turn(TurnDir.LEFT, Dir.EAST));

        
    }
}
