/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Mo
 */
public class RandomWorld {

    private final int X = 150;
    private final int Y = 150;
    private final int ANTHILL_LENGTH = 7;// makes 6 or 7?
    private final int NO_OF_ROCKS = 14;
    private final int NO_OF_FOOD = 11;
    private final int NO_OF_REDANTHILL = 1;
    private final int NO_OF_BLACKANTHILL = 1;

    private int foodCalled = 0; //amount of times food blob is called
    private int rocksCalled = 0;
    private int redAnthillCalled = 0;
    private int blackAnthillCalled = 0;

    private Random ran;

    //World[Y][X]
    private char[][] world;

    //Soters the points of the red anthill
    HashSet<Point> blockedCellsPerimeter = new HashSet<>();
    HashSet<Point> blockedCellsRocks = new HashSet<>();
    HashSet<Point> redAnthill = new HashSet<>();
    HashSet<Point> blackAnthill = new HashSet<>();
    HashSet<Point> foodBlobs = new HashSet<>();

    public RandomWorld() {
        world = new char[Y][X];
        ran = new Random();
    }

    /**
     * make a empty world
     */
    public void makeWorld() {
        // Sets the world to free cell
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                world[i][j] = '.';
            }
        }
        // Sets the perimeter to blocked (top + bottom)
        for (int i = 0; i < Y; i++) {
            world[0][i] = '#';
            blockedCellsPerimeter.add(new Point(0, i));
        }
        for (int i = 0; i < Y; i++) {
            world[Y - 1][i] = '#';
            blockedCellsPerimeter.add(new Point(Y - 1, i));
        }
        // Sets the perimeter to blocked (left + right)
        for (int i = 0; i < X; i++) {
            world[i][0] = '#';
            blockedCellsPerimeter.add(new Point(i, 0));
        }
        for (int i = 0; i < X; i++) {
            world[i][X - 1] = '#';
            blockedCellsPerimeter.add(new Point(i, X - 1));
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
    /**
     * add red ant hill in location x and y
     * @param x
     * @param y 
     */
    public void redAnthill(int x, int y) {
//        //Call once...SHAPE = +
//        Random ranX = new Random();
//        Random ranY = new Random();

//        // Gets two random poits at least 1 clear cell away from edge
//        int numb1 = ranX.nextInt(Y - 4) + 2;
//        int numb2 = ranY.nextInt(X - 4) + 2;
        
        //Makes sure the random point is not next to the wall
        x += 3;
        y += 3;

        int numb1 = x;
        int numb2 = y;

        int offsetX = numb1;
        int offsetY = numb2;

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
//        int num = 0;
//        int newOffset = 0;
        for (int i = 0; i < (ANTHILL_LENGTH * 2) - 1; i++) {
            //PRINTS FIRST ROW AWAY FROM PERIMETER
            for (int j = 0; j < ANTHILL_LENGTH; j++) {

                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetX++;
            }
            //num++;
            //Reset offset
            offsetX = numb1;
            //print 2nd 3rd and fourth
            offsetY++;
        }

        if (y % 2 == 0) {
            //If red anthill is on an odd line
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
            
            //Second line to left
            offsetX--;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 6;
            world[offsetY][offsetX] = '+';
            redAnthill.add(new Point(offsetX, offsetY));
        } else {
            //Ant hill is on a even line 
            
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
            //Second line to left
            offsetX--;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 6;
            world[offsetY][offsetX] = '+';
            redAnthill.add(new Point(offsetX, offsetY));

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                world[offsetY][offsetX] = '+';
                redAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
        }

        redAnthillCalled++;
    }

    /**
     * add black ant hill in location x and y
     * @param x
     * @param y 
     */
    public void blackAnthill(int x, int y) {
        //x += 3;
        //y += 3;

        int numb1 = x;
        int numb2 = y;

        int offsetX = numb1;
        int offsetY = numb2;

        for (int i = 0; i < (ANTHILL_LENGTH * 2) - 1; i++) {
            //PRINTS FIRST ROW AWAY FROM PERIMETER
            for (int j = 0; j < ANTHILL_LENGTH; j++) {

                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetX++;
            }

            //Reset offset
            offsetX = numb1;
            //print 2nd 3rd and fourth
            offsetY++;
        }

        if (y % 2 == 0) {
            //If red anthill is on an odd line
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
            
            //Second line to left
            offsetX--;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 6;
            world[offsetY][offsetX] = '-';
            blackAnthill.add(new Point(offsetX, offsetY));
        } else {
            //Ant hill is on a even line 
            
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
            //Second line to left
            offsetX--;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 6;
            world[offsetY][offsetX] = '-';
            blackAnthill.add(new Point(offsetX, offsetY));

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                world[offsetY][offsetX] = '-';
                blackAnthill.add(new Point(offsetX, offsetY));
                offsetY++;
            }
        }

        blackAnthillCalled++;
    }

    /**
     * add food blob in location x and y
     * @param x
     * @param y 
     */
    public void foodBlob(int x, int y) {
        //Call 11(NO_OF_FOOD) times only or run loop 11 times

//        //Random initial point
//        Random ranX = new Random();
//        Random ranY = new Random();
//
        // Gets two random poits at least 0 clear cells away from edge
        int twoX = x;
        int oneY = y;

        int orientation = 1; // 1 or -1, Changes the shape of the food blob
//        Random r = new Random();
//        if (r.nextInt(999) % 2 == 0) {
//            orientation = -1;
//        }

        int count;

        //If the random position is on an odd line, count is 1
        if (oneY % 2 == 0) {
            count = 1;
        } else {
            count = 0;
        }

        int twoTemp = twoX;

        //Checks even lined slanted right food blob
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                world[oneY][twoX] = '5';
                foodBlobs.add(new Point(twoX, oneY));
                //CHANGE TO -- AND BELOW FOR MAGIC
                twoX = twoX + orientation;
            }
            twoX = twoTemp;
            //System.out.println("c1: "+(count/2) );
            //Can not think straight anymore, but it works.
            if (count % 2 == 0) {
                //count % 2 == 0 OR 1 YESSSSSSSSSSSSSSSSSSSSSSS
                twoX = twoX + orientation;
                twoTemp = twoTemp + orientation;
            }

            count++;
            oneY++;

        }

        foodCalled++;
    }

    /**
     * add rock in location x and y
     * @param x
     * @param y 
     */
    public void rocks(int x, int y) {
        // if the random position is not a red/black anthill
        // and the cell is empty
        // and the surronding cells are free
//        Random ranX = new Random();
//        Random ranY = new Random();

        int num1 = y;
        int num2 = x;

        //world[y][x]
        world[num1][num2] = '#';
        //point(x,y)
        blockedCellsRocks.add(new Point(num2, num1));

        //System.out.println(blockedCellsRocks);
        rocksCalled++;
    }

