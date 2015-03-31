/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

/**
 * This class represents dropping the food in the current file and moving state
 * @author Andrew
 */
public class Drop extends Instruction {
    int state;
    
    /**
     * Create the drop instruction
     * @param state 
     */
    public Drop(int state){
        this.state = state;
        tokenLength = 2;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
    @Override
    public void execute() {

        System.out.println("Drop food in current cell and go to state " + "'"+state+"'");
    }
}
