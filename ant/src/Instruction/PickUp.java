/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.World;

/**
 * This class represents picking up food from current cell and moving state.
 * @author Andrew
 */
public class PickUp extends Instruction{
    int state1;
    int state2;
    
    public PickUp(int state1, int state2){
        this.state1 = state1;
        this.state2 = state2;
        tokenLength = 3;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
//    @Override
//    public void execute() {
//        System.out.println("Pick up food from current cell and go to " + "'"+state1+"'" + ";" +  " go to " + "'"+state2+"'" + " if there is no food in the current cell.");
//    }

    /**
     * KINETICS step method
     * execute the PickUp instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        if(ant.has_food() || world.food_at(ant.getPosition()) == 0){
            ant.set_state(state2);
        }
        else{
            world.set_food_at(ant.getPosition(), world.food_at(ant.getPosition())-1);
            ant.set_has_food(true);
            ant.set_state(state1);
        }       
    }

}
