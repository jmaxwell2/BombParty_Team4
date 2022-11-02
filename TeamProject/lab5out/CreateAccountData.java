package lab5out;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
	
	// Private data fields for the username and password.
	  private String username;
	  private String password1;
	  private String password2;
	  
	  // Getters for the username and password.
	  public String getUsername()
	  {
	    return username;
	  }
	  public String getPassword1()
	  {
	    return password1;
	  }
	  public String getPassword2()
	  {
	    return password2;
	  }
	  
	  // Setters for the username and password.
	  public void setUsername(String username)
	  {
	    this.username = username;
	  }
	  public void setPassword1(String password1)
	  {
	    this.password1 = password1;
	  }
	  public void setPassword2(String password2)
	  {
	    this.password2 = password2;
	  }
	  
	  // Constructor that initializes the username and passwords.
	  public CreateAccountData(String username, String password1, String password2)
	  {
	    setUsername(username);
	    setPassword1(password1);
	    setPassword2(password2);
	  }

}
