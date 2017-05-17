/**
 *  Tests the GraphView class by creating an object of type GraphView and adding components to it.
 *  Creates one container of type JFrame and adds an object of type GraphView.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class TestGraphView 
{
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 600;
	
	// 99 is the value that tells us "not finished" from the submit button
	Integer finished = new Integer(99); 

    /**
     * Creates a generic linked list. Then based on the user's input,
     * adds a random number of countries to the list.
     * @param allCountries      An array of Country objects
     */
	private LinkedList<Country> buildCountryList(Country [] allCountries){
	    SelectionBox checkboxready = new SelectionBox(allCountries);
        checkboxready.createCheckBoxFrame(finished);
      
        // Wait for the checkbox frame to press submit (signalled by the getFinished() result
        // We know that doing nothing in the while loop isn't good technique, but
        // wait() and sleep() were being interrupted and would not work here
        
        while(checkboxready.getFinished().equals(new Integer(99)))
        {
        }
        
      
        LinkedList<Country> selectedCountries = new LinkedList<Country>();
        
        if(checkboxready.isUpdatedList()){

            selectedCountries= checkboxready.getCountryToPlot();
        }

	
			return selectedCountries;
	}
	
	/**
	 * Initializes the frames for Graphview
	 * @param selectedCountries
	 */
		        
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final int LEGEND_WIDTH = 200;
		final int PLOTS_WIDTH = 600;
		final int MARGIN = 20;
		
		FlowLayout layout = new FlowLayout();
		frame.setLayout (layout);

		
		GraphHoverOver myPlots = new GraphHoverOver(PLOTS_WIDTH, FRAME_HEIGHT-MARGIN, selectedCountries);	
		myPlots.setPreferredSize(new Dimension(PLOTS_WIDTH, FRAME_HEIGHT-MARGIN));
		
		
		//  add the GraphView object to your layout
		frame.add(myPlots);
		
		// Create the legend
		
		LegendPanel myLegend = new LegendPanel(LEGEND_WIDTH-MARGIN,FRAME_HEIGHT - MARGIN-MARGIN, selectedCountries);
		myLegend.setPreferredSize(new Dimension(LEGEND_WIDTH-MARGIN, FRAME_HEIGHT - MARGIN-MARGIN));

		
		frame.add(myLegend);
		


		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(true);
		frame.setVisible(true);		
	}
	
    /**
     * Uses a CSV to parse a CSV file.
     * Adds the data for each country to an array of Country objects.
     * Adds a random selection of countries to a generic LinkedList object.
     * Draws the list of countries to a JFrame.
     */
	public static void main(String[] args) 
	{		
		// Create and set objects of type Country 
		//
		final String FILENAME = "resources/cellular.csv";	// Directory path for Mac OS X
		//final String FILENAME = "resources\\cellular.csv";	// Directory path for Windows OS (i.e. Operating System)
		final int NUM_COUNTRIES_TO_TEST = 3;			// Note: Include test cases in addition to 3


		// Parse the CSV data file
		//		
	
		CSVReader parser = new CSVReader(FILENAME);
		
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Create and set objects of type Country 
		//
		Country [] countries;
		countries = new Country[NUM_COUNTRIES_TO_TEST];

		Country current;
		countries = new Country[countryNames.length];

		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			current = new Country(countryNames[countryIndex]);	

			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}
		
		       

		TestGraphView application = new TestGraphView();

		// Note: Initially, to test your output you may hard code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);

		//application.initializeGUI(countries);
		
		LinkedList<Country> listOfCountries = application.buildCountryList(countries);
		//System.out.println("list of "+ listOfCountries.size()+ " Country to draw (t line 157)");
		application.initializeGui(listOfCountries);
        
        // flush the error stream
        System.err.flush();
        
        System.out.println("\nDone with TestGraphView.");
	}
}