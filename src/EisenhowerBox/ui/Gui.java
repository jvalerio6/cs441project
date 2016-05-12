package EisenhowerBox.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

import EisenhowerBox.DbManager;

public class Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static Gui instance = null;

	final Random rand = new Random();
	final static boolean coloredPanels = false;

	protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected int hight = screenSize.height / 3;
	protected int width = screenSize.width / 3;

	protected  DbManager db;

	JPanel jpnlTasks;

	/**
	 * default constructor
	 * initialises all gui components
	 */
	private Gui() {
		db = DbManager.getInstance();
		passwordFrame();
		createGUI();
	}

	/**
     *
     * @return Login instance
     */
    public static Gui getInstance() {
    	if (instance == null){
    		instance = new Gui();
    	}
    	return instance;
    }


    void createGUI() {
    	final JPanel panel = new JPanel(new GridBagLayout());
		final JPanel jpnlView = viewPanel();
		jpnlTasks = new ViewTaskArea();
		final JPanel jpnlMenu = menuBar();

		try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		GridBagConstraints c = new GridBagConstraints();

		//Adds the menue to the top so it takes up the entire width
		c.weightx = 1.0;   						//request any extra Horizontal space
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10,5,5,5);
		c.gridx = 0;
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridwidth = 2;
		panel.add(jpnlMenu,c);



		// Adds the View Panel to the side and sizes it
		c.weighty = 0.5;
		c.weightx = 0.0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,5,5);
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(jpnlView, c);


		// Adds the Task Panel to the right
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		panel.add(jpnlTasks, c);

		panel.setVisible(true);
		if (coloredPanels) panel.setBackground(randColor());

        SwingUtilities.invokeLater(new Runnable() {
    		public void run(){
    			JFrame frame = new JFrame("Project Managment");
        	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    JFrame.setDefaultLookAndFeelDecorated(true);
        		frame.setLayout(new BorderLayout());
        		if (coloredPanels) frame.setBackground(randColor());
        	    frame.add(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setPreferredSize(panel.getPreferredSize());
    		}
    	});
	}

	/**
	 * Initial Lodin frame
	 */
	private void passwordFrame() {
    	final Login login = new Login(this, true);
    	login.setVisible(true);


        /**
         * Method to invoke the thread whenever it is convinient
         * @Override: overrides the frame attributes of the parent class for the login window
         */
        SwingUtilities.invokeLater(new Runnable() {
            	// creates a new thread to run the login frame
            public void run() {
                JFrame frame = new JFrame();
                //frame.setMinimumSize(new Dimension(500, 200));
                //frame.add(login);
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setTitle("Logged In");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setPreferredSize(new Dimension(hight, width));
            }
        });

    }

	/**
	 * Sets up user view menue
	 * @return Jpanel for the user view menue
	 */
	private JPanel viewPanel() {
		int hgap = 10;
		int vgap = 10;

		final JButton jbtnManager = new JButton();
		final JLabel jlbl1 = new JLabel("Project Manager");
	    final JButton jbtnUser = new JButton();
	    final JLabel jlbl3 = new JLabel("Personal User");
	    final JButton jbtnCommunity = new JButton();
	    final JLabel jlbl5 = new JLabel("Community User");
	    final JButton jbtnObservation = new JButton("Observer");

	    jbtnManager.setLayout(new BorderLayout());
	    jbtnManager.add(BorderLayout.CENTER,jlbl1);

	    jbtnUser.setLayout(new BorderLayout());
	    jbtnUser.add(BorderLayout.CENTER,jlbl3);

	    jbtnCommunity.setLayout(new BorderLayout());
	    jbtnCommunity.add(BorderLayout.CENTER,jlbl5);

	    Dimension d = new Dimension(135,15);
	    jbtnManager.setPreferredSize(d);
	    jbtnUser.setPreferredSize(d);
	    jbtnCommunity.setPreferredSize(d);
	    jbtnObservation.setPreferredSize(d);

	    // Adding JOptionPane messages when each of the modes are clicked
	    jbtnManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "You are in Project Manager mode now.", "Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        });

	    jbtnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "You are in User mode now.", "Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        });

	    jbtnCommunity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "You are in Community mode now.", "Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        });

	    jbtnObservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "You are in Observation mode now.", "Mode", JOptionPane.INFORMATION_MESSAGE);
            }
        });

	    JPanel panel = new JPanel(new GridLayout(0, 1, hgap, vgap));
	    panel.add(jbtnManager);
	    panel.add(jbtnUser);
	    panel.add(jbtnCommunity);
	    panel.add(jbtnObservation);


	    if (coloredPanels){
			panel.setBackground(randColor());
		}
	    return panel;
	}

	private JPanel menuBar() {

		ImageIcon SEARCH_ICO = new ImageIcon(this.getClass().getResource("/img/search-32.png"));
		ImageIcon PENCIL_ICO = new ImageIcon(this.getClass().getResource("/img/pencil-32.png"));
		ImageIcon LOGOUT_ICO = new ImageIcon(this.getClass().getResource("/img/logout-32.png"));
		ImageIcon REFRESH_ICO = new ImageIcon(this.getClass().getResource("/img/refresh-32.png"));

		final JButton jbtnNewTask = new JButton("New Task");
		jbtnNewTask.setIcon(PENCIL_ICO);

	    final JButton jbtnLogin = new JButton("Log Out");
	    jbtnLogin.setIcon(LOGOUT_ICO);

	    final JButton jbtnSearch = new JButton("Search");
	    jbtnSearch.setIcon(SEARCH_ICO);

	    final JButton jbtnRefresh = new JButton("Refresh");
	    jbtnRefresh.setIcon(REFRESH_ICO);

	    final JPanel panel = new JPanel(new GridBagLayout());

	    jbtnNewTask.setPreferredSize(new Dimension(120,40));
	    jbtnLogin.setPreferredSize(new Dimension(120, 40));
	    jbtnSearch.setPreferredSize(new Dimension(120, 40));
	    jbtnRefresh.setPreferredSize(new Dimension(120, 40));

	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtnLogin, c);

	    c.gridx = 1;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtnNewTask, c);

	    c.gridx = 2;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtnSearch, c);

	    c.gridx = 5;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtnRefresh, c);


		if (coloredPanels){
			panel.setBackground(randColor());
		}

		jbtnNewTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddTask at = new AddTask();
            	at.setVisible(true);
            }
        });

		jbtnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Search st = new Search();
            	st.setVisible(true);
            }
        });

		jbtnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION);

            	if (answer == JOptionPane.YES_OPTION) {
            		dispose();
            		System.exit(0);
            	}
            }
        });


		jbtnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	jpnlTasks.setVisible(false);

            	((ViewTaskArea) jpnlTasks).removeAll();
            	((ViewTaskArea) jpnlTasks).refreshTaskArea();

            	JOptionPane.showMessageDialog(null, "Pane was updated!", "Refresh", JOptionPane.INFORMATION_MESSAGE);

            	jpnlTasks.doLayout();

            }
        });

	    return panel;
	}


	/**
	 * Set up for random panel colors
	 * @param min: lowest integer
	 * @param max: Largest integer
	 * @return randomized int between perameters
	 */
	public int randomInt(int min, int max) {
		int randInt = rand.nextInt((max - min) + 1) + min;
		return randInt;

	}
	public Color randColor() {
		return new Color(randomInt(0,255),
				randomInt(0,255),
				randomInt(0,255));
	}

	public void actionPerformed(ActionEvent arg0) {

	}
}