/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

/**
 * This class represents choosing a random number x and moving state.
 * @author Andrew
 */
public class Flip extends Instruction {
    int p;
    int state1;
    int state2;
    
    public Flip(int p, int state1, int state2){
        this.p = p;
        this.state1 = state1;
        this.state2 = state2;
        tokenLength = 4;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
    @Override
    public void execute() {

        System.out.println("Choose a random number x from 0 to " + "'"+p+"'" + "-1;" + " go to state " + "'"+state1+"'" + " if x=0 and " + "'"+state2+"'" + " otherwise.");
    }
}
