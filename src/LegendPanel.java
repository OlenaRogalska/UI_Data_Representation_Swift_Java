import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;

public class LegendPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    Color color;
    private int rectangleX;
    private int rectangleY;
    private int legendWidth;
    private int legendHeight;
    private int panelWidth;
    private int panelHeight;
    Font font;
    final double LEGENDY = 1;


    LinkedList<LegendRectangle> rectangleList = new LinkedList<LegendRectangle>();

   /**
    * 
    * @param panelWidth
    * @param panelHeight
    * @param countries
    * constructor
    */
    public LegendPanel(int panelWidth, int panelHeight, LinkedList<Country> countries) {
        super();
        font = new Font("Serif", Font.PLAIN, 11);
        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;
        this.setLegendWidth(panelWidth);
        this.setLegendHigth(panelHeight, countries);
        this.setRectangleX(legendWidth);
        this.setRectangleY(legendHeight);
        makeLegends(countries);
    }
/**
 * 
 * @return
 */
    public double getLegendHigth() {
        return legendHeight;
    }

/**
 * 
 * @param panelHigth
 * @param countries
 * set the legend rectangle height to be adjustable to the legend panel height.
 * If user like to display 1 or 2 countries only assign rectangle height to be rectangle width. 
 */
    public void setLegendHigth(int panelHigth, LinkedList<Country> countries) {
        if (countries.size() <= 2) {
            this.legendHeight = this.legendWidth;
        } else
            this.legendHeight = (int) (panelHigth / countries.size() / 3);
    }

    /**
     * 
     * @return
     */
    public double getLegendWidth() {
        return legendWidth;
    }

/**
 * 
 * @param panelWidth
 * set the legend rectangle width to 1/3 of the legend panel width
 */
    public void setLegendWidth(int panelWidth) {
        this.legendWidth = (int) (this.panelWidth / 3);
    }
/**
 * 
 * @return
 */
    public int getRectangleX() {
        return rectangleX;
    }
/**
 * 
 * @param legendWidth
 */
    public void setRectangleX(int legendWidth) {
        this.rectangleX = legendWidth;
    }

    /**
     * 
     * @return
     */
    public int getRectangleY() {
        return rectangleY;
    }
/**
 * 
 * @param legendHigth
 */
    public void setRectangleY(int legendHigth) {
        this.rectangleY = legendHigth;
    }
/**
 * 
 * @param name
 * @return
 */
    public Color setColor(Country name) {
        color = name.getColor();
        return color;
    }

    
    /**
     * 
     * @param randomColor
     * @param name
     * @param panelX
     * @param panelY
     * @param legendWidth
     * @param legendHigth
     * @return
     * creates LegendRectangle object
     */
    public LegendRectangle createRectangle(Color randomColor, String name, int panelX, int panelY, int legendWidth,
            int legendHigth) {
        LegendRectangle legendRectangle = new LegendRectangle(randomColor, name, panelX, panelY, legendWidth,
                legendHigth);
        return legendRectangle;
    }

/**
 * 
 * @param countryList
 * traverses trough the linked list of Country, take name of country and color from it, and fills the Linked List with the LegendRectangle objects.
 * Assigns values  to x and y coordinates to LegendRectangle objects.
 * y coordinates adjusts to the Legend panel height.
 */
    public void makeLegends(LinkedList<Country> countryList) {
        int incrementY = 0;
        int incrementYCurrent = this.panelHeight / countryList.size();
        int currentY = 0;

        Iterator<Country> iterCountry = countryList.iterator();
        while (iterCountry.hasNext()) {
            Country currentCountry = iterCountry.next();
            String iterName = currentCountry.getName();
            Color iterColor = currentCountry.getColor();
            currentY = this.rectangleY + incrementY;

            rectangleList
                    .add(createRectangle(iterColor, iterName, this.rectangleX, currentY, legendWidth, legendHeight));

            this.rectangleY = currentY;

            incrementY = incrementYCurrent;

        }
    }
    
/**
 * traverses trough the LegendRectangle objects linked list and set all components for graphics
 */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Iterator<LegendRectangle> pointIterLegend = this.rectangleList.iterator();

        while (pointIterLegend.hasNext()) {
            LegendRectangle currentLegend = pointIterLegend.next();
            g.setColor(currentLegend.getColor());
            g.drawString(currentLegend.getLabel(), 2, (int) (currentLegend.getY() - LEGENDY));

            g.fillRect((int) currentLegend.getX(), (int) currentLegend.getY(), (int) currentLegend.getWidth(),
                    (int) currentLegend.getHeight());

        }
    }

}
