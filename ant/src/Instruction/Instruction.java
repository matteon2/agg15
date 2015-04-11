package Instruction;

import ant.Ant;
import ant.World;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * An abstract super class that represent the structure of instruction
 * @author Andrew
 */
public abstract class Instruction {
    int tokenLength;
    
    public abstract int getTokenLength();
    /*
    To run the current instruction
    */
//    public abstract void execute();

    public abstract void execute(World world, Ant ant);
}
