
//Import external classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the screen for the activity
public class ActivityScreen extends JFrame implements ActionListener {

	// Labels
	JLabel logoImage = new JLabel(new ImageIcon("Images/compass.png"));
	JLabel title = new JLabel("CodeCompass");
	JLabel subTitle = new JLabel("Navigating Array Islands");
	JLabel mapImage = new JLabel(new ImageIcon("Images/gameMap.png"));

	// Panel
	JPanel mapPanel = new JPanel();

	// Buttons
	JButton helpButton = new JButton("HELP");
	JButton continueButton = new JButton("CONTINUE");
	JButton gameInstructions = new JButton("GAME INSTRUCTIONS");
	JButton diamond1 = new JButton(new ImageIcon("Images/diamond.png"));
	JButton diamond2 = new JButton(new ImageIcon("Images/diamond.png"));
	JButton diamond3 = new JButton(new ImageIcon("Images/diamond.png"));
	JButton coin = new JButton(new ImageIcon("Images/coin.png"));

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

	// Sound effect
	private Clip activitySound;

	// CONSTRUCTOR METHOD
	public ActivityScreen() {

		// Setup frame
		setSize(1920, 1080);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add logo image --> to main frame
		logoImage.setBounds(115, -170, 600, 580);
		add(logoImage);

		// Add title --> to main frame
		title.setBounds(540, 45, 600, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 80));
		add(title);

		// Add subtitle --> to main frame
		subTitle.setBounds(540, 120, 600, 100);
		subTitle.setFont(new Font("Palatino", Font.PLAIN, 45));
		add(subTitle);

		// Add map panel --> to main frame
		mapPanel.setBounds(300, 200, 800, 800);
		mapPanel.setOpaque(false);
		mapPanel.setLayout(null);
		add(mapPanel);

		// Add instructions button --> to panel
		gameInstructions.setBounds(270, 300, 300, 60);
		gameInstructions.setFont(new Font("Palatino", Font.PLAIN, 25));
		gameInstructions.setOpaque(true);
		gameInstructions.setBackground(Color.decode("#B01717"));
		gameInstructions.setForeground(Color.decode("#F3D998"));
		gameInstructions.setBorder(new LineBorder(Color.BLACK, 3));
		gameInstructions.addActionListener(this);
		mapPanel.add(gameInstructions);

		// Add continue button --> to main frame
		continueButton.setBounds(270, 300, 300, 60);
		continueButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		continueButton.setOpaque(true);
		continueButton.setBackground(Color.decode("#B01717"));
		continueButton.setForeground(Color.decode("#F3D998"));
		continueButton.setBorder(new LineBorder(Color.BLACK, 3));
		continueButton.addActionListener(this);
		// Initially set the "Continue" button to be invisible
		continueButton.setVisible(false);
		mapPanel.add(continueButton);

		// Add diamond buttons --> to panel
		diamond1.setBounds(198, 390, 120, 101);
		diamond1.setOpaque(false);
		diamond1.setBorderPainted(false);
		diamond1.addActionListener(this);
		// Initially set the "diamond 1" button to be invisible
		diamond1.setVisible(false);
		mapPanel.add(diamond1);

		diamond2.setBounds(530, 315, 120, 101);
		diamond2.setOpaque(false);
		diamond2.setBorderPainted(false);
		diamond2.addActionListener(this);
		// Initially set the "diamond 2" button to be invisible
		diamond2.setVisible(false);
		mapPanel.add(diamond2);

		diamond3.setBounds(240, 220, 120, 101);
		diamond3.setOpaque(false);
		diamond3.setBorderPainted(false);
		diamond3.addActionListener(this);
		// Initially set the "diamond 3" button to be invisible
		diamond3.setVisible(false);
		mapPanel.add(diamond3);

		// Add the coin button --> to panel
		coin.setBounds(365, 70, 150, 100);
		coin.setOpaque(false);
		coin.setBorderPainted(false);
		coin.addActionListener(this);
		// Initially set the "coin" button to be invisible
		coin.setVisible(false);
		mapPanel.add(coin);

		// Add map image --> to panel
		mapImage.setBounds(0, 0, 800, 600);
		mapPanel.add(mapImage);

		// Add help button --> to main frame
		helpButton.setBounds(1280, 10, 150, 60);
		helpButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		helpButton.setOpaque(true);
		helpButton.setBackground(Color.decode("#B01717"));
		helpButton.setForeground(Color.decode("#F3D998"));
		helpButton.setBorder(new LineBorder(Color.BLACK, 3));
		helpButton.addActionListener(this);
		add(helpButton);

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
		setJMenuBar(menuBar);

		// Add sound to the frame
		playActivitySound();

		// Show the frame
		setVisible(true);

	}

	// This method plays the sound effect when the game begins
	private void playActivitySound() {
		// Try to open and play the introductory sound
		try {
			// Open an audio input stream
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("Sounds/EscapeRoomSound.wav"));
			// Get a sound clip resource
			activitySound = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream
			activitySound.open(audioIn);
			// Loop the sound to play forever
			activitySound.loop(Clip.LOOP_CONTINUOUSLY);
			// Start playing the clip
			activitySound.start();
			// Handle exceptions related to unsupported audio file, IO, and line
			// unavailability; print an error message if not found
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException error) {
			System.out.println("File not found!");
		}
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
			// If quizMenuItem or "Continue" button is clicked;
		} else if (event.getSource() == quizMenuItem || event.getSource() == continueButton) {
			// Open the quiz screen
			setVisible(false);
			new QuizScreen();
			// Stop playing the clip
			activitySound.stop();
			// If game instructions button or menu item is clicked;
		} else if (event.getSource() == gameInstructions || event.getSource() == gameMenuItem) {
			// Open a new screen to display instructions
			new GameInstructions();
			// Make the instructions button disappear
			gameInstructions.setVisible(false);
			// Make the first diamond appear
			diamond1.setVisible(true);
			// If diamond 1 (blue diamond) button is clicked;
		} else if (event.getSource() == diamond1) {
			// Make the first game (Matching Game) screen
			new MatchingGame();
			// Make the second diamond appear
			diamond2.setVisible(true);
			// Add the first game to the menu bar to easy access in the future
			game.add(matchingMenuItem);
			// If diamond 2 (purple diamond) button is clicked;
		} else if (event.getSource() == diamond2) {
			// Make the second game (Puzzle Game) screen
			new PuzzleGame();
			// Make the third diamond appear
			diamond3.setVisible(true);
			// Add the second game to the menu bar to easy access in the future
			game.add(puzzleMenuItem);
			// If diamond 2 (purple diamond) button is clicked;
		} else if (event.getSource() == diamond3) {
			// Make the third game (Simon Says) screen
			new SimonSays();
			// Make the coin appear
			coin.setVisible(true);
			// Add the third game to the menu bar to easy access in the future
			game.add(simonMenuItem);
		} else if (event.getSource() == coin) {
			// Show a dialog box indicating successful completion of escape room
			JOptionPane.showMessageDialog(this,
					"Congratulations! You've successfully completed the escape room. Now, you can move on to the quiz!",
					"Escape Room Completed", JOptionPane.INFORMATION_MESSAGE);
			// Make the "Continue" button visible
			continueButton.setVisible(true);
		}

	}

}