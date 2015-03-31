/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

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
        tokenLength = 6;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
    
    
    @Override
    public void execute() {
        if(tokenLength == 5){
            System.out.println("Go to state " + "'"+state1+"'" + " if " + "'"+condition+"'" + " holds in " + "'"+senseDir+"'" + ";" + " and to state " + "'"+state2+"'"  + " otherwise.");
        }
        if(tokenLength == 6){
            System.out.println("Go to state " + "'"+state1+"'" + " if " + "'"+condition+"'" + "'"+marker+"'" + " holds in " + "'"+senseDir+"'" + ";" + " and to state " + "'"+state2+"'"  + " otherwise.");
        }
        else{
            System.out.println("false!");
        }     
    }
}
