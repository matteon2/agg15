/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *Formulas for cell placement :
 * Odd  Rolls (x-coordinate) : x + (int) R * n * Math.sqrt(3) / 2
 * Even Rolls (x-coordinate) : x + (int) (R + 1) * n * Math.sqrt(3) / 2
 * Odd  Rolls (y-coordiante) : y + C * 3 * n
 * Even Rolls (y-coordiante) : y + (C + 1.5) * 3 * n
 * 
 * x is the inital x-coordinate
 * y is the inital y-coordinate
 * C is the sequence of Cell
 * R is the sequence of Roll
 * @author Wayne Kwok
 */
public class DrawHexagon extends JPanel {
    
    public int[] HexaX = new int[6];
    public int[] HexaY = new int[6];
    
    @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    //if cell is Rocky, block is black
    
    g.setColor(java.awt.Color.black);
    this.getHexaX(200, 50);
    this.getHexaY(200, 50);
    g.fillPolygon(HexaX, HexaY, 6);
    
    //if cell is Black ant hill, block is grey
    g.setColor(java.awt.Color.gray);
    this.getHexaX(200, 50);
    this.getHexaY(350, 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is food, block is green
    g.setColor(java.awt.Color.green);
    this.getHexaX(200 + (int) (50 * Math.sqrt(3) / 2), 50);
    this.getHexaY(275, 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is Red ant hill, block is pink (as soften red color)
    g.setColor(java.awt.Color.pink);
    this.getHexaX(200 + (int) (50 * 3 * Math.sqrt(3) / 2), 50);
    this.getHexaY(275, 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is empty, block is white
    g.setColor(java.awt.Color.white);
    this.getHexaX(200 + (int) (50 * Math.sqrt(3)), 50);
    this.getHexaY(200, 50);
    g.fillPolygon(HexaX,HexaY, 6);
    }

    
    
    
    // int x for x-coordiante and int n for length of sides
    public int[] getHexaX(int x, int n) {
        
        // hw stands for half-width
        int hw = (int) (n * Math.sqrt(3) / 2);
        
        HexaX[0] = x;
        HexaX[1] = x ;
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
        HexaY[2] = y + n + (n/2);
        HexaY[3] = y + n;
        HexaY[4] = y;
        HexaY[5] = y - (n/2);
        
        
        return HexaY;
    }

    
    
    
    
    
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("DrawPoly");
    frame.setSize(1000, 1000);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    Container contentPane = frame.getContentPane();
    contentPane.add(new DrawHexagon());

    frame.show();
  }
}