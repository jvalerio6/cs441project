package EisenhowerBox.ui;


import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Random;

import javax.swing.*;


public class Gui extends JFrame {	 
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
	
	
	
	/**
	 * default constructor
	 * initialises all gui components
	 */
	private Gui(){
		createGUI();
		//passwordFrame();
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
		final JPanel jpnlView = viewPanel();
		final JPanel jpnlTasks =new ViewTaskArea();
		//final JPanel jpnlMenu = menuBar();
		//final JPanel jpnlPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		/*
		c.ipady = 0;       						//make this component tall
		c.weighty = 1.0;   						//request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  		//top padding
		c.gridx = 1;      						 //aligned with button 2
		c.gridwidth = 2;  						 //2 columns wide
		c.gridy = 2;      						 //third row
		
		c.ipady = 0;       						//make this component tall
		c.weighty = 1.0;   						//request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  		//top padding
		c.gridx = 1;      						 //aligned with button 2
		c.gridwidth = 2;  						 //2 columns wide
		c.gridy = 2;  
		
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipady = 40;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridheight = 3;
		c.gridwidth = 2;
		c.insets = new Insets(10,0,0,0);
		//c.fill
		
		
		
		
		
		
		
	   
	    frame.setLayout(new GridLayout(1,5,10,10));
	    frame.add(jpnlView);
	    frame.add(jpnlTasks);
        frame.pack();
        frame.setVisible(true);
        
        */
        SwingUtilities.invokeLater(new Runnable() {
    		public void run(){
    			JFrame frame = new JFrame("Project Managment");
        	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    JFrame.setDefaultLookAndFeelDecorated(true);	
        		frame.setLayout(new GridLayout(1,5,10,10));
        		if (coloredPanels) frame.setBackground(randColor());	
        	    frame.add(jpnlView);
        	    frame.add(jpnlTasks);
                frame.pack();
                frame.setVisible(true);
                frame.setSize(new Dimension(800, 400));
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
		final JButton jbtnManager = new JButton(" Project Maneger ");
	    final JButton jbtnUser = new JButton(" Personal User ");
	    final JButton jbtnCommunity = new JButton("Community User");
	    final JButton jbtnObservation = new JButton("Observation");
	    
	    Dimension d = new Dimension(30,50);
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
		int hgap = 10;
		int vgap = 10;
		final JButton jbtnNewTask = new JButton(" New Task ");
	    final JButton jbtnLogin = new JButton(" logout ");
	    
	    
	    Dimension d = new Dimension(30,50);
	    //jbtnManager.setPreferredSize(d);
	    //jbtnUser.setPreferredSize(d);
	   // jbtnCommunity.setPreferredSize(d);
	    //jbtnObservation.setPreferredSize(d);
	    
	    
	    
	    
	    
	    JPanel panel = null;
		if (coloredPanels){
			panel.setBackground(randColor());	
		}
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
	
	
	
	
	
	
}

	    

