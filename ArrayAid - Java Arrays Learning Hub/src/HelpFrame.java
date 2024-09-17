
//Import external classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the screen/window that provides user with assistance
public class HelpFrame extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("Instructions");
	JLabel helpDescription = new JLabel();

	// Back Button
	JButton backButton = new JButton("Back");

	// Menu bar
	JMenuBar menuBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu window = new JMenu("Window");
	// Menu items
	JMenuItem helpMenuItem = new JMenuItem("Help");
	JMenuItem quitMenuItem = new JMenuItem("Quit");
	JMenuItem mainMenuItem = new JMenuItem("Main Menu");
	JMenuItem conceptsMenuItem = new JMenuItem("Arrays Concepts");
	JMenuItem activityMenuItem = new JMenuItem("Activity - CodeCompass");
	JMenuItem quizMenuItem = new JMenuItem("Quiz");

	// CONSTRUCTOR METHOD
	public HelpFrame() {

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
		title.setBounds(140, 60, 850, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 60));
		add(title);

		// Add help description --> to the frame
		helpDescription.setBounds(30, -50, 550, 600);
		helpDescription.setText("<html>1. Enter your name on the title screen. <br>"
				+ "2. Go through the concepts screen and learn all about arrays! Make sure to study properly because you will be quizzed... <br>"
				+ "3. Move on to the activity screen and practice your arrays skills by finding your way out of an amazing escape room. <br>"
				+ "4. Lastly, take a quick quiz to test your knowledge on arrays and find resources to improve your skills! <br>"
				+ "Happy learning!! See you again soon!</html>");
		helpDescription.setFont(new Font("Palatino", Font.PLAIN, 20));
		add(helpDescription);

		// Menu bar (menu items) action listeners
		helpMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		mainMenuItem.addActionListener(this);
		conceptsMenuItem.addActionListener(this);
		activityMenuItem.addActionListener(this);
		quizMenuItem.addActionListener(this);
		// Add menu bar and menu items --> to the frame
		menuBar.setBackground(Color.decode("#B01717"));
		menuBar.add(file);
		menuBar.add(window);
		file.add(helpMenuItem);
		file.add(quitMenuItem);
		window.add(mainMenuItem);
		window.add(conceptsMenuItem);
		window.add(activityMenuItem);
		window.add(quizMenuItem);
		setJMenuBar(menuBar);

		// Add back button --> to the frame
		backButton.setBounds(210, 360, 150, 60);
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

	// This method is called when an action event occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If backButton is clicked;
		if (event.getSource() == backButton) {
			// Close the help frame
			setVisible(false);
			// If helpMenuItem is clicked;
		} else if (event.getSource() == helpMenuItem) {
			// Open the help frame
			new HelpFrame();
			// If quitMenuItem is clicked;
		} else if (event.getSource() == quitMenuItem) {
			// Close all open screens
			setVisible(false);
			// If mainMenuItem is clicked;
		} else if (event.getSource() == mainMenuItem) {
			// Open the main menu screen
			setVisible(false);
			new MainMenu();
			// If conceptsMenuItem;
		} else if (event.getSource() == conceptsMenuItem) {
			// Open the concepts screen
			setVisible(false);
			new ConceptsScreen();
			// If activityMenuItem is clicked;
		} else if (event.getSource() == activityMenuItem) {
			// Open the activity screen
			setVisible(false);
			new ActivityScreen();
			// If quizMenuItem is clicked;
		} else if (event.getSource() == quizMenuItem) {
			// Open the activity screen
			setVisible(false);
			new QuizScreen();
		}

	}

}