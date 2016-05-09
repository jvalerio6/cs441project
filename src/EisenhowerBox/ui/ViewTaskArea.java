/**
 *
 */
package EisenhowerBox.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import EisenhowerBox.*;

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
	DbManager dbm = new DbManager();
	List<Task> allTasks = dbm.getTaskList(1);

	User user = null;

	 /**
		 * Default text inside all Panes
		 */
	private static final String defaultText = "No Tasks Loaded for User \n" +
			  " 1.\n2.n3.\n4.\n5.\n6.\n7.\n8.\n9.\n";

	protected final Dimension panel_dimension = new Dimension(1000,480);
	//protected final JPanel viewPanel = new JPanel();
	//private Project project = new Project();



	public ViewTaskArea() {
		super();
		//initComponents();
		init();


		System.out.println("allTasks.toString()");

		for (Task t : allTasks) {
			System.out.println(t.toString());
		}
    }


	/** help
	 * Initializes components necisary for the Panel
	 * Needs to add generalized support and then full support for
	 * the individual sub classes
	 * May need to have some variables be changed to class variables
	 */
	private void initComponents() {

		this.setBackground(Color.black);
		this.setPreferredSize(panel_dimension);
		this.setLayout(new GridLayout(2,0));

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

		for (int i=0; i < NUMBER_OF_TASK_PRIORITY_BOXES; i++){
			scrollPanes[i] = new JScrollPane();
			scrollPanes[i].setName("JScrollPane" + i);
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
		final String scrollPaneName = scrollPane.getName();
		final String finalDisplayName = getPaneDisplayName(scrollPaneName);

		System.out.println("scrollpane name: " + scrollPaneName);

		final JTextPane textPane = new JTextPane();
		final JPanel wrapPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 2;
		c.ipadx = 90;
		//c.gridwidth = 30;
		c.gridx = 0;
		c.gridy = 0;

		StyleContext sc = new StyleContext();
		final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		textPane.setStyledDocument(doc);
		textPane.setBackground(Color.getHSBColor((float)0.55, (float)0.04, (float)0.96));

    	List<Task> taskListByName = getTaskListByDisplayName(finalDisplayName);

    	final PartsListModel pcm = new PartsListModel(taskListByName);
	    ListCellRenderer lcr = new LabelRenderer();

	    JList jl = new JList(pcm);
	    jl.setCellRenderer(lcr);

	    ListSelectionModel lsm = jl.getSelectionModel();
	    lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    jl.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        if (!e.getValueIsAdjusting()) {
	          Task element = (Task)pcm.getElementAt(e.getFirstIndex());
	          ViewTaskDetails vtd = new ViewTaskDetails(element);
	          vtd.setVisible(true);
	          System.out.println(element.getTskName());
	        }
	      }
	    });

	    JScrollPane jsp = new JScrollPane(jl);
	    JComboBox jc = new JComboBox(pcm);
	    jc.setRenderer(lcr);

	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    textPane.add(jc);


		final Style taskStyle = sc.addStyle("TaskStyle", null);
	    StyleConstants.setBold(taskStyle, true);
	    StyleConstants.setLeftIndent(taskStyle, 4);
	    StyleConstants.setRightIndent(taskStyle, 4);
	    StyleConstants.setFirstLineIndent(taskStyle, 8);
	    StyleConstants.setFontFamily(taskStyle, Font.SANS_SERIF);
	    StyleConstants.setFontSize(taskStyle, 14);
	    StyleConstants.setForeground(taskStyle, Color.darkGray);
	    StyleConstants.setBackground(taskStyle, Color.getHSBColor((float)0.55, (float)0.04, (float)0.96));

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

	            	// style for header

	            	doc.insertString(1, finalDisplayName + " (" + getNumTaskByDisplayName(finalDisplayName) + ")" + "\n", taskStyle);

	            	//System.out.println(tasks);
	            	//doc.insertString(doc.getLength(), getTaskListByDisplayName(finalDisplayName), DateStyle);

	            } catch (BadLocationException e) {
	            }
	          }
	        });
	      } catch (Exception e) {
	        System.out.println("Exception when constructing document: " + e);
	        System.exit(1);
	      }

	    wrapPanel.setBackground(Color.getHSBColor((float)0.55, (float)0.04, (float)0.96));
	    wrapPanel.add(textPane, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 90;
		c.ipady = 45;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 5;

	    wrapPanel.add(jsp, c);

	    scrollPane.setPreferredSize(new Dimension(
        		panel_dimension.width -10,
        		panel_dimension.height -10));
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setViewportView(wrapPanel);

	}

	/*
	    JScrollPane0 = PRIMORDIAL
	    JScrollPane1 = SUPER IMPORTANT
	    JScrollPane2 = IMPORTANT
	    JScrollPane3 = ORDINARY
	    JScrollPane4 = UNIMPORTANT
	    JScrollPane5 = NOT INDICATED
	*/
	private String getPaneDisplayName (String jPaneName) {
		String displayName = "";

		switch (jPaneName) {
		case "JScrollPane0" :
			displayName = "PRIMORDIAL";
			break;
		case "JScrollPane1" :
			displayName = "SUPERIMPORTANT";
			break;
		case "JScrollPane2" :
			displayName = "IMPORTANT";
			break;
		case "JScrollPane3" :
			displayName = "ORDINARY";
			break;
		case "JScrollPane4" :
			displayName = "UNIMPORTANT";
			break;
		case "JScrollPane5" :
			displayName = "NOTINDICATED";
			break;
		}

		return displayName;
	}

	private final int getNumTaskByDisplayName (String displayName) {
		int count = 0;

		for (Task t : allTasks) {
			if (t.getTskImportance().equals(displayName)) {
				count++;
			}
		}

		return count;
	}

	private final List<Task> getTaskListByDisplayName (String displayName) {
		//StringBuilder taskList = new StringBuilder();
		List<Task> resultingList = new ArrayList<Task>();

		for (Task t : allTasks) {
			if (t.getTskImportance().equals(displayName)) {
				resultingList.add(t);
			}
		}

		return resultingList;
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
		//user =  new TeamMember("mario", "Super", "MarBros");
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

