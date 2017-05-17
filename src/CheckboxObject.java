import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class CheckboxObject {
    
    private Country countryInBox;
    private JCheckBox checkInBox;
    
    CheckboxObject(Country country){
        this.countryInBox = country;
    }
    
    public Country getCountryInBox() {
        return countryInBox;    
    }
    public void setCountryInBox(Country countryInBox) {
        this.countryInBox = countryInBox;
    }
    public JCheckBox getCheckInBox() {
        return checkInBox;
    }
    public void setCheckInBox(JCheckBox checkInBox) {
        this.checkInBox = checkInBox;
    }
    
    


}