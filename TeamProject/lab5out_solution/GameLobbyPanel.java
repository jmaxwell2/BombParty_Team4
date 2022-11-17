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

public class GameLobbyPanel extends JPanel {

	// Private data fields for the important GUI components.
	private JLabel titleLabel;
	private JLabel timerLabel;
	private JLabel errorLabel;
	private ArrayList<Player> playerList;
	
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	// Constructor for the GameLobbyPanel.
	public GameLobbyPanel(GameLobbyControl glc) 
	{
		titleLabel = new JLabel("Start Game", JLabel.CENTER);
		
		// Create a panel for the player name labels at the top of the GUI.
		JPanel playerListPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		
		// create a label for every player
		/*
		for (int i = 0; i < playerList.size(); i++) {
			JLabel playerNameLabel = new JLabel(playerList.get(i).getUsername(), JLabel.CENTER);
			//JLabel playerNameLabel = new JLabel(playerList.get(i), JLabel.CENTER);
			playerListPanel.add(playerNameLabel);
		}
		*/
		
		JLabel playerNameLabel = new JLabel("TheJordanMaxwell		Trottingwizard		User1243", JLabel.CENTER);
		playerListPanel.add(playerNameLabel);


		// Create the Play Game button button.
	    JButton playButton = new JButton("Play");
	    playButton.addActionListener(glc);
	    JPanel playButtonBuffer = new JPanel();
	    playButtonBuffer.add(playButton);

		// Arrange the panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(titleLabel);
		grid.add(playerListPanel);
		grid.add(playButton);
		this.add(grid);
	}

}