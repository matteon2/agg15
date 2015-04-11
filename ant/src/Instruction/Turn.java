/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.World;

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
    
//    @Override
//    public void execute() {
//        System.out.println("Turn " + "'"+direction+"'" +  " and go to " + "'"+state+"'" + ".");
//    }

    /**
     * KINETICS step method
     * execute the Turn instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        try {
            ant.set_direction(ant.turn(direction, ant.get_direction()));     
            ant.set_state(state);
        } catch (Exception ex) {
            System.out.println("Caught exception in Turn execute method!");
        }
    }

}
