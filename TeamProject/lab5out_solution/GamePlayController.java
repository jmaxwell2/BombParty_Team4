package lab5out_solution;

import java.awt.event.ActionEvent;

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
		
	}
	
	// gets the array list of player objects from the server
	public void getPlayers() {
		
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
	public void turnSwitch() {
		
	}
}
