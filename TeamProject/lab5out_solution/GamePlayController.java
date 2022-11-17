package lab5out_solution;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPanel;

public class GamePlayController {

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
	
	// it is a difference player's turn
	public void switchTurns() {
		
	}
	
	// this sets the player list for the current players in the game
	public void displayPlayers(List<Player> playerList){
		GamePlayPanel gpPanel = (GamePlayPanel)container.getComponent(1);
		gpPanel.setPlayerList(playerList);
	}
}
