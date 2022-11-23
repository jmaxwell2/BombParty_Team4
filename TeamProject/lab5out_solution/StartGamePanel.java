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

public class StartGamePanel extends JPanel {

	// Private data fields for the important GUI components.
	private JLabel titleLabel;
	private JLabel winRatioLabel;
	private JLabel winLossValue;
	
	// Setter for the win/loss text.
	public void setWLValue(String value)
	{
		winLossValue.setText(value);
	}

	// Constructor for the StartGamePanel.
	public StartGamePanel(StartGameControl sc) 
	{
		
		titleLabel = new JLabel("Start Game", JLabel.CENTER);
		
		// Create a panel for win/loss ratio.
		JPanel winLossPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		winRatioLabel = new JLabel("Win/Loss Ratio", JLabel.CENTER);
		
		//Add code to get players win/loss ratio from database
		winLossValue = new JLabel("0 : 0", JLabel.CENTER);
		//winLossValue.setText(sc.requestWLRatio());
		winLossPanel.add(winRatioLabel);
		winLossPanel.add(winLossValue);
		
		// Create the Play Game button button.
	    JButton playButton = new JButton("Play");
	    playButton.addActionListener(sc);
	    JPanel playButtonBuffer = new JPanel();
	    playButtonBuffer.add(playButton);
	    
	    // Create the Rules button.
	    JButton rulesButton = new JButton("Rules");
	    rulesButton.addActionListener(sc);
	    JPanel rulesButtonBuffer = new JPanel();
	    rulesButtonBuffer.add(rulesButton);
		
		

		// Arrange the panels in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 0, 10));
		grid.add(titleLabel);
		grid.add(winLossPanel);
		grid.add(playButtonBuffer);
		grid.add(rulesButtonBuffer);
		this.add(grid);
	}
	
}
