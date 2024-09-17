
//Import external classes
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the screen to display the results of the quiz
public class QuizResults extends JFrame implements ActionListener {

	// Labels
	JLabel title = new JLabel("Quiz Results");
	JLabel resultsLabel = new JLabel();
	JLabel wrongQuestions = new JLabel();
	JLabel correctAnswersLabel = new JLabel();
	JLabel finalMessage = new JLabel();
	JLabel byeLabel = new JLabel();

	// Buttons
	JButton doneButton = new JButton("DONE");
	JButton helpButton = new JButton("HELP");
	JButton pastScores = new JButton("Past Scores");
	JButton resources = new JButton("Gain More Knowledge");

	// Variable for final score percentage
	public static double percentage = 0;
	// List to store all final scores
	private static ArrayList<String> finalScores = new ArrayList<>();

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

	// Correct answers array
	String[] correctAnswers = { "A piece of code structure that holds data in an organized manner",
			"To store a variable number of objects", "To determine the size of the array", "It can grow or shrink",
			"Non-negative integer & Corresponds to the value of the element",
			"Using square brackets, With a comma-separated list of values & Using the keyword \"new\"", "True", "False",
			"True", "True" };

	// CONSTRUCTOR METHOD
	public QuizResults() {

		// Setup frame
		setSize(1920, 1080);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		// Frame border
		LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
		getRootPane().setBorder(lineBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Load the csv file in the beginning
		loadFinalScore();

		// Add title --> to the frame
		title.setBounds(470, 25, 500, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 85));
		add(title);

		// Add the results display area
		resultsLabel.setFont(new Font("Palatino", Font.PLAIN, 23));
		resultsLabel.setBounds(570, 5, 500, 600);
		resultsLabel.setText(getResultsText());
		add(resultsLabel);

		// Add the "bye message" label
		byeLabel.setFont(new Font("Palatino", Font.ITALIC, 40));
		byeLabel.setBounds(545, 260, 500, 600);
		byeLabel.setText("See you later " + MainMenu.nameEnter.getText() + "!");
		add(byeLabel);

		// Add the wrong questions display area
		wrongQuestions.setFont(new Font("Palatino", Font.PLAIN, 23));
		wrongQuestions.setBounds(50, 80, 500, 600);
		wrongQuestions.setText(getWrongQuestions());
		add(wrongQuestions);

		// Add the correct answers display area
		correctAnswersLabel.setFont(new Font("Palatino", Font.PLAIN, 23));
		correctAnswersLabel.setBounds(940, 80, 450, 600);
		correctAnswersLabel.setText(getCorrectAnswers());
		add(correctAnswersLabel);

		// Add the final message display area
		finalMessage.setFont(new Font("Palatino", Font.ITALIC, 40));
		if (QuizScreen.incorrectAnswersList.size() == 0) {
			finalMessage.setText("Perfect!! Good Job!!");
			finalMessage.setBounds(540, -150, 500, 600);
		} else if (QuizScreen.incorrectAnswersList.size() > 0 && QuizScreen.incorrectAnswersList.size() < 5) {
			finalMessage.setText("Great Job!");
			finalMessage.setBounds(620, -150, 500, 600);
		} else if (QuizScreen.incorrectAnswersList.size() >= 5 && QuizScreen.incorrectAnswersList.size() < 9) {
			finalMessage.setText("Good Try! Better luck next time!!");
			finalMessage.setBounds(440, -150, 550, 600);
		}
		add(finalMessage);

		// Add done button --> to main frame
		doneButton.setBounds(610, 600, 200, 60);
		doneButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		doneButton.setOpaque(true);
		doneButton.setBackground(Color.decode("#B01717"));
		doneButton.setForeground(Color.decode("#F3D998"));
		doneButton.setBorder(new LineBorder(Color.BLACK, 3));
		doneButton.addActionListener(this);
		add(doneButton);

		// Add help button --> to main frame
		helpButton.setBounds(1270, 10, 150, 60);
		helpButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		helpButton.setOpaque(true);
		helpButton.setBackground(Color.decode("#B01717"));
		helpButton.setForeground(Color.decode("#F3D998"));
		helpButton.setBorder(new LineBorder(Color.BLACK, 3));
		helpButton.addActionListener(this);
		add(helpButton);

		// Add past scores button --> to main frame
		pastScores.setBounds(15, 10, 150, 60);
		pastScores.setFont(new Font("Palatino", Font.PLAIN, 25));
		pastScores.setOpaque(true);
		pastScores.setBackground(Color.decode("#B01717"));
		pastScores.setForeground(Color.decode("#F3D998"));
		pastScores.setBorder(new LineBorder(Color.BLACK, 3));
		pastScores.addActionListener(this);
		add(pastScores);

		// Add resources button --> to main frame
		resources.setBounds(560, 370, 280, 60);
		resources.setFont(new Font("Palatino", Font.PLAIN, 25));
		resources.setOpaque(true);
		resources.setBackground(Color.decode("#B01717"));
		resources.setForeground(Color.decode("#F3D998"));
		resources.setBorder(new LineBorder(Color.BLACK, 3));
		resources.addActionListener(this);
		add(resources);

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

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Show the frame
		setVisible(true);

	}

