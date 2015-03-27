/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.util.Random;

/**
 *
 * @author Mo
 */
public class RandomWorld {

    private final int Y = 60;
    private final int X = 60;

    private char[][] world;

    private final int ANTHILL_LENGTH = 4;// makes 6 or 7?

    public RandomWorld() {
        world = new char[Y][X];
    }

    public void makeWorld() {
        // Sets the world to free cell
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                world[y][x] = '.';
            }
        }
        // Sets the perimeter to blocked (top + bottom)
        for (int i = 0; i < Y; i++) {
            world[0][i] = '#';
        }
        for (int i = 0; i < Y; i++) {
            world[Y - 1][i] = '#';
        }
        // Sets the perimeter to blocked (left + right)
        for (int i = 0; i < X; i++) {
            world[i][0] = '#';
        }
        for (int i = 0; i < X; i++) {
            world[i][X - 1] = '#';
        }

    }

    public void printWorld() {
//        int num = 0;

        System.out.println(Y);
        System.out.println(X);
        for (int i = 0; i < Y; i++) {
            // Every other line, print a space

//            if (num % 2 == 1) {
//                System.out.print(" ");
//            }
            for (int j = 0; j < X; j++) {
                System.out.print(world[i][j] + " ");
            }

            System.out.println("");
//            num++;
        }
    }

    /* ***********
     2 anthills, 14 rocks, and 11 blobs of food.
     ************* */
    public void redAnthill() {
        //Call once...SHAPE = +
        Random ranX = new Random();
        Random ranY = new Random();
        
        // Gets two random poits at least 1 clear cell away from edge
        int numb1 = ranX.nextInt(Y - 4) + 2;
        int numb2 = ranY.nextInt(X - 4) + 2;

        int offsetY = numb1;
        int offsetX = numb2;

        //world[numb1][numb2] = '3';
        /* *********
         AS LONG AS THE RANDOM POSITION IS NOT THE PERIMETER && is within map (x<150 j<150)
         *************/
//        for(int i = 0; i<(ANTHILL_LENGTH*2)-1; i++){
//            //PRINTS FIRST ROW AWAY FROM PERIMETER
//            for (int j = 0; j < ANTHILL_LENGTH-1; j++) {
//                if(offsetX==X-2){
//                    world[numb1][--numb2] = '+';
//                }else{
//                    world[numb1][offsetX++] = '+';
//                }
//            }
//        //print 2nd 3rd and fourth
//            numb1+=i;
//        }
//        int offset1 = numb1;
//        int offset2 = ANTHILL_LENGTH;
//        for(int i=0; i<offset2-1; i++){
//            world[offset1][numb2] = '+';
//        }
        int num = 0;
        int newOffset = 0;
        for (int i = 0; i < (ANTHILL_LENGTH * 2) - 1; i++) {
            //PRINTS FIRST ROW AWAY FROM PERIMETER
            for (int j = 0; j < ANTHILL_LENGTH - 1; j++) {
                world[offsetY][++offsetX] = '+';
                if (num % 3 < 2) {
                    world[offsetY][newOffset + offsetX + 1] = 'P';
                }
            }
            num++;
            //Reset offset
            offsetX = numb2 + newOffset;
            //print 2nd 3rd and fourth
            offsetY++;
        }
    }

    public void blackAnthill() {
        //Call once...SHAPE = -
        //Make sure its 55 cells away from red?
    }

    public void foodBlob(int position) {
        //Call 11 times only
    }

    public void rocks() {

    }

    // REMEMBER, CELLS MUST CONTAIN ONE ELEMENT
    public void cellsInUse() {
        //Store cells in use in an array so that we dont overlap
    }

    public char[][] getWorld() {
        makeWorld();
        redAnthill();

        //printWorld();
        return world;
    }
}
