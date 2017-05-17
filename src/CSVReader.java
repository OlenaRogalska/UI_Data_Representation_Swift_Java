

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CSVReader object opens a CSV file of per-country Cellular penetration data, reads it, and parses it
 * @author Benjamin Linder
 */

public class CSVReader
{
	private File file1;
	private Scanner scanner1;
	private int numberOfCountries = 0, numberOfYears = 0;
	private double [][] dataTable;
	private String[] countryTable;
	private String yearsAsStrings[];
	private int[] years;


	/**
	 * Constructs a CSVReader object with the specified file.
	 * @param filename			The name of the file to open.
	 */	

	public CSVReader(String filename)  
	{
		try 
		{

			file1 = new File(filename);
			scanner1 = new Scanner(file1);
			parseWholeFile();

		}
		catch (FileNotFoundException e)
		{
			System.out.println("exception - file not found in CSVReader");
			
		}
	}

	/**
	 * Accessor method returns an array of strings containing the country names.
	 * @return  string[];
	 */
	public String[] getCountryNames() 
	{	
		return countryTable;
	}

	/**
	 * Helper method parses the file that's been opened
	 * @return 
	 */
	private void parseWholeFile() 
	{
		int countryCounter=0;

		// This helper method will already parse the entire file
		// It is done this way so that we have the values pre-calculated since we are unsure
		// in what order the other methods will get called.

		//  assumes scanner1 is at the beginning of the file

		String currentLine = scanner1.nextLine();

		//skip past the first lines
		// Return null if we have the wrong initial line in the file

		if (!currentLine.equals("World Development Indicators"))
		{
			return ;
		}

		// set the delimiters to be comma and newline
		scanner1.useDelimiter(",|\r");

		// get the next line which has number of countries
		String currentItem = scanner1.next();

		numberOfCountries = scanner1.nextInt();


		
		// Get past the end of the current line
		
		currentItem = scanner1.nextLine();
	
		// Get the line of years
		currentItem = scanner1.nextLine();
		// and now parse it into the array years

		numberOfYears = 0;

		// fill the years array
		// Item 0 will be "Year Name" heading so we'll ignore it
		// Get all the year labels as a String array

		yearsAsStrings = currentItem.split(",");


		// subtract 1 because we are ignoring the first item in the list
		numberOfYears = yearsAsStrings.length - 1;

		// Allocate the years int array
		years = new int[numberOfYears];

		// Copy all the year values from the String array to the Int array, skipping the [0] entry as it's the heading

		for (int x=1; x < numberOfYears+1; x++)
		{ 
			years[x-1] = Integer.parseInt(yearsAsStrings[x]);
		}

		dataTable = new double[numberOfCountries][numberOfYears];
		countryTable = new String[numberOfCountries];


		for (countryCounter = 0; countryCounter < numberOfCountries; countryCounter++ )
		{
			currentItem = scanner1.nextLine();
			yearsAsStrings = currentItem.split(",");

			countryTable[countryCounter] = yearsAsStrings[0];


		// Populate DataTable Row with year values
			for (int x=0; x < numberOfYears; x++)
			{ 
				dataTable[countryCounter][x] = Double.parseDouble(yearsAsStrings[x+1]);
			}

		}


	}

	/**
	 * Accessor returns the number of year labels for the file
	 * @return the number of year labels
	 */
	public int[] getYearLabels() {

		return this.years;
	}

	/**
	 * Accessor returns the table of values parsed from the file
	 * @return the two dimensional array containing the parsed yearly data
	 */
	
	public double[][] getParsedTable() {

		return dataTable;
	}

	/**
	 * Accessor returns the number of years in the file
	 * @return the number of years in the file
	 */
			
	public int getNumberOfYears() {

		return this.numberOfYears;
	}

}
