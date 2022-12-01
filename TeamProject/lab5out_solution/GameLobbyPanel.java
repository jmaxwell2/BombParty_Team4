package lab5out_solution;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
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
	private JPanel playerListPanel;
	//private JLabel playerNameLabel;
	private ArrayList<Player> playerList;
	
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
		System.out.println(playerList.toString());
		
		int i = 0;
		for (Player p : playerList) {
			JLabel test = (JLabel) playerListPanel.getComponent(i);
			test.setText(p.getUsername());
			i++;
		}
		
		playerListPanel.repaint();
	}

	// Constructor for the GameLobbyPanel.
	public GameLobbyPanel(GameLobbyControl glc) 
	{
		titleLabel = new JLabel("Start Game", JLabel.CENTER);
		
		// Create a panel for the player name labels at the top of the GUI.
		playerListPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		// create a label for every player
		JLabel playerNameOne = new JLabel("", JLabel.CENTER);
		JLabel playerNameTwo = new JLabel("", JLabel.CENTER);
		JLabel playerNameThree = new JLabel("", JLabel.CENTER);
		playerListPanel.add(playerNameOne);
		playerListPanel.add(playerNameTwo);
		playerListPanel.add(playerNameThree);
		
		
		//playerNameLabel = new JLabel("TheJordanMaxwell		Trottingwizard		User1243", JLabel.CENTER);
		//playerListPanel.add(playerNameLabel);

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