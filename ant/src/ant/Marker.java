/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ant;

/**
 *
 * @author Andrew
 */
public enum Marker {
    ZERO, ONE, TWO, THREE, FOUR, FIVE;
    
    public int getMarkerNum() throws Exception{
        int markerNum;
        switch(this){
            case ZERO:
                markerNum = 0;
                break;
            case ONE:
                markerNum = 1;
                break;
            case TWO:
                markerNum = 2;
                break;
            case THREE:
                markerNum = 3;
                break;
            case FOUR:
                markerNum = 4;
                break;
            case FIVE:
                markerNum = 5;
                break;
            default:
                throw new Exception("The marker number is invalid!");
        }
        return markerNum;
    }
    
}
