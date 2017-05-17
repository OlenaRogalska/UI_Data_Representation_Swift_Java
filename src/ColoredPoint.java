

import java.awt.Color;
import java.awt.Point;

public class ColoredPoint extends Point
{
	Color color;
	double originalX;
	double originalY;
	
	ColoredPoint(Color color, int mappedX, int mappedY, double originalX, double originalY)
	{
		super(mappedX, mappedY);
		this.color = color;
		this.originalX = originalX;
		this.originalY = originalY;
		
	}
		
	Color getColor()
	{
		return color;
	}
	
	String getLabel()
	{
		String output = "";
		
		
		output = "(" + (int) originalX + "," + String.format("%.2f", originalY)  + ")";
		
		return output;
		
	}
}
