package ant;


import Instruction.Condition;
import Instruction.Drop;
import Instruction.Flip;
import Instruction.Instruction;
import Instruction.Mark;
import Instruction.Move;
import Instruction.PickUp;
import Instruction.Sense;
import Instruction.SenseDir;
import Instruction.Turn;
import Instruction.TurnDir;
import Instruction.Unmark;
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
 * This class is used for parsing the ant brain file and creating a ant brain which is defined by a simple, finite state machine.
 * @author Andrew
 */
public class AntBrainParser {
    
    /**
     * Read the brain file and convert it to string
     * @param brainFile
     * @return the instruction string in the brainFile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static String readBrainFile(File brainFile) throws FileNotFoundException, IOException{
        File brain = brainFile;
        String brainStr = "";
        String buffstr;
        BufferedReader br = new BufferedReader(new FileReader(brain));
        while((buffstr = br.readLine()) != null){
            brainStr += buffstr + "\n";
        }
        br.close();   
        return brainStr;
    }
    
    /**
     * Read the file path of brain file and convert it to string
     * @param filePath
     * @return the instruction string
     */
    public static String readBrainFile(String filePath){
        try{
            File f = new File(filePath);
            return readBrainFile(f);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;    
    }
    
    /**
     * Convert all the instruction string to the instruction list
     * @param input
     * @return instruction list
     */
    public static String[] convertToInstructionList(String input){
        String[] instructionList = input.trim().split("\n"); 
        return instructionList;
    }
    
    /**
     * Create the ant brain file by reading its file path
     * @param filePath
     * @return AntBrain
     */
    public static AntBrain createBrain(String filePath){
        try{
            File f = new File(filePath);
            return createBrain(f);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;    
    }
    
    /**
     * Create the ant brain by reading the antbrain file
     * @param brainFile
     * @return AntBrain 
     * @throws IOException
     * @throws Exception 
     */
    public static AntBrain createBrain(File brainFile) throws IOException, Exception{
        String[] list = convertToInstructionList(readBrainFile(brainFile));
        Instruction[] insList = new Instruction[list.length];
        for(int i = 0; i < insList.length;i++){
             insList[i] = allocateInstruction(list[i]);
        }
        if(insList.length > 10000 || insList.length < 1){
            throw new Exception("The number of instructions is between 1 and 10000!");
        }
        return new AntBrain(insList, brainFile.getName());
    }
    
    /**
     * Allocate individual instruction string to the corresponding instruction class by split the instruction into token list
     * @param ins
     * @return Instrucition
     * @throws Exception 
     */
    public static Instruction allocateInstruction(String ins) throws Exception{
        Instruction instruction;
        String[] insToken = ins.trim().split(" ");
        switch(insToken[0].toUpperCase()){
            case "SENSE":
                SenseDir sensedir = checkSenseDirection(insToken[1]);
                int st1 = checkStateNumber(insToken[2]);
                int st2 = checkStateNumber(insToken[3]);
                Condition cond = checkCondition(insToken[4]);
                if(cond == cond.MARKER){
                    int marker = checkMarker(insToken[5]);
                    instruction = new Sense(sensedir, st1, st2, cond, marker);
                }else{
                    instruction = new Sense(sensedir, st1, st2, cond);
                } 
                break;
                
            case "MARK":
                int i = checkMarker(insToken[1]);
                int st = checkStateNumber(insToken[2]);
                instruction = new Mark(i, st);
                break;
                
            case "UNMARK":
                i = checkMarker(insToken[1]);
                st = checkStateNumber(insToken[2]);
                instruction = new Unmark(i, st); 
                break;
                
            case "PICKUP":
                st1 = checkStateNumber(insToken[1]);
                st2 = checkStateNumber(insToken[2]);
                instruction = new PickUp(st1, st2); 
                break;
                
            case "DROP":
                st = checkStateNumber(insToken[1]);
                instruction = new Drop(st); 
                break;
                
            case "TURN":
                TurnDir lr = checkTurnDirection(insToken[1]);
                st = checkStateNumber(insToken[2]);
                instruction = new Turn(lr, st);
                break;
                
            case "MOVE":
                st1 = checkStateNumber(insToken[1]);
                st2 = checkStateNumber(insToken[2]);
                instruction = new Move(st1, st2); 
                break;
                
            case "FLIP":
                int p = Integer.valueOf(insToken[1]);
                st1 = checkStateNumber(insToken[2]);
                st2 = checkStateNumber(insToken[3]);
                instruction = new Flip(p, st1, st2); 
                break;
                
            default:
                throw new Exception("The instruction is invalid!");
        }
        if(insToken.length != instruction.getTokenLength()){
            throw new Exception("The instruction length is invalid!");
        }
        return instruction;
    }
    
    /**
     * Check the sense direction from the direction token string in the individual instruction
     * @param dirString
     * @return SenseDir
     * @throws Exception 
     */
    public static SenseDir checkSenseDirection(String dirString) throws Exception{
        SenseDir direction;
        switch(dirString.toUpperCase()){
            case "HERE":
                direction = SenseDir.HERE;
                break;
            case "AHEAD":
                direction = SenseDir.AHEAD;
                break;
            case "LEFTAHEAD":
                direction = SenseDir.LEFTAHEAD;
                break;
            case "RIGHTAHEAD":
                direction = SenseDir.AHEAD;
                break;
            default:
                throw new Exception("This direction " + dirString + " is unvalid!");
        }
        return direction;
    }
    
    /**
     * Check the condition from the condition token string in the individual instruction
     * @param condString
     * @return Condition
     * @throws Exception 
     */
    public static Condition checkCondition(String condString) throws Exception{
        Condition condition;
        switch(condString.toUpperCase()){
            case "FRIEND":
                condition = Condition.FRIEND;
                break;
            case "FOE":
                condition = Condition.FOE;
                break;
            case "FRIENDWITHFOOD":
                condition = Condition.FRIENDWITHFOOD;
                break;
            case "FOEWITHFOOD":
                condition = Condition.FOEWITHFOOD;
                break;
            case "FOOD":
                condition = Condition.FOOD;
                break;
            case "ROCK":
                condition = Condition.ROCK;
                break;
            case "MARKER":
                condition = Condition.MARKER;
                break;
            case "FOEMARKER":
                condition = Condition.FOEMARKER;
                break;
            case "HOME":
                condition = Condition.HOME;
                break;
            case "FOEHOME":
                condition = Condition.FOEHOME;
                break;
            default:
                throw new Exception("The condition " + condString + " is not valid!");
        }
        return condition;
    }
    
    /**
     * Check the state number from the state number token string in the individual instruction
     * @param stateNumString
     * @return int
     * @throws Exception 
     */
    public static int checkStateNumber(String stateNumString) throws Exception{
        int stateNum = Integer.valueOf(stateNumString);
        if(stateNum < 0 || stateNum > 9999){
            throw new Exception("The state number " + stateNumString + " is invalid!");
        }
        return stateNum;
    }
    
    /**
     * Check the marker from the marker token string in the individual instruction
     * @param markerString
     * @return int
     * @throws Exception 
     */
    public static int checkMarker(String markerString) throws Exception{
        int marker = Integer.valueOf(markerString);
        if(marker < 0 || marker > 5){
            throw new Exception("The marker " + marker + " is invalid!");
        }
        return marker;
    }
    
    /**
     * Check the turn direction from the  turn direction token string in the individual instruction
     * @param lrString
     * @return TurnDir
     * @throws Exception 
     */
    public static TurnDir checkTurnDirection(String lrString) throws Exception{
        TurnDir lr;
        switch(lrString.toUpperCase()){
            case "LEFT":
                lr = TurnDir.LEFT;
                break;
            case "RIGHT":
                lr = TurnDir.RIGHT;
                break;
            default:
                throw new Exception("The turn direction " + lrString + " is invalid!");
        }
        return lr;      
    }
    
    /**
     * Main method
     * @param args
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException{
        AntBrain ant = AntBrainParser.createBrain("/Users/Andrew/NetBeansProjects/SoftwareEngineering/src/antbrain.brain");
        for (Instruction insList : ant.insList) {
            insList.execute();
        }
        
    }
}
