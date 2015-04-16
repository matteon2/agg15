/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.Position;
import ant.World;


/**
 * This class represents moving forward and moving state.
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

//    @Override
//    public void execute() {
//
//        System.out.println("Move forward and go to " + "'"+state1+"'" + ";" + " go to " + "'"+state2+"'" + " if the cell ahead is blocked.");
//    }

    /**
     * KINETICS step method
     * execute the Move instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        try {
            Position newp = ant.adjacent_cell(ant.getPosition(), ant.get_direction());
            if(world.isRocky(newp) || world.some_ant_is_at(newp)){
                ant.set_state(state2);
            }
            else{
                world.clear_ant_at(ant.getPosition());
                world.set_ant_at(newp, ant);
                ant.set_state(state1);
                ant.set_resting(14);
                world.check_for_surrounded_ants(newp);
            }
        } catch (Exception ex) {
            System.out.println("Caught exception in move execute method!");
        }
    }

}
