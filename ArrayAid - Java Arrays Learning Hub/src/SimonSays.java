
//Import External Classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//This class creates the screen for the Simon Says game
public class SimonSays extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("Simon Says");
	JLabel subtitle = new JLabel("Determine the answers to Simon's problems");
	JLabel simonHeading = new JLabel("<html><u>Simon Says...</u></html>");
	// Array for questions labels
	JLabel[] questionLabels = new JLabel[5];

	// TextArea for answers
	JTextArea[] textAreaArray = new JTextArea[5];
	// Array for "correct answer" headings
	JLabel[] correctHeading = new JLabel[5];
	// Array for correct answers
	JLabel[] correctAnswers = new JLabel[5];
	// Array for the checkmarks and crosses
	JLabel[] checkLabel = new JLabel[5];

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
	JMenuItem puzzleMenuItem = new JMenuItem("Puzzle Game");
	JMenuItem simonMenuItem = new JMenuItem("Simon Says Game");

	// CONSTRUCTOR METHOD
	public SimonSays() {

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
		title.setBounds(370, 25, 600, 100);
		title.setFont(new Font("Palatino", Font.BOLD, 50));
		add(title);

		// Add subtitle --> to main frame
		subtitle.setBounds(235, 70, 600, 100);
		subtitle.setFont(new Font("Palatino", Font.PLAIN, 28));
		add(subtitle);

		// Add simon says heading --> to main frame
		simonHeading.setBounds(100, 115, 200, 100);
		simonHeading.setFont(new Font("Palatino", Font.BOLD, 28));
		add(simonHeading);

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
		puzzleMenuItem.addActionListener(this);
		simonMenuItem.addActionListener(this);
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
		game.add(puzzleMenuItem);
		game.add(simonMenuItem);
		setJMenuBar(menuBar);

		// Add text areas to frame using a loop
		for (int index = 0; index < textAreaArray.length; index++) {
			textAreaArray[index] = new JTextArea();
			textAreaArray[index].setFont(new Font("Palatino", Font.PLAIN, 18));
			textAreaArray[index].setBounds(100, 87 * index + 220, 500, 20);
			add(textAreaArray[index]);

			// Add a JLabel for each question
			questionLabels[index] = new JLabel(getQuestions(index));
			questionLabels[index].setFont(new Font("Palatino", Font.PLAIN, 18));
			questionLabels[index].setBounds(100, 90 * index + 170, 850, 50);
			add(questionLabels[index]);

			// Add a JLabel for all the correct answers headings
			correctHeading[index] = new JLabel("<html><u>Correct Answer:</u></html>");
			correctHeading[index].setFont(new Font("Palatino", Font.BOLD, 16));
			correctHeading[index].setBounds(100, 93 * index + 215, 200, 50);
			correctHeading[index].setVisible(false);
			add(correctHeading[index]);

			// Add a JLabel for all the correct answers
			correctAnswers[index] = new JLabel(getCorrectAnswers(index));
			correctAnswers[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			correctAnswers[index].setBounds(225, 93 * index + 215, 400, 50);
			correctAnswers[index].setVisible(false);
			add(correctAnswers[index]);

			// Add a JLabel for the checkmarks and crosses
			checkLabel[index] = new JLabel();
			checkLabel[index].setBounds(900, 93 * index + 180, 50, 50);
			add(checkLabel[index]);
		}
		// Set specific positions for the labels as necessary
		correctHeading[0].setBounds(100, 230, 200, 50);
		correctHeading[1].setBounds(100, 318, 200, 50);

		correctAnswers[0].setBounds(225, 230, 400, 50);
		correctAnswers[1].setBounds(225, 318, 400, 50);

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Show the frame
		setVisible(true);

	}

	// This method is used to set the questions for each label index to display them
	private String getQuestions(int index) {
		String[] questions = { "1. Declare an integer array named 'scores' with a size of 5 in Java.",
				"2. Assign the value 10 to the third element of the 'scores' array that you previously created.",
				"3. Create a string array named 'names' with elements 'Alice', 'Bob', and 'Charlie'.",
				"4. Declare a 2D array named 'namesMatrix' with 3 rows and 4 columns in Java.",
				"5. Add the colour ‘Red’ to an ArrayList named ‘colours’." };
		return questions[index];
	}

	// This method is used to set each label index to display the correct answers
	private String getCorrectAnswers(int index) {
		String[] correctAnswers = { "int[] scores = new int[5];", "scores[2] = 10;",
				"String[] names = new String{\"Alice\", \"Bob\", \"Charlie\"};", "int[][] namesMatrix = new int[3][4];",
				"colours.add(\"Red\");" };
		return correctAnswers[index];
	}

	// This method is called when an action occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If backButton is clicked;
		if (event.getSource() == backButton) {
			// Close the help frame
			setVisible(false);
		} else if (event.getSource() == continueButton) {

			// Check each user-entered answer against the correct answer
			for (int index = 0; index < textAreaArray.length; index++) {
				// Make the correct heading and answers appear
				correctHeading[index].setVisible(true);
				correctAnswers[index].setVisible(true);
				String userAnswer = textAreaArray[index].getText().replaceAll("\\s+", "");
				String correctAnswer = (String) getCorrectAnswers(index).replaceAll("\\s+", "");
				// Check if the selected answer is correct
				if (userAnswer.equalsIgnoreCase(correctAnswer)) {
					// Add checkmark image next to the answer
					checkLabel[index].setIcon(new ImageIcon("Images/check.png"));
				} else {
					// Add cross image next to the answer
					checkLabel[index].setIcon(new ImageIcon("Images/cross.png"));
				}
			}

			// Make the continue button disappear and make the next button appear
			continueButton.setVisible(false);
			nextButton.setVisible(true);

		}

		// If next button clicked;
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
			// If puzzle game menu item is clicked;
		} else if (event.getSource() == puzzleMenuItem) {
			// Open the puzzle game window
			setVisible(false);
			new PuzzleGame();
			// If simon says menu item is clicked;
		} else if (event.getSource() == simonMenuItem) {
			// Open the simon says game window
			setVisible(false);
			new SimonSays();
		}

	}

}