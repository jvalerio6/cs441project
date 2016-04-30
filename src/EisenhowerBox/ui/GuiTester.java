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
	private JPanel pane = null;
	
	private Gui gui = null;
	
	public GuiTester(){
		
		super("Gui Tester");
		 gui = Gui.getInstance();
		//setSize(500,500);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());
		//runTester();
	}
		
	
	public void runTester(){
		pane = new ViewTaskArea();
		this.add(pane, BorderLayout.CENTER);
		this.setVisible(true);
	}
		
	/**
	Task t1 = new Task("get the pod 1", new Date(), new Date());
	Task t2 = new Task("get the next pod 2 ", new Date(), new Date());
	Task t3 = new Task("get the next pod 3", new Date(), new Date());
	Task t4 = new Task("get the next pod 4", new Date(), new Date());
	Task t5 = new Task("get the next pod 5", new Date(), new Date());
	Task t6 = new Task("get the next pod 6", new Date(), new Date());
	Task t7 = new Task("get the next pod 7", new Date(), new Date());
	Task t8 = new Task("get the next pod 8", new Date(), new Date());
	Task t9 = new Task("get the next pod 9", new Date(), new Date());
	
	TeamMember user = new TeamMember("TestUser","password", 100 );
*/

}
