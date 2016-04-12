/**
 * 
 */
package EisenhowerBox.ui;

import javax.swing.SwingUtilities;
import java.io.FileWriter;

/**
 * @author erikkalan
 *
 */
public class GuiTester {
	
	private Gui gui;
	
	public GuiTester(){
		gui = null;
	}
	
	public void runTester(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = Gui.getInstance();
			}
		});
	}

}