//    // REMEMBER, CELLS MUST CONTAIN ONE ELEMENT
//    public char getContents(int x, int y) {
//        //Store cells in use in an array so that we dont overlap
//        char cell = world[y][x];
//
////        switch (cell) {
////            case '.':
////                //do something
////                return '.';
////            case '#':
////                return '#';
////            case '+':
////                return '#';
////            case '-':
////                return '-';
////            default:
////                //must be food
////                return cell;
////        }
//        return cell;
//    }
    
    /**
     * get random world
     * @return 
     */
    public char[][] getRandomWorld() {
        int x, y;

        makeWorld();
        while (redAnthillCalled < NO_OF_REDANTHILL) {
            x = ran.nextInt(X - 6) + 6;
            y = ran.nextInt(Y - 10) + 4;
            if (isClear(x, y, 15, 15)) {
                redAnthill(x, y);
            }

        }
        
        while (blackAnthillCalled < NO_OF_BLACKANTHILL) {
            x = ran.nextInt(X - 6) + 6;
            y = ran.nextInt(Y - 10) + 4;
            if (isClear(x, y, 15, 15)) {
                blackAnthill(x,y);
            }

        }

        while (foodCalled < NO_OF_FOOD) {
            x = ran.nextInt(X - 4) + 2;
            y = ran.nextInt(Y - 4) + 2;
            if (isClear(x, y, 7, 6)) {
//                if(foodBlobs.contains()){
//                    
//                }
                //If the square grid is clear

                //And if the points are not already drawn
                foodBlob(x, y);
            }
        }
        while (rocksCalled < NO_OF_ROCKS) {
            x = ran.nextInt(X - 4) + 2;
            y = ran.nextInt(Y - 4) + 2;
            if (isSurroundingClear(x, y)) {
                rocks(x, y);
            }

        }
        writeToFile();
        //printWorld();
        return world;
    }

