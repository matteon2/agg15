/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

/**
 *
 * @author Andrew
 */
public class Game {
    
    static Duel dMode;
    static Tournament tMode;
    public static final boolean DEBUG = false;
    
    private static int randomCallCounter = 0;
    private static int seed;
    
    public Game(){
        dMode = null;
        tMode = null;
    }
    
    /**
     * NUMBER THEORY
     * random number generator that same as customer's
     * @param n
     * @return int 
     */
    public static int randomint(int n){
        for(; randomCallCounter < 3; randomCallCounter++){
            seed = seed * 22695477 + 1;
        }
        seed = (seed * 22695477) + 1;
	randomCallCounter++;
	int x = (int) ((Math.floor((double) seed / 65536)) % 16384);
	if (x < 0) {
            x = ((x + 16384) % 16384);
	}
        return x % n;
    }
    
        
    public void setSeed(int seed){
        this.seed = seed;
    }
    
    public int getSeed(){
        return seed;
    }
}
