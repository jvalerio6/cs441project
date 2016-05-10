package EisenhowerBox.ui;

import EisenhowerBox.*;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.*;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class Search extends JFrame implements DocumentListener {

    private JTextField filterText;
    private JLabel searchLabel, sr;
    public Vector<Task> tasksFromDB;
    private JComboBox tasks;
    private DbManager dbm = new DbManager();
    public Vector<Task> tVector = createVectorTasks();

    ImageIcon SEARCH_ICO = new ImageIcon(this.getClass().getResource("/img/search-24.png"));
    ImageIcon TASK_ICO = new ImageIcon(this.getClass().getResource("/img/notepad.png"));

    public Search () {

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

	public Vector<Task> createVectorTasks( ) {
		Vector<Task> tlist = new Vector<Task>();
		List<Task> allTasks = dbm.getTaskList(1);

		for (Task t : allTasks) {
			tlist.add(t);
		}

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
