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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
