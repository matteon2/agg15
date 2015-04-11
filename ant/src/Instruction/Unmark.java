/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

import ant.Ant;
import ant.World;


/**
 * This class represents clearing mark in the current cell and moving state.
 * @author Andrew
 */
public class Unmark extends Instruction{
    int marker;
    int state;
    
    public Unmark(int marker, int state){
        this.marker = marker;
        this.state = state;
        tokenLength = 3;
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
//    @Override
//    public void execute() {
//        System.out.println("Clear mark " + "'"+marker+"'" + "  in current cell and go to " + "'"+state+"'" + ".");
//    }

    /**
     * KINETICS step method
     * execute the Unmark instruction 
     * @param world
     * @param ant
     */
    @Override
    public void execute(World world, Ant ant) {
        try {
            world.clear_marker_at(ant.getPosition(), ant.get_color(), marker);    
            ant.set_state(state);
        } catch (Exception ex) {
            System.out.println("Caught exception in unmark execute method!");
        }
    }

}
