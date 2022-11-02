package lab5out;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener {

	  // Private data fields for the container and chat client.
	  private JPanel container;
	  private ChatClient client;
	  
	  
	  // Constructor for the create account controller.
	  public CreateAccountControl(JPanel container, ChatClient client)
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
	    if (command == "Cancel")
	    {
	      CardLayout cardLayout = (CardLayout)container.getLayout();
	      cardLayout.show(container, "initial");
	    }
	
	    // The Submit button submits the create account information to the server.
	    else if (command == "Submit")
	    {
	      // Get the username and password the user entered.
	      CreateAccountPanel caPanel = (CreateAccountPanel)container.getComponent(2);
	      CreateAccountData data = new CreateAccountData(caPanel.getUsername(), caPanel.getPassword1(), caPanel.getPassword2());
	      
	      // Check the validity of the information locally first.
	      if (data.getUsername().equals("") || data.getPassword1().equals("") || data.getPassword2().equals(""))
	      {
	        displayError("You must enter a username and password.");
	        return;
	      }
	
	      // Submit the create account information to the server.
	      try {
			client.sendToServer(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    }
	  }
	
	  // After the account creation is successful, set the User object and display the contacts screen. - this method would be invoked by 
	  //the ChatClient
	  public void createAccountSuccess()
	  {
	    
	  }
	
	  // Method that displays a message in the error - could be invoked by ChatClient or by this class (see above)
	  public void displayError(String error)
	  {
		  CreateAccountPanel caPanel = (CreateAccountPanel)container.getComponent(1);
		  caPanel.setError(error);
	    
	  }
	
}
