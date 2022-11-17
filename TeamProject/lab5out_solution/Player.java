package lab5out_solution;

public class Player {

	// Private data fields
	private String username;
	private Boolean turn;
	private Integer numOfHearts;
	private Integer winLoss_Ratio;

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
	
	public Integer getWinLoss_Ratio() {
		return winLoss_Ratio;
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
	
	public void setWinLoss_Ratio(Integer wlr) {
		this.winLoss_Ratio = wlr;
	}

//	// Constructor that initializes
//	public Player(String username) {
//		setUsername(username);
//	}
}
