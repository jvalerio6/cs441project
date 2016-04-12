package EisenhowerBox.ui;

import java.awt.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.GroupLayout.*;

public class Search extends JLabel implements DocumentListener {
	final static int SEARCH_FIELD_WIDTH = 20; // Number of collumns in the search field
	private JTextField jtxtfQuery;    
    private JLabel jlblSearch;
    
	/**
	 * Default Constructor to initialize the new search component and 
	 */
    public Search() {
        initComponents();

    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     */
 
    private void initComponents() {
        jtxtfQuery = new JTextField(" Search ", SEARCH_FIELD_WIDTH );
        
        jlblSearch = new JLabel();
        jlblSearch.add(jtxtfQuery);
    }
 
    public void searchTasks() {
    	
    }
    
    /*
    void message(String msg) {
        status.setText(msg);
    }*/
    
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
