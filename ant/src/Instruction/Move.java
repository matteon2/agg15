/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

/**
 *
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
