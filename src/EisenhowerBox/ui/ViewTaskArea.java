/**
 * 
 */
package EisenhowerBox.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import EisenhowerBox.Project.Utile;
import EisenhowerBox.Project;
import EisenhowerBox.Task;
import EisenhowerBox.TeamMember;
import EisenhowerBox.User;

/**
 * @author erikkalan
 *	Manages the Panel that shows the tasks
 *needs to add sub classes for each vieww option so we can add support for all buttons and implemt actions
 */
public class ViewTaskArea extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 30L;
	protected final static int NUMBER_OF_TASK_PRIORITY_BOXES = 6;
	protected final JScrollPane[] scrollPanes = new JScrollPane[ NUMBER_OF_TASK_PRIORITY_BOXES];
	
	protected final JTextPane jtxpn1 = new JTextPane();
	protected final JTextPane jtxpn2 = new JTextPane();
	protected final JTextPane jtxpn3 = new JTextPane();
	protected final JTextPane jtxpn4 = new JTextPane();
	
	protected final JScrollPane jscpn1 = new JScrollPane();
	protected final JScrollPane jscpn2 = new JScrollPane();
	protected final JScrollPane jscpn3 = new JScrollPane();
	protected final JScrollPane jscpn4 = new JScrollPane();
	//DbManager db = new DbManager();
	User user = null; 
	
	 /**
		 * Default text inside all Panes
		 */
	private static final String defaultText = "No Tasks Loaded for User \n" +
			  " 1.\n2.n3.\n4.\n5.\n6.\n7.\n8.\n9.\n";
	
	protected final Dimension panel_dimension = new Dimension(400,400);
	//protected final JPanel viewPanel = new JPanel();
	//private Project project = new Project();
	
	
	
	public ViewTaskArea() {
		super();
		//initComponents();
		init();
		
		
    }
	
	/** help
	 * Initializes components necisary for the Panel
	 * Needs to add generalized support and then full support for
	 * the individual sub classes
	 * May need to have some variables be changed to class variables
	 */
	private void initComponents(){
		
		this.setBackground(Color.black);
		this.setPreferredSize(panel_dimension);
		this.setLayout(new GridLayout(2,0));
		//this.setLayout(new GridBagLayout());
		
		setDefaultTextArea(jtxpn1);
		
		

        
        JPanel wrapPanel = new JPanel(new BorderLayout());
        wrapPanel.add(jtxpn1);
        
        jscpn1.setPreferredSize(new Dimension(
        		panel_dimension.width -10,
        		panel_dimension.height -10));
        jscpn1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscpn1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jscpn1.add(wrapPanel);
        jscpn1.setViewportView(jtxpn1);
        this.add(jscpn1);
        
        
        
        this.setVisible(true);
        
        
	}
	
	/** 
	 * Initializes components necisary for the Panel
	 * Needs to add generalized support and then full support for
	 * the individual sub classes
	 * May need to have some variables be changed to class variables
	 */
	private void init(){
		
		this.setBackground(Color.black);
		this.setPreferredSize(panel_dimension);
		this.setLayout(new GridLayout(2,0));
		
		for (int i=0; i<NUMBER_OF_TASK_PRIORITY_BOXES; i++){
			scrollPanes[i] = new JScrollPane();
		}
		
		for (JScrollPane pane: scrollPanes){
			defaultTextArea(pane);
		}
		
		for (JScrollPane pane: scrollPanes){
			this.add(pane);
		}

        this.setVisible(true);      
	}
	
	
	
	protected void defaultTextArea(JScrollPane scrollPane){
		final JTextPane textPane = new JTextPane();
		final JPanel wrapPanel = new JPanel(new BorderLayout());
		
		
		StyleContext sc = new StyleContext();
		final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setStyledDocument(doc);
		
		final Style taskStyle = sc.addStyle("TaskStyle", null);
	    StyleConstants.setBold(taskStyle, true);
	    StyleConstants.setLeftIndent(taskStyle, 4);
	    StyleConstants.setRightIndent(taskStyle, 4);
	    StyleConstants.setFirstLineIndent(taskStyle, 8);
	    StyleConstants.setFontFamily(taskStyle, "serif");
	    StyleConstants.setFontSize(taskStyle, 18);
	    StyleConstants.setForeground(taskStyle, Color.darkGray);

	   
	    // Create and add Date style
	    final Style DateStyle = sc.addStyle("DateStyle", null);
	    StyleConstants.setForeground(DateStyle, Color.black);
	    StyleConstants.setFontFamily(taskStyle, "serif");
	    StyleConstants.setFontSize(taskStyle, 16);

		
	    try {
	        SwingUtilities.invokeAndWait(new Runnable() {
	          public void run() {
	            try {
	            		// Add the text to the document
	              doc.insertString(doc.getLength(), defaultText, taskStyle);
	              doc.insertString(doc.getLength(), defaultText, DateStyle);
	              

	              
	            } catch (BadLocationException e) {
	            }
	          }
	        });
	      } catch (Exception e) {
	        System.out.println("Exception when constructing document: " + e);
	        System.exit(1);
	      }
	    
	    wrapPanel.add(textPane);
	    
	    scrollPane.setPreferredSize(new Dimension(
        		panel_dimension.width -10,
        		panel_dimension.height -10));
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.add(wrapPanel);
	    scrollPane.setViewportView(textPane);
		
		
		
	}
	
	
	
	
	
	
	private void setDefaultTextArea( JTextPane textArea){
		textArea.setEditable(false);
		
		StyleContext sc = new StyleContext();
		final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textArea.setStyledDocument(doc);
		
		final Style taskStyle = sc.addStyle("TaskStyle", null);
	    StyleConstants.setBold(taskStyle, true);
	    StyleConstants.setLeftIndent(taskStyle, 4);
	    StyleConstants.setRightIndent(taskStyle, 4);
	    StyleConstants.setFirstLineIndent(taskStyle, 8);
	    StyleConstants.setFontFamily(taskStyle, "serif");
	    StyleConstants.setFontSize(taskStyle, 16);
	    StyleConstants.setForeground(taskStyle, Color.darkGray);
		
		final Style heading2Style = sc.addStyle("Heading2", null);
	    heading2Style.addAttribute(StyleConstants.Foreground, Color.red);
	    heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
	    heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
	    heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));
	    
	    try {
	        SwingUtilities.invokeAndWait(new Runnable() {
	          public void run() {
	            try {
	              // Add the text to the document
	              doc.insertString(0, sampleTask, heading2Style);
	              doc.insertString(doc.getLength(), " \n\n\n\n\n\nllll\n", null);
	              doc.insertString(doc.getLength(), sampleTask, taskStyle);
	              doc.insertString(doc.getLength(), " \n\n\n\n\n\nllll\n", null);

	              // Finally, apply the style to the heading
	              //doc.setParagraphAttributes(0, 1, heading2Style, false);
	            } catch (BadLocationException e) {
	            }
	          }
	        });
	      } catch (Exception e) {
	        System.out.println("Exception when constructing document: " + e);
	        System.exit(1);
	      }
	    
	}
	
	protected List<Task> priorityListCreator(List<Task> userList, 
			EisenhowerBox.Project.Utile.Importance importance, 
			EisenhowerBox.Project.Utile.Priority priority){
		
		List<Task> p1 = null;
		
		if (userList == null) return p1;
	
		for (Task task: userList){
			if (priority == task.prio && importance == task.importance){
				p1.add(task);
			}
		}
		return p1;
	}
	
	protected void writeTasksToPane(User user){
		//Listdb.getTaskList(user); // List <Tasks> from the database manager
		List<Task> tList;
		
		
	}
	
	public void updateTasksInPane(){
		user =  new TeamMember("mario", "Super", "MarBros");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * This is just for sizing issues with the Panes and only used for debugging issues
	 */
  private static final String sampleTask = "Task 1 start date End Date priority .........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." ;
  
 
		  
         





} 
 
