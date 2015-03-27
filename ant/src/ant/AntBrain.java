package ant;


import Instruction.Instruction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class AntBrain {
    Instruction[] insList;
    String fileName = "";
    
    public AntBrain(Instruction[] insList){
        this.insList = insList;
        fileName = "";
    }
    
    public AntBrain(Instruction[] insList, String filename){
        this.insList = insList;
        this.fileName = filename;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    
    
}
