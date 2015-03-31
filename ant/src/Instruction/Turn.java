/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

/**
 * This class represents turn left or right and moving state.
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

        System.out.println("Turn " + "'"+direction+"'" +  " and go to " + "'"+state+"'" + ".");
    }
}
