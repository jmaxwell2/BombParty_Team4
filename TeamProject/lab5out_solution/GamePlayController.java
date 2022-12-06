package lab5out_solution;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.JPanel;

public class GamePlayController implements ActionListener {

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
	
	public void moveToGameOver() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "8");
	}
}