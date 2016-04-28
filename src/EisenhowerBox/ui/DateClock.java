/**
 * 
 */
package EisenhowerBox.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author erikkalan
 *
 */
public class DateClock extends JLabel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 40L;
	String dateString;
	public final SimpleDateFormat sdf;
	 
	public DateClock(String dateString) {
	    this.dateString = dateString;
	    setForeground(Color.green);
	    
	    if (dateString == "date"){
	    	sdf = new SimpleDateFormat("  MMMM dd yyyy");
	    }
	    else if (dateString == "time"){
	    	sdf = new SimpleDateFormat("hh:mm:ss a");
	    }
	    else if (dateString == "day"){
	    	 sdf = new SimpleDateFormat("EEEE  ");
	    }
	    else {
	    	sdf = new SimpleDateFormat();
	    }
	    
	 
	    Timer t = new Timer(1000, this);
	    t.start();    
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	Date d = new Date();
    setText(sdf.format(d));	
	}

}