	// This method gets and displays the correct answers for each of the incorrect
	// questions
	private String getCorrectAnswers() {

		// Display correct answers for the questions that were answered incorrectly
		String correctAns = "<html><div style='text-align: center;'><b>Correct Answers for Incorrect Questions:</b><br>";

		if (QuizScreen.incorrectAnswersList.size() > 0) {
			for (String questionNumber : QuizScreen.incorrectAnswersList) {
				int index = Integer.parseInt(questionNumber.split(" ")[1]) - 1;
				correctAns += "<i>" + questionNumber + ": </i>" + correctAnswers[index] + "<br>";
			}
		} else
			correctAns += "<i>None!</i>";

		return correctAns;
	}

	// This method is used to get the question numbers that the user got wrong from
	// the quiz screen
	private String getWrongQuestions() {

		String wrongQuestion = "<html><div style='text-align: center;'><b>You Got These Questions Wrong:</b>";

		if (QuizScreen.incorrectAnswersList.size() > 0) {
			for (int index = 0; index < QuizScreen.incorrectAnswersList.size(); index++) {
				wrongQuestion += "<br><i>" + QuizScreen.incorrectAnswersList.get(index) + "</i>";
			}
		} else
			wrongQuestion += "<br><i>None!</i>";

		return wrongQuestion;
	}

	// This method displays the results
	private String getResultsText() {
		// Calculate the total percentage
		int totalQuestions = 10;
		int correctAnswers = totalQuestions - QuizScreen.incorrectAnswersList.size();
		percentage = (double) correctAnswers / totalQuestions * 100;

		// Save the final score on the csv file
		saveFinalScore();

		// Display results
		String results = "<html><div style='text-align: center;'>" + "<b>Total Questions:</b> " + totalQuestions
				+ "<br>" + "<b>Correct Answers:</b> " + correctAnswers + "<br>" + "<b>Incorrect Answers: </b>"
				+ QuizScreen.incorrectAnswersList.size() + "<br>" + "<b>Your Percentage:</b> "
				+ String.format("%.2f", percentage) + "%<br><br></html>";

		return results;
	}

	// This method saves the final score of the user on a csv file
	private void saveFinalScore() {

		// Clear the existing highScores list
		finalScores.clear();

		// Add the current game's score and user name to the array list
		finalScores.add(MainMenu.nameEnter.getText() + "," + percentage);

		// Try to open/create the file to store high scores
		try {
			// Open & write the file to store all high scores
			Formatter outputFinalScore = new Formatter(new FileWriter("finalScore.csv", true));
			// Add the current user's name and score to the file
			outputFinalScore.format("%s,%f%n", MainMenu.nameEnter.getText(), percentage);
			// Close the file
			outputFinalScore.close();
			// Error if it doesn't work
		} catch (IOException event) {
			event.printStackTrace();
		}

	}

	// This methods loads the final scores csv file
	private static void loadFinalScore() {

		// Try opening the file
		try {
			// Open & read the file
			Scanner inputFinalScore = new Scanner(new File("finalScore.csv"));
			// Clear the list before loading new names
			finalScores.clear();
			// While the file has a next line
			while (inputFinalScore.hasNextLine()) {
				// Read the line
				String entry = inputFinalScore.nextLine();
				// Add it to the array list
				finalScores.add(entry);
			}
			// Close the file
			inputFinalScore.close();
			// Error if it doesn't work
		} catch (FileNotFoundException event) {
			event.printStackTrace();
		}
	}

	// This method is called when an action occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If "Help" button or helpMenuItem is clicked;
		if (event.getSource() == helpButton || event.getSource() == helpMenuItem) {
			// Open the help frame
			new HelpFrame();
			// If "Done" button or quitMenuItem is clicked;
		} else if (event.getSource() == quitMenuItem || event.getSource() == doneButton) {
			// Close the screen
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
			// If quizMenuItem is clicked;
		} else if (event.getSource() == quizMenuItem) {
			// Open the quiz screen
			setVisible(false);
			new QuizScreen();
		} else if (event.getSource() == pastScores) {
			// Open the past scores screen
			new PastScoresScreen();
		} else if (event.getSource() == resources) {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop()
							.browse(new URI("https://www.simplilearn.com/tutorials/java-tutorial/arrays-in-java"));
				} catch (IOException | URISyntaxException error) {
					error.printStackTrace();
				}
			}
			// Request focus on the main frame
			this.requestFocusInWindow();
		}

	}

}