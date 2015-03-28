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
public class Drop extends Instruction {
    int state;
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
