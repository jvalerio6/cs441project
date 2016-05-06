package EisenhowerBox.ui;

/** Class name: AddTask.java
 * 	Initial implementation: Javier Valerio
 *  Implementation Date: April 13th, 2016
*/

import EisenhowerBox.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
import javax.swing.text.BadLocationException;

public class AddTask extends JFrame implements ActionListener, DocumentListener {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 530;
	private JLabel taskNameLabel, startDateLabel, endDateLabel, importanceLabel, priorityLabel, descriptionLabel, teamMembersLabel;
	private JTextField taskNameInput, teamMembersInput, yearFieldStartDate, yearFieldEndDate;
    private JTextArea descriptionInput;
	private JButton submitButton, cancelButton;
	private JPanel startDatePanel, endDatePanel, datePanel, northPanel, southPanel;
	private JScrollPane jScrollPane1;

	private Date startDate, endDate;
	private Task newTask;

	JComboBox dayCombo_StartDate, monthCombo_StartDate, dayCombo_EndDate, monthCombo_EndDate, importanceCombo, priorityCombo;
	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] importance = {"Super Important", "Important", "Ordinary", "Not Important", "Not Indicated"};
	String[] priorities = {"Urgent", "Not Urgent"};

	ImageIcon checkMarkIMG = new ImageIcon(this.getClass().getResource("/img/sign-check.png"));
	ImageIcon errorIcon = new ImageIcon(this.getClass().getResource("/img/sign-error.png"));

	public AddTask ()
	{
		setSize(WIDTH, HEIGHT);
		setTitle("Add New Task");
		setLayout(new GridLayout(3,1));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/**northPanel*/
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
		/**end of northPanel*/

		/**purchaseDatePanel*/
		startDatePanel = new JPanel();
		//startDatePanel.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.WHITE));
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

		/**end of purchaseDatePanel panel*/

		/**expirationDatePanel*/
		endDatePanel = new JPanel();
		//endDatePanel.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.WHITE));
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

		/**end of expirationDatePanel panel*/

		datePanel = new JPanel();
		datePanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		datePanel.setLayout(new GridLayout(4,1));

		datePanel.add(startDateLabel);
		datePanel.add(startDatePanel);

		datePanel.add(endDateLabel);
		datePanel.add(endDatePanel);

		add(datePanel);

		/**southPanel*/
		southPanel = new JPanel();
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		southPanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		southPanel.setLayout(new GridLayout(4,1));

		importanceLabel = new JLabel("Importance");
		importanceLabel.setForeground(Color.DARK_GRAY);
		importanceCombo = new JComboBox(importance);
		importanceCombo.setSelectedIndex(0);
		importanceCombo.addActionListener(this);

		priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityCombo = new JComboBox(priorities);
		priorityCombo.setSelectedIndex(0);
		priorityCombo.addActionListener(this);

		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setBackground(Color.getHSBColor(0.42f, 0.72f, 0.70f));
		submitButton.setForeground(Color.WHITE);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setBackground(Color.getHSBColor(0.07f, 0.75f, 0.89f));
		cancelButton.setForeground(Color.BLACK);

		southPanel.add(importanceLabel);
		southPanel.add(importanceCombo);

		southPanel.add(priorityLabel);
		southPanel.add(priorityCombo);

		southPanel.add(submitButton, BorderLayout.EAST);
		southPanel.add(cancelButton, BorderLayout.WEST);

		add(southPanel);

		/**end of southPanel*/
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		StringBuilder errorString = new StringBuilder();

		// Get task name and description
		String name = taskNameInput.getText();
		String description = descriptionInput.getText();

		if (name.length() == 0) {
			errorString.append("Name of task is required.\n");
		}

		if (description.length() == 0) {
			errorString.append("Description of task is required.\n");
		}

		// Get each drop down value
		String importance = importanceCombo.getSelectedItem().toString();
		String priority = priorityCombo.getSelectedItem().toString();
		//String importance = importanceCombo.getSelectedItem().toString(); // selected user

		/** Start date */
		String monthSTART = monthCombo_StartDate.getSelectedItem().toString();
		int daySTART = Integer.parseInt(dayCombo_StartDate.getSelectedItem().toString());
		int yearSTART = validateYear(yearFieldStartDate);

		// invalid date
		if (yearSTART == 0) {
			errorString.append("Invalid start year.\n");
		}

		startDate = new Date(yearSTART + ", " + monthSTART + "-" + daySTART);


		/** End date */
		String monthEND = monthCombo_EndDate.getSelectedItem().toString();
		int dayEND = Integer.parseInt(dayCombo_EndDate.getSelectedItem().toString());
		int yearEND = validateYear(yearFieldEndDate);

		// invalid date
		if (yearEND == 0) {
			errorString.append("Invalid end year.\n");
		}

		endDate = new Date(yearEND + ", " + monthEND + "-" + dayEND);

		if (yearEND < yearSTART) {
			errorString.append("End date must be prior to start date.\n");
		}

		//When clicking OK
		if(action.equals("Submit"))
		{

			if(!errorString.toString().isEmpty())
			{
				JOptionPane.showMessageDialog(null, errorString.toString(), "REQUIRED FIELDS", JOptionPane.ERROR_MESSAGE, errorIcon);
			}
			else
			{
				//Task newTask = new Task(name, null, null);

				JOptionPane.showMessageDialog(null, "Product added successfully", "Task added", JOptionPane.DEFAULT_OPTION, checkMarkIMG);

				System.out.println("-----------------------------------------------------------------");
				System.out.println("Name: " + name + "\nDescription: " + description +
									"\nStart Date: " + startDate.toString() + "\nEnd Date: " + endDate.toString() +
									"\nImportance: " + importance + "\nPriority: " + priority);
				System.out.println("-----------------------------------------------------------------");

				//newTask = new Task(name, startDate, endDate);

				//dispose();
			}
		}

		//Close current window.
		if(cancelButton.hasFocus())
		{
			dispose();
		}
	}

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
