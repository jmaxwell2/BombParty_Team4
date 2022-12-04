package lab5out_solution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;

public class GameTurnData implements Serializable { 

	private ArrayList<Player> playerList;
	private Player theirTurn;
	private String turnString;
	private String threeLetters;
	private String playerInput;
//	private Timer timer;
	private Integer chances;
	private Boolean enabledInput;
	
	
	public Boolean getEnabledInput() {
		return enabledInput;
	}
	public void setEnabledInput(Boolean enabledInput) {
		this.enabledInput = enabledInput;
	}
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public String getTurnString() {
		return turnString;
	}
	public void setTurnString(String turnString) {
		this.turnString = turnString;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public Player getTheirTurn() {
		return theirTurn;
	}
	public void setTheirTurn(Player theirTurn) {
		this.theirTurn = theirTurn;
	}
	public String getThreeLetters() {
		return threeLetters;
	}
	public void setThreeLetters(String threeLetters) {
		this.threeLetters = threeLetters;
	}
//	public Timer getTimer() {
//		return timer;
//	}
//	public void setTimer(Timer timer) {
//		this.timer = timer;
//	}
	public Integer getChances() {
		return chances;
	}
	public void setChances(Integer chances) {
		this.chances = chances;
	}
	
	public String getPlayerInput() {
		return playerInput;
	}
	public void setPlayerInput(String playerInput) {
		this.playerInput = playerInput;
	}
	
	@Override
	public String toString() {
		
		String stringData = "";
		
		stringData = "Players: " + playerList.toString() + "\n";
		stringData += "It's " + theirTurn.getUsername() + "'s turn!!!\n";
		stringData += "Three random letters: " + threeLetters + "\n";
		
		return stringData;
	}
}
