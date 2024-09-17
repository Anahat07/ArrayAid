/* ---------------------------------------------------------PROJECT HEADER-----------------------------------------------------------
 * 
 * NAME: Anahat Chhatwal
 * DATE: January 19, 2024
 * COURSE CODE: ICS3U1-01 Mr.Fernandes
 * TITLE: CAI Culminating - Anahat Chhatwal
 * 
 * DESCRIPTION: 
 * Basic Description -->
 * Computer-Assisted Instruction (CAI) is an interactive instructional technique whereby a computer is used to present the 
 * instructional material and monitor the learning that takes place. 
 * CAI uses a combination of text, graphics, sound and video in enhancing the learning process.
 * My CAI application will teach students about Arrays in Java programming.
 * 
 * Small Description for each screen -->
 * - Main Menu Screen -> Display title to user and user enters their name (so that it can be stored and they can check their past scores)
 * - Concepts Screen -> Display the educational content as "slides" for the user to read and learn. Also has videos and pictures as examples & sample code
 * - Activity Screen -> User reads game instructions and clicks on the diamonds (as they appear) to attempt each game (they get results & correct answers as well)
 * - Quiz Screen -> User has 15 minutes to answer 10 quiz questions about arrays (mix of radiobuttons, checkboxes & comboboxes)
 * - Quiz Results -> Display the questions that the user got wrong and their corresponding correct answers. Users can also gain more knowledge (hyperlinked website)
 *                   and check their past highest final score.
 * 
 * MAJOR SKILLS:
 * GUI Elements --> JFrame, ImageIcon, JTextArea, ActionListener, JButton, JPanel, Menu Bar, Progress Bar, KeyListener, RadiobButtons, CheckBoxes, 
 *                  ComboBoxes, Timer, JTable, JScrollPane, etc.
 * Other Elements --> Classes, Methods, Arrays, Objects, ArrayList, 2D-Array, Switch-Statements, For Loops, Try-Catch, Formatter, Scanner, 
 *                    if-else if-else Statements, etc.
 *
 * ADDED FEATURES:
 * Menu Bar, Videos (hyperlinked on my concepts screen), Images (sample code, examples, etc.), Sounds (on Activity Screen & Quiz Screen)
 * Past Final Scores (user can view their past final scores (highest one); saved with their name), Help screen, Game/Activity Instructions Screen,
 * Checking user answers and correcting them in both my Activity Screen & Quiz Results Screen, Progress Bar on Concepts Screen, Timer on Quiz Screen,
 * Final Score/Percentage & "Gain More Knowledge" button on Quiz Results Screen takes user to a website (hyperlinked) to learn more about arrays
 * 
 * AREAS OF CONCERN: 
 * - No major areas of concern. 
 * - However, sometimes when an empty name field or score gets added to the finalScore csv file, the past scores gives an error. This happens very 
 *   rarely though. If that happens, open the csv file and delete all elements in it; this should fix the error and restart the past scores list.
 * 
 */

//This class is the application that runs the CAI.
public class CAIApplication {

	// This method runs the programs
	public static void main(String[] args) {

		// This creates a new class called "MainMenu" which sets up the title screen
		// window and allows user to enter the CAI through it
		new MainMenu();

	}

}