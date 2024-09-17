
//Import external classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

//This class creates the frames to display different information using the sample code buttons on the concepts frame
public class DisplaySampleCodeInfo extends JFrame implements ActionListener {

	// Panels
	JPanel contentPanel = new JPanel();

	// Labels
	JLabel sampleCodeDescription = new JLabel();

	// Buttons
	JButton backButton = new JButton("BACK");

	public DisplaySampleCodeInfo(int buttonIndex) {

		// Setup frame
		setSize(1000, 800);
		getContentPane().setBackground(Color.decode("#F3D998"));
		setLayout(null);
		setTitle("ArrayAid - Java Learning Hub");
		setIconImage(new ImageIcon("Images/logo.png").getImage());
		LineBorder lineBorder = new LineBorder(Color.decode("#B01717"), 5);
		getRootPane().setBorder(lineBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Setup panel
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(1000, 1420));
		contentPanel.setBackground(Color.decode("#F3D998"));
		add(contentPanel);

		// Add label to panel
		sampleCodeDescription.setBounds(180, -300, 600, 800);
		sampleCodeDescription.setFont(new Font("Palatino", Font.PLAIN, 17));
		sampleCodeDescription.setForeground(Color.decode("#B01717"));
		contentPanel.add(sampleCodeDescription);

		// Add button to panel
		backButton.setBounds(420, 1360, 120, 45);
		backButton.setFont(new Font("Palatino", Font.PLAIN, 30));
		backButton.setOpaque(true);
		backButton.setBackground(Color.decode("#B01717"));
		backButton.setForeground(Color.decode("#F3D998"));
		backButton.setBorder(new LineBorder(Color.BLACK, 3));
		backButton.addActionListener(this);
		contentPanel.add(backButton);

		// Use JScrollPane to handle scrolling
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		// Disable horizontal scroll bar
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Add the scroll pane to the frame
		setContentPane(scrollPane);

		// Set different information based on the buttonIndex
		switch (buttonIndex) {
		case 1:
			sampleCodeDescription.setText(
					"<html><div style='text-align: center;'>This application simulates the rolling of two dice.<br>"
							+ "This application simulates the rolling of two dice.<br>"
							+ "The application creates 2 random objects, one to simulate rolling the 1st die and the other to simulate rolling the 2nd die.<br>"
							+ "The sum of the two values is then calculated.<br>"
							+ "The application asks the user how many times to roll the dice and then it tallies the number of times each possible sum appears, storing the results in an array.<br>"
							+ "Next, it displays the final results in a table format with headings for the columns.</html>");

			// Image labels
			JLabel sample1img1 = new JLabel(new ImageIcon("Images/code1.png"));
			sample1img1.setBounds(70, 190, 824, 420);
			contentPanel.add(sample1img1);
			JLabel sample1img2 = new JLabel(new ImageIcon("Images/code2.png"));
			sample1img2.setBounds(100, 615, 760, 390);
			contentPanel.add(sample1img2);
			JLabel sample1imgAns = new JLabel(new ImageIcon("Images/code1Ans.png"));
			sample1imgAns.setBounds(100, 925, 760, 390);
			contentPanel.add(sample1imgAns);

			break;
		case 2:
			sampleCodeDescription.setText(
					"<html><div style='text-align: center;'>This application determines how many of the salespeople earned salaries in each of the following ranges; $200–299, $300–399, $400–499, $500–599, $600–699, $700–799, $800–899, $900–999, and $1000 and over.<br>"
							+ "The salespeople receive $200 per week plus 9% of their gross sales for that week.<br>"
							+ "The tallies are stored in an array and are put in a table to display the results.</html>");
			sampleCodeDescription.setBounds(180, -325, 600, 800);

			// Image labels
			JLabel sample2img1 = new JLabel(new ImageIcon("Images/code3.png"));
			sample2img1.setBounds(85, 30, 800, 600);
			contentPanel.add(sample2img1);
			JLabel sample2img2 = new JLabel(new ImageIcon("Images/code4.png"));
			sample2img2.setBounds(85, 460, 800, 600);
			contentPanel.add(sample2img2);
			JLabel sample2imgAns = new JLabel(new ImageIcon("Images/code3Ans.png"));
			sample2imgAns.setBounds(100, 945, 760, 390);
			contentPanel.add(sample2imgAns);

			break;
		case 3:
			sampleCodeDescription.setText(
					"<html><div style='text-align: center;'>A company has four salespeople (1 to 4) who sell five different products (1 to 5).<br>"
							+ "Once a day, each salesperson fills out a slip for each type of product sold.<br>"
							+ "Each slip contains the following; salesperson number, product number, and total dollar value of the product sold that day.<br>"
							+ "The application reads each slip (one at a time) and store the values in a matrix (2D array), with each row representing a salesperson and each column a product.<br>"
							+ "Then it calculates the total sales by each salesperson and the total amount sold for each product.<br>"
							+ "The results are displayed in a table.</html>");
			sampleCodeDescription.setBounds(80, -310, 800, 800);

			// Image labels
			JLabel sample3img1 = new JLabel(new ImageIcon("Images/code5.png"));
			sample3img1.setBounds(85, 120, 800, 600);
			contentPanel.add(sample3img1);
			JLabel sample3img2 = new JLabel(new ImageIcon("Images/code6.png"));
			sample3img2.setBounds(85, 600, 800, 600);
			contentPanel.add(sample3img2);
			JLabel sample3imgAns = new JLabel(new ImageIcon("Images/code5Ans.png"));
			sample3imgAns.setBounds(105, 1045, 760, 390);
			contentPanel.add(sample3imgAns);

			break;
		}

		// Makes the frame open in the middle of the computer screen
		setLocationRelativeTo(null);
		// Make the frame visible
		setVisible(true);

	}

	// This method is called when an action takes place
	@Override
	public void actionPerformed(ActionEvent event) {

		// If "Back" button or helpMenuItem is clicked;
		if (event.getSource() == backButton) {
			// Close the frame
			setVisible(false);
		}

	}

}