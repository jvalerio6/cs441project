package EisenhowerBox.ui;

import EisenhowerBox.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

/** Class name: ViewTaskDetails.java
 * 	Initial implementation: Javier Valerio
 *  Implementation Date: April 30th, 2016
*/

public class ViewTaskDetails extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 610;
	public static final int HEIGHT = 530;
	private static final int NUM_CHARACTERS_WIDTH = 45;
    private static final int NUM_ROWS = 5;

	private JLabel taskNameLabel, startDateLabel, endDateLabel, importanceLabel, priorityLabel, descriptionLabel, infoLabel, dateLabel;
	private JTextField taskNameText, startDate, endDate, priorityText, importanceText;
	private JTextArea descriptionText;
	private JScrollPane scrollPane;
	private JButton backButton, editButton;
	private JPanel southPanel, infoPanel, datePanel;
	private Task task;

	JComboBox importanceCombo, priorityCombo;

	ImageIcon checkMarkIMG = new ImageIcon(this.getClass().getResource("/img/sign-check.png"));

	private String[] importanceList = Project.getImportanceList();
	private String[] priorityList = Project.getPriorityList();

	// Icons for pane
	ImageIcon ARROW_LEFT_ICO = new ImageIcon(this.getClass().getResource("/img/arrow-back-28.png"));
	ImageIcon CALENDAR_ICO = new ImageIcon(this.getClass().getResource("/img/calendar-32.png"));
	ImageIcon PENCIL_ICO = new ImageIcon(this.getClass().getResource("/img/pencil-32.png"));
	ImageIcon INFO_ICO = new ImageIcon(this.getClass().getResource("/img/info-32.png"));
	ImageIcon HOME_ICO = new ImageIcon(this.getClass().getResource("/img/house-32.png"));

	public ViewTaskDetails (Task t)
	{
		task = t;

		initializeGUI(t);
	}

	public void initializeGUI(Task t) {
		try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		finally {
			setSize(WIDTH, HEIGHT);
			setTitle("View Task Details");
			setLayout(new MigLayout("wrap 2", "[right][fill]"));
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			backButton = new JButton("Go back");
			backButton.addActionListener(this);
			backButton.setIcon(ARROW_LEFT_ICO);
			backButton.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
			backButton.setPreferredSize(new Dimension(10, 10));

			add(backButton);
			add(new JLabel());

			infoPanel = new JPanel();
			infoLabel = new JLabel("Task Details");
			infoLabel.setIcon(INFO_ICO);
			infoPanel.add(infoLabel);

			taskNameLabel = new JLabel("Task Name");
			taskNameLabel.setForeground(Color.DARK_GRAY);
			taskNameText = new JTextField(task.getTskName());

			descriptionLabel = new JLabel("Task Description");
			descriptionLabel.setForeground(Color.DARK_GRAY);

			descriptionText = new JTextArea(task.getTskDescription(), NUM_ROWS, NUM_CHARACTERS_WIDTH);
			descriptionText.setLineWrap(true);
			descriptionText.setWrapStyleWord(true);
			scrollPane = new JScrollPane(descriptionText);

			datePanel = new JPanel();
			dateLabel = new JLabel("Deadlines");
			dateLabel.setIcon(CALENDAR_ICO);
			datePanel.add(dateLabel);

			importanceLabel = new JLabel("Importance");
			importanceLabel.setForeground(Color.DARK_GRAY);
			importanceCombo = new JComboBox(importanceList);
			importanceCombo.setSelectedItem(task.getTskImportance());
			importanceCombo.addActionListener(this);

			priorityLabel = new JLabel("Priority");
			priorityLabel.setForeground(Color.DARK_GRAY);
			priorityCombo = new JComboBox(priorityList);
			priorityCombo.setSelectedItem(task.getTskPriority());
			priorityCombo.addActionListener(this);

			startDateLabel = new JLabel("Start Date");
			startDateLabel.setForeground(Color.DARK_GRAY);
			startDate = new JTextField(task.getTskStrtDate().toString());
			startDate.setEditable(false);

			endDateLabel = new JLabel("End Date");
			endDateLabel.setForeground(Color.DARK_GRAY);
			endDate = new JTextField(task.getTskEndDate().toString());
			endDate.setEditable(false);

			add(infoPanel);
			add(new JLabel());

			add(taskNameLabel);
			add(taskNameText);
			add(descriptionLabel);
			add(scrollPane);

			add(importanceLabel);
			add(importanceCombo);

			add(priorityLabel);
			add(priorityCombo);

			add(datePanel);
			add(new JLabel());

			add(startDateLabel);
			add(startDate);

			add(endDateLabel);
			add(endDate);

			add(new JPanel());
			add(new JLabel());

			add(new JPanel());
			add(new JLabel());

			editButton = new JButton("Save Details");
			editButton.addActionListener(this);
			editButton.setIcon(PENCIL_ICO);
			editButton.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));

			southPanel = new JPanel();
			southPanel.add(editButton);

			add(new JPanel());
			add(southPanel);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if(action.equals("Save Details"))
		{

			System.out.println("\nInformation: \nName: " + task.getTskName() +
								"\nDescription: " + task.getTskDescription() +
								"\nPriority: " + task.getTskPriority() +
								"\nImportance: " + task.getTskImportance());

			task.setTskName(taskNameText.getText());
			task.setTskDescription(descriptionText.getText());
			task.setTskImportance(importanceCombo.getSelectedItem().toString());
			task.setTskPriority(priorityCombo.getSelectedItem().toString());


			// Get the integer representation for any priority, importance. These values are stored as integers on the database
			int priorityIndex = Project.Utile.Priority.valueOf(task.getTskPriority()).ordinal() + 1;
			int importanceIndex = Project.Utile.Importance.valueOf(task.getTskImportance()).ordinal() + 1;

			// save
			DbManager.getInstance().updateTask(task.getTaskId(), task.getTskName(), task.getTskDescription(), priorityIndex, importanceIndex);

			JOptionPane.showMessageDialog(null, "Task updated successfully", "Task Update", JOptionPane.DEFAULT_OPTION, checkMarkIMG);

			// send updated task to db
			System.out.println();

			System.out.println("\nUpdated Information: \nName: " + task.getTskName() +
								"\nDescription: " + task.getTskDescription() +
								"\nPriority: " + task.getTskPriority() +
								"\nImportance: " + task.getTskImportance());


			dispose();
		}

		else if(action.equals("Go back"))
		{
			dispose();
		}

	}
}
