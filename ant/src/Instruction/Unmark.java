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
    
    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}