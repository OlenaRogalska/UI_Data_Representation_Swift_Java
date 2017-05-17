import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * 
 * @author benjaminlinder
 *
 */

public class GraphHoverOver extends JPanel implements MouseInputListener 

{
	private int width, height;
	protected ColoredPoint[] testPoints = new ColoredPoint[10];
	private Font font;
	final int POINT_SIZE = 8;
	final int MARGIN = 30;
	final int RIGHT_MARGIN = 80;
	private double maxSubs = 0;
	private double minYear = 9999;
	private double maxYear = 0;
	private Graphics2D g2d;
	boolean mouseChange = false;

	private int mouseX, mouseY;
	
	
	private LinkedList<ColoredPoint> points = new LinkedList<ColoredPoint>();

	/**
	 * Constructor for Graphview
	 * @param width
	 * @param height
	 * @param countries
	 */
	
	public GraphHoverOver(int width, int height, LinkedList<Country> countries)
	{
		this.width = width;
		this.height = height;
		this.setBackground(Color.LIGHT_GRAY);
		this.setName("Graph Panel");
		font = new Font("Serif", Font.PLAIN, 12);
		
		// ASSIGNMENT 8
		addMouseListener(this);
        addMouseMotionListener(this);
        
        
		//Figure out the maximum subscriptions value and year range
		
		getMaxSubs(countries);
		
		// Create the list of points for plotting
		
		MakePoints(countries);
		
		return;
	}

