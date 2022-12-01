package lab5out_solution;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameLobbyControl implements ActionListener {
	// Private data fields for the container and chat client.
	private JPanel container;
	private ChatClient client;

	// Constructor for the create account controller.
	public GameLobbyControl(JPanel container, ChatClient client) {
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if (command == "Play") {
			// send to the server a message that the game needs to start
			/*Object msg = "StartTurn";

			try {
				client.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			/*
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "7");
			*/
		}
	}
	
	public void updatePlayerList(ArrayList<Player> playerList) 
	{
		 GameLobbyPanel gameLobbyPanel = (GameLobbyPanel)container.getComponent(5);
		 gameLobbyPanel.setPlayerList(playerList);
	}
	
	public void moveToGamePlay()
	{
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "7");
	}
}
