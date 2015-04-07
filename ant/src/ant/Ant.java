/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

import Instruction.SenseDir;
import Instruction.TurnDir;
import static ant.Dir.EAST;



/**
 *
 * @author Andrew
 */
public class Ant {
    private Color color;    //each ant should have color, either red or black, which is described by Color class 
    private int id; //A unique integer id that determines the order in which the ant takes its turn to move or sense during each round of gameplay.
    private int state;  //An integer between 0 and 9999 representing the current state of its brain.
    private int resting;    //An integer resting that keeps track of how long the ant has to rest after its last move before any other action. 
    private Dir direction;  //current direction
    private boolean hasFood;    //a boolean indicating whether the ant is currently carrying a food particle. 
    
    private Position p;
    private boolean isAlive;
    
    public Ant(Color color, int id, Dir d){
        this.color = color;
        this.id = id;
        this.state = 0;
        this.resting = 0;
        this.direction = d;
        this.hasFood = false;
        p.setX(0);
        p.setY(0);
        this.isAlive = true;
        
    }
    
    public int getState(Ant a){
        return a.state;
    }
    
    public Color getColor(Ant a){
        return a.color;
    }
    
    public int getResting(Ant a){
        return a.resting;
    }
    
    public Dir getDirection(Ant a){
        return a.direction;
    }
    
    public boolean hasFood(Ant a){
        return a.hasFood;
    }
    
    public void setState(Ant a, int s){
        a.state = s;
    }
           
    public void setResting(Ant a, int r){
        a.resting = r;
    }
    
    //may have some issue in this method, i need a better way to represent the direction 
    public void setDirection(Ant a, Dir d) throws Exception{
        a.direction = d;
    }
            
    public void setHasFood(Ant a, boolean b){
        a.hasFood = b;
    }
            
    public Color getEnemyColor(Color c) throws Exception{
        Color enemyColor = null;
        switch(c){
            case RED:
                enemyColor = enemyColor.BLACK;
                break;
            case BLACK:
                enemyColor = enemyColor.RED;
                break;
            default:
                throw new Exception("The color is invalid!");
        }
        return enemyColor;
    }
    
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
    
    public Dir turn(TurnDir lr, Dir d) throws Exception{
        Dir direction = EAST;
        int dir = d.getDirection();
        if(lr == lr.LEFT){
            dir = (dir+5)%6;
        }
        else{
            dir = (dir+1)%6;
        }        
        return direction.getDir(dir);      
    }
    
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

}
