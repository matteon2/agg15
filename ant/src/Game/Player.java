/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import ant.AntBrain;
import ant.Color;

/**
 *
 * @author Andrew
 */
public class Player {
    private String playerName;
    private AntBrain antbrain;
    private Color color;
    
    
    public Player(String playerName, AntBrain antbrain){
        this.playerName = playerName;
        this.antbrain = antbrain;
        this.color = null;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the antbrain
     */
    public AntBrain getAntbrain() {
        return antbrain;
    }

    /**
     * @param antbrain the antbrain to set
     */
    public void setAntbrain(AntBrain antbrain) {
        this.antbrain = antbrain;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }



}
