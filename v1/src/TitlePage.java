/**
 * Title page for arcade program
 *
 * @author laiba
 *
 */
import javax.swing.*;
import java.awt.*;


public class TitlePage extends JFrame {

	TitlePage(){

		JLabel background;
		setSize(4000,4000);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("ArcadeGame1.jpg");
		img.setImage(img.getImage().getScaledInstance(1200,1200,Image.SCALE_DEFAULT));


		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(0,0,1200,700);
		add(background);
		setVisible(true);
		
		//Main Menu Button
		JButton b = new JButton("Main Menu");
		b.setBounds(550,300,150,80);
		add(b);

	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[]args) {
		TitlePage m = new TitlePage();
	
	}


}


