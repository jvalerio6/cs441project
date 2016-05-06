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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Gui instance = null;
	
	final Random rand = new Random();
	final static boolean coloredPanels = true;
	
	protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected int hight = screenSize.height / 3;
	protected int width = screenSize.width / 3;
	
	protected  DbManager db;
	
	
	
	/**
	 * default constructor
	 * initialises all gui components
	 */
	private Gui(){
		db = DbManager.getInstance();
		passwordFrame();
		createGUI();
	}
	
	/**
     * 
     * @return Login instance
     */
    public static Gui getInstance(){
    	if (instance == null){
    		instance = new Gui();    		
    	}
    	return instance;
    }
    
    
    void createGUI(){
    	final JPanel panel = new JPanel(new GridBagLayout());
		final JPanel jpnlView = viewPanel();
		final JPanel jpnlTasks =new ViewTaskArea();
		final JPanel jpnlMenu = menuBar();
		
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
                frame.add(login);
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
		final JLabel jlbl1 = new JLabel("Project");
		final JLabel jlbl2 = new JLabel("Manager");
	    final JButton jbtnUser = new JButton();
	    final JLabel jlbl3 = new JLabel("Personal");
		final JLabel jlbl4 = new JLabel("  User");
	    final JButton jbtnCommunity = new JButton();
	    final JLabel jlbl5 = new JLabel("Comunity");
		final JLabel jlbl6 = new JLabel("  User");
	    final JButton jbtnObservation = new JButton("Observer");
	    
	    jbtnManager.setLayout(new BorderLayout());
	    jbtnManager.add(BorderLayout.NORTH,jlbl1);
	    jbtnManager.add(BorderLayout.CENTER,jlbl2);
	    
	    jbtnUser.setLayout(new BorderLayout());
	    jbtnUser.add(BorderLayout.NORTH,jlbl3);
	    jbtnUser.add(BorderLayout.CENTER,jlbl4);
	    
	    jbtnCommunity.setLayout(new BorderLayout());
	    jbtnCommunity.add(BorderLayout.NORTH,jlbl5);
	    jbtnCommunity.add(BorderLayout.CENTER,jlbl6);
	    
	    Dimension d = new Dimension(135,50);
	    jbtnManager.setPreferredSize(d);
	    jbtnUser.setPreferredSize(d);
	    jbtnCommunity.setPreferredSize(d);
	    jbtnObservation.setPreferredSize(d);
	    
	    
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
		
		final JButton jbtnNewTask = new JButton(" New Task ");
	    final JButton jbtnLogin = new JButton(" logout ");
	    final JPanel panel = new JPanel(new GridBagLayout());
	    final JPanel searchPanel = new Search();

	    jbtnNewTask.setPreferredSize(new Dimension(100,40));
	    jbtnLogin.setPreferredSize(new Dimension(80, 40));
	    

	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.ipadx = 100;
	    c.anchor = GridBagConstraints.WEST;
	    panel.add(jbtnLogin, c);
	    
	    c.gridx = 1;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.EAST;
	    panel.add(jbtnNewTask, c);
	    
	    c.gridx = 2;
	    c.ipadx = 25;
	    c.anchor = GridBagConstraints.EAST;
	    panel.add(searchPanel, c);
	    
		if (coloredPanels){
			panel.setBackground(randColor());	
		}
		
		jbtnNewTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddTask at = new AddTask(instance, true);
            	at.setVisible(true);
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
	public int randomInt(int min, int max){
		int randInt = rand.nextInt((max - min) + 1) + min;
		return randInt;
		
	}
	public Color randColor(){
		return new Color(randomInt(0,255),
				randomInt(0,255),
				randomInt(0,255));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}

	    

