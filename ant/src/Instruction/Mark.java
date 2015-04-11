/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.World;


/**
 * This class represents setting mark in current cell and move state.
 * @author Andrew
 */
public class Mark extends Instruction{
    int marker;
    int state;
    
    public Mark(int marker, int state){
        this.marker = marker;
        this.state = state;
        tokenLength = 3;
        assert(marker >=0 && marker <= 5);
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
//    @Override
//    public void execute() {
//
//        System.out.println("Set mark " + "'"+marker+"'" + " in current cell and go to " + state);
//    }

    /**
     * KINETICS step method
     * execute the Flip instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        try {
            world.set_marker_at(ant.getPosition(), ant.get_color(), marker);
            ant.set_state(state);
        } catch (Exception ex) {
            System.out.println("Caught exception in Mark execute method!");
        }
    }

}
