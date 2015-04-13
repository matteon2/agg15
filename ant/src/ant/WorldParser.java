/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template worldFile, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mo
 */
public class WorldParser {

    private String worldFile;//Store the whole file
    private String world;//Stores just the grid
    private char[][] worldArray;//World array whice I will perform the validation
    private int worldY, worldX;
    //Scanner scanner = null;
    BufferedReader input = null;
    FileReader fReader = null;

    ArrayList<Point> food = new ArrayList<>();

    private char[] parsedWorld;

    public WorldParser() {
        worldFile = "";
        world = "";
        worldY = 0;
        worldX = 0;
        //scanner = new Scanner(System.in);
    }

    public void openFile() {
        //Change path when I refactor with git
        //String name = "C:\\Users\\Mo\\Documents\\NetBeansProjects\\Learn Java\\ScreenTest\\src\\screentest\\antworld.world";
        String name = "C:\\Users\\Mo\\Downloads\\2.world";
        File file = new File(name/*fName*/);

        try {
            fReader = new FileReader(file);

        } catch (FileNotFoundException e) {
            // File not found
            System.out.println(e.getMessage());
        }
    }

    public void readFile() {
        String xVal, yVal, line;
        input = new BufferedReader(fReader);
        try {
            //Reads first two lines from worldFile and stores in variable
            xVal = input.readLine();
            yVal = input.readLine();

            //Adds the two values read to the worldFile
            worldFile += xVal + "\n";
            worldFile += yVal + "\n";

            //Sets the local variables to the world size
            worldY = Integer.valueOf(xVal);
            worldX = Integer.valueOf(yVal);

            //While there is another line in the worldFile
            while ((line = input.readLine()) != null) {
                //Stores line in world variable
                world += line + "\n";
            }
            worldFile += world;
            //Close worldFile at the end
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(WorldParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFile() {
        //Do not really need, testing purpose only
        return worldFile;
    }

    public void printMap() {
        //Do not need, testing purpose only
        for (int i = 0; i < worldY; i++) {
            for (int j = 0; j < worldX; j++) {
                System.out.print(worldArray[i][j]);
            }
            System.out.println("");
        }
    }

    public char[][] toArray() {
        //Stores world from a string to an array

//        for(int i=0; i<world.length();i++){
//            System.out.println("world leng: "+world.charAt(i));
//            System.out.println(i);
//        }
        //Prints the world and world len (string containing just the world)
        //System.out.println(world);
        //System.out.println("world length: "+world.length());
        //Should initialise at the beginning
        worldArray = new char[worldY][worldX];

        //Removes whitespaces from string
        world = world.replaceAll("\\s+", "");
        System.out.println("new WORLD:");
        System.out.println(world);

        System.out.println("worldX: " + worldX);
        System.out.println("worldY: " + worldY);
        int count = 0;
        for (int i = 0; i < worldY; i++) {
            for (int j = 0; j < worldX; j++) {
                worldArray[i][j] = world.charAt(count);
                //System.out.println(count);
                count++;
            }
        }
        return worldArray;
    }

    /* Methods */
    //Check x dimension
    public boolean checkX() {
        return worldX == 150;
    }

    //Check y dimension
    public boolean checkY() {
        return worldY == 150;
    }

    //Cheeck for NO illegal characters
    public boolean checkCharacter() {
        char character;
        boolean illegalCheck = true;
        for (int i = 0; i < worldY; i++) {
            for (int j = 0; j < worldX; j++) {
                // Find all rocky cells -> Add to hashset
                character = worldArray[i][j];
                //If we find a illegal character, return false
                switch (character) {
                    case '.':
                        break;
                    case '#':
                        break;
                    case '-':
                        break;
                    case '+':
                        break;
                    case '5':
                        break;
                    default:
                        illegalCheck = false;
                        return illegalCheck;
                }
            }
        }
        return illegalCheck;
    }

    //Is the perimeter rocks?
    public boolean checkPerimeter() {
        boolean blocked = true;
        // Checks if the perimeter is blocked (top + bottom)
        for (int i = 0; i < worldY; i++) {
            if (worldArray[0][i] != '#') {
                blocked = false;
                return blocked;
            }
        }
        for (int i = 0; i < worldY; i++) {
            if (worldArray[worldY - 1][i] != '#') {
                blocked = false;
                return blocked;
            }
        }
        // Checks if the perimeter is blocked (left + right)
        for (int i = 0; i < worldX; i++) {
            if (worldArray[i][0] != '#') {
                blocked = false;
                return blocked;
            }
        }
        for (int i = 0; i < worldX; i++) {
            if (worldArray[i][worldX - 1] != '#') {
                blocked = false;
                return blocked;
            }
        }
        //The perimeter is all blocked - return true
        return blocked;
    }

    //Is there one empty cell on the inside of ther perimeter?
    public boolean checkEmptyPerimeter() {
        boolean emptyPerimeter = true;

        // Checks if the perimeter is blocked (top + bottom)
        for (int i = 1; i < worldY - 1; i++) {
            if (worldArray[1][i] != '.') {
                emptyPerimeter = false;
                return emptyPerimeter;
            }
        }
        for (int i = 1; i < worldY - 1; i++) {
            if (worldArray[worldY - 2][i] != '.') {
                emptyPerimeter = false;
                return emptyPerimeter;
            }
        }
        // Sets the perimeter to blocked (left + right)
        for (int i = 1; i < worldX - 1; i++) {
            if (worldArray[i][1] != '.') {
                emptyPerimeter = false;
                return emptyPerimeter;
            }
        }
        for (int i = 1; i < worldX - 1; i++) {
            if (worldArray[i][worldX - 2] != '.') {
                emptyPerimeter = false;
                return emptyPerimeter;
            }
        }

        return emptyPerimeter;
    }

    // Is ther 14 rocks? and is there a cell gap adjacent to rocks?
    public boolean checkRocks() {
        boolean rockCheck = true;
        HashSet<Point> rocks = new HashSet<>();
        // Loop through world (excluding the rocky perimeter)
        for (int i = 1; i < worldY - 1; i++) {
            for (int j = 1; j < worldX - 1; j++) {
                // Find all rocky cells -> Add to hashset
                if (worldArray[i][j] == '#') {
                    rocks.add(new Point(j, i));
                }
            }
        }
        System.out.println("how many rocks.." + rocks.size());
        System.out.println(rocks);

        // If there is not 14 rocks (not including the perimeter) return false
        if (rocks.size() != 14) {
            rockCheck = false;
            return rockCheck;
        }
        for (Point p : rocks) {
            int rockX = p.x;
            int rockY = p.y;
            //System.out.println("Y is: " + rockY);

            // If the rock is located on a odd line (BELEIVE ME)
            if (rockY % 2 == 0) {
                //System.out.println("even!");
                //System.out.println("Cur Point(x,y): "+rockX+","+rockY);
                // Check adjacent cells
                if (worldArray[rockY - 1][rockX] != '.'
                        || worldArray[rockY - 1][rockX - 1] != '.'
                        || worldArray[rockY][rockX - 1] != '.'
                        || worldArray[rockY][rockX + 1] != '.'
                        || worldArray[rockY + 1][rockX - 1] != '.'
                        || worldArray[rockY + 1][rockX] != '.') {
                    // There is something adjacent to the point
                    rockCheck = false;
                    return rockCheck;
                }

            } else {
                // Check adjacent cells
                //System.out.println("Cur Point(x,y): " + rockX + "," + rockY);
                // Check adjacent cells
                if (worldArray[rockY - 1][rockX] != '.'
                        || worldArray[rockY - 1][rockX + 1] != '.'
                        || worldArray[rockY][rockX - 1] != '.'
                        || worldArray[rockY][rockX + 1] != '.'
                        || worldArray[rockY + 1][rockX + 1] != '.'
                        || worldArray[rockY + 1][rockX] != '.') {
                    // There is something adjacent to the point
                    rockCheck = false;
                    return rockCheck;
                }
            }
        }
        //if rock.size != 14 -> set false -> break

        // Check adjecent cells to the rocks
        return rockCheck;
    }

    //Find 11 food blobs
    public boolean checkFoodBlob() {
        boolean isFood = true;

        for (int i = 1; i < worldY - 1; i++) {
            for (int j = 1; j < worldX - 1; j++) {
                // Find all rocky cells -> Add to hashset
                if (worldArray[i][j] == '5') {

                    //do check function
                    //if the point is on an odd line:
                    if (i % 2 == 0) {
                        if (!food.contains(new Point(j, i))) {
                            isFood = doCheckShape(new Point(j, i), 1);
                        }

                    } else {
                        //if the point is on an even line
                        if (!food.contains(new Point(j, i))) {
                            isFood = doCheckShape(new Point(j, i), 0);
                        }
                    }

                }
            }
        }
        System.out.println("food size: " + food.size());
        if (food.size() != 275) {
            isFood = false;
            return isFood;
        }

//        //So we have the coorect amount of food cells at this point
//        int rockX, rockY;
//        //Is the orientation correct?
//        for (Point p : food) {
//            rockX = p.x;
//            rockY = p.y;
//            // If the rock is located on a odd line (BELEIVE ME)
//            if (rockY % 2 == 0) {
//                //Change 5 to food blob size later
//
//            } else {
//                //The rock is located on a even line
//                findAdjacent(food);
//            }
//        }
        return isFood;
    }
    //Find Red ant hill (right size? one clear cell?)
    //Find Black ant hill (right size? one clear cell?)
    //Are food blobs the correct size? and have one clear cell?

//    //Finds all adjacent cells of the same time
//    public void findAdjacent(HashSet<Point> hs) {
//        HashSet<Point> group = new HashSet<>();
////        int x = p.x;
////        int y = p.y;
//        for (Point p : hs) {
//            if (sameType(p, new Point(p.x + 1, p.y))) {
//                //System.out.println("BRAH");
//            }
//        }
//
//    }
//    public boolean sameType(Point p1, Point p2) {
//        boolean sameType = false;
//        int x1, y1, x2, y2;
//        x1 = p1.x;
//        y1 = p1.y;
//        x2 = p2.x;
//        y2 = p2.y;
//
//        if (worldArray[y1][x1] == worldArray[y2][x2]) {
//            sameType = true;
//        }
//
//        return sameType;
//    }
    public boolean doCheckShape(Point p, int line) {
        //Checks one food blob is the correct shape
        //b is false if on an odd line -> true otherwise

        boolean rightShape = true;
        int count = line;

        int pointX, pointY;
        pointX = p.x;
        pointY = p.y;

        int xTemp = pointX;

        int orientation = 1; //one or -1
        if (worldArray[pointY + 3][pointX] == '5') {
            orientation = -1;
            //Small hack below, line numbers are infuriating!!!
            if (count == 1) {
                count = 0;
            } else {
                count = 1;
            }
        }

        //Checks even lined slanted right food blob
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (worldArray[pointY][pointX] != '5') {
                    rightShape = false;
                    return rightShape;
                }
                //Current cell shape must be a food blob
                food.add(new Point(pointX, pointY));

                pointX += 1;
            }
            pointX = xTemp;
            if (count % 2 == 0) {
                pointX += orientation;
                xTemp += orientation;
            }
            pointY++;
            count++;
        }

        return rightShape;
    }

    public boolean checkRedAnthill() {
        boolean redHillCheck = true;
        Integer count = new Integer(1);

        HashMap<Integer, Point> redhill = new HashMap<>();
        // Loop through world (excluding the rocky perimeter)
        for (int i = 1; i < worldY - 1; i++) {
            for (int j = 1; j < worldX - 1; j++) {
                // Find all rocky cells -> Add to hashset
                if (worldArray[i][j] == '+') {
                    redhill.put(count, new Point(j, i));
                    count++;
                }
            }
        }
        System.out.println("how many cells in redhill.." + redhill.size());
        System.out.println(redhill);

//        //Is there the right amount of cells that make up the redhill?
//        if (redhill.size() != (57 * 2) + 13) {
//            redHillCheck = false;
//            return redHillCheck;
//        }
        //Now check the shape and make sure
        System.out.println("First value? " + redhill.get(1));
        int x = redhill.get(1).x;
        int y = redhill.get(1).y;

        int numb1 = x;
        int numb2 = y;

        int offsetX = numb1;
        int offsetY = numb2;

        for (int i = 0; i < (7 * 2) - 1; i++) {
            //PRINTS FIRST ROW AWAY FROM PERIMETER
            for (int j = 0; j < 7; j++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetX++;
            }
            //num++;
            //Reset offset
            offsetX = numb1;
            //print 2nd 3rd and fourth
            offsetY++;
        }

        if (y % 2 == 0) {
            //The hill is on a odd line
            //If red anthill is on an odd line
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Second line to left
            offsetX--;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 6;
            if (worldArray[offsetY][offsetX] != '+') {
                redHillCheck = false;
                return redHillCheck;
            }

        } else {
            //The hill is on a even line
            //Ant hill is on a even line 

            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }
            //Second line to left
            offsetX--;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 6;
            if (worldArray[offsetY][offsetX] != '+') {
                redHillCheck = false;
                return redHillCheck;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                if (worldArray[offsetY][offsetX] != '+') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetY++;
            }

        }

        //Now check the perimeter is either clear or a food cell
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
        offsetX = x;
        offsetY = y;

        if (y % 2 == 0) {
            // If red anthill is on a odd line
            //offsetX--;
            offsetY--;
            for (int i = 0; i < 8; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the top right perimeter

            offsetX -= 2;

            if (worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
            // -----------------------------------------------------------
            //bottom right perimeter

            if (worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the start of the bottom perimeter
            offsetX = x - 1;
            offsetY = y + 13;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the start of the bottom left perimeter

            offsetX = x - 1;
            offsetY--;
            if (worldArray[offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
            // -----------------------------------------------------------
            //top left perimeter

            if (worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
        } else {
            // The anthill is on a even line
            //offsetX--;
            offsetY--;
            for (int i = 0; i < 8; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the top right perimeter

            offsetX--;

            if (worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the bottom right perimeter
            if (worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the bottom right perimeter
            offsetX = x;
            offsetY = y + 13;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    redHillCheck = false;
                    return redHillCheck;
                }
                offsetX++;
            }

            offsetX = x - 1;
            offsetY--;

            if (worldArray[offsetY][offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }

            if (worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.') {
                redHillCheck = false;
                return redHillCheck;
            }
        }

        return redHillCheck;
    }

    public boolean checkBlackAnthill() {
        boolean blackHillCheck = true;
        Integer count = new Integer(1);

        HashMap<Integer, Point> blackhill = new HashMap<>();
        // Loop through world (excluding the rocky perimeter)
        for (int i = 1; i < worldY - 1; i++) {
            for (int j = 1; j < worldX - 1; j++) {
                // Find all rocky cells -> Add to hashset
                if (worldArray[i][j] == '-') {
                    blackhill.put(count, new Point(j, i));
                    count++;
                }
            }
        }
        System.out.println("how many cells in redhill.." + blackhill.size());
        System.out.println(blackhill);

//        //Is there the right amount of cells that make up the redhill?
//        if (redhill.size() != (57 * 2) + 13) {
//            redHillCheck = false;
//            return redHillCheck;
//        }
        //Now check the shape and make sure
        System.out.println("First value? " + blackhill.get(1));
        int x = blackhill.get(1).x;
        int y = blackhill.get(1).y;

        int numb1 = x;
        int numb2 = y;

        int offsetX = numb1;
        int offsetY = numb2;

        for (int i = 0; i < (7 * 2) - 1; i++) {
            //PRINTS FIRST ROW AWAY FROM PERIMETER
            for (int j = 0; j < 7; j++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetX++;
            }
            //num++;
            //Reset offset
            offsetX = numb1;
            //print 2nd 3rd and fourth
            offsetY++;
        }

        if (y % 2 == 0) {
            //The hill is on a odd line
            //If red anthill is on an odd line
            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Second line to left
            offsetX--;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 6;
            if (worldArray[offsetY][offsetX] != '-') {
                blackHillCheck = false;
                return blackHillCheck;
            }

        } else {
            //The hill is on a even line
            //Ant hill is on a even line 

            //Print the left line --------------
            offsetX--;
            offsetY = numb2 + 2;
            for (int i = 0; i < 9; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }
            //Second line to left
            offsetX--;
            offsetY = numb2 + 4;
            for (int i = 0; i < 5; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //third line to left 
            offsetX--;
            offsetY = numb2 + 6;
            if (worldArray[offsetY][offsetX] != '-') {
                blackHillCheck = false;
                return blackHillCheck;
            }

            //Make the right section  ---------
            offsetX = numb1 + 7;
            offsetY = numb2 + 1;
            for (int i = 0; i < 11; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Next line
            offsetX++;
            offsetY = numb2 + 3;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

            //Last line
            offsetX++;
            offsetY = numb2 + 5;
            for (int i = 0; i < 3; i++) {
                if (worldArray[offsetY][offsetX] != '-') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetY++;
            }

        }

        //Now check the perimeter is either clear or a food cell
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
        offsetX = x;
        offsetY = y;

        if (y % 2 == 0) {
            // If red anthill is on a odd line
            //offsetX--;
            offsetY--;
            for (int i = 0; i < 8; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the top right perimeter

            offsetX -= 2;

            if (worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
            // -----------------------------------------------------------
            //bottom right perimeter

            if (worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the start of the bottom perimeter
            offsetX = x - 1;
            offsetY = y + 13;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the start of the bottom left perimeter

            offsetX = x - 1;
            offsetY--;
            if (worldArray[offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
            // -----------------------------------------------------------
            //top left perimeter

            if (worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
        } else {
            // The anthill is on a even line
            //offsetX--;
            offsetY--;
            for (int i = 0; i < 8; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetX++;
            }
            // -----------------------------------------------------------
            //set offSet to the top right perimeter

            offsetX--;

            if (worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][++offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the bottom right perimeter
            if (worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.'
                    || worldArray[++offsetY][--offsetX] != '.'
                    || worldArray[++offsetY][offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
            // -----------------------------------------------------------
            //set offSet to the bottom right perimeter
            offsetX = x;
            offsetY = y + 13;
            for (int i = 0; i < 7; i++) {
                if (worldArray[offsetY][offsetX] != '.') {
                    blackHillCheck = false;
                    return blackHillCheck;
                }
                offsetX++;
            }

            offsetX = x - 1;
            offsetY--;

            if (worldArray[offsetY][offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][--offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }

            if (worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.'
                    || worldArray[--offsetY][++offsetX] != '.'
                    || worldArray[--offsetY][offsetX] != '.') {
                blackHillCheck = false;
                return blackHillCheck;
            }
        }

        return blackHillCheck;
    }

}
