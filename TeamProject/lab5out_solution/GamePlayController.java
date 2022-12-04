package lab5out_solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.JPanel;

public class GamePlayController implements ActionListener {

	// this class needs from the server:
	// an array of the players (within the player class is a bool that determines
	// whose turn it is)
	// Timer() object

	// methods:
	// playerSwitch that the server calls in order for the Panel to know what
	// player's turn it is
	// verify word
	// get three letters

	// Private data fields for the container and chat client.
	private JPanel container;
	private ChatClient client;

	// Constructor for the GamePlay controller.
	public GamePlayController(JPanel container, ChatClient client) {
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// click the submit button
		// verify whether their input word contains the three letters locally
		// if it does, then check to see if the word exists in the database
		// verified -> next player's turn
		// not verified ->
		// if timer is > 0, then nothing
		// if timer is 0, then player loses a heart and then next player's turn

		// timer runs out?

		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Submit button submits the input word to the server.
		if (command.equals("Submit")) {
			// Get the username and password the user entered.
			GamePlayPanel gpPanel = (GamePlayPanel) container.getComponent(6);

			// save the word
			GameTurnData gtData = new GameTurnData();
			gtData.setPlayerInput(gpPanel.getPlayerInput());
			gtData.setThreeLetters(gpPanel.getThreeLetters()); // gamePlayData holds the current 3 letters

			// Check the validity of the information locally first.
			if (gtData.getPlayerInput().equals("")) {
				displayError("You must enter a word.");
				return;
			}

			if (client.isConnected() == false) {
				try {
					client.openConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			// Submit the gamePlayData to the server.
			try {
				client.sendToServer(gtData);
			} catch (IOException e) {
				displayError("Error connecting to the server.");
				// System.out.println(e);
				e.printStackTrace();
			}
		}
	}

	// gets the three letters from the server
	public void getThreeLetters() {

	}

	// function called that sends input words to the server to verify
	public void verifyInputWord() {
		// helped function that can be called from the action performed method
		// this function gets the word from the textArea and sends it to the server

	}

	// word verified from the server method
	public void wordVerified() {

	}

	// get the new turn data from the client
	public void setNewTurnData(GameTurnData gtd) {
		GamePlayPanel gpPanel = (GamePlayPanel) container.getComponent(6);

		gpPanel.setTurnLabel(gtd.getTurnString());
		gpPanel.setPlayerListPanel(gtd.getPlayerList());
		gpPanel.setThreeLetters(gtd.getThreeLetters());
		gpPanel.setChancesText(3); // this should always start at 3
		// gpPanel.setTimerLabel(gtd.getTimer());
		gpPanel.enableInput(gtd.getEnabledInput());
		gpPanel.setPlayerInput("");
	}

	// this sets the player list for the current players in the game
	public void displayPlayers(ArrayList<Player> playerList) {
		GamePlayPanel gpPanel = (GamePlayPanel) container.getComponent(6);
		gpPanel.setPlayerListPanel(playerList);
	}

	// Method that displays a message in the error label.
	public void displayError(String error) {
		GamePlayPanel gpPanel = (GamePlayPanel) container.getComponent(6);
		gpPanel.setError(error);
	}
	
	public void updateChances(Integer chances) 
	{
		GamePlayPanel gpPanel = (GamePlayPanel) container.getComponent(6);
		gpPanel.setChancesText(chances);
	}
}