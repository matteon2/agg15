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
public class Turn extends Instruction{
    TurnDir direction;
    int state;
    
    public Turn(TurnDir direction, int state){
        this.direction = direction;
        this.state = state;
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
