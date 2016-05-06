/**
 * 
 */
package EisenhowerBox.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import EisenhowerBox.Task;
import EisenhowerBox.TeamMember;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileWriter;
import java.util.Date;

/**
 * @author erikkalan
 *
 */
public class GuiTester extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	/**
	 * 
	 */
	
	private static Gui gui = null;
	
	public static void test(){
		
		 gui = Gui.getInstance();
		 gui.setVisible(true);
		
	}

}
