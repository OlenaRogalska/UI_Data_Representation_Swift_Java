import java.awt.Color;
import java.util.Iterator;
import java.util.Random;




/**
 * Object Country - holds an array of subriptionYear and the name
 * @author benjaminlinder
 *
 */

public class Country
{
	private String name;
	private LinkedList<SubscriptionYear> subscriptions;
	int minYear=9999;
	int maxYear=0;
	Color color;

	

	/**
	 * Constructor that takes only name
	 * @param name
	 */
	
	public Country(String name) {
		
		this.name = name;
		subscriptions = new LinkedList<SubscriptionYear>();
		this.color = setColor();
		
	}

	/**
	 * Mutator that adds a year with it's data
	 * @param year
	 * @param countryData
	 */
	public void addSubscriptionYear(int year, double countryData) 
	{
		SubscriptionYear yearToAdd = new SubscriptionYear(year, countryData);
		
		subscriptions.add(yearToAdd);	
		
		// Adjust maxYear and MinYear based on the year we are adding
		
		if (yearToAdd.getYear() < minYear) minYear = yearToAdd.getYear();
		if (yearToAdd.getYear() > maxYear) maxYear = yearToAdd.getYear();
		
	}
/**
 * Assign a random color to the Country
 * @return Color
 */
	private Color setColor()
	{
		
		// Generate a color. Use 200 to keep colors darker.
		
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(150);
		int green = randomGenerator.nextInt(150);
		int blue = randomGenerator.nextInt(150);
		Color randomColor = new Color(red, green, blue); 
		
		return randomColor;
		
	}
	
	/**
	 * Returns the sum of subscriptions for a range of years
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public double getNumSubscriptionsForPeriod(int startYear, int endYear)
	{
		int startIndex, endIndex;
		double sum=0;
		
		// test to see if startYear, endYear are within boundaries of stored years and are correct lower/upper limitsx
		
		if ((startYear > endYear) | (startYear < minYear) | (endYear > maxYear))
		return -1;
		
		// USE AN ITERATOR HERE, PER ASSIGNMENT 6
		// Since we can't guarantee the order of the nodes in the list
		// look at all the nodes and see if a year is within our range, add it to the sum 
		// then return the sum
			
		for  (Iterator<SubscriptionYear> iterator = subscriptions.iterator(); iterator.hasNext(); )
		{
			SubscriptionYear currentSubscriptionYear = iterator.next();
			
			int currentYear = currentSubscriptionYear.getYear();
			
			
			if (currentYear >= startYear && currentYear <=endYear)
				sum += currentSubscriptionYear.getSubscriptions();
	
		}
		
		return sum;
	}
	
	/**
	 * Returns a String representation of the country Name followed by the data for the country tab separated
	 */
	public String toString()
	{
		String output="";
		
		Iterator<SubscriptionYear> iterator = subscriptions.iterator();
		
		output = output + this.name + "\t";
		
		while (iterator.hasNext())
		{	
			output = output + iterator.next().toString() + "\t";
		}
		
		output = output + "\r";
		return output;
		
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * Tests for equality of two Country objects
	 * @param otherCountry
	 * @return true if country names are equal, false if not
	 */
	public boolean equals(Object otherCountry) 
	
	// assume otherCountry is a country, if so is the name of the this.country = otherCountry.country 
	
	{
		if (otherCountry instanceof Country)
		{	
			// create a temporary reference to cast otherCountry to a Country
			Country temp = (Country) otherCountry;
			if (this.name.equals(temp.getName())) return true;
		}
		
		return false;
	}
	
	public LinkedList<SubscriptionYear> getSubscriptionList()
	{
		return subscriptions;
		
	}
	
	
}
