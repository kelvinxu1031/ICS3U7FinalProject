import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ResetPass extends JFrame implements ActionListener{
	
	private static String file = "accounts.txt";
	private static String[][] accounts = new String[2][1000];
	private String[] usernames;
	private String[] passwords;
	private static int numOfUsers;
	private String user, pass, confirmPass;
	private static String message;
	private static JFrame resetPassF;
	private JPanel info = new JPanel(new GridLayout(3,2));
	private JPanel buttons = new JPanel(new GridLayout(1,2));
	private JLabel username = new JLabel("USERNAME");
	private JLabel password = new JLabel("NEW PASSWORD");
	private JLabel confirmPassword = new JLabel("CONFIRM PASSWORD");
	private JTextField uText = new JTextField();
	private JPasswordField pText = new JPasswordField();
	private JPasswordField confirmPassText = new JPasswordField();
	private JButton enter = new JButton("ENTER");
	private JButton back = new JButton("BACK");
	
	public ResetPass(String title) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(file));
		usernames = in.readLine().split(" ");
		passwords = in.readLine().split(" ");
		for(int i = 0; i<usernames.length;i++) {
			accounts[0][i] = usernames[i];
			accounts[1][i] = passwords[i];
		}
		numOfUsers = usernames.length;
		enter.addActionListener(this);
		back.addActionListener(this);
		resetPassF = new JFrame(title);
		resetPassF.setLayout(new GridLayout(2,1));
		info.add(username);
		info.add(uText);
		info.add(password);
		info.add(pText);
		info.add(confirmPassword);
		info.add(confirmPassText);
		buttons.add(enter);
		buttons.add(back);
		resetPassF.add(info);
		resetPassF.add(buttons);
		resetPassF.setSize(800,400);
		resetPassF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		resetPassF.setLocationRelativeTo(null);
		resetPassF.setVisible(true);
	}
	
	public String isValid(String user, String pass, String confirmPass) {//method returns a string to distinguish between errors
		int index = -1;
		for (int i = 0; i < numOfUsers;i++) {
			if(user.equals(accounts[0][i])) {
				index = i;
				break;
			}
		}
		if(index == -1) {
			return "username not found";
		}
		else if (accounts[1][index].equals(pass)) {
			return "same pass";
		}
		else if(!pass.equals(confirmPass)) {
			return "pass not equal";
		}
		else {
			return "valid";
		}
	}
	
	public void updateInfo(String user, String pass) throws IOException {
		for (int i = 0; i<numOfUsers;i++) {
			if(user.equals(accounts[0][i])) {
				accounts[1][i] = pass;
			}
		}
		saveUsers();
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
	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		try {
			if(e.getSource()==enter) {
				user = uText.getText();
				pass = pText.getText();
				confirmPass = confirmPassText.getText();
				message = isValid(user,pass,confirmPass);
				if(message.equals("username not found")) {
					uText.setText("");
					pText.setText("");
					confirmPassText.setText("");
					JOptionPane.showMessageDialog(this, "Error: No account is linked to this username");
				}
				else if(message.equals("same pass")){
					uText.setText("");
					pText.setText("");
					confirmPassText.setText("");
					JOptionPane.showMessageDialog(this, "Error: New password must be different from current password");
				}
				else if(message.equals("pass not equal")) {
					uText.setText("");
					pText.setText("");
					confirmPassText.setText("");
					JOptionPane.showMessageDialog(this, "Error: Password and confirm password are different");
				}
				else {
					updateInfo(user, pass);
					resetPassF.dispose();
					Login loginF = new Login("LOGIN");
				}
			}
			else {
				saveUsers();
				resetPassF.dispose();
				Login loginF = new Login("LOGIN");
			}
		}catch(Exception e1) {
			System.out.println("Error with file IO");
		}
		
	}
}
