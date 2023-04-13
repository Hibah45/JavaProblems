//created by Hibah Mohammed Ghouse and Nuha abdul Rasheed

package PDGAME;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//GUI implementation with the class PDGameApp-used this class to avoid confusion
//USING PDGameApp instead of PDGameGUI to avoid changing stuff in other java files.

@SuppressWarnings("serial")
public class PDGameApp extends JFrame implements ActionListener,ListSelectionListener {
	private final DefaultListModel<String> Model_List = new DefaultListModel<>();
	private final JList<String> CompletedGamesList;
	private final JTextField TextFieldComputerStrategy = new JTextField(10);
	private final JLabel compStrategy = new JLabel("Computer Strategy-Combo Box");
	private final JComboBox<Object> ComboBoxComputerStrategy;
	
	private int computerStrategy = 1;
	private final JLabel RoundDecision = new JLabel("What is your decision this round ? ");
	private final JTextArea TextAreaGameResults = new JTextArea(15, 30);
	private PDGame currentpdgm1 = null;
	private String gameStartTime = null;
	
	private final HashMap<String, GameStat> stats = new HashMap<>();
	
	private final JLabel label1 = new JLabel("Rounds Played");
	private final JTextField TextFieldRounds = new JTextField(10);
	
	private final JLabel label2 = new JLabel("Computer Strategy");
	
	private final JLabel label3 = new JLabel("Player Sentence");
	private final JTextField TextFieldPlayerSentence = new JTextField(10);
	
	private final JLabel label4 = new JLabel("Computer Sentence");
	private final JTextField TextFieldComputerSentence = new JTextField(10);
	
	private final JLabel label5 = new JLabel("Winner");
	private final JTextField TextFieldWinner = new JTextField(10);
	
	private final JButton startButton = new JButton("Start New Game");
	private final JButton remainSilentB = new JButton("Remain Silent");
	private final JButton testifyB = new JButton("Testify");
	private final int Game_Limit = 5;


public static void main(String[] args) {
	createAndShowGUI(); // set GUI of the game.
}


// Creation of object
public static void createAndShowGUI() {
	PDGameApp PDgame1Ptr = new PDGameApp();
	PDgame1Ptr.addListeners(); //Add listeners to buttons
	PDgame1Ptr.pack();
	PDgame1Ptr.setVisible(true); //set visibility to true
}
// Constructor is created to set up GUI.
public PDGameApp() {
	super("GAME : Prisoner's Dilemma ");
	currentpdgm1 = new PDGame("inputs.txt"); // The file is required to Read from Input File strategy when option is chosen
	Color c1 = new Color(0, 255, 125); //since Greater numbers =light colors
	Color c2 = new Color(255, 182, 173);
	super.setLayout(new BorderLayout());
	// Set up left panel
	JPanel PanelOne = new JPanel(new BorderLayout());
	super.add(PanelOne, BorderLayout.WEST); //add to frame
	CompletedGamesList = new JList<>(Model_List);
	CompletedGamesList.setFont(new Font("TimesNewRoman", Font.BOLD, 26));
	CompletedGamesList.setVisibleRowCount(4);
	CompletedGamesList.setFixedCellWidth(450);
	CompletedGamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	PanelOne.add(new JScrollPane(CompletedGamesList), BorderLayout.NORTH);
	PanelOne.setBackground(c2);

	//Create list in a scroll panel and set the background color.
	// Panel to display results of Games
	JPanel PanelTwo = new JPanel(); //Panel displaying results of the game.	Located on south of Panel one

	// adding required labels and text fields to the Panel
	PanelTwo.setLayout(new GridLayout(5, 2, 5, 5));
	PanelTwo.add(label1);
	PanelTwo.add(TextFieldRounds);
	TextFieldRounds.setEditable(false); // to prevent user from entering the data
	PanelTwo.add(label2);
	PanelTwo.add(TextFieldComputerStrategy);
	TextFieldComputerStrategy.setEditable(false);
	PanelTwo.add(label3);
	PanelTwo.add(TextFieldPlayerSentence);
	TextFieldPlayerSentence.setEditable(false);
	PanelTwo.add(label4);
	PanelTwo.add(TextFieldComputerSentence);
	TextFieldComputerSentence.setEditable(false);
	PanelTwo.add(label5);
	PanelTwo.add(TextFieldWinner);
	TextFieldWinner.setEditable(false);
	PanelTwo.setBackground(c2);

	// adding to the left panel
	PanelOne.add(PanelTwo, BorderLayout.SOUTH); // adding panel Two to the south of panel One.
	TitledBorder title;// This is used to set up the title border for panel One
	title = BorderFactory.createTitledBorder("List of Games");
	PanelOne.setBorder(title);

	//create panel Three for the right side
	JPanel PanelThree = new JPanel(new BorderLayout());
	super.add(PanelThree, BorderLayout.EAST);

	//Create panel Four to read and select the strategy from the user
	JPanel PanelFour = new JPanel();
	PanelFour.setLayout(new GridLayout(2, 1));
	JPanel PanelFive = new JPanel(new FlowLayout());
	PanelFive.add(compStrategy);
	
	// Combo box is created to save the strategies that user chooses
	Object[] strategyArray = currentpdgm1.getStrategiesList().toArray();
	ComboBoxComputerStrategy = new JComboBox<>(strategyArray);
	ComboBoxComputerStrategy.setEditable(false);
	ComboBoxComputerStrategy.setSelectedIndex(0);
	
	// Adding Combo Box and buttons
	PanelFive.add(ComboBoxComputerStrategy);
	PanelFive.add(startButton); // set up the background
	PanelFive.setBackground(c1);

	// panel six is used to create buttons for reading user decisions
	JPanel PanelSix = new JPanel(new FlowLayout());
	PanelSix.add(RoundDecision);
	PanelSix.setBackground(c1);
	PanelSix.add(remainSilentB);
	PanelSix.add(testifyB);
	
	// add panel 5 and panel6 to panel4
	PanelFour.add(PanelFive);
	PanelFour.add(PanelSix);
	PanelThree.add(PanelFour, BorderLayout.NORTH);
	PanelThree.add(new JScrollPane(TextAreaGameResults), BorderLayout.SOUTH);
	TextAreaGameResults.setEditable(false);
	testifyB.setEnabled(false);
	remainSilentB.setEnabled(false); // User can only select new game button
	startButton.setEnabled(true);
}


// Setting up Listeners for buttons, list, Combo Box
public void addListeners() {
	startButton.addActionListener(this);
	remainSilentB.addActionListener(this);
	testifyB.addActionListener(this);
	ComboBoxComputerStrategy.addActionListener(this);
	CompletedGamesList.addListSelectionListener(this);
}


@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == startButton) {
		startGame(); // starting new game
	} 
	else if (e.getSource() == remainSilentB) {
		cooperate(); // user decision
	}
    else if (e.getSource() == testifyB) {
		betray();
	}
    else if (e.getSource() == ComboBoxComputerStrategy) {
		computerStrategy = ComboBoxComputerStrategy.getSelectedIndex() + 1;
	}
}


