/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
*
 * @author Wayne Kwok
 */
public class DrawAnt extends JPanel {
    
    public int[] AntX = new int[4];
    public int[] AntY = new int[4];
    
    /* Pass it a World, an if statement to get the cell and paint*/
    
    @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    AffineTransform old = g2d.getTransform();

    //An Black ant facing North as template
    
    g.setColor(java.awt.Color.black);
    this.getAntX(200, 10);
    this.getAntY(200, 10);
    g.fillPolygon(AntX, AntY, 4);
    
    //An Red ant facing North as template
    
    g.setColor(java.awt.Color.red);
    this.getAntX(250, 10);
    this.getAntY(200, 10);
    g.fillPolygon(AntX, AntY, 4);
    
//            // An Ant facing North East
//            g2d.rotate(60, 290, 290);
//            // drawings that will be rotate
//            g2d.setColor(java.awt.Color.green);
//            this.getAntX(250, 20);
//            this.getAntY(250, 20);
//            g2d.fillPolygon(AntX, AntY, 4);
//            g2d.setTransform(old);
//            //Further things drawn after here will not be rotated
//
//            // An Ant facing North East
//            g2d.rotate(90, 290, 290);
//            // drawings that will be rotate
//            g2d.setColor(java.awt.Color.green);
//            this.getAntX(250, 20);
//            this.getAntY(250, 20);
//            g2d.fillPolygon(AntX, AntY, 4);
//            g2d.setTransform(old);
//            //Further things drawn after here will not be rotated
    
    
    
    
    
    
    }



    
    
    
    // int x for x-coordiante and int n for length of sides
    public int[] getAntX(int x, int n) {
        
        AntX[0] = x + 2 * n ;
        AntX[1] = x + 3 * n ;
        AntX[2] = x + 2 * n ;
        AntX[3] = x + n ;
        
        return AntX;
  }
    
    // int y for y-coordiante and int n for length of sides
    public int[] getAntY(int y, int n) {
        
        AntY[0] = y ;
        AntY[1] = y + 4 * n ;
        AntY[2] = y + 3 * n ;
        AntY[3] = y + 4 * n;
        
        
        return AntY;
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
    contentPane.add(new DrawAnt());

    frame.setVisible(true);
  }
}