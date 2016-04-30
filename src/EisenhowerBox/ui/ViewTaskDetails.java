package EisenhowerBox.ui;

import EisenhowerBox.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/** Class name: ViewTaskDetails.java
 * 	Initial implementation: Javier Valerio
 *  Implementation Date: April 21st, 2016
*/

public class ViewTaskDetails extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 530;
	private JLabel taskNameLabel, startDateLabel, endDateLabel, importanceLabel, priorityLabel, descriptionLabel, homeLabel, infoLabel, dateLabel;
	private JTextField taskNameText, startDate, endDate, descriptionText, priorityText, importanceText;
	private JTextArea tt;
	private JButton backButton, editButton;
	private JPanel southPanel, infoPanel;
	private Task task;

	// Icons for pane
	ImageIcon ARROW_LEFT_ICO = new ImageIcon(this.getClass().getResource("/img/arrow_left.png"));
	ImageIcon CALENDAR_ICO = new ImageIcon(this.getClass().getResource("/img/calendar.png"));
	ImageIcon PENCIL_ICO = new ImageIcon(this.getClass().getResource("/img/pencil-32.png"));
	ImageIcon INFO_ICO = new ImageIcon(this.getClass().getResource("/img/info-32.png"));
	ImageIcon HOME_ICO = new ImageIcon(this.getClass().getResource("/img/house-32.png"));

	public ViewTaskDetails (Task t)
	{
		task = t;

		setSize(WIDTH, HEIGHT);
		setTitle("View Task Details");
		setLayout(new GridLayout(2,1));
//		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		taskNameLabel = new JLabel("Task Name");
		taskNameLabel.setForeground(Color.DARK_GRAY);
		taskNameText = new JTextField(task.getTskName());
//		taskNameText.setEditable(false);

		descriptionLabel = new JLabel("Task Description");
		descriptionLabel.setForeground(Color.DARK_GRAY);
		tt = new JTextArea(task.getTskDescription());
//		descriptionText.setEditable(false);
		tt.setColumns(20);
		tt.setLineWrap(true);
		tt.setRows(5);

		JScrollPane jScrollPane1 = new JScrollPane(tt);


		importanceLabel = new JLabel("Importance");
		importanceLabel.setForeground(Color.DARK_GRAY);
		importanceText = new JTextField(task.getTskImportance());
		importanceText.setEditable(false);

		priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityText = new JTextField(task.getTskPriority());
//		priorityText.setEditable(false);

		startDateLabel = new JLabel("Start Date");
		startDateLabel.setForeground(Color.DARK_GRAY);
		startDate = new JTextField(task.getTskStrtDate().toString());
		startDate.setEditable(false);

		endDateLabel = new JLabel("End Date");
		endDateLabel.setForeground(Color.DARK_GRAY);
		endDate = new JTextField(task.getTskEndDate().toString());
		endDate.setEditable(false);

		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(9, 2));

//		homeLabel = new JLabel("Home");
//		homeLabel.setIcon(HOME_ICO);
//		homeLabel.addMouseListener(new MouseAdapter()
//		{
//		    public void mouseClicked(MouseEvent e)
//		    {
//		       dispose();
//		    }
//		});
//
//		infoPanel.add(homeLabel);
//		infoPanel.add(new JLabel());

		infoLabel = new JLabel("Task Details");
		infoLabel.setIcon(INFO_ICO);
		infoLabel.setSize(new Dimension(50,50));

		infoPanel.add(new JLabel());
		infoPanel.add(new JLabel());

		infoPanel.add(infoLabel);
		infoPanel.add(new JLabel());

		infoPanel.add(taskNameLabel);
		infoPanel.add(taskNameText);
		infoPanel.add(descriptionLabel);
		infoPanel.add(jScrollPane1);

		infoPanel.add(importanceLabel);
		infoPanel.add(importanceText);

		infoPanel.add(priorityLabel);
		infoPanel.add(priorityText);

		infoPanel.add(startDateLabel);
		infoPanel.add(startDate);

		infoPanel.add(endDateLabel);
		infoPanel.add(endDate);

		backButton = new JButton("Go back");
		backButton.addActionListener(this);
		backButton.setIcon(ARROW_LEFT_ICO);
		backButton.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));

		editButton = new JButton("Edit");
		editButton.addActionListener(this);
		editButton.setIcon(PENCIL_ICO);
		editButton.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));

		southPanel = new JPanel();
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
//		southPanel.setBackground(Color.getHSBColor(0.15f, .12f, 0.80f));
		southPanel.setLayout(new GridLayout(3, 1));

		southPanel.add(editButton, BorderLayout.CENTER);

		add(infoPanel);
		add(southPanel);
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if(action.equals("Edit"))
		{
			// save
			task.setTskName(taskNameText.getText());
			task.setTskDescription(descriptionText.getText());
			task.setTskImportance(importanceText.getText());
			task.setTskPriority(priorityText.getText());

			// send updated task to db

		}

	}

	public static void main(String[] args) {

		Task t = new Task();
		t.setTskName("Testing Name");
		t.setTskDescription("Testing description with lots of words just to test whether the pane where description text goes overflows");
		t.setTskStrtDate(new Date(2016, 8, 5));
		t.setTskEndDate(new Date(2016, 10, 12));
		t.setTskPriority("URGENT");
		t.setTskImportance("IMPORTANT");

		ViewTaskDetails taskDetails = new ViewTaskDetails(t);
		taskDetails.setVisible(true);
	}

}
