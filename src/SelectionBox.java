import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * 
 * @author Olena Rogalskaya 
 *
 */
class SelectionBox extends JPanel implements ActionListener{

    private CheckboxObject check;
    private LinkedList<CheckboxObject> options = new LinkedList<CheckboxObject>();
    private LinkedList<Country>countryToPlot = new LinkedList<Country>();
    Country[] countriesFromList;
    JFrame frameOpt;
    private boolean updatedList;
    private static final long serialVersionUID = 1L;
    Integer finished;
  
    /**
     * getter for countryToPlot
     * @return
     */
    public LinkedList<Country> getCountryToPlot() {
        return countryToPlot;
    }

    /**
     * Setter 
     * @param countryToPlot
     */
    public void setCountryToPlot(LinkedList<Country> countryToPlot) {
        this.countryToPlot = countryToPlot;
    }

    /**
     * getter for check
     * @return check
     */
    public CheckboxObject getCheck() {
        return check;
    }

    /**
     * setter for check
     * @param check
     */
    public void setCheck(CheckboxObject check) {
        this.check = check;
    }

    /**
     * get Options
     * @return
     */
    public LinkedList<CheckboxObject> getOptions() {
        return options;
    }

    /**
     * set Options
     * @param options
     */
    public void setOptions(LinkedList<CheckboxObject> options) {
        this.options = options;
    }


    
    /**
     *  creates and fills options - LinkedList of CheckboxObject
     * @param countriesFromList
     */
    SelectionBox(Country[] countriesFromList) {
        this.countriesFromList = countriesFromList;
    }
    
    /**
     * creates LinkedList of options (objects of type CheckboxObject 
     */
     public void fillsOption(){
         for (Country currentCountry : countriesFromList) {
            JCheckBox assignToBox = new JCheckBox(currentCountry.getName());
            this.check = new CheckboxObject(currentCountry);
            this.check.setCountryInBox(currentCountry);
            this.check.setCheckInBox(assignToBox);
            this.options.add(check);
        }
    }
     
     /**
      * creates checkbox with all countries from File frame as an options. 
      *Adds "submit" button and ActionListener
      * @param finished
      */
    public void createCheckBoxFrame(Integer finished) {
        this.finished = finished;
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.fillsOption();
        this.frameOpt = new JFrame("Country Selection");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        Border border = BorderFactory.createTitledBorder("Choose countries to plot:");
        panel.setBorder(border);
       
        Iterator<CheckboxObject> iterOptions = this.options.iterator();
        while (iterOptions.hasNext()) {
            CheckboxObject checkBoxCell = iterOptions.next();
            panel.add(checkBoxCell.getCheckInBox());   
        }
        JButton button = new JButton("Submit");
        button.addActionListener(this);
            
        Container contentPane = frameOpt.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
       contentPane.add(button, BorderLayout.SOUTH);
       this.frameOpt.add(panel);
      JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       this.frameOpt.add(scrollPane);
       this.frameOpt.setSize(600, 600);
       this.frameOpt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.frameOpt.setVisible(true);  
    }
    
/**
 * //override ActionEvent results
 */

  @Override
  public void actionPerformed(ActionEvent ev) {
      LinkedList<Country> checkedCountry = new LinkedList<Country>();
      Iterator<CheckboxObject> iterCheck = this.options.iterator();
      while (iterCheck.hasNext()) {
          CheckboxObject checkStatus = iterCheck.next();
          if (checkStatus.getCheckInBox().isSelected()) {
              checkedCountry.add(checkStatus.getCountryInBox());
          }
      }   
      setCountryToPlot(checkedCountry);
      this.finished=(new Integer (1));
      this.setUpdatedList(true);
  }

  /**
   * getter for the finished flag
   * @return
   */
  public Integer getFinished(){
      return this.finished;
  }
/**
 * Is the list updated? 
 * @return
 */
public boolean isUpdatedList() {
    return updatedList;
}

/**
 * 
 * @param updatedList
 */
public void setUpdatedList(boolean updatedList) {
    this.updatedList = updatedList;
}

    }