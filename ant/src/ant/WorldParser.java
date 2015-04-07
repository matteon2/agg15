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

        // Or make a getter from random world
        if (rocks.size() != 14) {
            rockCheck = false;
            return rockCheck;
        }
        for (Point p : rocks) {
            int rockX = p.x;
            int rockY = p.y;
            //System.out.println("Y is: " + rockY);

            // If the rock is located on a even line
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
                    //rocks.add(new Point(j, i));
                }
            }
        }

        return isFood;
    }
    //Find Red ant hill (right size? one clear cell?)
    //Find Black ant hill (right size? one clear cell?)
    //Are food blobs the correct size? and have one clear cell?
    //Lastly -> are there any illegal characters
}
