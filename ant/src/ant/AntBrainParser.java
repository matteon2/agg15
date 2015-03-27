package ant;


import Instruction.Instruction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class AntBrainParser {
    
    /**
     * Read the brain file and convert it to string
     * @param brainFile
     * @return the instruciton string in the brainFile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public AntBrain readBrainFile(File brainFile) throws FileNotFoundException, IOException{
        File brain = brainFile;
        String output = "";
        String buffstr;
        BufferedReader br = new BufferedReader(new FileReader(brain));
        while((buffstr = br.readLine()) != null){
            output += buffstr + "\n";
        }
        br.close();
        
        String[] outputList = output.trim().split("\n");
        Instruction[] insList = new Instruction[outputList.length];
        //only create a empty antBrain
        AntBrain antBrain = new AntBrain(insList);
        return antBrain;
    }
    
    
    
    
    public static void main(String args[]) throws IOException{
        AntBrainParser a = new AntBrainParser();
        File f = new File("/Users/Andrew/NetBeansProjects/SoftwareEngineering/src/antbrain.brain");
        AntBrain s = a.readBrainFile(f);
        System.out.println(s.insList);
    }
}
