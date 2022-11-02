package lab5out;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ContactControl implements ActionListener {

	// Private data field for storing the container.
	  private JPanel container;
	 
	  // Constructor for the initial controller.
	  public ContactControl(JPanel container)
	  {
	    this.container = container;
	   
	  }
	  
	  // Handle button clicks.
	  public void actionPerformed(ActionEvent ae)
	  {
	    // these buttons dont do anything for now
		System.out.println("Button clicked on the Contacts page!");
	  }
}
