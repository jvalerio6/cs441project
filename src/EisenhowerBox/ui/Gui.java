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
	
	protected final JTextPane jtxpn1 = new JTextPane();
	protected final JTextPane jtxpn2 = new JTextPane();
	protected final JTextPane jtxpn3 = new JTextPane();
	protected final JTextPane jtxpn4 = new JTextPane();
	

	private Gui()
	{
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
    	
		final JPanel jpnlView = viewPanel();
		final JPanel jpnlTasks = taskPanel();
		final JPanel jpnlMenu = menuBar();
		final JPanel jpnlPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
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
		
		
		
		
		
		
		
	   
	    //frame.setLayout(new GridLayout(1,5,10,10));
	    //frame.add(jpnlView);
	    //frame.add(jpnlTasks);
        //frame.pack();
        //frame.setVisible(true);
        
        
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
    		}
    	});
	}

	/**
	 * @return the tArea1
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
	
	private JPanel taskPanel(){
		int hgap = 10;
		int vgap = 10;
		
		final JPanel jpnl1 = new JPanel(new GridLayout(0, 1, hgap, vgap));
		final JPanel jpnl2 = new JPanel(new GridLayout(0, 1, hgap, vgap));
		final JPanel jpnl3 = new JPanel(new GridLayout(0, 1, hgap, vgap));
		final JPanel jpnl4 = new JPanel(new GridLayout(0, 1, hgap, vgap));
		
		
		
		final JLabel jlbl1 = new JLabel("Task 1");
		final JLabel jlbl2 = new JLabel("Task 2");
		final JLabel jlbl3 = new JLabel("Task 3");
		final JLabel jlbl4 = new JLabel("Task 4");
		
		jtxpn1.setEditable(false);
		jtxpn2.setEditable(false);
		jtxpn3.setEditable(false);
		jtxpn4.setEditable(false);
		
		jpnl1.add(jlbl1);
		jpnl1.add(jtxpn1);
		
		jpnl2.add(jlbl2);
		jpnl2.add(jtxpn2);
		
		jpnl3.add(jlbl3);
		jpnl3.add(jtxpn3);
		
		jpnl4.add(jlbl4);
		jpnl4.add(jtxpn4);
		
		JPanel panel = new JPanel(new GridLayout(2, 2, hgap, vgap));
		panel.add(jpnl1);
		panel.add(jpnl2);
		panel.add(jpnl3);
		panel.add(jpnl4);
  
		if (coloredPanels){
			jpnl1.setBackground(randColor());
			jpnl2.setBackground(randColor());
			jpnl3.setBackground(randColor());
			jpnl4.setBackground(randColor());
			panel.setBackground(randColor());
			
		}
	    
	    return panel;
		
	}
	
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
//JPanel panel = new JPanel(new GridBagLayout());
		//GridBagConstraints c = new GridBagConstraints();
		/*
		final JTextPane jtxpn1 = new JTextPane();
		final JTextPane jtxpn2 = new JTextPane();
		final JTextPane jtxpn3 = new JTextPane();
		final JTextPane jtxpn4 = new JTextPane();
		
	
		
		
		
		jpnl1.add(comp);
		
		c.insets = new Insets(10, 10, 10, 10);
		
		
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    //panelTasks.add(labelBox1, c);
	    
	    
	    
	    c.gridx = 1;
	    c.gridy = 0;
	    //panelTasks.add(labelBox2, c);
	    
	    
	    c.gridx = 0;
	    c.gridy = 1;
	    //panelTasks.add(tAreaBox1, c);
	    
  	
  	c.gridx = 1;
	    c.gridy = 1;
	    //panelTasks.add( tAreaBox2, c);
  	
	    
	    c.gridx = 0;
	    c.gridy = 3;
	    //panelTasks.add(labelBox3, c);
	    
	    
	    c.gridx = 1;
	    c.gridy = 3;
	    //panelTasks.add(labelBox4, c);
		
	    
  	
  	c.gridx = 0;
	    c.gridy = 4;
	    //panelTasks.add(tAreaBox3, c);
	    
  	
  	c.gridx = 1;
	    c.gridy = 4;
	    //panelTasks.add(tAreaBox4, c);
	     * */
	    

