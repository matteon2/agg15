/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import ant.Color;
import ant.World;
import java.util.HashMap;

/**
 *
 * @author Andrew
 */
public class Duel {

    private Player playerRed;
    private Player playerBlack;
    private World antWorld;
    private Player winner;
    private static int maxRound = 300000;
    private int rountCount;
    private boolean draw;
    private HashMap<Player, Stat> scores;
    
    public Duel(Player pr, Player pb, World world){
        this.playerRed = pr;
        this.setPlayerRed(pr);
        this.playerBlack = pb;
        this.setPlayerRed(pb);
        this.antWorld = world;
        this.winner = null;
        this.rountCount = 0;
        this.draw = false;    
        this.scores.put(pr, new Stat());
        scores.put(pb, new Stat());
    }

    /**
     * @return the playerRed
     */
    public Player getPlayerRed() {
        return playerRed;
    }

    /**
     * @param playerRed the playerRed to set
     */
    public void setPlayerRed(Player player) {
        this.playerRed = player;
        player.setColor(Color.RED);
    }

    /**
     * @return the playerBlack
     */
    public Player getPlayerBlack() {
        return playerBlack;
    }

    /**
     * @param playerBlack the playerBlack to set
     */
    public void setPlayerBlack(Player player) {
        this.playerBlack = player;
        player.setColor(Color.BLACK);
    }

    /**
     * @return the antWorld
     */
    public World getAntWorld() {
        return antWorld;
    }

    /**
     * @param antWorld the antWorld to set
     */
    public void setAntWorld(World antWorld) {
        this.antWorld = antWorld;
        
    }

    /**
     * @return the winner
     */
    public Player getWinner() {
        if(this.scores.get(this.playerBlack).getFoodCollected() > this.scores.get(this.playerRed).getFoodCollected()){
            this.winner = playerBlack;
        }
        if(this.scores.get(this.playerBlack).getFoodCollected() < this.scores.get(this.playerRed).getFoodCollected()){
            this.winner = playerRed;
        }else{
            winner = null;
            this.draw = true;
        }
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * @return the rountCount
     */
    public int getRountCount() {
        return rountCount;
    }

    /**
     * @param rountCount the rountCount to set
     */
    public void setRountCount(int rountCount) {
        this.rountCount = rountCount;
    }

    /**
     * @return the draw
     */
    public boolean isDraw() {
        return (this.winner == null);
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    /**
     * @return the scores
     */
    public HashMap<Player, Stat> getScores() {
        return scores;
    }

    /**
     * @param scores the scores to set
     */
    public void setScores(HashMap<Player, Stat> scores) {
        this.scores = scores;
    }
    
    
}
