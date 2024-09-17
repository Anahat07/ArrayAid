
//Import external classes
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

//This class creates the screen/window that displays the high scores by player name
public class PastScoresScreen extends JFrame implements ActionListener {

	// JLabel
	JLabel titleLabel = new JLabel("Past Final Scores");

	// Buttons
	JButton backButton = new JButton("Back");

	// Create a JTable
	/*
	 * Sources: https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html
	 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
	 * https://www.geeksforgeeks.org/java-swing-jtable/
	 */
	// Data
	private DefaultTableModel tableModel;
	// Table
	JTable scoresTable = new JTable();

	// Constructor method
	public PastScoresScreen() {

		// Set up the frame
		setSize(600, 600);
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		getContentPane().setBackground(Color.decode("#F3D998"));
		// Frame border
		LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
		getRootPane().setBorder(lineBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a table model with columns "User" and "Final Score"
		tableModel = new DefaultTableModel();
		tableModel.addColumn("User");
		tableModel.addColumn("Final Score (in %)");
		// Add the columns to the Jtable created above
		scoresTable = new JTable(tableModel);

		// Scores table
		// Use JScrollPane to handle scrolling
		JScrollPane scrollPane = new JScrollPane(scoresTable);
		scrollPane.setBounds(15, 90, 560, 400);
		scrollPane.setBackground(Color.decode("#F3D998"));
		// Format the table
		scoresTable.setFont(new Font("Palatino", Font.PLAIN, 24));
		scoresTable.setBackground(Color.decode("#F3D998"));
		scoresTable.setForeground(Color.BLACK);
		scoresTable.setRowHeight(30);
		// Format table header
		scoresTable.getTableHeader().setFont(new Font("Palatino", Font.PLAIN, 24));
		scoresTable.getTableHeader().setBackground(Color.decode("#F3D998"));
		scoresTable.getTableHeader().setForeground(Color.BLACK);
		scoresTable.getTableHeader().setPreferredSize(new Dimension(scoresTable.getTableHeader().getWidth(), 50));
		scoresTable.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		// Set score table size as viewport
		scoresTable.setPreferredScrollableViewportSize(scoresTable.getPreferredSize());
		// Format viewport
		scrollPane.getViewport().setBackground(Color.decode("#F3D998"));
		// Add the table to the scroll pane
		scrollPane.setViewportView(scoresTable);
		// Add the scroll pane to the frame
		add(scrollPane);

		// Title label
		titleLabel.setBounds(120, 0, 500, 100);
		titleLabel.setFont(new Font("Palatino", Font.PLAIN, 50));
		add(titleLabel);

		// Format & add back button
		backButton.setBounds(255, 505, 80, 35);
		backButton.setOpaque(true);
		backButton.setBackground(Color.decode("#B01717"));
		backButton.setForeground(Color.decode("#F3D998"));
		backButton.setBorder(new LineBorder(Color.BLACK, 3));
		backButton.setFont(new Font("Palatino", Font.PLAIN, 25));
		backButton.addActionListener(this);
		add(backButton);

		// Load data from the CSV file
		loadDataFromCSV();

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Display the frame
		setVisible(true);

	}

	// This method loads the data from the high score CSV file
	/*
	 * Sources: https://www.youtube.com/watch?v=iJhtRJiv-iU
	 * https://www.youtube.com/watch?v=FqiyzchhscQ
	 */
	private void loadDataFromCSV() {

		// Try opening the file
		try {
			// Open & read the file
			Scanner inputFinalScore = new Scanner(new File("finalScore.csv"));
			// While the file has a next line
			while (inputFinalScore.hasNextLine()) {
				// Read the line
				String data = inputFinalScore.nextLine();
				// Split the entry into name and score
				String[] parts = data.split(",");
				// Get the player name part
				String userName = parts[0].trim();
				// Use Integer.valueOf to convert the score string to Integer
				double finalScore = Double.valueOf(parts[1].trim());
				// Update the table with user name and score
				updateTable(userName, finalScore);
			}
			// Close the file
			inputFinalScore.close();
		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		}

	}

	// This method adds a new score & user name to the table
	/*
	 * Sources: https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html
	 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
	 * https://www.geeksforgeeks.org/java-swing-jtable/
	 */
	private void updateTable(String userName, double finalScore) {

		// Check if a row with the same user name already exists
		for (int index = 0; index < tableModel.getRowCount(); index++) {
			if (tableModel.getValueAt(index, 0).equals(userName)) {
				// If the new score is higher, update the score
				if (finalScore > (double) tableModel.getValueAt(index, 1)) {
					tableModel.setValueAt(finalScore, index, 1);
				}
				return; // Exit the method after updating the score
			}
		}

		// If the user name is not found, add a new row
		tableModel.addRow(new Object[] { userName, finalScore });

	}

	// This method is called when an action event occurs
	@Override
	public void actionPerformed(ActionEvent event) {

		// if back button is clicked;
		if (event.getSource() == backButton) {
			// this frame should disappear
			setVisible(false);
		}

	}

}