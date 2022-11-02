package lab5out;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel{
	
	// Private data fields for the important GUI components.
	  private JTextField usernameField;
	  private JPasswordField passwordField1;
	  private JPasswordField passwordField2;
	  private JLabel errorLabel;
	  
	  // Getter for the text in the username field.
	  public String getUsername()
	  {
	    return usernameField.getText();
	  }
	  
	  // Getter for the text in the password field 1.
	  public String getPassword1()
	  {
	    return new String(passwordField1.getPassword());
	  }
	  
	  // Getter for the text in the password field 2.
	  public String getPassword2()
	  {
	    return new String(passwordField2.getPassword());
	  }
	  
	  // Setter for the error text.
	  public void setError(String error)
	  {
	    errorLabel.setText(error);
	  }
	  
	  // Constructor for the createAccount panel.
	  public CreateAccountPanel(CreateAccountControl cac)
	  {
	    // Create the controller and set it in the chat client.
	    //CreateAccountControl controller = new CreateAccountControl(container, client);
	    //client.setCreateAccountControl(controller);
	    
	    // Create a panel for the labels at the top of the GUI.
	    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
	    errorLabel = new JLabel("", JLabel.CENTER);
	    errorLabel.setForeground(Color.RED);
	    JLabel instructionLabel = new JLabel("Enter a username and password to create a new account.", JLabel.CENTER);
	    labelPanel.add(errorLabel);
	    labelPanel.add(instructionLabel);

	    // Create a panel for the register information form.
	    JPanel caPanel = new JPanel(new GridLayout(3, 2, 5, 5));
	    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
	    usernameField = new JTextField(10);
	    JLabel passwordLabel1 = new JLabel("Password:", JLabel.RIGHT);
	    passwordField1 = new JPasswordField(10);
	    JLabel passwordLabel2 = new JLabel("Re-enter Password:", JLabel.RIGHT);
	    passwordField2 = new JPasswordField(10);
	    caPanel.add(usernameLabel);
	    caPanel.add(usernameField);
	    caPanel.add(passwordLabel1);
	    caPanel.add(passwordField1);
	    caPanel.add(passwordLabel2);
	    caPanel.add(passwordField2);
	    
	    // Create a panel for the buttons.
	    JPanel buttonPanel = new JPanel();
	    JButton submitButton = new JButton("Submit");
	    submitButton.addActionListener(cac);
	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(cac);    
	    buttonPanel.add(submitButton);
	    buttonPanel.add(cancelButton);

	    // Arrange the three panels in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
	    grid.add(labelPanel);
	    grid.add(caPanel);
	    grid.add(buttonPanel);
	    this.add(grid);
	  }

}
