package Instruction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public abstract class Instruction {
    int tokenLength;
    
    public abstract int getTokenLength();
    /*
    To run the current instruction
    */
    public abstract void execute();
    
}