// reading the item from the list and give responses
@Override
public void valueChanged(ListSelectionEvent e) {
	String searchKey;
	if (!CompletedGamesList.isSelectionEmpty()) {
		searchKey = (String) CompletedGamesList.getSelectedValue();
		GameStat gameStatsInfo;
		gameStatsInfo = stats.get(searchKey);
		TextFieldRounds.setText(Integer.toString(gameStatsInfo.getRoundsPlayedNum()));
		int playerSentenceYrs = gameStatsInfo.getUserYearsInPrison();
		TextFieldPlayerSentence.setText(String.format("%d %s",playerSentenceYrs,((playerSentenceYrs > 1) ? " years" : " year")));
		int compSentenceYrs = gameStatsInfo.getComputerYearsInPrison();
		TextFieldComputerSentence.setText(String.format("%d %s",compSentenceYrs,((compSentenceYrs > 1) ? " years" : " year")));
		String compStrategy = gameStatsInfo.getComputerStrategy();
		TextFieldComputerStrategy.setText(String.format("%s", compStrategy));
		String win = gameStatsInfo.getWinner();
		TextFieldWinner.setText(String.format("%s", win));
	}
}


// execute this when new button is clicked, create new game, get date then start new game here
public void startGame() {
	currentpdgm1 = new PDGame("inputs.txt");
	currentpdgm1.setStrategy(computerStrategy);
	gameStartTime = (new Date()).toString(); //Key in hash map
	TextAreaGameResults.append(" Prisoner's Dilemma \n");
	remainSilentB.setEnabled(true);
	testifyB.setEnabled(true);
	startButton.setEnabled(false);
	promptPlayer(); //prompting the user to get his decision.
}


//If user cooperates
public void cooperate() {
	String roundResult = currentpdgm1.playRound(1); // returns the result
	TextAreaGameResults.append(roundResult + "\n");
	if (currentpdgm1.getStats().getRoundsPlayedNum() >= Game_Limit) { //end game when rounds=5
		endGame();
	} else {
		promptPlayer();
	}
}

// If user betrays
public void betray() {
	String roundResult = currentpdgm1.playRound(2);
	TextAreaGameResults.append(roundResult + "\n");
	if (currentpdgm1.getStats().getRoundsPlayedNum() >= Game_Limit) { //end game when rounds=5
		endGame();
	} else {
		promptPlayer();
	}
}

//prompting user for decision
private void promptPlayer() {
	String promptString = "\n1. Remain silent." + "\n" + "2. Testify." + "\n\n" +"What is your decision this round?\n\n";
	TextAreaGameResults.append(promptString);
}



// Create updated list with statistics when round = 5
private void endGame() {
	String endScores = currentpdgm1.getScores();
	TextAreaGameResults.append(endScores + "\n"); //Displaying scores
	stats.put(gameStartTime, currentpdgm1.getStats());
	Model_List.addElement(gameStartTime);
	testifyB.setEnabled(false);
	remainSilentB.setEnabled(false); //User can only press new game button.
	startButton.setEnabled(true);
}
}
