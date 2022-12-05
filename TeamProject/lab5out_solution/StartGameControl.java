package lab5out_solution;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class StartGameControl implements ActionListener
{
  // Private data fields for the container and chat client.
  private JPanel container;
  private ChatClient client;
  
  // Constructor for the create account controller.
  public StartGameControl(JPanel container, ChatClient client)
  {
    this.container = container;
    this.client = client;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Cancel button takes the user back to the initial panel.
    if (command == "Play")
    {
    	System.out.println("Play button");
    	
    	StartGameData data = new StartGameData("");
        try
        {
          client.sendToServer(data);
        }
        catch (IOException e)
        {
          System.out.println("Didn't send to server.");
        }
        
        try {
        	client.sendToServer("Check Players");
        } catch (IOException e) {
        	System.out.println("Didn't send to server.");
        }
        /*
    	CardLayout cardLayout = (CardLayout)container.getLayout();
    	//GameLobbyPanel gameLobbyPanel = (GameLobbyPanel)container.getComponent(5);
    	//gameLobbyPanel.repaint();
    	cardLayout.show(container, "6");
    	*/
    }

    // The Submit button creates a new account.
    else if (command == "Rules")
    {
     

    	JOptionPane.showMessageDialog(container, "On players turn, they are given 3 letters and they must correctly guess the right word\n"
    			+ "That contains those letters\n \n"
    			+ "You gets three chance to correctly guess the word then the game moves to next player.\n"
    			+ "If you don't guess the correct word, you lose 1 of your 3 total hearts.\n \n"
    			+ "If the player loses all 3 of their hearts, they lose the game.\n"
    			+ "The game continues until there is only 1 player left\n\nEnjoy!");
    }
  }
  
  public void moveToLobby()
  {
	  	CardLayout cardLayout = (CardLayout)container.getLayout();
  		//GameLobbyPanel gameLobbyPanel = (GameLobbyPanel)container.getComponent(5);
  		//gameLobbyPanel.repaint();
  		cardLayout.show(container, "6");
  }
  
}
