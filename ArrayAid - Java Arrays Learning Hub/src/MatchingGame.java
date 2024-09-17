
//Import external classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

//This class creates the screen for the matching game
public class MatchingGame extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("Matching Game");
	JLabel subtitle = new JLabel("Match all the terms to their correct definitions");
	JLabel termsHeading = new JLabel("<html><u>Terms</u></html>");
	JLabel defHeading = new JLabel("<html><u>Definitions</u></html>");
	// Array for definitions labels
	JLabel[] definitionLabels = new JLabel[5];
	// Array for "correct answer" headings
	JLabel[] correctHeading = new JLabel[5];
	// Array for correct answers
	JLabel[] correctAnswers = new JLabel[5];
	// Array for labels to display checkmarks or crosses
	JLabel[] checkLabels = new JLabel[5];

	// Buttons
	JButton backButton = new JButton("Back");
	JButton continueButton = new JButton("Continue");
	JButton nextButton = new JButton("Next");

	// Menu bar
	JMenuBar menuBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu window = new JMenu("Window");
	JMenu game = new JMenu("CodeCompass");
	// Menu items
	JMenuItem helpMenuItem = new JMenuItem("Help");
	JMenuItem quitMenuItem = new JMenuItem("Quit");
	JMenuItem mainMenuItem = new JMenuItem("Main Menu");
	JMenuItem conceptsMenuItem = new JMenuItem("Arrays Concepts");
	JMenuItem activityMenuItem = new JMenuItem("Activity - CodeCompass");
	JMenuItem quizMenuItem = new JMenuItem("Quiz");
	JMenuItem gameMenuItem = new JMenuItem("Instructions");
	JMenuItem matchingMenuItem = new JMenuItem("Matching Game");

	// ComboBox for answers
	String[] terms = { "Array Index", "Array Length", "Array Element", "Data Structures", "ArrayList" };
	JComboBox<String>[] comboBoxArray = new JComboBox[5];

	// CONSTRUCTOR METHOD
	public MatchingGame() {

		// Setup frame
		setSize(1000, 1080);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		// Frame border
		LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
		getRootPane().setBorder(lineBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add title --> to main frame
		title.setBounds(315, 25, 600, 100);
		title.setFont(new Font("Palatino", Font.BOLD, 50));
		add(title);

		// Add subtitle --> to main frame
		subtitle.setBounds(215, 70, 600, 100);
		subtitle.setFont(new Font("Palatino", Font.PLAIN, 28));
		add(subtitle);

		// Add terms heading --> to main frame
		termsHeading.setBounds(705, 150, 200, 100);
		termsHeading.setFont(new Font("Palatino", Font.BOLD, 28));
		add(termsHeading);

		// Add definitions heading --> to main frame
		defHeading.setBounds(200, 150, 200, 100);
		defHeading.setFont(new Font("Palatino", Font.BOLD, 28));
		add(defHeading);

		// Add back button --> to the frame
		backButton.setBounds(880, 10, 100, 50);
		backButton.setFont(new Font("Palatino", Font.PLAIN, 35));
		backButton.setOpaque(true);
		backButton.setBackground(Color.decode("#B01717"));
		backButton.setForeground(Color.decode("#F3D998"));
		backButton.setBorder(new LineBorder(Color.BLACK, 3));
		backButton.addActionListener(this);
		add(backButton);

		// Add continue button --> to the frame
		continueButton.setBounds(420, 650, 170, 50);
		continueButton.setFont(new Font("Palatino", Font.PLAIN, 35));
		continueButton.setOpaque(true);
		continueButton.setBackground(Color.decode("#B01717"));
		continueButton.setForeground(Color.decode("#F3D998"));
		continueButton.setBorder(new LineBorder(Color.BLACK, 3));
		continueButton.addActionListener(this);
		add(continueButton);

		// Add next button --> to the frame
		nextButton.setBounds(465, 650, 100, 50);
		nextButton.setFont(new Font("Palatino", Font.PLAIN, 35));
		nextButton.setOpaque(true);
		nextButton.setBackground(Color.decode("#B01717"));
		nextButton.setForeground(Color.decode("#F3D998"));
		nextButton.setBorder(new LineBorder(Color.BLACK, 3));
		nextButton.addActionListener(this);
		// Initially, set visibility as false
		nextButton.setVisible(false);
		add(nextButton);

		// Menu bar (menu items) action listeners
		helpMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		mainMenuItem.addActionListener(this);
		conceptsMenuItem.addActionListener(this);
		activityMenuItem.addActionListener(this);
		quizMenuItem.addActionListener(this);
		gameMenuItem.addActionListener(this);
		matchingMenuItem.addActionListener(this);
		// Add menu bar and menu items --> to the frame
		menuBar.setBackground(Color.decode("#B01717"));
		menuBar.add(file);
		menuBar.add(window);
		menuBar.add(game);
		file.add(helpMenuItem);
		file.add(quitMenuItem);
		window.add(mainMenuItem);
		window.add(conceptsMenuItem);
		window.add(activityMenuItem);
		window.add(quizMenuItem);
		game.add(gameMenuItem);
		game.add(matchingMenuItem);
		setJMenuBar(menuBar);

		// Add ComboBoxes to frame using a loop
		for (int index = 0; index < comboBoxArray.length; index++) {
			comboBoxArray[index] = new JComboBox<>(terms);
			comboBoxArray[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			comboBoxArray[index].addActionListener(this);
			comboBoxArray[index].setBounds(630, 80 * index + 230, 250, 50);
			add(comboBoxArray[index]);

			// Add a JLabel for each JComboBox definition
			definitionLabels[index] = new JLabel(getDefinition(index));
			definitionLabels[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			definitionLabels[index].setBounds(100, 80 * index + 230, 600, 50);
			add(definitionLabels[index]);

			// Add a JLabel for all the correct answers
			correctAnswers[index] = new JLabel(getCorrectAnswers(index));
			correctAnswers[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			correctAnswers[index].setBounds(225, 80 * index + 270, 600, 50);
			correctAnswers[index].setVisible(false);
			add(correctAnswers[index]);

			// Add a JLabel for all the correct answers headings
			correctHeading[index] = new JLabel("<html><u>Correct Answer:</u></html>");
			correctHeading[index].setFont(new Font("Palatino", Font.BOLD, 16));
			correctHeading[index].setBounds(100, 80 * index + 270, 600, 50);
			correctHeading[index].setVisible(false);
			add(correctHeading[index]);

			// Add a JLabel for the checkmarks and crosses
			checkLabels[index] = new JLabel();
			checkLabels[index].setBounds(900, 80 * index + 230, 50, 50);
			add(checkLabels[index]);
		}

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Show the frame
		setVisible(true);

	}

	// This method is used to set the definitions for each label index to display
	// them
	private String getDefinition(int index) {
		String[] definitions = { "One data item in an array is called an ____.",
				"The position of one particular element in an array is called ____.",
				"The number of elements in the array is called ____.",
				"<html>A type of array that can grow or shrink to store a variable number of<br> objects is called an ____.</html>",
				"Collections of related data items are called ____." };
		return definitions[index];
	}

	// This method is used to set each label index to display the correct answers
	private String getCorrectAnswers(int index) {
		String[] correctAnswers = { "Array Element", "Array Index", "Array Length", "ArrayList", "Data Structures" };
		return correctAnswers[index];
	}

	// This method is called when an action occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If backButton is clicked;
		if (event.getSource() == backButton) {
			// Close the frame
			setVisible(false);
		} else if (event.getSource() == continueButton) {

			// ComboBox options clicked
			for (int index = 0; index < comboBoxArray.length; index++) {
				correctAnswers[index].setVisible(true);
				correctHeading[index].setVisible(true);
				String selectedAnswer = (String) comboBoxArray[index].getSelectedItem();
				String correctAnswer = (String) getCorrectAnswers(index);

				// Check if the selected answer is correct
				if (selectedAnswer.equals(correctAnswer)) {
					// Add checkmark image next to the answer
					checkLabels[index].setIcon(new ImageIcon("Images/check.png"));
				} else {
					// Add cross image next to the answer
					checkLabels[index].setIcon(new ImageIcon("Images/cross.png"));
				}
			}
			// Make the continue button disappear and make the next button appear
			continueButton.setVisible(false);
			nextButton.setVisible(true);

		}
		// Repaint the frame to make the new labels visible
		repaint();

		if (event.getSource() == nextButton) {
			// Close the frame (goes back to activity frame)
			setVisible(false);
		}

		// If helpMenuItem is clicked;
		if (event.getSource() == helpMenuItem) {
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
			// If conceptsMenuItem is clicked;
		} else if (event.getSource() == conceptsMenuItem) {
			// Open the concepts screen
			setVisible(false);
			new ConceptsScreen();
			// If activityMenuItem is clicked;
		} else if (event.getSource() == activityMenuItem) {
			// Open the activity screen
			setVisible(false);
			new ActivityScreen();
			// If quizMenuItem or "Continue" button is clicked;
		} else if (event.getSource() == quizMenuItem) {
			// Open the quiz screen
			setVisible(false);
			new QuizScreen();
			// If game instructions button or menu item is clicked;
		} else if (event.getSource() == gameMenuItem) {
			// Open a new screen to display instructions
			new GameInstructions();
			// If matching game menu item is clicked;
		} else if (event.getSource() == matchingMenuItem) {
			// Open the matching game window
			setVisible(false);
			new MatchingGame();
		}

	}

}