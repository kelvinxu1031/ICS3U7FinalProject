import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Login extends JFrame implements ActionListener{
	private static JFrame loginF;
	private JPanel top = new JPanel(new GridLayout(2,2));
	private JPanel bottom = new JPanel(new GridLayout(1,3));
	private JButton enter = new JButton("Enter");
	private JButton createAcc = new JButton("Create Account");
	private JButton resetPass = new JButton("Reset Password");
	private JTextField uText = new JTextField();
	private JPasswordField pText = new JPasswordField();
	
	private static String file = "accounts.txt";
	private static String[][] accounts = new String[2][1000];
	private String[] usernames;
	private String[] passwords;
	private static int numOfUsers;
	public static String currUser;
	public static String currPass;
	
	public Login(String title) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(file));
		usernames = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		numOfUsers = usernames.length;
		for (int i = 0; i<numOfUsers;i++) {
			accounts[0][i] = usernames[i];
			accounts[1][i] = passwords[i];
		}
		
		loginF = new JFrame(title);
		loginF.setLayout(null);
		enter.addActionListener(this);
		createAcc.addActionListener(this);
		top.add(new JLabel("Username:"));
		top.add(uText);
		top.add(new JLabel("Password:"));
		top.add(pText);
		bottom.add(createAcc);
		bottom.add(enter);
		loginF.add(top, BorderLayout.CENTER);
		top.setBounds(0,0,800,300);
		loginF.add(bottom, BorderLayout.SOUTH);
		bottom.setBounds(0,300, 800, 100);
		loginF.setSize(800,400);
		loginF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginF.setLocationRelativeTo(null);
		loginF.setVisible(true);
	}

	public boolean isUser(String user, String pass) {
		for (int i = 0; i<numOfUsers;i++) {
			if (accounts[0][i].equals(user.trim()) && accounts[1][i].equals(pass.trim())) {
				return true;
			}
		}
		return false;
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
				JOptionPane.showMessageDialog(this, "Error: Incorrect username and password combination");
			}
			
		}
		if(e.getSource() == createAcc) {
			loginF.dispose();
			try {
				JFrame createAccF = new CreateAcc("CREATE ACCOUNT");
			} catch (IOException e1) {
				System.out.println("Error with file io!");
			}
		}
	}

}