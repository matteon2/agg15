/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Formulas for cell placement : 
 * Odd Rows  (x-coordinate) : x + (int) R * n * Math.sqrt(3)
 * Even Rows (x-coordinate) : x + (int) (R + 1) * n * Math.sqrt(3)
 * Odd Rows  (y-coordiante) : y + C * 3 * n
 * Even Rows (y-coordiante) : y + (C + 1.5) * 3 * n
 *
 * x is the inital x-coordinate y is the inital y-coordinate C is the sequence
 * of Cell R is the sequence of Row
 *
 * @author Wayne Kwok
 */
public class WorldViewer extends JPanel {

    public int[] HexaX = new int[6];
    public int[] HexaY = new int[6];
    World w;
    Cell[][] unitCell;
    
    public WorldViewer(){
        w = new World();
        unitCell = w.initialiseWorld();
    }

    /* Pass it a World, an if statement to get the cell and paint*/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
//            //if cell is Rocky, block is black
//


        int n = 6; //length of sides
        
        
        for (int column = 0; column < 150; column++) {
            for (int row = 0; row < 150; row++) {

                //If the random position is on an odd line, column is odd
                if (column % 2 == 1) {
                    this.getHexaX(10 + (int) (row * n * Math.sqrt(3)) + (int) (n * Math.sqrt(3) / 2), n);
                    this.getHexaY(10 + 3 * n * column * 1 / 2, n);
                    
                    
                    if (unitCell[column][row].isContainRock()) {
                        g.setColor(Color.BLACK); //Set Rock Cell in Black Color
                         // g.setColor(java.awt.Color.white);
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    } 
                    else if(unitCell[column][row].isIsClear()) {
                        g.setColor(Color.WHITE); //Set Black Ant hill in Lightgrey Color
                         // g.setColor(java.awt.Color.white);
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    } 
                    else if (unitCell[column][row].isContainsRedAnt()) {
                        g.setColor(Color.RED); //Set Red Ant hill in Pink Color
                         // g.setColor(java.awt.Color.white);
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    
                    }else if (unitCell[column][row].isContainsBlackAnt()){
                        g.setColor(Color.BLACK); //Set Black Ant hill in Pink Color
                         // g.setColor(java.awt.Color.white);
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    }
                    else if (unitCell[column][row].hasFood()) {
                        g.setColor(Color.GREEN); //Set Food in Green Color
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    }
                
            

                   
                } else {
                    this.getHexaX(10 + (int) (row * n * Math.sqrt(3)), n);
                    this.getHexaY(10 + 3 * n * column * 1 / 2, n);
                    

                    if (unitCell[column][row].isContainRock()) {
                        g.setColor(Color.BLACK); //Set Rock Cell in Black Color
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                        
                    } 
                    else if(unitCell[column][row].isIsClear()) {
                        g.setColor(Color.WHITE); //Set Black Ant hill in Lightgrey Color
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    } 
                    else if (unitCell[column][row].isContainsRedAnt()) {
                        g.setColor(Color.RED); //Set Red Ant hill in Pink Color
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    }else if (unitCell[column][row].isContainsBlackAnt()){
                        g.setColor(Color.BLACK); //Set Black Ant hill in Pink Color
                         // g.setColor(java.awt.Color.white);
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    }
                    else if (unitCell[column][row].hasFood()) {
                        g.setColor(Color.GREEN); //Set Food in Green Color
                        g.fillPolygon(HexaX, HexaY, 6);
                        g.drawPolygon(HexaX, HexaY, 6);
                    }

                    // g.setColor(java.awt.Color.white);

                    
                }


            }
        }
        

    }

    public int[] getHexaX(int x, int n) {

        // hw stands for half-width
        int hw = (int) (n * Math.sqrt(3) / 2);

        HexaX[0] = x;
        HexaX[1] = x;
        HexaX[2] = x + hw;
        HexaX[3] = x + 2 * hw;
        HexaX[4] = x + 2 * hw;
        HexaX[5] = x + hw;

        return HexaX;
    }

    // int y for y-coordiante and int n for length of sides
    public int[] getHexaY(int y, int n) {

//        {x, x + n, x + 1.5 * n, x + n, x, x - 0.5 * n};//

        HexaY[0] = y;
        HexaY[1] = y + n;
        HexaY[2] = y + n + (n / 2);
        HexaY[3] = y + n;
        HexaY[4] = y;
        HexaY[5] = y - (n / 2);


        return HexaY;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("DrawPoly");
        frame.setSize(1600, 1405);
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
        Container contentPane = frame.getContentPane();
        contentPane.add(new WorldViewer());

        frame.setVisible(true);
       // frame.setLocationRelativeTo(null);
    }
}