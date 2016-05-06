package EisenhowerBox.ui;

import EisenhowerBox.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.*;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Search extends JFrame implements DocumentListener {

    private JTextField filterText;
    private JLabel searchLabel, sr;
    //public List<Task> tList = createTestTasks();
    public Vector<Task> tVector = createVectorTasks();
    public Vector<Task> tasksFromDB;
    //private String[] taskNames = getTaskNames();
    private JComboBox tasks;
    private DbManager dbm = new DbManager();

    ImageIcon SEARCH_ICO = new ImageIcon(this.getClass().getResource("/img/search-24.png"));
    ImageIcon TASK_ICO = new ImageIcon(this.getClass().getResource("/img/notepad.png"));

    public Search () {

    	//List<Task> test = dbm.getTaskArray(1);

    	//System.out.println(test.toString());

		try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		finally {
			setSize(500, 200);
			setTitle("Search");
			setLayout(new MigLayout("wrap 2", "[right][fill]"));
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			searchLabel = new JLabel("Search:");
			searchLabel.setIcon(SEARCH_ICO);

			filterText = new JTextField(200);
	        filterText.getDocument().addDocumentListener(this);

			sr = new JLabel();
			sr.setIcon(TASK_ICO);
			sr.setVisible(false);

	        tasks = new JComboBox(tVector.toArray());

	        tasks.addActionListener(
	                new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        JComboBox combo = (JComboBox)e.getSource();

	                        if (combo.isFocusOwner()) {
	                        	String tName = (String)combo.getSelectedItem();
	                        	Task t = getTaskByName(tVector, tName);

		                        System.out.println("Selected Item: " + t.getTskName());
		                        System.out.println("Opening GUI - ViewTaskDetails");

		                        ViewTaskDetails vtd = new ViewTaskDetails(t);
		                        vtd.setVisible(true);

	                        }
	                    }
	                }
	        );

	        tasks.setVisible(false);

			add(searchLabel);
			add(filterText);

			add(sr);
			add(tasks);

			filterTasks(tVector, "");
		}
    }

    private void newFilter() {

    	if (filterText.getText().length() > 0) {
    		SwingUtilities.invokeLater(new Runnable(){
        	    public void run()
        	    {
        	    	tasks.setVisible(true);
        	    	sr.setVisible(true);
        	    	tasks.showPopup();
        	    	filterTasks(tVector, filterText.getText());
        	    }
        	});
    	} else {
    		tasks.setVisible(false);
    		sr.setVisible(false);
    		tasks.hidePopup();
    	}

    }

    public static void main(String[] args) {
    	Search search = new Search();
    	search.setVisible(true);
    }

	public void changedUpdate(DocumentEvent e) {
		newFilter();
	}

	public void insertUpdate(DocumentEvent e) {
		newFilter();
	}

	public void removeUpdate(DocumentEvent e) {
		newFilter();
	}

//	public List<Task> createTestTasks( ) {
//		List<Task> tlist = new ArrayList<Task>();
//
//		tlist.add(new Task("Finish CS 433 Assignment"));
//		tlist.add(new Task("Report work study hours"));
//		tlist.add(new Task("Implement Java classes"));
//		tlist.add(new Task("Finish networking project"));
//		tlist.add(new Task("Visit the moon"));
//		tlist.add(new Task("Contact Google headquarters"));
//
//		return tlist;
//	}

	public Vector<Task> createVectorTasks( ) {
		Vector<Task> tlist = new Vector<Task>();

		tlist.add(new Task(1, "Finish CS 433 Assignment", "Testing Description", new Date(2015, 1, 1), new Date(2016, 3, 6)));
		tlist.add(new Task(2, "Report work study hours", "Testing Description 2", new Date(2015, 1, 1), new Date(2016, 3, 6)));
		tlist.add(new Task(3, "Implement Java classes", "Testing Description 3", new Date(2015, 1, 1), new Date(2016, 3, 6)));
		tlist.add(new Task(4, "Finish networking project", "Testing Description 4", new Date(2015, 1, 1), new Date(2016, 3, 6)));
		tlist.add(new Task(5, "Visit the moon", "Testing Description 5", new Date(2015, 1, 1), new Date(2016, 3, 6)));
		tlist.add(new Task(6, "Contact Google headquarters", "Testing Description 6", new Date(2015, 1, 1), new Date(2016, 3, 6)));

		return tlist;
	}

	void filterTasks(Vector<Task> tlist, String filter) {
		int numResults = 0;

		tasks.removeAllItems();

		for (int i = 0; i < tlist.size(); i++) {
			tasks.addItem(tlist.get(i).getTskName());
		}

		System.out.println("Filter: " + filter);

		for (Task t : tlist) {
			if (t.getTskName().toLowerCase().contains(filter.toLowerCase())) {
				numResults++;

			} else {
				tasks.removeItem(t.getTskName());
			}
		}

		System.out.println(numResults + " results found.");
	}

//	public String[] getTaskNames() {
//		String[] tasks = new String[tList.size()];
//
//		for (int i = 0; i < tList.size(); i++) {
//			tasks[i] = tList.get(i).getTskName();
//		}
//
//		return tasks;
//	}

	public Task getTaskByName (Vector<Task> tList, String targetName) {
		Task theTask = new Task();

		for (Task t : tList) {
			if (t.getTskName().equals(targetName)) {
				theTask = t;
			}
		}

		return theTask;
	}
}
