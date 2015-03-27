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
public class Sense extends Instruction{
    SenseDir senseDir;
    int state1;
    int state2;
    Condition condition;
    
    public Sense(SenseDir senseDir, int st1, int st2, Condition condition){
        this.senseDir = senseDir;
        this.state1 = st1;
        this.state2 = st2;
        this.condition = condition;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
