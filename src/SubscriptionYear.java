
public class SubscriptionYear {
/**
 * SubscriptionYear object container a year and it's number of subscriptions
 * Author: Benjamin Linder
 */
	private int year;
	private double subscriptions;

	/**
	 * Constructor constructs a SubscriptionYear object with required parameters
	 * @param year
	 * @param subscriptions
	 */
	
	public SubscriptionYear(int year, double subscriptions)
	{
		setYear(year);
		setSubscriptions(subscriptions);
	}

	/**
	 * Accessor provides the year 
	 * @return Year for object
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 * Mutator sets the year 
	 * @param year
	 */
	public void setYear(int year)
	{
		this.year = year;
	}
	
	/**
	 * Mutator for setting the subscriptions
	 * @param subscriptions
	 */
	public void setSubscriptions(double subscriptions)
	{
		this.subscriptions = subscriptions;
	}
	/**
	 * Accessor for getting subscriptions
	 * @return subscriptions
	 */
	public double getSubscriptions()
	{
		return subscriptions;
	}
	
	/**
	 * Provides string representation
	 */
	public String toString()
	{
		return (String.valueOf(subscriptions));
	}
	

}
