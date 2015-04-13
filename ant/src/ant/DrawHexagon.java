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
 * Odd Rolls (x-coordiante): x + C * n
 * Even Rolls (x-coordinate): x + (C + 1.5) * n
 * 
 * Rolls (y-coordinate) : y + (int) R * n * Math.sqrt(3) / 2
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
    this.getHexaX(350, 50);
    this.getHexaY(200, 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is food, block is green
    g.setColor(java.awt.Color.green);
    this.getHexaX(275, 50);
    this.getHexaY(200 + (int) (50 * Math.sqrt(3) / 2), 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is Red ant hill, block is pink (as soften red color)
    g.setColor(java.awt.Color.pink);
    this.getHexaX(425, 50);
    this.getHexaY(200 + (int) (50 * Math.sqrt(3) / 2), 50);
    g.fillPolygon(HexaX,HexaY, 6);
    
    //if cell is empty, block is white
    g.setColor(java.awt.Color.white);
    this.getHexaX(200, 50);
    this.getHexaY(200 + (int) (50 * Math.sqrt(3)), 50);
    g.fillPolygon(HexaX,HexaY, 6);
//    g.fillPolygon(this.getHexaX(200, 50), this.getHexaY(200, 50), 6);
    
    
//    Polygon p = new Polygon();
//    Polygon p1 = new Polygon();
//    for (int i = 0; i < 6; i++)
//      p.addPoint((int) (200 + 50 * Math.cos(i * 2 * Math.PI / 6)), (int) (200 + 50 * Math.sin(i * 2 * Math.PI / 6)));
//    g.fillPolygon(p);
//    for (int i = 0; i < 6; i++)
//      p1.addPoint((int) (275 + 50 * Math.cos(i * 2 * Math.PI / 6)), (int) (200 + 25 * Math.sqrt(3) + 50 * Math.sin(i * 2 * Math.PI / 6)));
//    g.drawPolygon(p1);
  }
    
    
    // int x for x-coordiante and int n for length of sides
    public int[] getHexaX(int x, int n) {
        
//        {x, x + n, x + 1.5 * n, x + n, x, x - 0.5 * n};//
        
        HexaX[0] = x;
        HexaX[1] = x + n;
        HexaX[2] = x + n + (n/2);
        HexaX[3] = x + n;
        HexaX[4] = x;
        HexaX[5] = x - (n/2);
        
        
        return HexaX;
    }
    
    // int y for y-coordiante and int n for length of sides
    public int[] getHexaY(int y, int n) {
        
        //{y, y, y + h, y + 2 * h, y + 2 * h, y + h};//
        int h = (int) (n * Math.sqrt(3) / 2);
        
        HexaY[0] = y;
        HexaY[1] = y ;
        HexaY[2] = y + h;
        HexaY[3] = y + 2 * h;
        HexaY[4] = y + 2 * h;
        HexaY[5] = y + h;
        
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