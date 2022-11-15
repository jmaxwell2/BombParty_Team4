package lab5out_solution;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GamePlayPanel extends JPanel {

	// Private data fields for the important GUI components.
	private JTextField playerInput;
	private JLabel turnLabel;
	private JLabel threeLettersLabel;
	private JLabel timerLabel;
	private JLabel errorLabel;
	private List<String> playerList = Arrays.asList("jmaxwell","trottingwizard","player123","newboi");

	// Getter for the text in the playerInput field.
	public String getPlayerInput() {
		return playerInput.getText();
	}

	// Setter for the error text.
	public void setError(String error) {
		errorLabel.setText(error);
	}

	// Constructor for the GamePlayPanel.
	//public GamePlayPanel(GamePlayController gpc) {
	public GamePlayPanel() {
		// Create the controller and set it in the chat client.
		// GamePlayController controller = new GamePlayController(container, client);
		// client.setGamePlayController(controller);

		// Create a panel for the player name labels at the top of the GUI.
		JPanel playerListPanel = new JPanel(new GridLayout(1, playerList.size(), 5, 5));
		// create a label for every player
		for (int i = 0; i < playerList.size(); i++) {
			//JLabel playerNameLabel = new JLabel(playerList.get(i).getUsername(), JLabel.CENTER);
			JLabel playerNameLabel = new JLabel(playerList.get(i), JLabel.CENTER);
			playerListPanel.add(playerNameLabel);
		}
		
		// Create a panel for the TIMER label
		JPanel emptyPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		JLabel emptyLabel = new JLabel("", JLabel.CENTER);
		emptyPanel.add(emptyLabel);

		// Create a panel for the TURN label
		JPanel turnPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		turnLabel = new JLabel("It's YOUR turn!", JLabel.CENTER);
		turnLabel.setFont(new Font("Serif", Font.BOLD, 25));
		turnPanel.add(turnLabel);

		// Create a panel for the TIMER label
		JPanel timerPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		timerLabel = new JLabel("10 seconds", JLabel.CENTER);
		timerPanel.add(timerLabel);

		// Create a panel for the TIMER label
		JPanel infoPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		JLabel infoLabel = new JLabel("Type in a word with these letters: ", JLabel.CENTER);
		infoPanel.add(infoLabel);

		// panel that holds the three letters
		JPanel threeLettersPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		threeLettersLabel = new JLabel("IDL", JLabel.CENTER);
		threeLettersLabel.setFont(new Font("Serif", Font.BOLD, 20));
		threeLettersPanel.add(threeLettersLabel);

		// panel that holds the error message
		JPanel errorPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		errorPanel.add(errorLabel);

		// Create a panel for the input field information form.
		JPanel inputPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		playerInput = new JTextField(15);
		inputPanel.add(playerInput);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		JButton submitBttn = new JButton("Submit");
		// submitBttn.addActionListener(gpc);
		buttonPanel.add(submitBttn);

		// Arrange the panels in a grid.
		JPanel grid = new JPanel(new GridLayout(10, 1, 0, 10));
		grid.add(playerListPanel);
		grid.add(turnPanel);
		grid.add(emptyPanel);
		grid.add(timerPanel);
		grid.add(emptyPanel);
		grid.add(errorPanel);
		grid.add(infoPanel);
		grid.add(threeLettersPanel);
		grid.add(inputPanel);
		grid.add(buttonPanel);
		this.add(grid);
	}
	
	public static void main(String[] args)
	  {
	    System.out.println("hello bubba");
	    new GamePlayPanel();
	  }
	
}
