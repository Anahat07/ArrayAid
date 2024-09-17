
//Import external classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 * This is the GUI class. 
 * It sets up the window that holds the actual CAI.
 */
public class MainMenu extends JFrame implements ActionListener {

	// GUI ELEMENTS

	// Labels
	JLabel logoImage = new JLabel(new ImageIcon("Images/logo.png"));
	JLabel title = new JLabel("ArrayAid");
	JLabel subTitle = new JLabel("Java Learning Hub");
	JLabel nameLabel = new JLabel("Enter your name: ");

	// Text Area
	static JTextArea nameEnter = new JTextArea();

	// Buttons
	JButton continueButton = new JButton("CONTINUE");
	JButton helpButton = new JButton("HELP");
	JButton pastScores = new JButton("Past Scores");

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
	public MainMenu() {

		// Setup frame
		setSize(1920, 1080);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add logo image --> to main frame
		logoImage.setBounds(100, 5, 600, 580);
		add(logoImage);

		// Add title --> to main frame
		title.setBounds(220, 485, 400, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 80));
		add(title);

		// Add subtitle --> to main frame
		subTitle.setBounds(210, 555, 400, 100);
		subTitle.setFont(new Font("Palatino", Font.PLAIN, 45));
		add(subTitle);

		// Add name label to ask user to enter their name --> to main frame
		nameLabel.setBounds(750, 165, 500, 100);
		nameLabel.setFont(new Font("Palatino", Font.PLAIN, 60));
		add(nameLabel);

		// Add text area --> to main frame
		nameEnter.setBounds(735, 265, 500, 70);
		nameEnter.setFont(new Font("Palatino", Font.PLAIN, 55));
		nameEnter.setBackground(Color.decode("#B01717"));
		nameEnter.setForeground(Color.decode("#F3D998"));
		nameEnter.setBorder(new LineBorder(Color.BLACK, 3));
		add(nameEnter);

		// Add continue button --> to main frame
		continueButton.setBounds(835, 365, 300, 60);
		continueButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		continueButton.setOpaque(true);
		continueButton.setBackground(Color.decode("#B01717"));
		continueButton.setForeground(Color.decode("#F3D998"));
		continueButton.setBorder(new LineBorder(Color.BLACK, 3));
		continueButton.addActionListener(this);
		add(continueButton);

		// Add help button --> to main frame
		helpButton.setBounds(1280, 10, 150, 60);
		helpButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		helpButton.setOpaque(true);
		helpButton.setBackground(Color.decode("#B01717"));
		helpButton.setForeground(Color.decode("#F3D998"));
		helpButton.setBorder(new LineBorder(Color.BLACK, 3));
		helpButton.addActionListener(this);
		add(helpButton);

		// Add past scores button --> to main frame
		pastScores.setBounds(1280, 80, 150, 60);
		pastScores.setFont(new Font("Palatino", Font.PLAIN, 25));
		pastScores.setOpaque(true);
		pastScores.setBackground(Color.decode("#B01717"));
		pastScores.setForeground(Color.decode("#F3D998"));
		pastScores.setBorder(new LineBorder(Color.BLACK, 3));
		pastScores.addActionListener(this);
		add(pastScores);

		// Menu bar (menu items) action listeners
		helpMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		mainMenuItem.addActionListener(this);
		conceptsMenuItem.addActionListener(this);
		activityMenuItem.addActionListener(this);
		quizMenuItem.addActionListener(this);
		// Add menu bar and menu items --> to main frame
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

		// Show the frame
		setVisible(true);

	}

	// This method is called when an action event occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If "Help" button or helpMenuItem is clicked;
		if (event.getSource() == helpButton || event.getSource() == helpMenuItem) {
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
			// If conceptsMenuItem or "Continue" button is clicked;
		} else if (event.getSource() == conceptsMenuItem || event.getSource() == continueButton) {
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
			// Open the quiz screen
			setVisible(false);
			new QuizScreen();
		} else if (event.getSource() == pastScores) {
			// Open the past scores screen
			new PastScoresScreen();
		}

	}

}