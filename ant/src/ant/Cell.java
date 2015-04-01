
package ant;

/**
 *
 * @author Stan
 */
public class Cell {
    
    private int x;
    private int y;
    private char contents;
    
    public Cell(int x, int y, char contents){
        this.x = x;
        this.y = y;
        this.contents = contents;
    }
    
    public int getX(){return x;}
    public int getY(){return y;}
    public char getContents(){return contents;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setContents(char contents){this.contents = contents;}
}
