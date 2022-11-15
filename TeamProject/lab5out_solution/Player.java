package lab5out_solution;

public class Player {

	// Private data fields 
	  private String username;
	  private Boolean turn;
	  
	  // Getters 
	  public String getUsername()
	  {
	    return username;
	  }
	  public Boolean getTurn()
	  {
	    return turn;
	  }
	  
	  // Setters
	  public void setUsername(String username)
	  {
	    this.username = username;
	  }
	  public void setTurn(Boolean turn)
	  {
	    this.turn = turn;
	  }
	  
	  // Constructor that initializes
	  public Player(String username, Boolean turn)
	  {
	    setUsername(username);
	    setTurn(turn);
	  }
}
