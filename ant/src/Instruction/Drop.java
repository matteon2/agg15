/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.World;



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
    
//    @Override
//    public void execute() {
//        System.out.println("Drop food in current cell and go to state " + "'"+state+"'");
//    }

    /**
     * KINETICS step method
     * execute the Drop instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        if(ant.has_food()){
            world.set_food_at(ant.getPosition(), world.food_at(ant.getPosition())+1);
            ant.set_has_food(false);
        }
        ant.set_state(state);
    }

}
