/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

/**
 * This class represents moving forward and moving state.
 * @author Andrew
 */
public class Move extends Instruction{
    int state1;
    int state2;
    
    public Move(int state1, int state2){
        this.state1 = state1;
        this.state2 = state2;
        tokenLength = 3;
    }
    
    @Override
    public int getTokenLength(){
        return tokenLength;
    }

    @Override
    public void execute() {

        System.out.println("Move forward and go to " + "'"+state1+"'" + ";" + " go to " + "'"+state2+"'" + " if the cell ahead is blocked.");
    }
}
