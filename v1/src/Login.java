import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This program allows the user to login to access the game, create an account, or reset a password
 * Author: Kelvin Xu
 *Date: June 7, 2022
 */
public class Login extends JFrame implements ActionListener{
	//DECLARATION OF VARIABLES

	//components for GUI
	private static JFrame     loginF;
	private JPanel            backgroundP;
	private JButton           enter;
	private JButton           createAcc;
	private JButton           resetPass;
	private JTextField        uText;
	private JPasswordField    pText;
	private JLabel            lblUser;
	private JLabel            lblPass;
	//file IO variables
	private final static int  CAP = 1000;
	private static String     file = "accounts.txt";
	private static String[][] accounts = new String[2][CAP];
	private String[]          usernames;
	private String[]          passwords;
	private static int        numOfUsers;
	public static String      currUser;
	public static String      currPass;
	//font files
	String fName = "fonts/titleFont.ttf";
	public Login(String title) throws Exception{
		//read input from "accounts.txt"
		BufferedReader in = new BufferedReader(new FileReader(file));
		usernames = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		numOfUsers = usernames.length;
		for (int i = 0; i<numOfUsers;i++) {
			accounts[0][i] = usernames[i];
			accounts[1][i] = passwords[i];
		}

		//instantiating components for GUI
		loginF    = new JFrame(title);
		backgroundP = new JPanel();
		lblUser   = new JLabel("Username: ");
		lblUser.setBounds(100,100, 100,25);
		lblPass   = new JLabel("Password:");
		lblPass.setBounds(100,200,100,25);
		enter     = new JButton("ENTER");
		enter.setBounds(100,300,200,75);
		createAcc = new JButton("CREATE ACCOUNT");
		createAcc.setBounds(300,300,200,75);
		resetPass = new JButton("RESET PASSWORD");
		resetPass.setBounds(500,300,200,75);
		uText     = new JTextField();
		uText.setBounds(300,100,400,25);
		pText     = new JPasswordField();
		pText.setBounds(300,200,400,25);

		//set the layout for the background

		//add functionality to buttons
		createButton(enter);
		createButton(createAcc);
		createButton(resetPass);

		//adding components to JFrame
		backgroundP.setLayout(null);
		backgroundP.setSize(800,400);
		backgroundP.add(lblUser);
		backgroundP.add(uText);
		backgroundP.add(lblPass);
		backgroundP.add(pText);
		backgroundP.add(resetPass);
		backgroundP.add(createAcc);
		backgroundP.add(enter);
		loginF.add(backgroundP);
		loginF.setSize(800,400);
		loginF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginF.setLayout(null);
		loginF.setLocationRelativeTo(null);
		loginF.setVisible(true);
		in.close();
	}

	public boolean isUser(String user, String pass) {
		for (int i = 0; i<numOfUsers;i++) {
			if (accounts[0][i].equals(user.trim()) && accounts[1][i].equals(pass.trim())) {
				return true;
			}
		}
		return false;
	}

	public void createButton(JButton b) throws Exception{
		//import fonts
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fName)).deriveFont(12f);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(font);
		b.addActionListener(this);
		b.setFont(font);
	}
	public boolean foundUser(String user) {
		for (int i = 0; i<numOfUsers;i++) {
			if (accounts[0][i].equals(user.trim())){
				return true;
			}
		}
		return false;
	}

	public static String getUser() {
		return currUser;
	}

	public void setUser(String user) {
		currUser = user;
	}
	public static String getPass() {
		return currPass;
	}

	public void setPass(String pass) {
		currPass = pass;
	}


	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		try {
			if (e.getSource() == enter) {
				String user = uText.getText();
				String pass = pText.getText();
				if(isUser(user,pass)) {
					setUser(user);
					setPass(pass);
					JOptionPane.showMessageDialog(this, "Access Granted!");
					loginF.dispose();
					JFrame mainMenuF = new MainMenu("WELCOME TO THE ARCADE");
				}
				else if(foundUser(user)) {
					uText.setText("");
					pText.setText("");
					JOptionPane.showMessageDialog(this, "Error: Incorrect password");
				}
				else {
					uText.setText("");
					pText.setText("");
					JOptionPane.showMessageDialog(this, "Error: Account not created with this username");
				}

			}
			if(e.getSource() == createAcc) {
				loginF.dispose();
				JFrame createAccF = new CreateAcc("CREATE ACCOUNT");
			}
			if(e.getSource() == resetPass) {
				System.out.println("resetpassbuttonclicked");
				loginF.dispose();
				JFrame resetPassF = new ResetPass("RESET PASSWORD");
			}
		}
		catch (IOException e1) {
			System.out.println("Error with file io!");
		}
	}

}
