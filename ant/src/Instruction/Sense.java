/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.Position;
import ant.World;



/**
 * This class represents moving state after sensing the direction.
 * @author Andrew
 */
public class Sense extends Instruction{
    SenseDir senseDir;
    int state1;
    int state2;
    Condition condition;
    int marker;
    
    
    public Sense(SenseDir senseDir, int st1, int st2, Condition condition){
        this.senseDir = senseDir;
        this.state1 = st1;
        this.state2 = st2;
        this.condition = condition;
        tokenLength = 5;
    }
    
    public Sense(SenseDir senseDir, int st1, int st2, Condition condition, int marker){
        this.senseDir = senseDir;
        this.state1 = st1;
        this.state2 = st2;
        this.condition = condition;
        this.marker = marker;
        tokenLength = 6;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
//    @Override
//    public void execute() {
//        if(tokenLength == 5){
//            System.out.println("Go to state " + "'"+state1+"'" + " if " + "'"+condition+"'" + " holds in " + "'"+senseDir+"'" + ";" + " and to state " + "'"+state2+"'"  + " otherwise.");
//        }
//        if(tokenLength == 6){
//            System.out.println("Go to state " + "'"+state1+"'" + " if " + "'"+condition+"'" + "'"+marker+"'" + " holds in " + "'"+senseDir+"'" + ";" + " and to state " + "'"+state2+"'"  + " otherwise.");
//        }
//        else{
//            System.out.println("false!");
//        }     
//    }

    /**
     * KINETICS step method(may have bug)
     * execute the Sense instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        try {
            Position p2 = ant.sensed_cell(ant.getPosition(), ant.get_direction(), senseDir);
            int st;
            if(world.cellMatches(p2, condition, ant.get_color(), marker)){
                st = state1;
            }else{
                st = state2;
            }
            ant.set_state(st);
        } catch (Exception ex) {
            System.out.println("Caught exception in Sense execute method!");
        }
    }

}
