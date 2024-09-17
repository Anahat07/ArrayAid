
//Import external classes
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.JDesktopPane;

//This class displays the slides for the information that is taught to the user

public class ConceptsScreen extends JFrame implements ActionListener, KeyListener {

	// Labels & Images
	JLabel iconImage = new JLabel(new ImageIcon("Images/icon.png"));
	JLabel title = new JLabel("ArrayAid");

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

	// Array to store the panels for the information to be displayed
	private static JPanel[] infoPanelArray = new JPanel[11];
	// Accumulator variable used to keep track of the current visible panel
	private static int currentPanel = 0;
	// Progress bar
	JProgressBar progressBar = new JProgressBar(0, infoPanelArray.length - 1);

	// Buttons to display sample code --> stored in an array
	private static JButton[] sampleCodeButtons;

	// Buttons
	JButton continueButton = new JButton("CONTINUE");
	JButton helpButton = new JButton("HELP");
	JButton video1Button = new JButton("Video #1");
	JButton video2Button = new JButton("Video #2");

	// CONSTRUCTOR METHOD
	public ConceptsScreen() {

		// Setup frame
		setSize(1920, 1080);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add title --> to the frame
		title.setBounds(120, 10, 850, 100);
		title.setFont(new Font("Palatino", Font.PLAIN, 45));
		add(title);

		// Add icon image --> to the frame
		iconImage.setBounds(-370, 10, 900, 100);
		add(iconImage);

		// Setup the panels in the panels array
		for (int index = 0; index < infoPanelArray.length; index++) {
			infoPanelArray[index] = new JPanel();
			infoPanelArray[index].setBounds(45, 100, 1350, 500);
			LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
			infoPanelArray[index].setBorder(lineBorder);
			infoPanelArray[index].setLayout(null);
			infoPanelArray[index].setOpaque(false);
			// Only the first panel is shown initially
			infoPanelArray[index].setVisible(index == 0);
			// Add different content on each panel
			customizePanelContent(infoPanelArray[index], index);
			add(infoPanelArray[index]);
		}

		// Add the progress bar --> to the frame
		progressBar.setBounds(530, 620, 400, 30);
		progressBar.setValue(currentPanel);
		progressBar.setForeground(Color.decode("#B01717"));
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Palatino", Font.PLAIN, 20));
		add(progressBar);

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

		// Add continue button --> to the frame
		continueButton.setBounds(630, 670, 200, 45);
		continueButton.setFont(new Font("Palatino", Font.PLAIN, 30));
		continueButton.setOpaque(true);
		continueButton.setBackground(Color.decode("#B01717"));
		continueButton.setForeground(Color.decode("#F3D998"));
		continueButton.setBorder(new LineBorder(Color.BLACK, 3));
		continueButton.addActionListener(this);
		// Initially set the "Continue" button to be invisible
		continueButton.setVisible(false);
		add(continueButton);

		// Add help button --> to the frame
		helpButton.setBounds(1280, 10, 150, 60);
		helpButton.setFont(new Font("Palatino", Font.PLAIN, 45));
		helpButton.setOpaque(true);
		helpButton.setBackground(Color.decode("#B01717"));
		helpButton.setForeground(Color.decode("#F3D998"));
		helpButton.setBorder(new LineBorder(Color.BLACK, 3));
		helpButton.addActionListener(this);
		add(helpButton);

		// Add key listener to the frame
		addKeyListener(this);
		// Add the focus on the main frame
		this.setFocusable(true);
		this.requestFocusInWindow();

		// Show the frame
		setVisible(true);

	}

