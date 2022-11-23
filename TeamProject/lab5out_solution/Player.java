package lab5out_solution;

import java.io.Serializable;

public class Player implements Serializable {

	// Private data fields
	private String playerID;
	private String username;
	private Boolean turn;
	private Integer numOfHearts;
	private Integer wins;
	private Integer losses;
	private String winLossRatio;

	// Getters
	public String getPlayerID() {
		return playerID;
	}
	
	public String getUsername() {
		return username;
	}

	public Boolean getTurn() {
		return turn;
	}

	public Integer getNumOfHearts() {
		return numOfHearts;
	}
	
	public Integer getWins() {
		return wins;
	}
	
	public Integer getLosses() {
		return losses;
	}
	
	public String getWinLossRatio() {
		return winLossRatio;
	}

	// Setters
	public void setPlayerID(Long id) {
		this.playerID = id.toString();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void setNumOfHearts(Integer num) {
		this.numOfHearts = num;
	}
	
	public void setWins(Integer num) {
		this.wins = num;
	}
	
	public void setLosses(Integer num) {
		this.losses = num;
	}
	
	public void setWinLossRatio(String wlr) {
		this.winLossRatio = wlr;
	}

//	// Constructor that initializes
//	public Player(String username) {
//		setUsername(username);
//	}
	
	@Override
	public String toString() {
		return username;
	}
}
