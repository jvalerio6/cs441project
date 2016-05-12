/**
 * 
 */
package EisenhowerBox.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import EisenhowerBox.DbManager;
import EisenhowerBox.Project.Utile;
import EisenhowerBox.Project;
import EisenhowerBox.Task;
import EisenhowerBox.TeamMember;
import EisenhowerBox.User;

/**
 * @author erikkalan
 *	Manages the Panel that shows the tasks
 *needs to add sub classes for each vieww option so we can add support for all buttons and implemt actions
 */
public class ViewNewUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 61L;
	//private static final Icon Action = null;
	protected final Dimension panel_dimension = new Dimension(1000,600);
	protected static JPanel viewPanelCreate;
	protected JPanel startPanel;
	protected JPanel newUserPanel;
	protected final CardLayout cl = new CardLayout();
	
	//protected final JScrollPane scrollPanes = new JScrollPane();

	User user = null; 

	public ViewNewUser() {
		this.init();	
    }
	
	
	/** 
	 * Initializes components necisary for the Panel
	 * Needs to add generalized support and then full support for
	 * the individual sub classes
	 * May need to have some variables be changed to class variables
	 */
	private void init(){
		
		this.setBackground(Color.gray);
		this.setPreferredSize(panel_dimension);
		this.setLayout(new BorderLayout());
		startPanel = startPane();
		newUserPanel = createUser();
		viewPanelCreate = new JPanel(cl);
		viewPanelCreate.add(startPanel, "start");
		viewPanelCreate.add(newUserPanel, "newUser");	
		//cl.first(startPanel);
		
		this.add(viewPanelCreate, BorderLayout.CENTER);
        this.setVisible(true);      
	}
	
	
	
	protected JPanel startPane(){
		
		final JPanel panel = new JPanel(new GridBagLayout());
		final JPanel jdlLogin = new Login();
		final JPanel jpnlNewUser = new JPanel(new GridLayout(2,1));
		final JLabel jlblNewUser = new JLabel("Create New User");
		final JButton jbtnNewUser = new JButton();
		final Dimension button_dimensions = new Dimension(40, 30);
		Dimension preffered_label_dimension = null;
		
		Image scaled_image = null;
		
		String image_location = "/cs441project/src/images/adobestock_1991823999_productive.jpg";
		final String fileNameP = "adobestock_1991823999_productive.jpg";
		final String fileNameU = "new_user.jpg";
		JLabel jlblImage = new JLabel();
		BufferedImage image = null;
		
		JLabel jlblImage2 = new JLabel();
		BufferedImage image2 = null;
		GridBagConstraints c = new GridBagConstraints();
		
		// sets and adds image
		try {                
			
            System.out.println("Getting image:");
            
            java.net.URL url = getClass().getResource(fileNameP);
        	String relativePath = new String(url.getPath());
        	image = ImageIO.read(new File(relativePath));
			
			java.net.URL url2 = getClass().getResource(fileNameU);
        	String relativePath2 = new String(url2.getPath());	
			image2 = ImageIO.read(new File(relativePath2));
			   // sets the size of image so it is the same ratio
				double h = (double)image.getHeight();
				double w = (double)image.getWidth();
				double ratio = h/w;
				int hight = (int) ((ratio) * (panel_dimension.width - 15));
				int width = (panel_dimension.width - 15);
				
				preffered_label_dimension = new Dimension( (int) (width+10), 
						(int) (hight + 10));
				
				scaled_image =  image.getScaledInstance(width,hight, 
						BufferedImage.SCALE_DEFAULT); 
				
	    } catch (IOException e) {
	            System.out.println("Error: unable to add picture to login pane \n");
	    } catch (IllegalArgumentException e){
	    	System.out.println("Error: friends dont let friends devide by zero \n" +
	    			"\t\t hight is zero in image");
	    }
		
		jlblImage.setPreferredSize(preffered_label_dimension);

		jlblImage = new JLabel(new ImageIcon(scaled_image), JLabel.CENTER);
		jlblImage2 = new JLabel(new ImageIcon(image2), JLabel.CENTER);
		
		jlblNewUser.setFont(new Font("Sarif", Font.PLAIN, 20));
		jbtnNewUser.add(jlblImage2);
		jpnlNewUser.add(jlblNewUser);
		jpnlNewUser.add(jbtnNewUser);

		
		c.weightx = 1.0;   						//request any extra Horizontal space
		c.weighty = 0.25; 
		c.anchor = GridBagConstraints.SOUTH;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		c.gridwidth = 3;
		panel.add(jlblImage,c);
		
		// Sets up Login Button
		c.weightx = 0.25;   //request any extra Horizontal space
		c.weighty = 0.75;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.fill =  GridBagConstraints.VERTICAL;
		c.gridy = 0;
		c.gridwidth = 1;
		panel.add(jdlLogin,c);
		
		// Sets up New Button
		c.weightx = 0.5;   						//request any extra Horizontal space
		c.weighty = 0.75;   						//request any extra Horizontal space
		jbtnNewUser.setSize(button_dimensions);
		c.fill =  GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		panel.add(jpnlNewUser,c);
		
		// Sets up New Button
		c.weightx = 0.25;   						//request any extra Horizontal space
		c.weighty = 0.0;   						//request any extra Horizontal space
		c.fill =  GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		
		
		
		JLabel jlblOr = new JLabel(" -OR- ");
		jlblOr.setFont(new Font("Sarif", Font.BOLD, 20));
		panel.add(jlblOr,c);		
		//this.setVisible(true);
		panel.setPreferredSize(panel_dimension);
		
		jbtnNewUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("New User button pressd:");
            	//viewPanel = createUser();
            	cl.show(viewPanelCreate, "newUser");
            	viewPanelCreate.setVisible(true);
            }
        });
		
		
		return panel;	
	}
	
