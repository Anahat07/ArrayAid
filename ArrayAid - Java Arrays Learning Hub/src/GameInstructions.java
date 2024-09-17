
//Import external classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the frame to display the instructions for the game in the activity screen
public class GameInstructions extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("CodeCompass Instructions");
	JLabel instructions = new JLabel();

	// Back Button
	JButton backButton = new JButton("BACK");

	// CONSTRUCTOR METHOD
	public GameInstructions() {

		// Setup frame
		setSize(600, 600);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		// Frame border
		LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
		getRootPane().setBorder(lineBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add title --> to the frame
		title.setBounds(20, 5, 850, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 45));
		add(title);

		// Add game instructions --> to the frame
		instructions.setBounds(30, -20, 550, 600);
		instructions.setText("<html>1. Read all the game instructions carefully. <br>"
				+ "2. Click on the first big, red diamond that will appear on the game map once you close the instructions. Finish the matching game that appears and gain access to the 2nd game.<br>"
				+ "3. Click on the second big, red diamond that will appear on the game map after the 1st game. Finish the puzzle game to gain access to the 3rd game.<br>"
				+ "4. Click on the third big, red diamond that will appear on the game map after the 2nd game. Finish the Simon Says game to finish the escape room.<br>"
				+ "5. Click on the coin that appears on the game map treasure after the 3rd game. Yay! You completed the escape room!!<br>"
				+ "If you need to review the game instructions again, you can find them in the menu bar under 'CodeCompass'.<br>"
				+ "As you finish each game, those will be added to the menu bar as well in case you want to re-visit them.</html>");
		instructions.setFont(new Font("Palatino", Font.PLAIN, 20));
		add(instructions);

		// Add back button --> to the frame
		backButton.setBounds(210, 490, 150, 60);
		backButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		backButton.setOpaque(true);
		backButton.setBackground(Color.decode("#B01717"));
		backButton.setForeground(Color.decode("#F3D998"));
		backButton.setBorder(new LineBorder(Color.BLACK, 3));
		backButton.addActionListener(this);
		add(backButton);

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Show the frame
		setVisible(true);

	}

	// This method is called when an action occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If backButton is clicked;
		if (event.getSource() == backButton) {
			// Close the help frame
			setVisible(false);
		}

	}

}