import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class CreateAcc extends JFrame implements ActionListener{
	private static JFrame createAccF;

	//file IO variables
	private static String file = "accounts.txt";
	private static String[][] accounts = new String[2][1000];
	private String[] usernames;
	private String[] passwords;
	private static int numOfUsers;

	private JPanel loginInfo = new JPanel(new GridLayout(2,2));
	private JPanel buttons = new JPanel(new GridLayout(1,2));
	private JLabel username = new JLabel("USERNAME");
	private JLabel password = new JLabel("PASSWORD");
	private JTextField uText = new JTextField();
	private JPasswordField pText = new JPasswordField();
	private JButton enter = new JButton("ENTER");
	private JButton back = new JButton("BACK");


	public CreateAcc(String title) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		usernames = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		numOfUsers = usernames.length;
		for(int i = 0; i<usernames.length;i++) {
			accounts[0][i] = usernames[i];
			accounts[1][i] = passwords[i];
		}


		createAccF = new JFrame(title);
		createAccF.setLayout(new BorderLayout());
		loginInfo.add(username);
		loginInfo.add(uText);
		loginInfo.add(password);
		loginInfo.add(pText);
		back.addActionListener(this);
		enter.addActionListener(this);
		buttons.add(back);
		buttons.add(enter);
		createAccF.add(loginInfo, BorderLayout.CENTER);
		createAccF.add(buttons, BorderLayout.SOUTH);
		createAccF.setSize(640,480);
		createAccF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		createAccF.setLocationRelativeTo(null);
		createAccF.setVisible(true);
		in.close();
	}

	public void createUser(String username, String password){
		accounts[0][numOfUsers] = username;
		accounts[1][numOfUsers] = password;
		numOfUsers++;
	}

	public boolean isRegistered(String username) throws IOException{
		for(int i = 0; i<numOfUsers;i++) {
			if (accounts[0][i].equals(username)) {
				return true;
			}
		}
		return false;
	}

	public static void saveUsers() throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i<numOfUsers;i++) {
			out.write(accounts[0][i]);
			out.write(" ");
		}
		out.newLine();
		for (int i = 0; i<numOfUsers;i++){
			out.write(accounts[1][i]);
			out.write(" ");
		}
		out.close();
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == enter) {
			String user = uText.getText();
			String pass = pText.getText();
			try {
				if(isRegistered(user)) {
					uText.setText("");
					pText.setText("");
					JOptionPane.showMessageDialog(this, "Error: An account with this username already exists.");

				}
				else {
					createUser(user, pass);
					CreateAcc.saveUsers();
					createAccF.dispose();
					Login loginF = new Login("LOGIN");
				}

				if(e.getSource() == back) {
					createAccF.dispose();
					Login loginF = new Login("LOGIN");
				} 
			}catch (Exception e1) {
				System.out.println("Error with file IO");
			}
		}
	}

}
