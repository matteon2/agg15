/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Instruction;

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
    }

    @Override
    public int getTokenLength(){
        return tokenLength;
    }
    
    @Override
    public void execute() {

        System.out.println("Set mark " + "'"+marker+"'" + " in current cell and go to " + state);
    }
}
