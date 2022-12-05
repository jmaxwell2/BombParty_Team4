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

public class GameOverPanel extends JPanel {

	// Private data fields for the important GUI components.
	private JLabel titleLabel;
	private JLabel resultLabel;
	private JLabel winRatioLabel;
	private JLabel winLossValue;
	
	// Setter for the win/loss text.
	public void setResultLabel(String result)
	{
		resultLabel.setText("You " + result + "!!!");
	}

	// Constructor for the StartGamePanel.
	public GameOverPanel(GameOverControl goc) 
	{
		
		titleLabel = new JLabel("Game Over", JLabel.CENTER);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
		titleLabel.setForeground(Color.RED);
		
		resultLabel = new JLabel("", JLabel.CENTER);
		resultLabel.setFont(new Font("Serif", Font.BOLD, 30));
		resultLabel.setForeground(Color.BLUE);
		
		JLabel playAgain = new JLabel("Would you like to play the game again?", JLabel.CENTER);
		
		// Create a panel for play again buttons.
		JPanel playChoicePanel = new JPanel(new GridLayout(1, 2, 5, 5));
		JButton yesButton = new JButton("Yes");
	    yesButton.addActionListener(goc);
	    JButton noButton = new JButton("No");
	    noButton.addActionListener(goc);
		playChoicePanel.add(yesButton);
		playChoicePanel.add(noButton);
		
		
		
		// Arrange the panels in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 0, 10));
		grid.add(titleLabel);
		grid.add(resultLabel);
		grid.add(playAgain);
		grid.add(playChoicePanel);
		this.add(grid);
	}
	
}