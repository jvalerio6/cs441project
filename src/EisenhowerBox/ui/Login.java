package EisenhowerBox.ui;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import EisenhowerBox.DbManager;



class Login extends JPanel implements ActionListener {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 21L;
	
	private final JLabel jlblUsername = new JLabel("Username");
    private final JLabel jlblPassword = new JLabel("Password");

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final JButton jbtCancel = new JButton("Cancel");

    private final JLabel jlblStatus = new JLabel(" ");
    
    
    
    
    
    public Login() {
        //super(parent, "User Login", modal);

        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(jlblUsername);
        p3.add(jlblPassword);

        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(jtfUsername);
        p4.add(jpfPassword);

        JPanel p1 = new JPanel(new GridLayout(1, 2));
        p1.add(p3);
        p1.add(p4);

        JPanel p2 = new JPanel();
        p2.add(jbtOk);
        p2.add(jbtCancel);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        p5.add(jlblStatus, BorderLayout.NORTH);
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.0;   						//request any extra Horizontal space
		c.weighty = 0.25; 
		c.anchor = GridBagConstraints.SOUTH;
		c.insets = new Insets(15,15,15,15);
		c.ipadx = 10;
		c.ipady = 30;
		c.gridx = 0;
		c.fill =  GridBagConstraints.NONE;
		c.gridy = 1;
		c.gridwidth = 1;  
        this.add(p1, c);
        
        c.weightx = 0.25;   						//request any extra Horizontal space
		c.weighty = 0.1; 
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(15,15,15,15);
		c.ipadx = 20;
		c.ipady = 70;
		c.gridx = 0;
		c.fill =  GridBagConstraints.NONE;
		c.gridy = 2;
		c.gridwidth = 1;
        this.add(p5, c);
        //pack();
        //setLocationRelativeTo(null);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        jbtOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals("password".toCharArray(), jpfPassword.getPassword())
                        && "username".equals(jtfUsername.getText())) {
                    Gui.getInstance().loadTasks(
                    		DbManager.getInstance().getUser(
                    				jtfUsername.getText())
                    		);
                    setVisible(false);
                } else {
                    jlblStatus.setText("Invalid username or password");
                }
            }
        });
        
        jbtCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ((Window) getParent()).dispose();
                System.exit(0);
            }
        });
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}