	// This method is used to add different content on each panel in the array
	private void customizePanelContent(JPanel infoPanels, int index) {

		// JLabel for all the different content
		JLabel content = new JLabel();
		content.setBounds(30, -20, 1300, 500);
		content.setFont(new Font("Palatino", Font.PLAIN, 30));

		// Each label is customized based on the index of the array
		switch (index) {
		case 0:
			content.setText("<html><div style='text-align: center;'> Hey " + MainMenu.nameEnter.getText() + "!<br>"
					+ "Let's learn about arrays!<br>"
					+ "Click on the arrows (left & right) on your keyboard to control the slides.</html>");
			content.setBounds(180, -20, 1900, 500);
			break;
		case 1:
			content.setText(
					"<html><div style='text-align: center;'>Click on the buttons below to view the 2 introductory videos:</html>");
			content.setBounds(280, -60, 1900, 500);

			// Add the buttons
			video1Button.setBounds(360, 230, 300, 45);
			video1Button.setFont(new Font("Palatino", Font.PLAIN, 30));
			video1Button.setOpaque(true);
			video1Button.setBackground(Color.decode("#B01717"));
			video1Button.setForeground(Color.decode("#F3D998"));
			video1Button.setBorder(new LineBorder(Color.BLACK, 3));
			video1Button.addActionListener(this);
			infoPanelArray[1].add(video1Button);

			// Add the buttons
			video2Button.setBounds(725, 230, 300, 45);
			video2Button.setFont(new Font("Palatino", Font.PLAIN, 30));
			video2Button.setOpaque(true);
			video2Button.setBackground(Color.decode("#B01717"));
			video2Button.setForeground(Color.decode("#F3D998"));
			video2Button.setBorder(new LineBorder(Color.BLACK, 3));
			video2Button.addActionListener(this);
			infoPanelArray[1].add(video2Button);

			break;
		case 2:
			content.setText("<html><div style='text-align: center;'><u><b>What are Data Structures?</u></b><br>"
					+ "Data structures are collections of related data items.<br>"
					+ "It is a piece of code structure that holds data in an organized manner and allows the user to store, retrieve, "
					+ "and work with data in a way that makes programs run smoothly.<br>"
					+ "An array is a type of data structure.</html>");
			break;
		case 3:
			content.setText("<html><div style='text-align: center;'><u><b>What is an Array?</u></b><br>"
					+ "Arrays are data structures that are essentially a list of data items of the same type.<br>"
					+ "They allow for the user to store and organize these items/elements under a single variable name.<br>"
					+ "They have a sequential order - starting at 0 and a set size/length.<br>"
					+ "They make handling a list of values more efficient within a Java program.<br>"
					+ "Arrays are objects.</html>");
			break;
		case 4:
			content.setText("<html><div style='text-align: center;'><u><b>What is an Array Element?</u></b><br>"
					+ "One data item in an array is known as an array element.<br> "
					+ "<u><b>What is an Array Index?</u></b><br>"
					+ "The position of one particular element in an array is known as its index.<br>"
					+ "The first element of each array is stored at index 0, second element is stored at index 1, and so on.<br>"
					+ "The index must be a non-negative integer.<br>" + "<u><b>What is an Array Length?</u></b><br>"
					+ "Every array knows its length - the number of elements in the array.</html>");
			break;
		case 5:
			content.setText("<html><div style='text-align: center;'><u><b>What is a Two-Dimensional Array?</u></b><br>"
					+ "This is a type of array that is often used to represent tables of values consisting of information arranged in rows and columns.<br> "
					+ "<u><b>What is an ArrayList?</u></b><br>"
					+ "This is a type of array that can grow or shrink to store a variable number of objects.</html>");
			// Example images & labels
			JLabel ex1Image = new JLabel(new ImageIcon("Images/2DExample.png"));
			ex1Image.setBounds(320, 30, 600, 110);
			content.add(ex1Image);
			JLabel ex1Label = new JLabel("2D-Array Example");
			ex1Label.setBounds(450, 10, 600, 110);
			ex1Label.setFont(new Font("Palatino", Font.PLAIN, 16));
			content.add(ex1Label);

			JLabel ex2Image = new JLabel(new ImageIcon("Images/ArrayListExample.png"));
			ex2Image.setBounds(320, 350, 600, 110);
			content.add(ex2Image);
			JLabel ex2Label = new JLabel("ArrayList Example");
			ex2Label.setBounds(550, 400, 600, 110);
			ex2Label.setFont(new Font("Palatino", Font.PLAIN, 16));
			content.add(ex2Label);
			break;
		case 6:
			content.setText("<html><div style='text-align: center;'><u><b>Declaring & Creating Arrays:</u></b><br>"
					+ "To create an array you need to use the keyword “new” along with square brackets to determine the number of elements in the array and the data type of the elements.</html>");
			content.setBounds(30, -330, 1300, 900);
			// Example image
			JLabel ex3Image = new JLabel(new ImageIcon("Images/DeclaringExample.png"));
			ex3Image.setBounds(300, 500, 700, 300);
			content.add(ex3Image);
			break;
		case 7:
			content.setText("<html><div style='text-align: center;'><u><b>Array Initialization:</u></b><br>"
					+ "An array initializer is a comma-separated list of values/expressions enclosed in brackets.<br>"
					+ "This initializes the elements in the array and the array length is determined by the number of elements in the initializer list.</html>");
			content.setBounds(30, -330, 1300, 900);
			// Example image
			JLabel ex4Image = new JLabel(new ImageIcon("Images/InitializationExample.png"));
			ex4Image.setBounds(335, 520, 630, 290);
			content.add(ex4Image);
			break;
		case 8:
			content.setText("<html><u><b>Code Examples:</u></b></html>");
			content.setBounds(550, -370, 1300, 900);
			// Example images and label
			JLabel ex5Image = new JLabel(new ImageIcon("Images/CodeEx1.png"));
			ex5Image.setBounds(0, 85, 750, 465);
			infoPanels.add(ex5Image);
			JLabel ex5ImageAns = new JLabel(new ImageIcon("Images/CodeEx1Ans.png"));
			ex5ImageAns.setBounds(725, 150, 750, 465);
			infoPanels.add(ex5ImageAns);

			JLabel ex5Label = new JLabel("Example #1");
			ex5Label.setBounds(620, 410, 600, 110);
			ex5Label.setFont(new Font("Palatino", Font.PLAIN, 23));
			infoPanels.add(ex5Label);
			break;
		case 9:
			content.setText("<html><u><b>Code Examples:</u></b></html>");
			content.setBounds(550, -370, 1300, 900);
			// Example images and label
			JLabel ex6Image = new JLabel(new ImageIcon("Images/CodeEx2.png"));
			ex6Image.setBounds(15, 85, 750, 465);
			infoPanels.add(ex6Image);
			JLabel ex6ImageAns = new JLabel(new ImageIcon("Images/CodeEx2Ans.png"));
			ex6ImageAns.setBounds(765, 150, 550, 215);
			infoPanels.add(ex6ImageAns);

			JLabel ex6Label = new JLabel("Example #2");
			ex6Label.setBounds(620, 390, 600, 110);
			ex6Label.setFont(new Font("Palatino", Font.PLAIN, 23));
			infoPanels.add(ex6Label);
			break;
		case 10:
			content.setText("<html><div style='text-align: center;'><u><b>Sample Code:</u></b><br>"
					+ "Click on the buttons below to view sample code problems.</html>");
			content.setBounds(280, -300, 1300, 900);

			// Initialize sampleCodeButtons array
			sampleCodeButtons = new JButton[3];

			for (int index2 = 0; index2 < sampleCodeButtons.length; index2++) {
				// Create the button
				JButton sampleButton = new JButton("Sample Problem " + (index2 + 1));
				sampleButton.setBounds(515, 200 + index2 * 60, 300, 45);
				sampleButton.setFont(new Font("Palatino", Font.PLAIN, 30));
				sampleButton.setOpaque(true);
				sampleButton.setBackground(Color.decode("#B01717"));
				sampleButton.setForeground(Color.decode("#F3D998"));
				sampleButton.setBorder(new LineBorder(Color.BLACK, 3));
				sampleButton.addActionListener(this);
				// Add the button to the array
				sampleCodeButtons[index2] = sampleButton;
				infoPanelArray[10].add(sampleCodeButtons[index2]);
			}

			break;
		}

		// Add the label to the panels
		infoPanels.add(content);

	}