//    public void getBlockedPoints() {
//        //INCOMPLETE
//        System.out.println(blockedCellsPerimeter);
//        System.out.println(blockedCellsPerimeter.size());
//    }
    public boolean isClear(int x, int y, int sizeX, int sizeY) {
        boolean isFree = true;
        int tempX = x; //To reset x after loop

        y--;//Ensures that the space above is also clear

        //Checks if the cells are clear before drawing anything
        for (int i = 0; i < sizeY + 1; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (world[y][x] != '.') {
                    isFree = false;
                    return isFree;
                }
                x += 1;
            }
            x = tempX;
            y += 1;
        }
        return isFree;
    }

    public boolean isSurroundingClear(int x, int y) {
        boolean surroundCheck = true;
        int rockX = x;
        int rockY = y;
            //System.out.println("Y is: " + rockY);

        // If the rock is located on a odd line (BELEIVE ME)
        if (rockY % 2 == 0) {
            //System.out.println("even!");
            //System.out.println("Cur Point(x,y): "+rockX+","+rockY);
            // Check adjacent cells
            if (world[rockY - 1][rockX] != '.'
                    || world[rockY - 1][rockX - 1] != '.'
                    || world[rockY][rockX - 1] != '.'
                    || world[rockY][rockX + 1] != '.'
                    || world[rockY + 1][rockX - 1] != '.'
                    || world[rockY + 1][rockX] != '.') {
                // There is something adjacent to the point
                surroundCheck = false;
                return surroundCheck;
            }

        } else {
            // Check adjacent cells
            //System.out.println("Cur Point(x,y): " + rockX + "," + rockY);
            // Check adjacent cells
            if (world[rockY - 1][rockX] != '.'
                    || world[rockY - 1][rockX + 1] != '.'
                    || world[rockY][rockX - 1] != '.'
                    || world[rockY][rockX + 1] != '.'
                    || world[rockY + 1][rockX + 1] != '.'
                    || world[rockY + 1][rockX] != '.') {
                // There is something adjacent to the point
                surroundCheck = false;
                return surroundCheck;
            }
        }
        return surroundCheck;
    }

    public void writeToFile(/*String fName*/) {
        int line = 0;
        try {
            //Change path when I refactor with git
            String name = "C:\\Users\\Mo\\Documents\\NetBeansProjects\\Learn Java\\ScreenTest\\src\\screentest\\antworld.world";
            File file = new File(name/*fName*/);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));

            //Write x and y to first two lines
            output.write(Integer.toString(X));
            output.newLine();
            output.write(Integer.toString(Y));
            output.newLine();
            for (int i = 0; i < Y; i++) {
                // Tab out every other line UNCOMMENT
                if (line % 2 == 1) {
                    output.write(" ");
                }
                for (int j = 0; j < X; j++) {
                    //write to file with space
                    output.write(world[i][j] + " ");

                    //withoutspace
                    //output.write(world[i][j]);
                }
                //output.write('\n');
                output.newLine();
                line++;
            }

            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
