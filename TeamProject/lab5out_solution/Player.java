package lab5out_solution;

public class Player {

	// Private data fields
	private String username;
	private Boolean turn;
	private Integer numOfHearts;

	// Getters
	public String getUsername() {
		return username;
	}

	public Boolean getTurn() {
		return turn;
	}

	public Integer getNumOfHearts() {
		return numOfHearts;
	}

	// Setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void setNumOfHearts(Integer num) {
		this.numOfHearts = num;
	}

	// Constructor that initializes
	public Player(String username) {
		setUsername(username);
	}
}
