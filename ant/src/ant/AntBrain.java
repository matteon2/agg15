package ant;


import Instruction.Instruction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class represent the ant brain which is a finite state machine, 
 * it contains a list of instruction object and can control the whole group of ant.
 * @author Andrew
 */
public class AntBrain {
    Instruction[] insList;
    String fileName = "";
    
    /**
     * Create an ant brain by a instruction list
     * @param insList 
     */
    public AntBrain(Instruction[] insList){
        this.insList = insList;
        fileName = "";
    }
    
    /**
     * Create an ant brain by a instruction list and a string as filename
     * @param insList
     * @param filename 
     */
    public AntBrain(Instruction[] insList, String filename){
        this.insList = insList;
        this.fileName = filename;
    }
    
    /**
     * Get the name of the ant brain file
     * @return string
     */
    public String getFileName(){
        return fileName;
    }
    
    
    
}
