
//Import external classes
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the screen for the quiz
public class QuizScreen extends JFrame implements ActionListener {

	// Panels
	JPanel contentPanel = new JPanel();

	// Labels
	JLabel title = new JLabel("Final Quiz");
	JLabel subtitle = new JLabel("<html>Time To Test Your Knowledge!<br>You have 15 minutes to finish.</html>");
	JLabel iconImage = new JLabel(new ImageIcon("Images/quizLogo.png"));

	// Buttons
	JButton helpButton = new JButton("HELP");
	JButton submitButton = new JButton("SUBMIT");

	// Two-dimensional array to store radio buttons for each question
	JRadioButton[][] questionRadioButtons = new JRadioButton[4][4];
	ButtonGroup[] questionButtonGroups = new ButtonGroup[4];
	// Two-dimensional array to store checkboxes for each question
	JCheckBox[][] questionCheckBoxes = new JCheckBox[2][4];
	// Array to store ComboBoxes for the true or false questions
	JComboBox<String>[] trueFalseComboBoxes = new JComboBox[4];
	// Arrays for the questions (right side of screen & left side of screen)
	JLabel[] rightSideQuestions = new JLabel[5];
	JLabel[] leftSideQuestions = new JLabel[5];
	// Questions headings
	JLabel multipleHeading = new JLabel("<html><u>Multiple Choice - Pick The Best Answer:</u></html>");
	JLabel multiple2Heading = new JLabel("<html><u>Multiple Choice - Pick All Answers That Apply:</u></html>");
	JLabel TFHeading = new JLabel("<html><u>True or False:</u></html>");

	// Initialize an ArrayList to store incorrect answers
	public static ArrayList<String> incorrectAnswersList = new ArrayList<>();
	// Accumulator to calculate the user's final results
	public static int correct = 0;

	// 15-minute timer
	private Timer timer = new Timer(900, this);
	private int timeRemainingInSeconds = 900;
	private JLabel timerLabel = new JLabel();

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

	// Sound effect
	private Clip timerSound;

	// CONSTRUCTOR METHOD
	public QuizScreen() {

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

		// Setup panel
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(1000, 1150));
		contentPanel.setBackground(Color.decode("#F3D998"));
		add(contentPanel);

