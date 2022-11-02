package lab5out;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ContactPanel extends JPanel {

	// Private data fields for the important GUI components.
	  private String[] contacts;
	  
	  // Getter for the text in the username field.
	  public String[] getContacts()
	  {
	    return contacts;
	  }
	  public void setContacts(String[] contacts) {
		  // sets the contacts
		  this.contacts = contacts;
	  }
	  
	  // Constructor for the contacts panel.
	  public ContactPanel(ContactControl cc)
	  {
	    // Create the controller and set it in the chat client.
	    //ContactControl controller = new ContactControl(container, client);
	    //client.setContactControl(controller);
	        
	    // Create a panel for the labels at the top of the GUI.
	    JPanel labelPanel = new JPanel(new GridLayout(1, 1, 5, 5));
	    JLabel instructionLabel = new JLabel("Contacts", JLabel.CENTER);
	    labelPanel.add(instructionLabel);

	    // Create a panel for the contacts list.
	    JPanel listPanel = new JPanel(new GridLayout(1, 2, 5, 5));
	    JTextArea listArea = new JTextArea();
	    listArea.setText("Person one \nPerson two \nPerson three \nPerson four \nPerson five");
	    listPanel.add(listArea);
	    
	    // Create a panel for the buttons.
	    JPanel buttonPanel = new JPanel();
	    JButton deleteButton = new JButton("Delete Contact");
	    deleteButton.addActionListener(cc);
	    JButton addButton = new JButton("Add Contact");
	    addButton.addActionListener(cc); 
	    JButton logOutButton = new JButton(" Log Out");
	    logOutButton.addActionListener(cc);
	    buttonPanel.add(deleteButton);
	    buttonPanel.add(addButton);
	    buttonPanel.add(logOutButton);

	    // Arrange the three panels in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
	    grid.add(labelPanel);
	    grid.add(listPanel);
	    grid.add(buttonPanel);
	    this.add(grid);
	  }
}
