package ant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public enum Dir {
    EAST, SOUTHEAST, SOUTHWEST, WEST, NORTHWEST, NORTHEAST;
    
    public int getDirection() throws Exception{
        int dir;
        switch(this){
            case EAST:
                dir = 0;
                break;
            case SOUTHEAST:
                dir = 1;
                break;
            case SOUTHWEST:
                dir = 2;
                break;
            case WEST:
                dir = 3;
                break;
            case NORTHWEST:
                dir = 4;
                break;
            case NORTHEAST:
                dir = 5;
                break;
            default:
                throw new Exception("This direction is invalid!");
        }
        return dir;
    }
    
    public Dir getDir(int numDir) throws Exception{
        Dir dir;
        switch(numDir){
            case 0:
                dir = Dir.EAST;
                break;
            case 1:
                dir = Dir.SOUTHEAST;
                break;
            case 2:
                dir = Dir.SOUTHWEST;
                break;
            case 3:
                dir = Dir.WEST;
                break;
            case 4:
                dir = Dir.NORTHWEST;
                break;
            case 5:
                dir = Dir.NORTHEAST;
                break;
            default:
                throw new Exception("not a valid dir!");
        }
        return dir;
    }
    
    public static void main(String args[]) throws Exception{
        Dir d = EAST;
        System.out.println(d.getDir(5));
    }
}
