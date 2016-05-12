package EisenhowerBox.ui;

/** Class name: AddTask.java
 * 	Initial implementation: Javier Valerio
 *  Implementation Date: April 1st, 2016
 *  Last Modified Date: May 8th, 2016
*/

import EisenhowerBox.*;

import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddTask extends JFrame implements ActionListener, DocumentListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 530;

	private JLabel taskNameLabel, startDateLabel, endDateLabel, importanceLabel, priorityLabel, descriptionLabel;
	private JTextField taskNameInput, yearFieldStartDate, yearFieldEndDate;
    private JTextArea descriptionInput;
	private JButton submitButton, cancelButton;
	private JPanel startDatePanel, endDatePanel, datePanel, northPanel, southPanel;
	private JScrollPane jScrollPane1;

	private Date startDate, endDate;

	private JComboBox dayCombo_StartDate, monthCombo_StartDate, dayCombo_EndDate, monthCombo_EndDate, importanceCombo, priorityCombo;
	private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

	private Project.Utile.Importance[] importanceList = Project.Utile.Importance.values();
	private Project.Utile.Priority[] priorityList = Project.Utile.Priority.values();

	ImageIcon checkMarkIMG = new ImageIcon(this.getClass().getResource("/img/sign-check.png"));
	ImageIcon errorIcon = new ImageIcon(this.getClass().getResource("/img/sign-error.png"));

	public AddTask ()
	{

		// Set up basic frame window configurations
		setSize(WIDTH, HEIGHT);
		setTitle("Add New Task");
		setLayout(new GridLayout(3,1));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/** northPanel */
		northPanel = new JPanel();
		northPanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		northPanel.setLayout(new GridLayout(4,1));

		taskNameLabel = new JLabel("Task Name");
		taskNameLabel.setForeground(Color.DARK_GRAY);
		taskNameInput = new JTextField("");
		taskNameInput.addActionListener(this);

		descriptionLabel = new JLabel("Task Description");
		descriptionLabel.setForeground(Color.DARK_GRAY);

		descriptionInput = new JTextArea();
        descriptionInput.setColumns(20);
        descriptionInput.setLineWrap(true);
        descriptionInput.setRows(5);
        descriptionInput.setWrapStyleWord(true);
        descriptionInput.getDocument().addDocumentListener(this);
        jScrollPane1 = new JScrollPane(descriptionInput);

		northPanel.add(taskNameLabel);
		northPanel.add(taskNameInput);

		northPanel.add(descriptionLabel);
		northPanel.add(jScrollPane1);

		add(northPanel);
		/** end of northPanel */

		/** startDatePanel */
		startDatePanel = new JPanel();
		startDatePanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		startDatePanel.setLayout(new GridLayout(1,3));

		startDateLabel = new JLabel("Start Date");
		startDateLabel.setForeground(Color.DARK_GRAY);

		monthCombo_StartDate = new JComboBox(months);
		monthCombo_StartDate.setSelectedIndex(0);
		monthCombo_StartDate.addActionListener(this);

		dayCombo_StartDate = new JComboBox(days);
		dayCombo_StartDate.setSelectedIndex(0);
		dayCombo_StartDate.addActionListener(this);

		yearFieldStartDate = new JTextField("");
		yearFieldStartDate.addActionListener(this);

		startDatePanel.add(monthCombo_StartDate);
		startDatePanel.add(dayCombo_StartDate);
		startDatePanel.add(yearFieldStartDate);

		/**end of startDatePanel */

		/** endDatePanel */
		endDatePanel = new JPanel();
		endDatePanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		endDatePanel.setLayout(new GridLayout(1,3));

		endDateLabel = new JLabel("End Date");
		endDateLabel.setForeground(Color.DARK_GRAY);

		monthCombo_EndDate = new JComboBox(months);
		monthCombo_EndDate.setSelectedIndex(0);
		monthCombo_EndDate.addActionListener(this);

		dayCombo_EndDate = new JComboBox(days);
		dayCombo_EndDate.setSelectedIndex(0);
		dayCombo_EndDate.addActionListener(this);

		yearFieldEndDate = new JTextField("");
		yearFieldEndDate.addActionListener(this);

		endDatePanel.add(monthCombo_EndDate);
		endDatePanel.add(dayCombo_EndDate);
		endDatePanel.add(yearFieldEndDate);

		/** end of endDatePanel */

		datePanel = new JPanel();
		datePanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		datePanel.setLayout(new GridLayout(4,1));

		datePanel.add(startDateLabel);
		datePanel.add(startDatePanel);

		datePanel.add(endDateLabel);
		datePanel.add(endDatePanel);

		add(datePanel);


		/** southPanel */
		southPanel = new JPanel();
		southPanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		southPanel.setLayout(new GridLayout(4,1));

		importanceLabel = new JLabel("Importance");
		importanceLabel.setForeground(Color.DARK_GRAY);
		importanceCombo = new JComboBox(importanceList);
		importanceCombo.setSelectedIndex(0);
		importanceCombo.addActionListener(this);

		priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityCombo = new JComboBox(priorityList);
		priorityCombo.setSelectedIndex(0);
		priorityCombo.addActionListener(this);

		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setBackground(Color.getHSBColor(0.42f, 0.72f, 0.70f));

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setBackground(Color.getHSBColor(0.07f, 0.75f, 0.89f));

		southPanel.add(importanceLabel);
		southPanel.add(importanceCombo);

		southPanel.add(priorityLabel);
		southPanel.add(priorityCombo);

		southPanel.add(submitButton, BorderLayout.EAST);
		southPanel.add(cancelButton, BorderLayout.WEST);

		add(southPanel);
		/** end of southPanel */

	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		StringBuilder errorString = new StringBuilder();

		// Validate name field
		String name = taskNameInput.getText();
		if (name.length() == 0) {
			errorString.append("Name of task is required.\n");
		}

		// Validate description field
		String description = descriptionInput.getText();
		if (description.length() == 0) {
			errorString.append("Description of task is required.\n");
		}

		// Get each drop down value for priority and importance
		String importance = importanceCombo.getSelectedItem().toString();
		String priority = priorityCombo.getSelectedItem().toString();

		// Get the integer representation for any priority, importance. These values are stored as integers on the database
		int priorityIndex = Project.Utile.Priority.valueOf(priority).ordinal() + 1;
		int importanceIndex = Project.Utile.Importance.valueOf(importance).ordinal() + 1;


		/** Validate Start Date **/
		String monthSTART = monthCombo_StartDate.getSelectedItem().toString();
		int daySTART = Integer.parseInt(dayCombo_StartDate.getSelectedItem().toString());
		int yearSTART = validateYear(yearFieldStartDate);

		// invalid date
		if (yearSTART == 0) {
			errorString.append("Invalid start year.\n");
		}

		String st = "" + yearSTART + ", " + monthSTART + " " + daySTART;
		startDate = new Date(st);

		// Date object must be converted as a string
		st = stringifyDate(startDate);


		/** Validate End Date */
		String monthEND = monthCombo_EndDate.getSelectedItem().toString();
		int dayEND = Integer.parseInt(dayCombo_EndDate.getSelectedItem().toString());
		int yearEND = validateYear(yearFieldEndDate);

		// invalid date
		if (yearEND == 0) {
			errorString.append("Invalid end year.\n");
		}

		String ed = "" + yearEND + ", " + monthEND + " " + dayEND;
		endDate = new Date(ed);
		ed = stringifyDate(endDate);

		if (yearEND < yearSTART) {
			errorString.append("End date must be prior to start date.\n");
		}


		// All fields are to be validated upon Submission
		if(action.equals("Submit"))
		{

			// errorString contains the list of all the errors that must be fixed in order for the task to stored in db
			if(!errorString.toString().isEmpty())
			{
				JOptionPane.showMessageDialog(null, errorString.toString(), "REQUIRED FIELDS", JOptionPane.ERROR_MESSAGE, errorIcon);
			}
			else // errorString is empty. No errors and it's ok to proceed
			{
				// Save task into the database
				DbManager.getInstance().createTask(1, name, description, priorityIndex, importanceIndex, stringifyDate(startDate), stringifyDate(endDate));

				// alert user throughout the process
				JOptionPane.showMessageDialog(null, "Task added successfully", "Task added", JOptionPane.DEFAULT_OPTION, checkMarkIMG);

				System.out.println("-----------------------------------------------------------------");
				System.out.println("Name: " + name + "\nDescription: " + description +
									"\nStart Date: " + st + "\nEnd Date: " + ed +
									"\nImportance: " + importance + "\nPriority: " + priority);
				System.out.println("-----------------------------------------------------------------");


				// close AddTask frame
				dispose();
			}
		}

		//Close current window.
		if(cancelButton.hasFocus())
		{
			dispose();
		}
	}

	/*
		PURPOSE: This method returns the String representation of the Date object passed as a parameter.
		RETURN: String of date in the format of 'yyyy-MM-dd HH:mm:ss.SSS'
	*/
	public String stringifyDate (Date date) {
        String datetime;

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
        datetime = ft.format(date);

		return datetime;
	}

	/*
		PURPOSE: This method validates the year field and makes sure it is a valid integer
	*/
	public int validateYear(JTextField dateTextField) {
		int year = 0;

		//Make sure year is numeric and has exactly 4 digits
		if(!dateTextField.getText().equals("")
				&& dateTextField.getText().length() == 4 &&
				(dateTextField.getText().matches("[0-9]+")))
		{
			year = Integer.parseInt(dateTextField.getText());

			if(Character.isDigit(year))
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}

		return year;
	}


	public void changedUpdate(DocumentEvent arg0) {	}

	public void insertUpdate(DocumentEvent ev) { }

	public void removeUpdate(DocumentEvent arg0) { }

}
