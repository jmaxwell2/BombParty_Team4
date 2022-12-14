package lab5out_solution;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientGUI extends JFrame
{
  
  
  // Constructor that creates the client GUI.
  public ClientGUI()
  {
    // Set up the chat client.
   ChatClient client = new ChatClient();
    //client.setHost("10.252.97.49");
    //client.setHost("192.168.0.28");
   client.setHost("localhost");
    client.setPort(8300);
    try
    {
      client.openConnection();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    
    
    // Set the title and default close operation.
    this.setTitle("Chat Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    
    //Create the Controllers next
    //Next, create the Controllers
    InitialControl ic = new InitialControl(container,client);
    LoginControl lc = new LoginControl(container,client);
    CreateAccountControl cac = new CreateAccountControl(container,client);
    StartGameControl sc = new StartGameControl(container, client);
    GamePlayController gpc = new GamePlayController(container, client);
    GameLobbyControl glc = new GameLobbyControl(container, client);
    GameOverControl goc = new GameOverControl(container, client);
    
    //Set the client info
    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);
    client.setStartGameControl(sc);
    client.setGameLobbyControl(glc);
    client.setGamePlayController(gpc);
    client.setGameOverControl(goc);
   
    
    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    JPanel view4 = new ContactsPanel();
    JPanel view5 = new StartGamePanel(sc);
    JPanel view6 = new GameLobbyPanel(glc);
    JPanel view7 = new GamePlayPanel(gpc);
    JPanel view8 = new GameOverPanel(goc);
    
    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view4, "4");
    container.add(view5, "5");
    container.add(view6, "6");
    container.add(view7, "7");
    container.add(view8, "8");
    
   
    
    // Show the initial view in the card layout.
    cardLayout.show(container, "1");
    
    // Add the card layout container to the JFrame.
    // GridBagLayout makes the container stay centered in the window.
    this.setLayout(new GridBagLayout());
    this.add(container);

    // Show the JFrame.
    this.setSize(750, 550);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args)
  {
    new ClientGUI();
  }
}
