/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

/**
 * This class only represent the ant hill cell, but it still has the normal cell feature.
 * @author Andrew
 */
public class AntHillCell extends Cell{

    private Color hillColor;
    
    public AntHillCell(Color hillColor){
        this.hillColor = hillColor;
    }

    public Color getColor(){
        return hillColor;
    }

    @Override
    public boolean isAntHill(){
        return true;
    }
}