		// Add title --> to the panel
		title.setBounds(570, 10, 350, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 65));
		contentPanel.add(title);

		// Add subtitle --> to main panel
		subtitle.setBounds(570, 80, 450, 100);
		subtitle.setFont(new Font("Palatino", Font.PLAIN, 30));
		contentPanel.add(subtitle);

		// Add subtitle --> to main panel
		multipleHeading.setBounds(100, 140, 450, 100);
		multipleHeading.setFont(new Font("Palatino", Font.BOLD, 20));
		contentPanel.add(multipleHeading);

		// Add subtitle --> to main panel
		multiple2Heading.setBounds(100, 540, 450, 100);
		multiple2Heading.setFont(new Font("Palatino", Font.BOLD, 20));
		contentPanel.add(multiple2Heading);

		// Add subtitle --> to main panel
		TFHeading.setBounds(100, 740, 450, 100);
		TFHeading.setFont(new Font("Palatino", Font.BOLD, 20));
		contentPanel.add(TFHeading);

		// Add icon image --> to the panel
		iconImage.setBounds(460, 20, 90, 105);
		contentPanel.add(iconImage);

		// Add the timer label to panel
		timerLabel.setBounds(15, 10, 300, 60);
		timerLabel.setFont(new Font("Palatino", Font.PLAIN, 25));
		timerLabel.setOpaque(true);
		timerLabel.setBackground(Color.decode("#B01717"));
		timerLabel.setForeground(Color.decode("#F3D998"));
		timerLabel.setBorder(new LineBorder(Color.BLACK, 3));
		contentPanel.add(timerLabel);

		// Start the timer & update the timer initially
		timer.start();
		updateTimerLabel();

		// Add help button --> to the panel
		helpButton.setBounds(1250, 10, 150, 60);
		helpButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		helpButton.setOpaque(true);
		helpButton.setBackground(Color.decode("#B01717"));
		helpButton.setForeground(Color.decode("#F3D998"));
		helpButton.setBorder(new LineBorder(Color.BLACK, 3));
		helpButton.addActionListener(this);
		contentPanel.add(helpButton);

		// Use JScrollPane to handle scrolling
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		// Disable horizontal scroll bar
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Add the scroll pane to the frame
		setContentPane(scrollPane);

		// Add submit button --> to panel
		submitButton.setBounds(570, 1060, 300, 60);
		submitButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		submitButton.setOpaque(true);
		submitButton.setBackground(Color.decode("#B01717"));
		submitButton.setForeground(Color.decode("#F3D998"));
		submitButton.setBorder(new LineBorder(Color.BLACK, 3));
		submitButton.addActionListener(this);
		contentPanel.add(submitButton);

		// Right & left side of screen questions set-up
		for (int index = 0; index < rightSideQuestions.length; index++) {
			// Add a JLabel for right side questions
			rightSideQuestions[index] = new JLabel(getRightQuestions(index));
			rightSideQuestions[index].setFont(new Font("Palatino", Font.ITALIC, 22));
			rightSideQuestions[index].setBounds(780, 200 * index + 200, 600, 50);
			contentPanel.add(rightSideQuestions[index]);

			// Add a JLabel for left side questions
			leftSideQuestions[index] = new JLabel(getLeftQuestions(index));
			leftSideQuestions[index].setFont(new Font("Palatino", Font.ITALIC, 22));
			leftSideQuestions[index].setBounds(100, 200 * index + 200, 600, 50);
			contentPanel.add(leftSideQuestions[index]);
		}
		// Set specific positions as needed
		leftSideQuestions[3].setBounds(100, 815, 600, 50);
		leftSideQuestions[4].setBounds(100, 930, 600, 50);
		rightSideQuestions[3].setBounds(780, 800, 600, 50);
		rightSideQuestions[4].setBounds(780, 930, 600, 50);

		// Loop through each question for the radiobuttons
		for (int index = 0; index < 4; index++) {
			questionButtonGroups[index] = new ButtonGroup();

			// Loop through each option for the current question
			for (int index2 = 0; index2 < 4; index2++) {
				questionRadioButtons[index][index2] = new JRadioButton();
				// questionRadioButtons[index][index2].setBounds(100, 235 + index2 * 25, 500,
				// 30);
				questionRadioButtons[index][index2].setFont(new Font("Palatino", Font.PLAIN, 20));
				contentPanel.add(questionRadioButtons[index][index2]);
				questionButtonGroups[index].add(questionRadioButtons[index][index2]);
			}
		}
		// Set the text for the radio buttons for the first question
		questionRadioButtons[0][0].setText("A type of array");
		questionRadioButtons[0][0].setBounds(100, 245, 500, 30);
		questionRadioButtons[0][1].setText("A piece of code structure that holds data in an organized manner");
		questionRadioButtons[0][1].setBounds(100, 270, 650, 30);
		questionRadioButtons[0][2].setText("A variable name for storing data");
		questionRadioButtons[0][2].setBounds(100, 295, 500, 30);
		questionRadioButtons[0][3].setText("A sequential order of elements");
		questionRadioButtons[0][3].setBounds(100, 320, 500, 30);

		// Set the text for the radio buttons for the second question
		questionRadioButtons[1][0].setText("To simulate rolling dice");
		questionRadioButtons[1][0].setBounds(780, 245, 500, 30);
		questionRadioButtons[1][1].setText("To represent tables of values");
		questionRadioButtons[1][1].setBounds(780, 270, 500, 30);
		questionRadioButtons[1][2].setText("To store a variable number of objects");
		questionRadioButtons[1][2].setBounds(780, 295, 500, 30);
		questionRadioButtons[1][3].setText("To determine salesperson numbers");
		questionRadioButtons[1][3].setBounds(780, 320, 500, 30);

		// Set the text for the radio buttons for the third question
		questionRadioButtons[2][0].setText("To determine the size of the array");
		questionRadioButtons[2][0].setBounds(100, 445, 500, 30);
		questionRadioButtons[2][1].setText("To store the elements of the array");
		questionRadioButtons[2][1].setBounds(100, 470, 500, 30);
		questionRadioButtons[2][2].setText("To represent the index of an array");
		questionRadioButtons[2][2].setBounds(100, 495, 500, 30);
		questionRadioButtons[2][3].setText("To calculate the sum of array elements");
		questionRadioButtons[2][3].setBounds(100, 520, 500, 30);

		// Set the text for the radio buttons for the fourth question
		questionRadioButtons[3][0].setText("It can grow or shrink");
		questionRadioButtons[3][0].setBounds(780, 445, 500, 30);
		questionRadioButtons[3][1].setText("It has a fixed size");
		questionRadioButtons[3][1].setBounds(780, 470, 500, 30);
		questionRadioButtons[3][2].setText("It always starts with index 0");
		questionRadioButtons[3][2].setBounds(780, 495, 500, 30);
		questionRadioButtons[3][3].setText("It is used for two-dimensional arrays");
		questionRadioButtons[3][3].setBounds(780, 520, 500, 30);

		// Loop through each question for the checkboxes
		for (int index = 0; index < 2; index++) {
			// Loop through each option for the current question
			for (int index2 = 0; index2 < 4; index2++) {
				questionCheckBoxes[index][index2] = new JCheckBox();
				// questionCheckBoxes[index][index2].setBounds(100, 235 + index2 * 25, 500, 30);
				questionCheckBoxes[index][index2].setFont(new Font("Palatino", Font.PLAIN, 20));
				contentPanel.add(questionCheckBoxes[index][index2]);
			}
		}
		// Set the text for the radio buttons for the first question
		questionCheckBoxes[0][0].setText("Always negative");
		questionCheckBoxes[0][0].setBounds(100, 645, 500, 30);
		questionCheckBoxes[0][1].setText("Non-negative integer");
		questionCheckBoxes[0][1].setBounds(100, 670, 500, 30);
		questionCheckBoxes[0][2].setText("Can be a floating-point number");
		questionCheckBoxes[0][2].setBounds(100, 695, 500, 30);
		questionCheckBoxes[0][3].setText("Corresponds to the value of the element");
		questionCheckBoxes[0][3].setBounds(100, 720, 500, 30);

		// Set the text for the radio buttons for the second question
		questionCheckBoxes[1][0].setText("Using square brackets");
		questionCheckBoxes[1][0].setBounds(780, 645, 500, 30);
		questionCheckBoxes[1][1].setText("With a comma-separated list of values");
		questionCheckBoxes[1][1].setBounds(780, 670, 500, 30);
		questionCheckBoxes[1][2].setText("Enclosed in curly brackets");
		questionCheckBoxes[1][2].setBounds(780, 695, 500, 30);
		questionCheckBoxes[1][3].setText("Using the keyword \"new\"");
		questionCheckBoxes[1][3].setBounds(780, 720, 500, 30);

		// Add ComboBoxes for the true or false questions
		for (int index = 0; index < 4; index++) {
			trueFalseComboBoxes[index] = new JComboBox<>(new String[] { "True", "False" });
			trueFalseComboBoxes[index].setBounds(100, 875 + index * 100, 100, 30);
			trueFalseComboBoxes[index].setFont(new Font("Palatino", Font.PLAIN, 20));
			contentPanel.add(trueFalseComboBoxes[index]);
		}
		trueFalseComboBoxes[2].setBounds(780, 870, 100, 30);
		trueFalseComboBoxes[3].setBounds(780, 990, 100, 30);

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

		// Add sound to the frame
		playTimerSound();

		// Show the frame
		setVisible(true);

	}

	// This method plays the sound effect when the timer begins
	private void playTimerSound() {
		// Try to open and play the introductory sound
		try {
			// Open an audio input stream
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("Sounds/QuizSound.wav"));
			// Get a sound clip resource
			timerSound = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream
			timerSound.open(audioIn);
			// Loop the sound to play forever
			timerSound.loop(Clip.LOOP_CONTINUOUSLY);
			// Start playing the clip
			timerSound.start();
			// Handle exceptions related to unsupported audio file, IO, and line
			// unavailability; print an error message if not found
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException error) {
			System.out.println("File not found!");
		}
	}

	// This method is used to set the questions on the left side of the frame
	private String getLeftQuestions(int index) {
		String[] leftQuestions = { "1. What is a data structure?", "3. What is the purpose of an array length in Java?",
				"5. What are characteristics of an array index?",
				"<html>7. A two-dimensional array is used to represent tables of values arranged in rows and columns.</html>",
				"9. Arrays in Java can only store elements of the same data type." };
		return leftQuestions[index];
	}

	// This method is used to set the questions on the right side of the frame
	private String getRightQuestions(int index) {
		String[] rightQuestions = { "2. What is the purpose of an ArrayList?",
				"4. How does an ArrayList differ from a regular array?", "6. How is array initialization done?",
				"8. Arrays in Java always start indexing from 1.",
				"<html>10. This is the proper way to initialize an array:<br> int[] numbers = {1, 2, 3, 4, 5};</html>" };
		return rightQuestions[index];
	}

	// This method is called when an action occurs
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
			// If "Continue" button is clicked;
		} else if (event.getSource() == submitButton) {
			// Check each answer and save incorrect ones
			checkAnswersAndSaveIncorrect();
			// Stop the timer sound effect
			timerSound.stop();
			// Open the quiz results screen
			setVisible(false);
			new QuizResults();
		}

		// When timer starts
		if (event.getSource() == timer) {
			// Update the time remaining
			timeRemainingInSeconds--;

			// Update the timer label with the remaining time
			updateTimerLabel();

			// Check if the time has run out
			if (timeRemainingInSeconds <= 0) {
				timer.stop();
				// Stop the timer sound effect
				timerSound.stop();
				// Show a dialog box indicating that the time is up
				JOptionPane.showMessageDialog(this, "Your time is up!! The computer will submit it for you now!",
						"TIME UP", JOptionPane.INFORMATION_MESSAGE);
				// Check each answer and save incorrect ones
				checkAnswersAndSaveIncorrect();
				// Open the quiz results screen
				setVisible(false);
				new QuizResults();
			}
		}

	}

	// This method updates the timer label
	private void updateTimerLabel() {
		// Format the time remaining in minutes and seconds
		int minutes = timeRemainingInSeconds / 60;
		int seconds = timeRemainingInSeconds % 60;

		// Update the timer label with the remaining time
		timerLabel.setText(String.format("    Time remaining: %02d:%02d", minutes, seconds));
	}

	// This method checks each answer that the user entered and saves the incorrect
	// ones onto an ArrayList
	private void checkAnswersAndSaveIncorrect() {

		// Check answers for the multiple choice (1 answer only)
		if (questionRadioButtons[0][1].isSelected())
			correct++;
		else
			incorrectAnswersList.add("Question 1");
		if (questionRadioButtons[1][2].isSelected())
			correct++;
		else
			incorrectAnswersList.add("Question 2");
		if (questionRadioButtons[2][0].isSelected())
			correct++;
		else
			incorrectAnswersList.add("Question 3");
		if (questionRadioButtons[3][0].isSelected())
			correct++;
		else
			incorrectAnswersList.add("Question 4");

		// Check answers for the multiple choice (multiple answers)
		// Question 5
		if (questionCheckBoxes[0][1].isSelected() && questionCheckBoxes[0][3].isSelected())
			correct++;
		else
			incorrectAnswersList.add("Question 5");
		// Question 6
		if (questionCheckBoxes[1][0].isSelected() && questionCheckBoxes[1][1].isSelected()
				&& questionCheckBoxes[1][3].isSelected()) {
			correct++;
		} else {
			incorrectAnswersList.add("Question 6");
		}

		// Check answers for the true or false
		// Question 7
		if (trueFalseComboBoxes[0].getSelectedItem().equals("True"))
			correct++;
		else
			incorrectAnswersList.add("Question 7");
		// Question 8
		if (trueFalseComboBoxes[2].getSelectedItem().equals("False"))
			correct++;
		else
			incorrectAnswersList.add("Question 8");
		// Question 9
		if (trueFalseComboBoxes[1].getSelectedItem().equals("True"))
			correct++;
		else
			incorrectAnswersList.add("Question 9");
		// Question 10
		if (trueFalseComboBoxes[3].getSelectedItem().equals("True"))
			correct++;
		else
			incorrectAnswersList.add("Question 10");
	}

}