protected JPanel createUser(){
	System.out.println("Starting Create User:");
		final JPanel panel = new JPanel(new BorderLayout());
		final JPanel jdlInfo = new JPanel(new GridBagLayout());
		final JPanel jpnlDone = new JPanel(new GridLayout(1,2));
		final JLabel jlblName = new JLabel("User name:");
		final JTextField jtfldName = new JTextField();
		final JLabel jlblPass = new JLabel("Password:");
		final JPasswordField jpasPass = new JPasswordField();
		final JLabel jlblPassConfirm = new JLabel("Confirm Password:");
		final JPasswordField jpasConfirm = new JPasswordField();
		final JLabel jlblPM = new JLabel("Project Manager");
		final JLabel jlblCreateUser = new JLabel("      Create User Account");
		final JCheckBox jchbx = new JCheckBox();
		final JButton jbtnFinished = new JButton("Finished");
		final JButton jbtnCancel = new JButton("Cancel");
		
	
		GridBagConstraints c = new GridBagConstraints();
		
		jlblCreateUser.setFont(new Font("Sarif", Font.PLAIN, 20));
		
		//	 NAME FIELD
		c.weightx = 0.75;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		jdlInfo.add(jtfldName,c);
		
		// 	PASSWORD FIELD
		c.weightx = 0.75;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		jdlInfo.add(jpasPass,c);
		
		
		//	 	CONFIRM FIELD
		c.weightx = 0.75;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		jdlInfo.add(jpasConfirm,c);
		
		
			//	CREATE USER LABEL
		c.weightx = 1.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.NORTH;
		c.fill =  GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		jdlInfo.add(jlblCreateUser,c);
		
		
		
		//	USER NAME LABEL
		c.weightx = 0.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		jdlInfo.add(jlblName,c);
		
		// 	PASSWORD LABEL
		c.weightx = 0.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,15,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		jdlInfo.add(jlblPass,c);
		
		
		//	 	CONFIRM LABEL
		c.weightx = 0.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,15,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		jdlInfo.add(jlblPassConfirm,c);
		
		
		// 	PROJECT MANAGER LABEL
		c.weightx = 0.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,15,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		jdlInfo.add(jlblPM,c);
		
		
		// CHECK BOX
		c.weightx = 0.0;   						
		c.weighty = 0.01; 
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		jdlInfo.add(jchbx,c);
		
		jpnlDone.add(jbtnFinished);
		jpnlDone.add(jbtnCancel);
		
		
		c.weightx = 0.2;   						
		c.weighty = 0.1; 
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(5,5,5,5);	
		c.fill =  GridBagConstraints.NONE;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		jdlInfo.add(jpnlDone,c);
		
		jdlInfo.setPreferredSize(new Dimension (panel_dimension.width / 2,
				panel_dimension.height-10));
		panel.setPreferredSize(panel_dimension);
		panel.add(jdlInfo, BorderLayout.CENTER);
		
		
		jbtnFinished.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if ((Arrays.equals(
	                		jpasConfirm.getPassword(), jpasPass.getPassword())
	                		)){
	                	if (jtfldName.getText() != null){
	                		if (!jchbx.isSelected()){
	                			TeamMember user3 =new TeamMember(jtfldName.getText(),
            							new String(jpasPass.getPassword())
	                			);
	                			DbManager.getInstance().createUser(user3);
	                			//DbManager.getInstance().createUser(user);	                					
	                			 Gui.getInstance().loadTasks(user3);
	                		}else if (jchbx.equals(true)){
	                			System.out.println("dont have support for this yet");
	                		}
	                		System.out.println("Finished button pressd:");
	                    	
	                	}else{
	                		System.out.println("Have not field in all fields");
	                	}
	                }
	            }
	        });
		
		jbtnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Cancel button pressed:");
            	//viewPanel = createUser();
            	cl.show(viewPanelCreate, "start");
            	viewPanelCreate.setVisible(true);
            }
        });
		System.out.println("finished Create user:");
		 return panel;
	}



		
} 
 
