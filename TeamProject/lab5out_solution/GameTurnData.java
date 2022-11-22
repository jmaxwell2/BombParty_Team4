package lab5out_solution;

import java.util.ArrayList;
import java.util.Timer;

public class GameTurnData {

	private ArrayList<Player> playerList;
	private Player theirTurn;
	private String turnString;
	private String threeLetters;
	private Timer timer;
	
	
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
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
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
