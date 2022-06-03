import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class MainMenu extends JFrame implements ActionListener{
	private static JFrame mainMenuF;
	private JPanel titlePanel = new JPanel(new BorderLayout());
	private JPanel options = new JPanel(new GridLayout(3,2));
	private JLabel title = new JLabel("WELCOME TO THE ARCADE");
	private JButton play = new JButton("PLAY");
	private JButton stats = new JButton("STATS");
	private JButton leaderboard = new JButton("LEADERBORDS");
	private JButton howToPlay = new JButton("HOW TO PLAY");
	private JButton logout = new JButton("LOGOUT");
	private JButton exit = new JButton("EXIT");
	private JLabel user = new JLabel("USER: " + Login.getUser());
	public MainMenu(String title) {
		mainMenuF = new JFrame(title);
		mainMenuF.setLayout(new GridLayout(2,1));
		titlePanel.add(this.title, BorderLayout.CENTER);
		titlePanel.add(user, BorderLayout.EAST);
		play.addActionListener(this);
		stats.addActionListener(this);
		leaderboard.addActionListener(this);
		howToPlay.addActionListener(this);
		logout.addActionListener(this);
		exit.addActionListener(this);
		options.add(play);
		options.add(stats);
		options.add(howToPlay);
		options.add(leaderboard);
		options.add(logout);
		options.add(exit);
		mainMenuF.add(titlePanel);
		mainMenuF.add(options);
		mainMenuF.setSize(800,400);
		mainMenuF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainMenuF.setLocationRelativeTo(null);
		mainMenuF.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			System.exit(0);
		}
		else {
			mainMenuF.dispose();
			try {
				JFrame loginF = new Login("LOGIN");
			} catch (IOException e1) {
				System.out.println("Error with file IO");
			}
		}
		
	}
}
