/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mo
 */
public class WorldParser {

    private String file;
    //Scanner scanner = null;
    BufferedReader input = null;
    FileReader fReader = null;

    public WorldParser() {
        file = "";
        //scanner = new Scanner(System.in);
    }

    public void openFile() {
        //Change path when I refactor with git
        String name = "C:\\Users\\Mo\\Documents\\NetBeansProjects\\Learn Java\\ScreenTest\\src\\screentest\\antworld.world";
        File file = new File(name/*fName*/);

        try {
            fReader = new FileReader(file);

            //Write x and y to first two lines
        } catch (FileNotFoundException e) {
            // File not found
            System.out.println(e.getMessage());
        }
    }

    public void readFile() {
        String xVal, yVal, line;
        input = new BufferedReader(fReader);
        try {
            //Reads first two lines from file and stores in variable
            xVal = input.readLine();
            yVal = input.readLine();

            //Adds the two values read to the file
            file += xVal + "\n";
            file += yVal + "\n";
            
            //While there is another line in the file
            while ((line = input.readLine()) != null) {
                file += line + "\n";
                //System.out.println(line);
            }
            //Close file at the end
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(WorldParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFile() {
        return file;
    }
}