	/**
	 * Map function
	 * @param value
	 * @param dataMin
	 * @param dataMax
	 * @param plottedMin
	 * @param plottedMax
	 * @return coordinate that's mapped
	 */
	static public final double map(double value,
		    double dataMin, double dataMax,
		    double plottedMin, double plottedMax)
	{
	    return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax - dataMin));
	}
	
	/**
	 * Create the list of points derived from the list of countries
	 * @param countries
	 */
	
	private void MakePoints(LinkedList <Country> countries)
	{
		
		Iterator<Country> iterCountry = countries.iterator();
		
		while(iterCountry.hasNext())
		{
			
			Country currentCountry = iterCountry.next();
			
			// Our color for this country is already in the Country object;
			Color randomColor = currentCountry.getColor();
			
			LinkedList<SubscriptionYear> years = currentCountry.getSubscriptionList();
		
			
			Iterator<SubscriptionYear> iterYear = years.iterator();
			
			
			while(iterYear.hasNext())
			{
				SubscriptionYear currentYear = iterYear.next();
				points.add(createPoint(randomColor, currentYear.getYear(), currentYear.getSubscriptions()));
			}
		}
		
		
		
	}
	
	/**
	 * Search the entire Countries list for the maximum value of Subscriptions
	 * Sets the maximum value in Instance variable maxSubscription
	 * @param countries
	 */
	
	private void getMaxSubs(LinkedList <Country> countries)
	{
		// This is brute force, but we'll do it anyway to be thorough
		
		Iterator<Country> iterCountry = countries.iterator();
		
		while(iterCountry.hasNext())
		{
			Country currentCountry = iterCountry.next();
			
			LinkedList<SubscriptionYear> years = currentCountry.getSubscriptionList();
		
			
			Iterator<SubscriptionYear> iterYear = years.iterator();
			
			
			while(iterYear.hasNext())
			{
				// Scan through all the subscriptions and find maximum Subscriptions as 
				// well as our minimum and maxiumum years
				
				SubscriptionYear currentYear = iterYear.next();
				
				if (currentYear.getYear() < minYear)
					this.minYear = currentYear.getYear();
				
				if (currentYear.getYear() > maxYear)
					this.maxYear = currentYear.getYear();
				
				if (currentYear.getSubscriptions() > maxSubs)
					this.maxSubs = currentYear.getSubscriptions();
				
			}
			 
		}
		
		
	}
	
	/**
	 * Create a single point
	 * @param randomColor
	 * @param year
	 * @param subscription
	 * @return ColoredPoint object
	 */
	private ColoredPoint createPoint(Color randomColor, int year, double subscription)
	{
	
		
		ColoredPoint thisPoint = new ColoredPoint(randomColor, 
				(int)map(year,(int)minYear, (int)maxYear, MARGIN, width-(RIGHT_MARGIN)),
				(int)map(subscription, 0, (int)(maxSubs+.5), height-MARGIN, MARGIN),
				(double)year, subscription);
		
		return thisPoint;
		
	}
	
	/**
	 * paintComponent method to paint the panel
	 */
	public void paintComponent(Graphics g)
	{
		
		final int TEXT_OFFSET_X = 10;
		final int TEXT_OFFSET_Y = 7;
		final int VERT_LINE_TOP_X = 20;
		final int VERT_LINE_TOP_Y = 10;
		final int  VERT_LINE_BOTTOM_X = 20;
		final int VERT_LINE_BOTTOM_Y = this.width - 40;
		final int HOR_LINE_LEFT_X = 20;
		final int HOR_LINE_LEFT_Y =  VERT_LINE_BOTTOM_Y;
		final int HOR_LINE_RIGHT_X =  this.width;
		final int HOR_LINE_RIGHT_Y = VERT_LINE_BOTTOM_Y;
		
		
		
		// cast to a Graphics2D object so we can do fancier graphics
		g2d = (Graphics2D) g;
		
		super.paintComponent(g2d);
		
		
		
		// iterate through all the points and plot them on the screen
		Iterator<ColoredPoint> pointIter = points.iterator();
		
		while(pointIter.hasNext())
		{
			
			ColoredPoint current = pointIter.next();
			
			g2d.setColor(current.getColor());
			g2d.fillOval((int)current.getX(), (int)current.getY(), POINT_SIZE, POINT_SIZE);
		
			
			
			// ASSIGNMENT 8
			if (near(mouseX, mouseY, current))
			{
				g2d.setColor(Color.BLACK);
				g2d.drawString(current.getLabel(),(int)current.getX()+TEXT_OFFSET_X,(int)current.getY()+TEXT_OFFSET_Y);
			}
			
			
			
		}
		
		g2d.setFont(font);
        
		
			
		
	
		
		// draw the axis
		
			g2d.setColor(Color.DARK_GRAY);
			
			g2d.draw( new Line2D.Double(VERT_LINE_TOP_X, VERT_LINE_TOP_Y, VERT_LINE_BOTTOM_X, VERT_LINE_BOTTOM_Y));
			g2d.draw(new Line2D.Double(HOR_LINE_LEFT_X, HOR_LINE_LEFT_Y, HOR_LINE_RIGHT_X, HOR_LINE_RIGHT_Y));
			g2d.setFont(new Font("Serif", Font.PLAIN, 18));
			g2d.drawString("Hover over any point to see data values", 40,20);
			g2d.setFont(font);
			
			// put reference labels for every decade, map the location for the decade
			for (int x = (((int)minYear % 10) * 10); x <= maxYear; x= x+10)
			{	
				g2d.drawString(Integer.toString(x),(int)map(x,(int)minYear, (int)maxYear, MARGIN, width-RIGHT_MARGIN), 570);	
			}
					
				
		// Draw text rotated on the Y axis
			g2d.setColor(Color.BLACK);
			g2d.setFont(font);
			
			AffineTransform orig = g2d.getTransform();
			AffineTransform at = new AffineTransform();
		    at.setToRotation(- Math.PI / 2.0);
			   
			g2d.setTransform(at);
			g2d.drawString("NUMBER OF SUBSCRIPTIONS (PER 100 PEOPLE)", -400,20); 
			g2d.setTransform(orig);
			
			
		
	}

	/**
	 * FOR ASSSIGNMENT 8 - Helper function to determine if a point is near the cursor location
	 * @param mouseX
	 * @param mouseY
	 * @param p
	 * @return
	 */
	private boolean near(int mouseX, int mouseY, ColoredPoint p)
	{
		final int DISTANCE = 6;
		
		
		int pointX = (int) p.getX();
		int pointY = (int) p.getY();
		
		if ((Math.abs(pointX - mouseX) < DISTANCE) && (Math.abs(pointY - mouseY) < DISTANCE))
			return true;
		else
			return false;
				
	}

	// ASSIGNMENT 8 - REQUIRED METHODS FOR MOUSE EVENTS
	
	public void mouseClicked(MouseEvent e) 
    { 
		mouseChange = true;
		
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		
		this.repaint();
		
    }

    public void mouseMoved(MouseEvent e) 
    {	
    	
    	mouseChange = true;
    	
    	this.mouseX = e.getX();
		this.mouseY = e.getY();
		
		this.repaint();
    }

    public void mouseExited(MouseEvent e) 
    { 
        
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }


}
		
	
	