	// This method allows the user to control the slides using the arrow keys on
	// their keyboard
	// It checks if a key was pressed on the keyboard
	@Override
	public void keyPressed(KeyEvent Key) {

		// Check if the right arrow key was pressed
		if (Key.getKeyCode() == 39) {
			// If the progress bar is already at its maximum, do nothing
			if (progressBar.getValue() == progressBar.getMaximum()) {
				return;
			}
			// Move to the next panel
			infoPanelArray[currentPanel].setVisible(false);
			currentPanel = (currentPanel + 1) % infoPanelArray.length;
			infoPanelArray[currentPanel].setVisible(true);
			// Update progress bar
			progressBar.setValue(currentPanel);
			// Update visibility of the "Continue" button
			updateButtonVisibility();
			// Check if the left arrow key was pressed
		} else if (Key.getKeyCode() == 37) {
			// If the progress bar is already at its minimum, do nothing
			if (progressBar.getValue() == progressBar.getMinimum()) {
				return;
			}
			// Move to the previous panel
			infoPanelArray[currentPanel].setVisible(false);
			currentPanel = (currentPanel - 1 + infoPanelArray.length) % infoPanelArray.length;
			infoPanelArray[currentPanel].setVisible(true);
			// Update progress bar
			progressBar.setValue(currentPanel);
		}

	}

	// This method updates the visibility of the "Continue" button
	private void updateButtonVisibility() {
		// Set the visibility of the "Continue" button to true if the user has reached
		// the last panel
		if (currentPanel == 10) {
			continueButton.setVisible(true);
		}
	}

	// This method is called when an action event occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// If "Help" button or helpMenuItem is clicked;
		if (event.getSource() == helpButton || event.getSource() == helpMenuItem) {
			// Open the help frame
			new HelpFrame();
			// Request focus on the main frame
			this.requestFocusInWindow();
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
			// If activityMenuItem is clicked or "Continue" button is clicked;
		} else if (event.getSource() == activityMenuItem || event.getSource() == continueButton) {
			// Open the activity screen
			setVisible(false);
			new ActivityScreen();
			// If quizMenuItem is clicked;
		} else if (event.getSource() == quizMenuItem) {
			// Open the activity screen
			setVisible(false);
			new QuizScreen();
		} else if (event.getSource() == video1Button) {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=ei_4Nt7XWOw"));
				} catch (IOException | URISyntaxException error) {
					error.printStackTrace();
				}
			}
			// Request focus on the main frame
			this.requestFocusInWindow();
		} else if (event.getSource() == video2Button) {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=Ri9ljAqm-is"));
				} catch (IOException | URISyntaxException error) {
					error.printStackTrace();
				}
			}
			// Request focus on the main frame
			this.requestFocusInWindow();
		} else {

			// If any sample button is clicked
			for (int i = 0; i < sampleCodeButtons.length; i++) {
				if (event.getSource() == sampleCodeButtons[i]) {
					// Create a new class to create frames to display information for the clicked
					// sample button
					new DisplaySampleCodeInfo(i + 1);
					break;
				}
			}
			// Request focus on the main frame
			this.requestFocusInWindow();
		}
	}

	// Not used
	@Override
	public void keyTyped(KeyEvent event) {
	}

	// Not used
	@Override
	public void keyReleased(KeyEvent event) {
	}

}