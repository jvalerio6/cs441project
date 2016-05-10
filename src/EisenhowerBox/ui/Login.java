package EisenhowerBox.ui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import EisenhowerBox.*;


class Login extends JDialog implements ActionListener {

	private static final long serialVersionUID = 21L;

	private final JLabel jlblUsername = new JLabel("Username");
    private final JLabel jlblPassword = new JLabel("Password");

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final JButton jbtCancel = new JButton("Cancel");

    private final JLabel jlblStatus = new JLabel(" ");

    private DbManager dbm = new DbManager();
    List<TeamMember> memberList = dbm.getTeamMemberList();
    List<ProjectManager> managerList = dbm.getProjectManagerList();


    public Login() {

        this(null, true);
    }


    public Login(final JFrame parent, boolean modal) {
        super(parent, "User Login", modal);

        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(jlblUsername);
        p3.add(jlblPassword);

        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(jtfUsername);
        p4.add(jpfPassword);

        JPanel p1 = new JPanel();
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

        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        jbtOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//              if (Arrays.equals("password".toCharArray(), jpfPassword.getPassword())
//                        && "username".equals(jtfUsername.getText())) {
//                    parent.setVisible(true);
//                    setVisible(false);

            	String password = String.valueOf(jpfPassword.getPassword());

            	if ( isExistingUser(jtfUsername.getText(), password) ) {
            		parent.setVisible(true);
            	  	setVisible(false);
                } else {
                    jlblStatus.setText("Invalid username or password");
                }
            }
        });

        jbtCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.dispose();
                System.exit(0);
            }
        });
    }

	public void actionPerformed(ActionEvent arg0) {

	}

	public boolean isExistingUser(String username, String password) {
		boolean isExisting = false;

		for (ProjectManager pm : managerList) {
			if (pm.getName().equals(username) && pm.getPassword().equals(password)) {
				isExisting = true;
				break;
			}
		}

		if (!isExisting) {
			for (TeamMember tm : memberList) {
				if (tm.getName().equals(username) && tm.getPassword().equals(password)) {
					isExisting = true;
					break;
				}
			}
		}

		System.out.println("isExisting " + isExisting);

		return isExisting;
	}
}