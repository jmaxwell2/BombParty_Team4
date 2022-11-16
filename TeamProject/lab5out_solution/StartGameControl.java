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
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "5");
    }

    // The Submit button creates a new account.
    else if (command == "Rules")
    {
      
    }
  }
}
