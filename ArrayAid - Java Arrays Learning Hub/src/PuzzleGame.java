
//Import external classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the screen for the puzzle game
public class PuzzleGame extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("Puzzle Game");
	JLabel subtitle = new JLabel("Fill in the blanks to complete the puzzles");
	// Array for questions labels
	JLabel[] questionLabels = new JLabel[5];
	// Array to display labels of "Note" with some questions
	JLabel[] noteLabels = new JLabel[3];

	// TextArea for answers
	JTextArea[] textAreaArray = new JTextArea[9];
	// Array for "correct answer" headings
	JLabel[] correctHeading = new JLabel[5];
	// Array for correct answers
	JLabel[] correctAnswers = new JLabel[9];

	// Array for the checkmarks and crosses images
	JLabel[] checkLabel = new JLabel[9];

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

	// CONSTRUCTOR METHOD
	public PuzzleGame() {

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
		title.setBounds(340, 25, 600, 100);
		title.setFont(new Font("Palatino", Font.BOLD, 50));
		add(title);

		// Add subtitle --> to main frame
		subtitle.setBounds(235, 70, 600, 100);
		subtitle.setFont(new Font("Palatino", Font.PLAIN, 28));
		add(subtitle);

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
		setJMenuBar(menuBar);

		// Add the questions & correct answers heading label --> to the frame
		for (int index = 0; index < questionLabels.length; index++) {
			questionLabels[index] = new JLabel(getQuestion(index));
			questionLabels[index].setFont(new Font("Palatino", Font.PLAIN, 20));
			questionLabels[index].setBounds(260, 80 * index + 190, 600, 50);
			add(questionLabels[index]);

			// Add a JLabel for all the correct answers
			correctHeading[index] = new JLabel("<html><u>Correct Answer(s):</u></html>");
			correctHeading[index].setFont(new Font("Palatino", Font.BOLD, 16));
			correctHeading[index].setBounds(260, 80 * index + 230, 200, 50);
			correctHeading[index].setVisible(false);
			add(correctHeading[index]);

		}

		// Add the "Note" for the questions --> to the frame
		for (int index = 0; index < noteLabels.length; index++) {
			noteLabels[index] = new JLabel(getNote(index));
			noteLabels[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			noteLabels[index].setBounds(260, 80 * index + 230, 600, 50);
			add(noteLabels[index]);
		}
		// Set the position of the last note
		noteLabels[2].setBounds(260, 545, 400, 50);

		// Add TextAreas to frame using a loop
		for (int index = 0; index < textAreaArray.length; index++) {
			textAreaArray[index] = new JTextArea();
			textAreaArray[index].setFont(new Font("Palatino", Font.PLAIN, 20));
			add(textAreaArray[index]);
		}
		// Set the position of each TextArea to make them "blanks" in the sentence
		textAreaArray[0].setBounds(280, 205, 50, 20);
		textAreaArray[1].setBounds(555, 205, 50, 20);
		textAreaArray[2].setBounds(543, 285, 50, 20);
		textAreaArray[3].setBounds(280, 365, 80, 20);
		textAreaArray[4].setBounds(555, 365, 80, 20);
		textAreaArray[5].setBounds(280, 445, 100, 20);
		textAreaArray[6].setBounds(590, 445, 100, 20);
		textAreaArray[7].setBounds(658, 525, 30, 20);
		textAreaArray[8].setBounds(702, 525, 30, 20);

		// Add checkmarks & crosses to frame using a loop
		for (int index = 0; index < checkLabel.length; index++) {
			checkLabel[index] = new JLabel();
			add(checkLabel[index]);
		}
		// Set the position of each checkmark label
		checkLabel[0].setBounds(650, 190, 50, 50);
		checkLabel[1].setBounds(700, 190, 50, 50);
		checkLabel[2].setBounds(610, 270, 50, 50);
		checkLabel[3].setBounds(785, 350, 50, 50);
		checkLabel[4].setBounds(835, 350, 50, 50);
		checkLabel[5].setBounds(795, 430, 50, 50);
		checkLabel[6].setBounds(845, 430, 50, 50);
		checkLabel[7].setBounds(745, 510, 50, 50);
		checkLabel[8].setBounds(795, 510, 50, 50);

		// Add correct answers to the frame
		for (int index = 0; index < correctAnswers.length; index++) {
			// Add a JLabel for all the correct answers
			correctAnswers[index] = new JLabel(getCorrectAnswers(index));
			correctAnswers[index].setFont(new Font("Palatino", Font.PLAIN, 16));
			correctAnswers[index].setVisible(false);
			add(correctAnswers[index]);
		}
		// Set their location
		correctAnswers[0].setBounds(400, 245, 30, 20);
		correctAnswers[1].setBounds(435, 245, 30, 20);
		correctAnswers[2].setBounds(400, 325, 30, 20);
		correctAnswers[3].setBounds(400, 405, 60, 20);
		correctAnswers[4].setBounds(460, 405, 60, 20);
		correctAnswers[5].setBounds(400, 485, 80, 20);
		correctAnswers[6].setBounds(480, 485, 80, 20);
		correctAnswers[7].setBounds(400, 565, 30, 20);
		correctAnswers[8].setBounds(425, 565, 30, 20);

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Show the frame
		setVisible(true);

	}

	// This method is used to set the questions for each label index to display them
	private String getQuestion(int index) {
		String[] questions = { "1.            [] diceTallyArray = new            [13];",
				"2. int[] salariesArray = new int[           ];",
				"3.                  [] scoresArray = new                 {0.5, 6.7, 8.8, 9.2};",
				"4.                      <String> names = new                      <String>( );",
				"5. double[][] salesTotalMatrix = new double[      ][      ];" };
		return questions[index];
	}

	// This method is used to set the "Note" for the 3 questions that require it
	// "Note" allows the user to answer the question with ease (background knowledge
	// that is necessary to answer questions)
	private String getNote(int index) {
		String[] note = { "<html><u><b>Note:</u></b> the array holds the values of a dice roll (1-6).",
				"<html><u><b>Note:</u></b> the user wants the array to hold 9 total elements, starting at index 2.",
				"<html><u><b>Note:</u></b> the user wants 5 rows and 6 columns." };
		return note[index];
	}

	// This method is used to set the correct answers for each label index to
	// display them
	private String getCorrectAnswers(int index) {
		String[] correctAnswers = { "int", "int", "11", "double", "double", "ArrayList", "ArrayList", "5", "6" };
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

			// Make the "Note" label disappear
			for (int index = 0; index < noteLabels.length; index++) {
				noteLabels[index].setVisible(false);
			}

			// Set the correct headings as visible
			for (int index = 0; index < correctHeading.length; index++) {
				correctHeading[index].setVisible(true);
			}

			// Check each user-entered answer against the correct answer
			for (int index = 0; index < textAreaArray.length; index++) {
				correctAnswers[index].setVisible(true);
				String userAnswer = textAreaArray[index].getText().replaceAll("\\s+", "");
				String correctAnswer = (String) getCorrectAnswers(index).replaceAll("\\s+", "");
				// Check if the selected answer is correct
				if (userAnswer.equalsIgnoreCase(correctAnswer)) {
					// Add checkmark image next to answer
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
		}

	}

}