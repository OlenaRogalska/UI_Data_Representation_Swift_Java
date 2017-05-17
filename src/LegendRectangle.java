
import java.awt.Color;
import java.awt.Rectangle;


public class LegendRectangle extends Rectangle{

    private static final long serialVersionUID = 1L;
    private String lable;
    Color color;
    int lebX;
    int lebY;
    int width;
    int  high;
   
    /**
     * 
     * @return
     */
    public int getLebX() {
        return lebX;
    }


    /**
     * @param lebX
     */
    public void setLebX(int lebX) {
        this.lebX = lebX;
    }

/**
 * 
 * @return
 */
    public int getLebY() {
        return lebY;
    }

/**
 * 
 * @param lebY
 */
    public void setLebY(int lebY) {
        this.lebY = lebY;
    }

/**
 *  
 * @param color
 * @param lable
 * @param lebX
 * @param lebY
 * @param width
 * @param high
 * creates object of type LegendRectangle
 */
    public LegendRectangle(Color color, String lable, int lebX, int lebY, int width, int high) {
        super(lebX, lebY, width, high);
        this.color = color;
        this.lable = lable;
        
    }

/**
 * 
 * @return
 */
    public Color getColor(){
        return color;
    }
    
    /**
     * 
     * @param lable
     */
    public void setLabel(String lable){
        this.lable = lable;
    }
    
    /**
     * 
     * @return
     */
    public String getLabel()
    { 
        return lable;
    }
    
  
}